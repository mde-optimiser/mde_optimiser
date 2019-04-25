package uk.ac.kcl.inf.mdeoptimiser.languages.ui.validation

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ObjectiveInterpreterSpec
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.URI
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.jdt.core.JavaCore
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ConstraintInterpreterSpec
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.AbstractMoptValidator
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.MoptValidatorIssues
import uk.ac.kcl.inf.mdeoptimiser.languages.ui.classpath.MoptClasspathContainer

class MoptClasspathValidator extends AbstractMoptValidator {
	
	
	public static val MDEO_PREFIX = "uk.ac.kcl.mdeoptimiser.";
	public static val MDEO_LIB_NOT_ON_CLASSPATH = MDEO_PREFIX + 'mdeo_lib_not_on_classpath'

	@Check
	def void checkClasspathContainsMdeoLibraryForJavaObjectives(ObjectiveInterpreterSpec objectiveSpec) {
		if (objectiveSpec.objectiveType.equals("java") && workspaceAccessible) {
			var resourceURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(objectiveSpec.eContainer);
			checkMDEOClasspath(objectiveSpec, resourceURI);
		}
	}

	@Check
	def void checkClasspathContainsMdeoLibraryForJavaConstraints(ConstraintInterpreterSpec objectiveSpec) {
		
		if (objectiveSpec.constraintType.equals("java") && workspaceAccessible) {
			var resourceURI = EcoreUtil2.getPlatformResourceOrNormalizedURI(objectiveSpec.eContainer);
			checkMDEOClasspath(objectiveSpec, resourceURI);
		}
	}
	
	/**
	 * Check if eclipse is running in GUI mode or headless without having to require that the Platform
	 * package is on the classpath.
	 *
	 */
	def boolean workspaceAccessible(){
		
		var Class platformClass = null
		
		 try {
           platformClass = Class.forName("org.eclipse.core.runtime.Platform", false, this.class.getClassLoader());
        } catch (LinkageError | ClassNotFoundException e) {
            return false;
        }
        
        val isPlatformRunning = platformClass.getDeclaredMethod("isRunning");
        
		return isPlatformRunning.invoke(null) as Boolean
	}

	def void checkMDEOClasspath(EObject contextObject, URI resourceURI) {
		
		
		var platformStringUri = resourceURI.toPlatformString(true)
		
		//Is the resource uri valid?
		if(resourceURI.empty || platformStringUri === null){
			return
		}
		
		var path = new Path(platformStringUri)
		var mdeoOnClasspath = false;
		var project = ResourcesPlugin.getWorkspace().root.getFile(path)
		var javaProject = JavaCore.create(project.project);
		var classpath = javaProject.rawClasspath

		for (var i = 0; i < classpath.length; i++) {
			if (classpath.get(i).getPath().equals(MoptClasspathContainer.CONTAINER_PATH)) {
				mdeoOnClasspath = true;
			}
		}

		if (!mdeoOnClasspath) {
			error("Couldn't find the mandatory library 'uk.ac.kcl.inf.mdeoptimiser.core' on the classpath.", contextObject, null,
				MoptValidatorIssues.MDEO_LIB_NOT_ON_CLASSPATH);
		}
	}
	
	
}