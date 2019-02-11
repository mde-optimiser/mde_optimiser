package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator

import org.eclipse.emf.henshin.model.Module

interface IRuleGenerationCommand {
	
	def Module generate();
}
