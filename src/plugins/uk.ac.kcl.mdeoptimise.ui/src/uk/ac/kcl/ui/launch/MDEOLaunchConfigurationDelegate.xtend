package uk.ac.kcl.ui.launch

import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.debug.core.ILaunch
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.CoreException
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate
import org.eclipse.jdt.launching.ExecutionArguments
import org.eclipse.jdt.launching.VMRunnerConfiguration
import org.eclipse.jdt.internal.launching.LaunchingMessages
import org.osgi.framework.FrameworkUtil
import com.google.inject.Injector
import uk.ac.kcl.MDEOptimiseStandaloneSetup
import org.eclipse.core.runtime.Platform
import org.eclipse.core.runtime.FileLocator
import java.net.URL
import java.util.ArrayList

class MDEOLaunchConfigurationDelegate extends AbstractJavaLaunchConfigurationDelegate {
	
	static val Injector injector = new MDEOptimiseStandaloneSetup().createInjectorAndDoEMFRegistration()
	
	override launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		
		
		try {
			monitor.subTask(LaunchingMessages.JavaLocalApplicationLaunchConfigurationDelegate_Verifying_launch_attributes____1); 
							
			var mainTypeName = "uk.ac.kcl.ui.launch.RunOptimisation"
			
			var runner = getVMRunner(configuration, mode);
	
			var workingDir = verifyWorkingDirectory(configuration);
			var String workingDirName = null;
			if (workingDir != null) {
				workingDirName = workingDir.getAbsolutePath();
			}
			
			// Environment variables
			var envp= getEnvironment(configuration);
			
			// Program & VM arguments
			var pgmArgs = getProgramArguments(configuration);
			var vmArgs = getVMArguments(configuration);
			var execArgs = new ExecutionArguments(vmArgs, pgmArgs);
			
			// VM-specific attributes
			var vmAttributesMap = getVMSpecificAttributesMap(configuration);
			
			
			var bundlesContext =  FrameworkUtil.getBundle(uk.ac.kcl.ui.launch.RunOptimisation).getBundleContext();
		    
		    var mdeoBundle = FrameworkUtil.getBundle(uk.ac.kcl.ui.launch.RunOptimisation)
		        
		    var bundlePath = FileLocator.getBundleFile(mdeoBundle)

		    var bundles = bundlesContext.getBundles();
			
			// Classpath
			val classpath = getClasspath(configuration);
			
			val bundlesClasspath =  new ArrayList<String>();
			bundlesClasspath.addAll(classpath)
			
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
			
			//bundles.forEach[ bundle | classpath.add(bundle.getEntry("/").toString)]

			// Create VM config
			var runConfig = new VMRunnerConfiguration(mainTypeName, bundlesClasspath);
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
}