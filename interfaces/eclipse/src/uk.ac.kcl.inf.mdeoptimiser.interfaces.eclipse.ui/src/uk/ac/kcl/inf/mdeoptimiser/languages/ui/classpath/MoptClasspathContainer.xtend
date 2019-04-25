package uk.ac.kcl.inf.mdeoptimiser.languages.ui.classpath

import org.eclipse.core.runtime.Path
import org.eclipse.jdt.core.IClasspathContainer
import org.eclipse.jdt.core.JavaCore

import org.eclipse.jdt.core.IClasspathEntry
import java.util.ArrayList
import java.util.List
import org.eclipse.core.runtime.Platform
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.FileLocator
import org.osgi.framework.Bundle
import java.io.IOException

/**
 * MDEOptimiser classpath container.
 */
class MoptClasspathContainer implements IClasspathContainer {
	
	
	static final String UK_AC_KCL_MDEO_LANGUAGES_MOPT_BUNDLE_ID = "uk.ac.kcl.inf.mdeoptimiser.languages.mopt.xtext";
	static final String UK_AC_KCL_MDEO_ECLIPSE_UI_BUNDLE_ID = "uk.ac.kcl.inf.mdeoptimiser.interfaces.eclipse.ui";
	static final String UK_AC_KCL_MDEO_INTERFACE_CLI_BUNDLE_ID = "uk.ac.kcl.inf.mdeoptimiser.interfaces.cli";
	static final String UK_AC_KCL_MDEO_LIBRARY_CORE_BUNDLE_ID = "uk.ac.kcl.inf.mdeoptimiser.libraries.core";
	public static final IPath CONTAINER_PATH = MoptClasspathContainerInitializer.MDEO_LIBRARY_PATH.append("/dsl");
	
	
	public static final String[] BUNDLE_IDS_TO_INCLUDE = #["org.eclipse.emf.ecore", 
															"org.eclipse.emf.common", 
															UK_AC_KCL_MDEO_LANGUAGES_MOPT_BUNDLE_ID, 
															UK_AC_KCL_MDEO_ECLIPSE_UI_BUNDLE_ID,
															UK_AC_KCL_MDEO_INTERFACE_CLI_BUNDLE_ID,
															UK_AC_KCL_MDEO_LIBRARY_CORE_BUNDLE_ID]
	
	IClasspathEntry[] classpathEntries;
	
	override getClasspathEntries() {
		
		if(this.classpathEntries === null) {
			this.classpathEntries = MoptClasspathContainer.BUNDLE_IDS_TO_INCLUDE.fold(new ArrayList<IClasspathEntry>(), [ list, entry | 
				list.addEntry(entry) return list
			])
		}
		
		return classpathEntries;
	}
	
	override getDescription() '''MDEO DSL Libraries'''
		
	
	override getKind() {
		IClasspathContainer.K_APPLICATION
	}
	
	override getPath() { 
		return CONTAINER_PATH
	}
	
	private def void addEntry(List<IClasspathEntry> classpathEntries, String bundleId){
	
		val bundle = Platform.getBundle(bundleId);
		
		if(bundle !== null) {
			
			//We may need to add target/classes to the bundle path if in Debug mode
			if(buildFolderPath(bundle) !== null) {
				classpathEntries.add(JavaCore.newLibraryEntry(buildFolderPath(bundle), null, null, false));
			}
				
			classpathEntries.add(JavaCore.newLibraryEntry(getBundlePath(bundle), null, null, false));
		}
	}
	
	private def IPath getBundlePath(Bundle bundle) {
		
		val path = buildFolderPath(bundle);
		
		if(path === null) {
			
			//No need to deal with the target/classes case, so adding a normal JAR
			try {
				return new Path(FileLocator.getBundleFile(bundle).canonicalPath);
			} catch (IOException e) {
				println("Can't resolve path '" + bundle.getSymbolicName() + "'");
			}	
		}
		
		return path
	}
	
	/**
	 * For cases when debugging inside Eclipse, the bundle is compiled to target/classes
	 * and by default this folder is not recognised by the classpath loader. This is a fix that
	 * adds the required path suffix to make sure the plugin can be used correctly when debugging.
	 * 
	 */
	def static IPath buildFolderPath(Bundle bundle) {
		
		val buildFolderURL = FileLocator.find(bundle, new Path("target/classes"), null);
		
		if(buildFolderURL !== null) {
			try {
				val buildFolderFileURL = FileLocator.toFileURL(buildFolderURL);
				
				return new Path(buildFolderFileURL.getPath).makeAbsolute
				
			} catch (IOException e) {
				//TODO Logger
				println("Can't resolve path '" + bundle.getSymbolicName() + "'");
			}
		}
		
		return null
	}
	
}
