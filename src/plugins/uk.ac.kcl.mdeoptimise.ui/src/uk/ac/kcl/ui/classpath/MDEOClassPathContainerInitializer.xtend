package uk.ac.kcl.ui.classpath

import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IPath
import org.eclipse.jdt.core.ClasspathContainerInitializer
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore

/**
 * Classpath container initialiser for the MDEOptimiser classpath container. Implements the Eclipse extension point.  
 */
class MDEOClassPathContainerInitializer extends ClasspathContainerInitializer {
	
	override initialize(IPath containerPath, IJavaProject project) throws CoreException {
		JavaCore.setClasspathContainer(
			containerPath,
			#{project},
			#[new MDEOClassPathContainer], 
			null)
	}
	
}