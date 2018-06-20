package uk.ac.kcl.mdeoptimise.rulegen.generator

import org.eclipse.emf.henshin.model.Module

interface IRuleGenerationCommand {
	
	def Module generate();
}