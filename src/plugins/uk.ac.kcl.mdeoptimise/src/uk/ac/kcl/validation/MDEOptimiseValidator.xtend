/*
 * generated by Xtext 2.10.0
 */
package uk.ac.kcl.validation

import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.jdt.core.JavaCore
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import uk.ac.kcl.mdeoptimise.ConstraintInterpreterSpec
import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec
import org.eclipse.core.runtime.Platform

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class MDEOptimiseValidator extends AbstractMDEOptimiseValidator {

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
	
	//TODO This might be a heavy check
	/**
	 * Check if eclipse is running in GUI mode or headless
	 */
	def boolean workspaceAccessible(){
		return Platform.isRunning
	}

	def void checkMDEOClasspath(EObject contextObject, URI resourceURI) {
		
		
		var platformStringUri = resourceURI.toPlatformString(true)
		
		//Is the resource uri valid?
		if(resourceURI.empty || platformStringUri == null){
			return
		}
		
		var path = new Path(platformStringUri)
		
		var mdeoOnClasspath = false;
		
		var project = ResourcesPlugin.getWorkspace().root.getFile(path)

		var javaProject = JavaCore.create(project.project);
		var classpath = javaProject.rawClasspath

		for (var i = 0; i < classpath.length; i++) {
			if (classpath.get(i).getPath().toString().indexOf("uk.ac.kcl.mdeoptimise.MDEO_CONTAINER/dsl") != -1) {
				mdeoOnClasspath = true;
			}
		}

		if (!mdeoOnClasspath) {
			error("Couldn't find the mandatory library 'uk.ac.kcl.mdeoptimise' on the classpath.", contextObject, null,
				MDEOptimiseValidatorIssues.MDEO_LIB_NOT_ON_CLASSPATH);
		}
	}

}
