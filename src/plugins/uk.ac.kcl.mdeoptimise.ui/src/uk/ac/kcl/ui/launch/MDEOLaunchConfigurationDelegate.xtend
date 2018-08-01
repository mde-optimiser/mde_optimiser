package uk.ac.kcl.ui.launch

import java.util.HashMap
import java.util.LinkedHashSet
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.Platform
import org.eclipse.debug.core.ILaunch
import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.jdt.internal.launching.LaunchingMessages
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate
import org.eclipse.jdt.launching.ExecutionArguments
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants
import org.eclipse.jdt.launching.JavaRuntime
import org.eclipse.jdt.launching.VMRunnerConfiguration
import org.eclipse.osgi.util.NLS
import org.osgi.framework.Bundle
import org.osgi.framework.wiring.BundleRevision
import org.osgi.framework.wiring.BundleWiring
import uk.ac.kcl.ui.classpath.MDEOClasspathContainer

class MDEOLaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {
		
	override launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
	
		try {
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Verifying_launch_attributes____1); 
		
			val runConfig = getVMRunnerConfiguration(configuration, mode, monitor);
			if (runConfig === null) {
				return;
			}
		
			// done the verification phase
			monitor.worked(1);
			
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Creating_source_locator____2);
			// set the default source locator if required
			setDefaultSourceLocator(launch, configuration);
			monitor.worked(1);
			// Launch the configuration - 1 unit of work
			val runner = getVMRunner(configuration, mode);
			runner.run(runConfig, launch, monitor);

			// check for cancellation
			if (monitor.isCanceled()) {
				return;
			}
		}
		finally {
			monitor.done();
		}
	}
	
	/**
	 * Builds a VM runner configuration
	 */
	def VMRunnerConfiguration getVMRunnerConfiguration(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor) throws CoreException {

		monitor.beginTask(NLS.bind("{0}...", #[configuration.getName()]), 3);
		
		// check for cancellation
		if (monitor.isCanceled()) {
			return null;
		}
		
		monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Verifying_launch_attributes____1);
		
		var workingDir = verifyWorkingDirectory(configuration);
		var String workingDirName = null;
		if (workingDir !== null) {
			workingDirName = workingDir.getAbsolutePath();
		}
		
		// Environment variables
		var envp = getEnvironment(configuration);
		
		// Program & VM arguments
		var pgmArgs = getStandaloneLauncherArguments(getConfiguredMoptPath(configuration))
		var vmArgs = concat(getVMArguments(configuration), getVMArguments(configuration, mode));
		var execArgs = new ExecutionArguments(vmArgs, pgmArgs);
		
		// VM-specific attributes
		var vmAttributesMap = getVMSpecificAttributesMap(configuration);
		
		// Bug 522333 :to be used for modulepath only for 4.7.*
		val paths = getMDEOClasspathAndModulePath(configuration);
		
		// Create VM config using MDEO RunConfiguration as main class
		var runConfig = new VMRunnerConfiguration(MDEOptimiserLaunchConfigurationAttributes.ATTR_MOPT_MAIN_CLASS_NAME, appendBundleDependenciesToClasspath(getClasspath(configuration)));
		runConfig.setProgramArguments(execArgs.getProgramArgumentsArray());
		runConfig.setEnvironment(envp);
		runConfig.setVMArguments(execArgs.getVMArgumentsArray());
		runConfig.setWorkingDirectory(workingDirName);
		runConfig.setVMSpecificAttributesMap(vmAttributesMap);
		
		// current module name, if so
		try {
			var proj = JavaRuntime.getJavaProject(configuration);
			if (proj !== null) {
				val module = if(proj === null) null else proj.getModuleDescription();
				val modName = if(module === null) null else module.getElementName();
				if (modName !== null) {
					runConfig.setModuleDescription(modName);
				}
			}
		} catch (CoreException e) {
			// Not a java Project so no need to set module description
		}
		
		if (!JavaRuntime.isModularConfiguration(configuration)) {
			// Bootpath
			runConfig.setBootClassPath(getBootpath(configuration));
		} else {
			// module path
			runConfig.setModulepath(paths.get(1));
			if (!configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_DEFAULT_MODULE_CLI_OPTIONS, true)) {
				runConfig.setOverrideDependencies(configuration.getAttribute(IJavaLaunchConfigurationConstants.ATTR_MODULE_CLI_OPTIONS, ""));
			} else {
				runConfig.setOverrideDependencies(getModuleCLIOptions(configuration));
			}
		}
		// check for cancellation
		if (monitor.isCanceled()) {
			return null;
		}
		monitor.worked(1);

		return runConfig;
	}
	
	/**
	 * Returns the program arguments specified by the given launch
	 * configuration, as a string. The returned string is empty if no program
	 * arguments are specified.
	 * 
	 * @param configuration
	 *            launch configuration
	 * @return the specified mopt configuration file path
	 * @exception CoreException
	 *                if unable to retrieve the attribute
	 */
	def String getConfiguredMoptPath(ILaunchConfiguration configuration) throws CoreException {
		return configuration.getAttribute(
				MDEOptimiserLaunchConfigurationAttributes.ATTR_MOPT_SOURCE_PATH, "");
	}
	
	/**
	 * Returns the full mopt file path
	 */
	def String getStandaloneLauncherArguments(String configuredMoptFile){
		
		var arguments = ""
		
		var moptFile =  ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(configuredMoptFile));
		
		if(moptFile.exists){
			
			arguments += "-p " + moptFile.getProject().getLocation().toOSString()
			arguments += " "
			arguments += "-m " + moptFile.getRawLocation().toOSString(); 
			
			return arguments
		}
	}
	
	/**
	 * Returns the complete classpath of the launch configuration, including the Eclipse bundles
	 * as well as all the user configured dependencies
	 * @param configuration launch configuration
	 * @return the complete bundles classpath including eclipse jars and user configured classpath
	 */
	def String[] appendBundleDependenciesToClasspath(String[] classpathEntries) {
		
		val classpathEntriesSet = new LinkedHashSet<String>();
		
		if(classpathEntries !== null){
			classpathEntriesSet.addAll(classpathEntries);
		}
		
		val mdeoContainerBundles = MDEOClasspathContainer.BUNDLE_IDS_TO_INCLUDE;
		
		val bundleDependencies = mdeoContainerBundles.fold(new HashMap<Long, Bundle>, [map, bundleId | { 
			getBundleDependencies(map, Platform.getBundle(bundleId));	
		}])
		
		bundleDependencies.values.fold(classpathEntriesSet, [set, bundle | {
			
			var file = FileLocator.getBundleFile(bundle).canonicalPath
			println("Adding " + file)
			set.add(file) 
			
			if(MDEOClasspathContainer.buildFolderPath(bundle) !== null) {
				set.add(MDEOClasspathContainer.buildFolderPath(bundle).toPortableString);
			}
			
			
			return set
			
			}
		])

		return classpathEntriesSet
	}
	
	private def HashMap<Long, Bundle> getBundleDependencies(HashMap<Long, Bundle> bundleDependencies, Bundle bundle){
		
		if(!bundleDependencies.keySet.contains(bundle.bundleId)) {

				bundleDependencies.put(bundle.bundleId, bundle)

				val dependencies = getBundleWiredDependencies(bundle)
				dependencies.forEach[ d | getBundleDependencies(bundleDependencies, d) ]
		}
		
		return bundleDependencies;
	} 
	
	private def LinkedHashSet<Bundle> getBundleWiredDependencies(Bundle bundle) {
		
	  val wiredBundles = new LinkedHashSet<Bundle>();
	  var wiring = bundle.adapt(BundleWiring);

	  if (wiring !== null) {
	    
	    var wires = wiring.getRequiredWires(BundleRevision.PACKAGE_NAMESPACE);
	    wires.forEach[wire |  wiredBundles.add(wire.getProviderWiring().getBundle())]
	    wires = wiring.getRequiredWires(BundleRevision.BUNDLE_NAMESPACE);   
	    wires.forEach[wire |  wiredBundles.add(wire.getProviderWiring().getBundle())]
	    
	  }
	  
	  return wiredBundles;
	
	}
	
	
	def String[][] getMDEOClasspathAndModulePath(ILaunchConfiguration config) throws CoreException {
		
		var classpathAndModulePath = getClasspathAndModulepath(config)
		
		return #[appendBundleDependenciesToClasspath(classpathAndModulePath.get(0)), classpathAndModulePath.get(1)];
	}
	
	
	def static String concat(String args1, String args2) {
		val args = new StringBuilder();
		if (args1 !== null && !args1.isEmpty()) {
			args.append(args1);
		}
		if (args2 !== null && !args2.isEmpty()) {
			args.append(" ");
			args.append(args2);
		}
		return args.toString();
	}
}