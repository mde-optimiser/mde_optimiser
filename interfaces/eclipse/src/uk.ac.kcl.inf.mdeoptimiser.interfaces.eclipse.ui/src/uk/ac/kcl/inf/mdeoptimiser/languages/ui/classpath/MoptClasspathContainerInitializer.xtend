package uk.ac.kcl.inf.mdeoptimiser.languages.ui.classpath

import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IPath
import org.eclipse.jdt.core.ClasspathContainerInitializer
import org.eclipse.jdt.core.IJavaProject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.core.runtime.Path
import uk.ac.kcl.inf.mdeoptimiser.languages.ui.classpath.MoptClasspathContainer

/**
 * Classpath container initialiser for the MDEOptimiser classpath container. Implements the Eclipse extension point.  
 */
class MoptClasspathContainerInitializer extends ClasspathContainerInitializer {
	

	public static final Path MDEO_LIBRARY_PATH = new Path("uk.ac.kcl.inf.mdeoptimiser.languages.ui.Mopt.MDEO_CONTAINER")

	override initialize(IPath containerPath, IJavaProject project) throws CoreException {
		JavaCore.setClasspathContainer(
			containerPath,
			#{project},
			#[new MoptClasspathContainer], 
			null)
	}
	
}
