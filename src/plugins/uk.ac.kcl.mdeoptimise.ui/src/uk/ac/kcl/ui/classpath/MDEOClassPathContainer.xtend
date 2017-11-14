package uk.ac.kcl.ui.classpath

import org.eclipse.core.runtime.Path
import org.eclipse.jdt.core.IClasspathContainer
import org.eclipse.jdt.core.JavaCore

import static org.eclipse.core.runtime.FileLocator.*
import static org.eclipse.core.runtime.Platform.*

/**
 * MDEOptimiser classpath container.
 * 
 */
class MDEOClassPathContainer implements IClasspathContainer {
	
	override getClasspathEntries() {
		#{
			JavaCore.newLibraryEntry(new Path(getBundleFile(getBundle("uk.ac.kcl.mdeoptimise")).canonicalPath), null, null, false),
			JavaCore.newLibraryEntry(new Path(getBundleFile(getBundle("org.eclipse.emf.ecore")).canonicalPath), null, null, false),
			JavaCore.newLibraryEntry(new Path(getBundleFile(getBundle("org.eclipse.emf.common")).canonicalPath), null, null, false)
		 }
	}
	
	override getDescription() '''Core MDEOptimiser Libraries'''
		
	
	override getKind() {
		IClasspathContainer.K_SYSTEM
	}
	
	override getPath() { 
		MDEOClassPathContainerInitializer.MDEO_LIBRARY_PATH
	}
}