package uk.ac.kcl.ui.launch

import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.debug.core.ILaunch
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.CoreException
import org.eclipse.jdt.launching.JavaLaunchDelegate
import org.eclipse.debug.core.model.LaunchConfigurationDelegate
import uk.ac.kcl.ui.launch.MDEOptimiserLaunchConfigurationAttributes
import uk.ac.kcl.interpreter.OptimisationInterpreter
import org.eclipse.emf.common.util.URI
import uk.ac.kcl.mdeoptimise.Optimisation
import org.eclipse.ui.PlatformUI
import org.eclipse.ui.part.FileEditorInput
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import java.nio.file.Files

class MDEOLaunchConfigurationDelegate extends LaunchConfigurationDelegate {
	
	override launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor) throws CoreException {
		println("Application launched!")
		
		var attribute = configuration.getAttribute(MDEOptimiserLaunchConfigurationAttributes.CONSOLE_TEXT, "src/test.mopt");
        System.out.println(attribute);
	
		var file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("src/test.mopt"));
		
		println(file.name)             

		 var content = new String();

//        if(model != null){
//        	
//			var optimisation = new OptimisationInterpreter(model)
//        	optimisation.start();
//		}
//	
	
	}
	
}