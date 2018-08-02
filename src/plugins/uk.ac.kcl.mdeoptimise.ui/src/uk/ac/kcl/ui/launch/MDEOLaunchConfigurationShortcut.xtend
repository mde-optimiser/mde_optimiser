package uk.ac.kcl.ui.launch

import org.eclipse.core.resources.IFile
import org.eclipse.core.runtime.CoreException
import org.eclipse.debug.core.DebugPlugin
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy
import org.eclipse.debug.ui.DebugUITools
import org.eclipse.debug.ui.ILaunchShortcut
import org.eclipse.jdt.core.JavaCore
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants
import org.eclipse.jface.viewers.ISelection
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.ui.IEditorPart
import org.eclipse.ui.ide.ResourceUtil

class MDEOLaunchConfigurationShortcut implements ILaunchShortcut {
	
	/**
	 * Launch MDEOptimiser from a file selection in Project Explorer.
	 */
	override launch(ISelection selection, String mode) {
		val moptFile = (selection as IStructuredSelection).getFirstElement() as IFile
		launch(moptFile, mode)
	}
	
	/**
	 * Launch MDEOptimiser from an editor right click -> Run as action
	 */
	override launch(IEditorPart editor, String mode) {
		val moptFile =  ResourceUtil.getFile(editor.getEditorInput());
		launch(moptFile, mode)	
	}
	
	/**
	 * Launch a MDEOptimiser LaunchConfiguration using the given <code>moptFile</code> file
	 * reference. This will search for an already existing launch configuration for the 
	 * given file and if one is not found it will create one from scratch.
	 */
	def void launch(IFile moptFile, String mode){
		
		if(moptFile === null) {
			return;
		}
		
		val moptFilePath = moptFile.fullPath.toString();
		val launchManager = DebugPlugin.getDefault().getLaunchManager
		val launchConfigurationType = launchManager
				.getLaunchConfigurationType(MDEOptimiserLaunchConfigurationAttributes.MDEO_LAUNCH_CONFIGURATION_TYPE)
		
		try {
			val launchConfiguration = launchManager.getLaunchConfigurations(launchConfigurationType).filter[ l | 
				l.getAttribute(MDEOptimiserLaunchConfigurationAttributes.ATTR_MOPT_SOURCE_PATH, "").equals(moptFilePath)
			].head
			
			if(launchConfiguration !== null) {
				DebugUITools.launch(launchConfiguration, mode)
				return;
			}
			
		} catch(CoreException e){
			return;
		}
		
		//Create a new launch configuration if one was not found
		try {
			
			val launchConfigurationWorkingCopy = launchConfigurationType.newInstance(null, moptFile.name);
			launchConfigurationWorkingCopy.setAttribute(MDEOptimiserLaunchConfigurationAttributes.ATTR_MOPT_SOURCE_PATH, moptFilePath);
			setProjectOnClasspath(launchConfigurationWorkingCopy, moptFile)
			val launchConfiguration = launchConfigurationWorkingCopy.doSave;
			
			DebugUITools.launch(launchConfiguration, mode)
			
		} catch(CoreException e) {
			return;
		}
	}
	
	/**
	 * Adds the project of the current file as a project name attribute to the launch configuration.
	 * This is then added by the launch configuration to the classpath.
	 */
	def void setProjectOnClasspath(ILaunchConfigurationWorkingCopy configuration, IFile moptFile) {
		
		val moptFileJavaProject = JavaCore.create(moptFile.project)
		var javaProject = moptFileJavaProject.getJavaProject();

		if (javaProject !== null && javaProject.exists()) {
			configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, javaProject.elementName);
		}	
	}

}