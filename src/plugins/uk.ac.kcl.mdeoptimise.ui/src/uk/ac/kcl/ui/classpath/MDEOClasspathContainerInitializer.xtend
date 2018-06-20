package uk.ac.kcl.ui.classpath

import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IPath
import org.eclipse.jdt.core.ClasspathContainerInitializer
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.core.runtime.Path

/**
 * Classpath container initialiser for the MDEOptimiser classpath container. Implements the Eclipse extension point.  
 */
class MDEOClasspathContainerInitializer extends ClasspathContainerInitializer {
	

	public static final Path MDEO_LIBRARY_PATH = new Path("uk.ac.kcl.mdeoptimise.MDEO_CONTAINER")

	override initialize(IPath containerPath, IJavaProject project) throws CoreException {
		JavaCore.setClasspathContainer(
			containerPath,
			#{project},
			#[new MDEOClasspathContainer], 
			null)
	}
	
}