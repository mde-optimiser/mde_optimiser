package uk.ac.kcl.mdeoptimise.rulegen.tests.utils

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.compare.Comparison
import org.eclipse.emf.compare.EMFCompare
import org.eclipse.emf.compare.match.DefaultComparisonFactory
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory
import org.eclipse.emf.compare.match.DefaultMatchEngine
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl
import org.eclipse.emf.compare.scope.DefaultComparisonScope
import org.eclipse.emf.compare.utils.EMFComparePrettyPrinter
import org.eclipse.emf.compare.utils.UseIdentifiers
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.henshin.model.Module
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet

final class ComparatorHelper {

	private Comparison comparison
	private Module left;
	private Module right

	new(Module left, Module right){
		this.left = left;
		this.right = right;
	}

	def EMFCompare setupComparator() {
		
		var matcherWithoutId = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.NEVER);
		
		var comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
		var matchEngineFactory = new MatchEngineFactoryImpl(matcherWithoutId, comparisonFactory);
		//matchEngineFactory.setRanking(20);
		
		
		var matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
		matchEngineRegistry.add(matchEngineFactory);
		
		var comparator = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).build();
		
		return comparator;
	}
	
	private def Resource getResourceSet(Module module){
		
		if(module.eResource !== null && module.eResource.resourceSet != null){
			return module.eResource;
		}
		
		var henshinResourceSet = new HenshinResourceSet();
		
		var resource = henshinResourceSet.createResource(URI.createURI(String.format("%s.henshin", module.name)))
		
		resource.contents.add(module)
		
		return resource;
	}
	
	def boolean compare(){

		var leftResource = getResourceSet(this.left);
		var rightResource = getResourceSet(this.right);

		var scope = new DefaultComparisonScope(leftResource, rightResource, null);
		this.comparison = setupComparator().compare(scope);
		return this.comparison.differences.size == 0
	}
	
	/**
	 * Pretty print a list of diferences.
	 */
	def String getDiferences(){
		
		var outputStream = new ByteArrayOutputStream();
		var printStream = new PrintStream(outputStream, true, "utf-8")
		
		EMFComparePrettyPrinter.printDifferences(this.comparison, printStream)
		
		return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
	}
}
