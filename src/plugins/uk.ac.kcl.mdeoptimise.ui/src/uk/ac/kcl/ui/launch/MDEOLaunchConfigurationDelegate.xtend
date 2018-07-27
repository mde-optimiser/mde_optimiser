package uk.ac.kcl.ui.launch

import java.util.ArrayList
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.debug.core.ILaunch
import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.jdt.internal.launching.LaunchingMessages
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate
import org.eclipse.jdt.launching.ExecutionArguments
import org.eclipse.jdt.launching.VMRunnerConfiguration
import org.osgi.framework.FrameworkUtil

class MDEOLaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {
		
	override launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
	
		try {
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Verifying_launch_attributes____1); 
										
			var runner = getVMRunner(configuration, mode);
	
			var workingDir = verifyWorkingDirectory(configuration);
			var String workingDirName = null;
			if (workingDir != null) {
				workingDirName = workingDir.getAbsolutePath();
			}
			
			// Environment variables
			var envp = getEnvironment(configuration);
			
			// Get the configured MOPT file path
			var pgmArgs = getStandaloneLauncherArguments(getConfiguredMoptPath(configuration))
			
			//Prepare the execution arguments, with no VM arguments and the mopt file path
			var execArgs = new ExecutionArguments("", pgmArgs);
			
			// VM-specific attributes
			var vmAttributesMap = getVMSpecificAttributesMap(configuration);
			
			// Get the complete classpath
			val classpath = buildStandaloneClasspath(configuration);
			
			// Create VM config
			var runConfig = new VMRunnerConfiguration(MDEOptimiserLaunchConfigurationAttributes.ATTR_MOPT_MAIN_CLASS_NAME, classpath);
			runConfig.setProgramArguments(execArgs.getProgramArgumentsArray());
			runConfig.setEnvironment(envp);
			runConfig.setVMArguments(execArgs.getVMArgumentsArray());
			runConfig.setWorkingDirectory(workingDirName);
			runConfig.setVMSpecificAttributesMap(vmAttributesMap);
			
			// Bootpath
			runConfig.setBootClassPath(getBootpath(configuration));
			
			// check for cancellation
			if (monitor.isCanceled()) {
				return;
			}	
			
			// done the verification phase
			monitor.worked(1);
			
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Creating_source_locator____2); 
			// set the default source locator if required
			setDefaultSourceLocator(launch, configuration);
			monitor.worked(1);		
			
			runConfig.classPath.forEach[ x | println(x) ]
			
			// Launch the configuration - 1 unit of work
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
	def String[] buildStandaloneClasspath(ILaunchConfiguration configuration) {
		
		val bundlesContext = FrameworkUtil.getBundle(this.class).getBundleContext();
		val bundles = bundlesContext.getBundles();
		
		val bundlesClasspath = new ArrayList<String>(getClasspath(configuration));
		
		/**
		 * TODO This is a hack to allow running the bundles from within eclipse.
		 * Find if there is a better way of working around this without having to hardcode the
		 * path to the .class files. This path is specified in the project pom file.
		 */
		bundles.forEach[ bundle | 
			var bundleFilePath = FileLocator.getBundleFile(bundle).absolutePath
			if(!bundleFilePath.endsWith(".jar")){
				bundleFilePath = bundleFilePath.concat("/target/classes")
			}	
			
			bundlesClasspath.add(bundleFilePath)
		]
		
		return bundlesClasspath
	}
}