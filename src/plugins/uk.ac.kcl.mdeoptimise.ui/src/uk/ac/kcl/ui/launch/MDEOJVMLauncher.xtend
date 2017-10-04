package uk.ac.kcl.ui.launch

import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.debug.core.ILaunch
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.CoreException
import org.eclipse.jdt.launching.AbstractJavaLaunchConfigurationDelegate
import org.eclipse.jdt.launching.ExecutionArguments
import org.eclipse.jdt.launching.VMRunnerConfiguration
import org.eclipse.jdt.internal.launching.LaunchingMessages
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IProject
import org.eclipse.jdt.launching.JavaLaunchDelegate

class MDEOJVMLauncher  extends JavaLaunchDelegate {
	
	def launch(IFile moptFile, IProject moptProject) throws CoreException {
		
	}
	
}