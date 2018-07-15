package uk.ac.kcl.mdeoptimise.rulegen.tests

import java.util.ArrayList
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.ecore.EPackage
import uk.ac.kcl.mdeoptimise.rulegen.RulesGenerator
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec

import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelLoader

import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.junit.jupiter.api.^extension.ExtendWith
import org.junit.jupiter.api.BeforeEach
import static org.junit.jupiter.api.Assertions.*;
import org.eclipse.emf.henshin.model.Module
import java.util.stream.Stream
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.Map
import com.google.common.collect.ImmutableMap
import static uk.ac.kcl.mdeoptimise.rulegen.tests.utils.ExpectedRulesHelper.*
import org.junit.jupiter.api.DisplayName

@ExtendWith(InjectionExtension)
class RulegenTest {
	
	
	EPackage metamodel;
	List<Multiplicity> multiplicities;
	MetamodelLoader metamodelLoader;

	@BeforeEach
	def void rulegenInitialise(){
		this.metamodelLoader = new MetamodelLoader();
		this.metamodel = metamodelLoader.fakeGenericMetamodel
		this.multiplicities = new LinkedList<Multiplicity>();
	}
	
	static def Stream<Arguments> createNodeMultiplicities(){
		//n m k l
		return Stream.of(
		
			//First set
			Arguments.of(0, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = 0 and k = 0 and l < *"),
   				
			Arguments.of(0, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = 0 and k > 0 and l < *"),
   				
			Arguments.of(0, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = 0 and k = 0 and l = *"),
   				
			Arguments.of(0, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = 0 and k > 0 and l = *"),
   				
			Arguments.of(0, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = 0 and k = l = 1"),
   				
			Arguments.of(0, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = 0 and k = l = 2"),
   				
			//Second set
			
			Arguments.of(1, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_1B_f5A")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_1B")
   				.build(),
   				"n = 1 and m > n and k = 0 and l < *"),
   				
			Arguments.of(1, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_1B_f5A")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_1B")
   				.build(),
   				"n = 1 and m > n and k = 1 and l < *"),
			
			Arguments.of(1, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_1B")
   				.build(),
   				"n = 1 and m > n and k = 0 and l = *"),
			
			Arguments.of(1, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_1B")
   				.build(),
   				"n = 1 and m > n and k = 1 and l = *"),
				
			Arguments.of(1, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_1B")
   				.build(),
   				"n = 1 and m > n and k = l = 1"),
			
			Arguments.of(1, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_1B")
   				.build(),
   				"n = 1 and m > n and k = l = 2"),
			
			// Third set
			
			Arguments.of(2, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_2B_f5A")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_2B")
      			.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_MANY", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_MANY_2B")
   				.build(),
   				"n > 1 and m > n and k = 0 and l < *"),
			
			Arguments.of(2, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_2B")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_2B_f5A")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_MANY", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_MANY_2B")
   				.build(),
   				"n > 1 and m > n and k = 1 and l < *"),
   			
			Arguments.of(2, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_2B")
   				.build(),
   				"n > 1 and m > n and k = 0 and l = *"),
   			
			Arguments.of(2, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_2B")
   				.build(),
   				"n > 1 and m > n and k = 1 and l = *"),

			Arguments.of(2, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_2B")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_MANY", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_MANY_2B")
   				.build(),
   				"n > 1 and m > n and k = l = 1"),

			//TODO No create for this case as it would not be a valid model instance. Fix table mistake in the paper
			Arguments.of(2, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_2B")
   				.put("S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_MANY", "S_CREATE_C_edgeCB_edge_CREATE_LB_REPAIR_MANY_2B")
   				.build(),
   				"n > 1 and m > n and k = l = 2"),
   			
   			// Fourth set
   			
			Arguments.of(1, 1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_1B_f5A")
   				.build(),
   				"n = m = 1 and k = 0 and l < *"),
   			
			Arguments.of(1, 1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = m = 1 and k = l = 1"),
   				
			Arguments.of(1, 1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = m = 1 and k = l = 2"),
   				
 			Arguments.of(2, 2, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.put("S_CREATE_C_edgeCB_edge_CREATE", "S_CREATE_C_edgeCB_edge_CREATE_2B_f5A")
   				.build(),
   				"n = m = 2 and k = 0 l < *"),
   				
			Arguments.of(2, 2, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = m = 2 and k = l = 1"),
   				
			Arguments.of(2, 2, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_CREATE_C_edgeCB_edge_CREATE", "P_CREATE_C_edgeCB_edge_CREATE")
   				.build(),
   				"n = m = 2 and k = l = 2")
		);
	}
	
	@ParameterizedTest(name = "{index} => Case: {5}  Values: n={0}, m={1}  k={2}, l={3}")
	@MethodSource("createNodeMultiplicities")
	@DisplayName("Test node creation rules and repairs generation")
	def void assertThatTheCorrectCreateNodeRulesAreGeneratedForGivenMultiplicities(int n, int m, int k, int l, Map<String, String> rules, String description) {
					
		//Original metamodel with 0..* 0..* multiplicities	
		this.multiplicities.add(new Multiplicity("B", "edgeBC", k, l, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", n, m, this.metamodel));
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val List<RuleSpec> ruleSpecs = #[new RuleSpec("C", "CREATE")]
		
		val rulesGenerator = new RulesGenerator(this.metamodel,  this.multiplicities, ruleSpecs)
		
		val createdRules = rulesGenerator.generateRules.values.fold(new ArrayList<Module>, [list, modules | 
			list.addAll(modules) return list
		]) 
	
		outputRules(createdRules, rules, "CREATE", n, m, k, l);
	
		createdRules.forEach[module |
			
			var ruleName = rules.get(module.name)	
			var expectedRule = loadModule(this.metamodelLoader, ruleName)
			
			assertTrue(isEqual(expectedRule, module), String.format("Assert that %s rule is as expected with %s", module.name, ruleName))
		]
	
		assertEquals(rules.keySet.length, createdRules.length, "The expected number of rules and repairs has been generated.")
	}


	static def Stream<Arguments> deleteNodeMultiplicities(){
		//n m k l
		return Stream.of(
		// m > n and m < *
			//First set n = 0 m = 1
			Arguments.of(0, 1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"m > n and m < * and k = 0 and l = *"),
   			
   			Arguments.of(0, 1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"m > n and m < * and k = 0 and l < *"),
   			
   			Arguments.of(0, 1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"m > n and m < * and k = 0 and l < *"),
			
//			//TODO Fix. Either add the forbid rule for node C being deleted or a 
//			//multi rule to move any nodes of tybe B assigned to another node of type C
//   			Arguments.of(0, 1, 1, -1, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
//   				.build(),
//   				"m > n and m < * and k = 1 and l = *"),
//   				
//			//TODO Fix, Check again	
//   			Arguments.of(0, 1, 2, -1, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
//   				.build(),
//   				"m > n and m < * and k = 2 and l = *"),
//   				
//   			//TODO Fix, Check again	
//   			Arguments.of(0, 1, 1, 5, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
//   				.build(),
//   				"m > n and m < * and k = 1 and l < *"),		
//   				
//   			//TODO Fix, Check again	
//   			Arguments.of(0, 1, 2, 5, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
//   				.build(),
//   				"m > n and m < * and k = 2 and l < *"),		
//
//   			//TODO Check again	
//   			Arguments.of(0, 1, 1, 1, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_1B_f1B")
//   				.build(),
//   				"m > n and m < * and k = l = 1"),
//				
//			//TODO Check again
//   			Arguments.of(0, 1, 2, 2, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_MANY", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_MANY_2B_f1B")
//   				.build(),
//   				"m > n and m < * and k = l = 2"),
				
			//Second set n = 1 and m = 2
   			Arguments.of(1, 2, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
  				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m < * and k = 0 and l = *"),
   				
   			Arguments.of(1, 2, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m < * and k = 0 and l < *"),

   			Arguments.of(1, 2, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m < * and k = 0 and l < *"),		
   				
   			Arguments.of(1, 2, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l = *"),	
			
   			Arguments.of(1, 2, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l = *"),	
   				
   			Arguments.of(1, 2, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l < *"),		

   			Arguments.of(1, 2, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l < *"),
   				
   			Arguments.of(1, 2, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_1B_f2B")
   				.build(),
   				"m > n and m < * and k = l = 1"),
			
			//TODO Here we should have at least one node connected, so a single repair is necessary
			//where we take an assigned node B and move to another node of type C with a forbid
   			Arguments.of(1, 2, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_1B_f2B")
   				.build(),
   				"m > n and m < * and k = l = 2"),
			
			//Third set n = 2 and m = 5
			Arguments.of(2, 5, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m < * and k = 0 and l = *"),
   				
   			Arguments.of(2, 5, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m < * and k = 0 and l < *"),

   			Arguments.of(2, 5, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m < * and k = 0 and l < *"),		
   				
   			Arguments.of(2, 5, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l = *"),	
			
   			Arguments.of(2, 5, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l = *"),	
   				
   			Arguments.of(2, 5, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l < *"),		
   				
   			Arguments.of(2, 5, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l < *"),
   				
   			Arguments.of(2, 5, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_2B_f5B")
   				.build(),
   				"m > n and m < * and k = l = 1"),

   			Arguments.of(2, 5, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_2B_f5B")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_MANY", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_MANY_2B_f5B")
   				.build(),
   				"m > n and m < * and k = l = 2"),
  		
  		// m = *
  		 			
   			//Fourth set n = 0 and m = *
   			Arguments.of(0, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"m > n and m = * and k = 0 and l = *"),
   				
   			Arguments.of(0, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	

   			Arguments.of(0, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	
   				
//   			//TODO Same as above	
//   			Arguments.of(0, -1, 1, -1, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
//   				.build(),
//   				"m > n and m < * and k = 1 and l = *"),	
//			
//			//TODO Same as above
//   			Arguments.of(0, -1, 2, -1, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
//   				.build(),
//   				"m > n and m < * and k = 2 and l = *"),
//
//			//TODO Same as above
//   			Arguments.of(0, -1, 1, 5, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
//   				.build(),
//   				"m > n and m < * and k = 1 and l < *"),		
//   				
//   			//TODO Same as above
//   			Arguments.of(0, -1, 2, 5, 
//				ImmutableMap.<String, String>builder()
//   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
//   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
//   				.build(),
//   				"m > n and m < * and k = 2 and l < *"),
   				
   			Arguments.of(0, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_1B")
   				.build(),
   				"m > n and m < * and k = l = 1"),

   			Arguments.of(0, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_2B")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_MANY", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_MANY_2B")
   				.build(),
   				"m > n and m < * and k = l = 2"),
   				
   				
   			//Fifth set n = 1 and m = *
   			Arguments.of(1, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m = * and k = 0 and l = *"),
   				
   			Arguments.of(1, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	

   			Arguments.of(1, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	
   				
   			Arguments.of(1, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l = *"),	
			
   			Arguments.of(1, -1, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l = *"),

   			Arguments.of(1, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l < *"),		
 
   			Arguments.of(1, -1, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l < *"),
   				
   			Arguments.of(1, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_1B")
   				.build(),
   				"m > n and m < * and k = l = 1"),

   			Arguments.of(1, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_1B")
   				.build(),
   				"m > n and m < * and k = l = 2"),
   				
   			//Sixth set n = 2 and m = *
   			Arguments.of(2, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m = * and k = 0 and l = *"),
   				
   			Arguments.of(2, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	

   			Arguments.of(2, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	
   				
   			Arguments.of(2, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l = *"),	
			
   			Arguments.of(2, -1, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l = *"),

   			Arguments.of(2, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l < *"),		

   			Arguments.of(2, -1, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l < *"),
   				
   			Arguments.of(2, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_2B")
   				.build(),
   				"m > n and m < * and k = l = 1"),

   			Arguments.of(2, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_2B")
   				.put("S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_MANY", "S_DELETE_C_edgeCB_edge_DELETE_LB_REPAIR_MANY_2B")
   				.build(),
   				"m > n and m < * and k = l = 2"),
   		// n = m	
   			//Seventh set n = 1 and m = 1
   			Arguments.of(1, 1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m = * and k = 0 and l = *"),
   				
   			Arguments.of(1, 1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	

   			Arguments.of(1, 1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	
   				
   			Arguments.of(1, 1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l < *"),	
			
   			Arguments.of(1, 1, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l = *"),
   				
   			Arguments.of(1, 1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l < *"),		
   				
   			Arguments.of(1, 1, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l < *"),
   				
   			Arguments.of(1, 1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"m > n and m < * and k = l = 1"),

   			Arguments.of(1, 1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"m > n and m < * and k = l = 2"),
   				
   			//Seventh set n = 2 and m = 2
   			Arguments.of(2, 2, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m = * and k = 0 and l = *"),
   				
   			Arguments.of(2, 2, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	

   			Arguments.of(2, 2, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B")
   				.build(),
   				"m > n and m = * and k = 0 and l < *"),	

   			Arguments.of(2, 2, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l = *"),	
			
   			Arguments.of(2, 2, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r2C")
   				.build(),
   				"m > n and m < * and k = 2 and l = *"),

   			Arguments.of(1, 1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_1B_r1C")
   				.build(),
   				"m > n and m < * and k = 1 and l < *"),		
   				
   			Arguments.of(2, 2, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.put("S_DELETE_C_edgeCB_edge_DELETE", "S_DELETE_C_edgeCB_edge_DELETE_2B_r2C")
   				.build(),
   				"n = m and k = 2 and l < *"),
   				
   			Arguments.of(2, 2, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"n = m and k = l = 1"),

   			Arguments.of(2, 2, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"n = m and k = l = 2"),

   			Arguments.of(2, 2, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_DELETE_C_edgeCB_edge_DELETE", "P_DELETE_C_edgeCB_edge_DELETE")
   				.build(),
   				"n = m = 2 and k = l = 2")
		);
	}

	@ParameterizedTest(name = "{index} => Case: {5}  Values: n={0}, m={1}  k={2}, l={3}")
	@MethodSource("deleteNodeMultiplicities")
	@DisplayName("Test node deletion rules and repairs generation")
	def void assertThatTheCorrectDeleteNodeRulesAreGeneratedForGivenMultiplicities(int n, int m, int k, int l, Map<String, String> rules, String description) {
					
		//Original metamodel with 0..* 0..* multiplicities	
		this.multiplicities.add(new Multiplicity("B", "edgeBC", k, l, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", n, m, this.metamodel));
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val List<RuleSpec> ruleSpecs = #[new RuleSpec("C", "DELETE")]
		
		val rulesGenerator = new RulesGenerator(this.metamodel,  this.multiplicities, ruleSpecs)
		
		val createdRules = rulesGenerator.generateRules.values.fold(new ArrayList<Module>, [list, modules | 
			list.addAll(modules) return list
		]) 
	
		outputRules(createdRules, rules, "DELETE", n, m, k, l);
	
		createdRules.forEach[module |
			
			var ruleName = rules.get(module.name)	
			var expectedRule = loadModule(this.metamodelLoader, ruleName)
			
			assertTrue(isEqual(expectedRule, module), String.format("Assert that %s rule is as expected with %s", module.name, ruleName))
		]
	
		assertEquals(rules.keySet.length, createdRules.length, "The expected number of rules and repairs has been generated.")
	}
	
	
	static def Stream<Arguments> addEdgeMultiplicities(){
		//n m k l
		return Stream.of(
			
		//m < *
			
			//First set
			Arguments.of(0, 1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1B_f5C")
   				.build(),
   				"m < * and l < *"),

			Arguments.of(0, 1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1B_f1C")
   				.build(),
   				"m < * and l < *"),		
   			
   			Arguments.of(0, 1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1B_f5C")
   				.build(),
   				"m < * and l < *"),
   				
   			Arguments.of(0, 1, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1B_f5C")
   				.build(),
   				"m < * and l < *"),
   					
			Arguments.of(0, 1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1B")
   				.build(),
   				"m < * and l = *"),

			Arguments.of(0, 1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1B")
   				.build(),
   				"m < * and l = *"),   			
   			
   			Arguments.of(0, 1, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1B")
   				.build(),
   				"m < * and l = *"),
   				
   			Arguments.of(0, 1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_f1B")
   				.build(),
   				"m < * and k = l"),

   			Arguments.of(0, 1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_f1B")
   				.build(),
   				"m < * and k = l"),
   				
   				   			
   			//Second set
			Arguments.of(1, 2, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f2B_f5C")
   				.build(),
   				"m < * and l < *"),

			Arguments.of(1, 2, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f2B_f1C")
   				.build(),
   				"m < * and l < *"),   			
   			
   			Arguments.of(1, 2, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f2B_f5C")
   				.build(),
   				"m < * and l < *"),
   				
   			Arguments.of(1, 2, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f2B_f5C")
   				.build(),
   				"m < * and l < *"),
   					
			Arguments.of(1, 2, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f2B")
   				.build(),
   				"m < * and l = *"),

			Arguments.of(1, 2, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f2B")
   				.build(),
   				"m < * and l = *"),   			
   			
   			Arguments.of(1, 2, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f2B")
   				.build(),
   				"m < * and l = *"),
   				
   			Arguments.of(1, 2, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_f2B")
   				.build(),
   				"m < * and k = l"),

   			Arguments.of(1, 2, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_f2B")
   				.build(),
   				"m < * and k = l"),
   				
     		//Third set
			Arguments.of(2, 5, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5B_f5C")
   				.build(),
   				"m < * and l < *"),

			Arguments.of(2, 5, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5B_f1C")
   				.build(),
   				"m < * and l < *"),   			
   			
   			Arguments.of(2, 5, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5B_f5C")
   				.build(),
   				"m < * and l < *"),
   				
   			Arguments.of(2, 5, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5B_f5C")
   				.build(),
   				"m < * and l < *"),
   					
			Arguments.of(2, 5, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5B")
   				.build(),
   				"m < * and l = *"),

			Arguments.of(2, 5, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5B")
   				.build(),
   				"m < * and l = *"),   			
   			
   			Arguments.of(2, 5, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5B")
   				.build(),
   				"m < * and l = *"),
   				
   			Arguments.of(2, 5, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_f5B")
   				.build(),
   				"m < * and k = l"),

   			Arguments.of(2, 5, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_f5B")
   				.build(),
   				"m < * and k = l"),
   				
		//m = *
			
			//First set
			Arguments.of(0, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),

			Arguments.of(0, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1C")
   				.build(),
   				"m = * and l < *"),   			
   			
   			Arguments.of(0, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),
   				
   			Arguments.of(0, -1, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),
   					
			Arguments.of(0, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),

			Arguments.of(0, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),   			
   			
   			Arguments.of(0, -1, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),
   				
   			Arguments.of(0, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE")
   				.build(),
   				"m = * and k = l"),

   			Arguments.of(0, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE")
   				.build(),
   				"m = * and k = l"),
   				
   			
   			//Second set
			Arguments.of(1, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),

			Arguments.of(1, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1C")
   				.build(),
   				"m = * and l < *"),   			
   			
   			Arguments.of(1, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),
   				
   			Arguments.of(1, -1, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),
   					
			Arguments.of(1, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),

			Arguments.of(1, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),   			
   			
   			Arguments.of(1, -1, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),
   				
   			Arguments.of(1, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_r1B")
   				.build(),
   				"m = * and k = l"),

   			Arguments.of(1, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_r1B")
   				.build(),
   				"m = * and k = l"),
   				
     		//Third set
			Arguments.of(2, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),

			Arguments.of(2, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f1C")
   				.build(),
   				"m = * and l < *"),   			
   			
   			Arguments.of(2, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),
   				
   			Arguments.of(2, -1, 2, 5, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_ADD", "S_ADD_C_edgeCB_edge_ADD_f5C")
   				.build(),
   				"m = * and l < *"),
   					
			Arguments.of(2, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),

			Arguments.of(2, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),   			
   			
   			Arguments.of(2, -1, 2, -1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.build(),
   				"m = * and l = *"),
   				
   			Arguments.of(2, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_r2B")
   				.build(),
   				"m = * and k = l"),

   			Arguments.of(2, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
   				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_CHANGE", "S_ADD_C_edgeCB_edge_CHANGE_r2B")
   				.build(),
   				"m = * and k = l"),
   				
		//n = m
			
			//First set
			Arguments.of(1, 1, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l < *"),

			Arguments.of(1, 1, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l < *"),   			
   			
   			Arguments.of(1, 1, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l < *"),
   				
   			Arguments.of(1, 1, 2, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l < *"),
   					
			Arguments.of(1, 1, 0, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l = *"),

			Arguments.of(1, 1, 1, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l = *"),   			
   			
   			Arguments.of(1, 1, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l = *"),
   				
   			Arguments.of(1, 1, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and k = l"),

   			Arguments.of(1, 1, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and k = l"),
   				
			
			//Second set
			Arguments.of(2, 2, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l < *"),

			Arguments.of(2, 2, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l < *"),   			
   			
   			Arguments.of(2, 2, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l < *"),
   				
   			Arguments.of(2, 2, 2, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l < *"),
   					
			Arguments.of(2, 2, 0, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l = *"),

			Arguments.of(2, 2, 1, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l = *"),   			
   			
   			Arguments.of(2, 2, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and l = *"),
   				
   			Arguments.of(2, 2, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and k = l"),

   			Arguments.of(2, 2, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_ADD_C_edgeCB_edge_ADD", "P_ADD_C_edgeCB_edge_ADD")
   				.put("S_ADD_C_edgeCB_edge_SWAP", "S_ADD_C_edgeCB_edge_SWAP")
   				.build(),
   				"n = m and k = l")
		);
	}

	@ParameterizedTest(name = "{index} => Case: {5}  Values: n={0}, m={1}  k={2}, l={3}")
	@MethodSource("addEdgeMultiplicities")
	@DisplayName("Test add edge rules and repairs generation")
	def void assertThatTheCorrectAddEdgeRulesAreGeneratedForGivenMultiplicities(int n, int m, int k, int l, Map<String, String> rules, String description) {
					
		//Original metamodel with 0..* 0..* multiplicities	
		this.multiplicities.add(new Multiplicity("B", "edgeBC", k, l, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", n, m, this.metamodel));
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val List<RuleSpec> ruleSpecs = #[new RuleSpec("C", "ADD")]
		
		val rulesGenerator = new RulesGenerator(this.metamodel,  this.multiplicities, ruleSpecs)
		
		val createdRules = rulesGenerator.generateRules.values.fold(new ArrayList<Module>, [list, modules | 
			list.addAll(modules) return list
		]) 
		
		outputRules(createdRules, rules, "ADD", n, m, k, l);
		
		createdRules.forEach[module |
			
			var ruleName = rules.get(module.name)	
			var expectedRule = loadModule(this.metamodelLoader, ruleName)
			assertTrue(isEqual(expectedRule, module), String.format("Assert that %s rule is as expected with %s", module.name, ruleName))
		]
	
		assertEquals(rules.keySet.length, createdRules.length, "The expected number of rules and repairs has been generated.")
	}
	
	
	
	
	static def Stream<Arguments> removeEdgeMultiplicities(){
		//n m k l
		return Stream.of(
			
		//n = 0
			
			//First set
			Arguments.of(0, 1, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
   				.build(),
   				"n = 0 and k = 0"),

			Arguments.of(0, 1, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
   				.build(),
   				"n = 0 and k = 0"),		

			Arguments.of(0, 1, 0, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
   				.build(),
   				"n = 0 and k = 0"),
   			
   			Arguments.of(0, 1, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1C")
   				.build(),
   				"n = 0 and k > 0"),
   				
   			Arguments.of(0, 1, 2, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2C")
   				.build(),
   				"n = 0 and k > 0"),

			Arguments.of(0, 1, 1, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1C")
   				.build(),
   				"n = 0 and k > 0"),   			
   			
   			Arguments.of(0, 1, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2C")
   				.build(),
   				"n = 0 and k > 0"),
   				
   			Arguments.of(0, 1, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_f1B")
   				.build(),
   				"n = 0 and k = l"),

   			Arguments.of(0, 1, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_f1B")
   				.build(),
   				"n = 0 and k = l"),
   			
   			//Second set	
			Arguments.of(0, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
   				.build(),
   				"n = 0 and l < *"),

			Arguments.of(0, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
   				.build(),
   				"n = 0 and l < *"),   			
   			
   			Arguments.of(0, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1C")
   				.build(),
   				"n = 0 and l < *"),
   				
   			Arguments.of(0, -1, 2, 5, 
				ImmutableMap.<String, String>builder()
				
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2C")
   				.build(),
   				"n = 0 and l < *"),
   					
			Arguments.of(0, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
				
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
   				.build(),
   				"n = 0 and l = *"),

			Arguments.of(0, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
				
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1C")
   				.build(),
   				"n = 0 and l = *"),   			
   			
   			Arguments.of(0, -1, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2C")
   				.build(),
   				"n = 0 and l = *"),
   				
   			Arguments.of(0, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE")
   				.build(),
   				"n = 0 and k = l"),

   			Arguments.of(0, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE")
   				.build(),
   				"n = 0 and k = l"),
   				
   		// n > 0		   			
   			//First set
			Arguments.of(1, 2, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B")
   				.build(),
   				"n > 0 and l < *"),

			Arguments.of(1, 2, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B")
   				.build(),
   				"n > 0 and l < *"),   			
   			
   			Arguments.of(1, 2, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B_r1C")
   				.build(),
   				"n > 0 and l < *"),
   				
   			Arguments.of(1, 2, 2, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B_r2C")
   				.build(),
   				"n > 0 and l < *"),
   					
			Arguments.of(1, 2, 0, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B")
   				.build(),
   				"n > 0 and l = *"),

			Arguments.of(1, 2, 1, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B_r1C")
   				.build(),
   				"n > 0 and l = *"),   			
   			
   			Arguments.of(1, 2, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B_r2C")
   				.build(),
   				"n > 0 and l = *"),
   				
   			Arguments.of(1, 2, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_r1B_f2B")
   				.build(),
   				"n > 0 and k = l"),

   			Arguments.of(1, 2, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_r1B_f2B")
   				.build(),
   				"n > 0 and k = l"),
   				
     		//Second set
			Arguments.of(2, 5, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B")
   				.build(),
   				"n > 0 and l < *"),

			Arguments.of(2, 5, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B")
   				.build(),
   				"n > 0 and l < *"),   			
   			
   			Arguments.of(2, 5, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B_r1C")
   				.build(),
   				"n > 0 and l < *"),
   				
   			Arguments.of(2, 5, 2, 5, 
				ImmutableMap.<String, String>builder()
				
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B_r2C")
   				.build(),
   				"n > 0 and l < *"),
   					
			Arguments.of(2, 5, 0, -1, 
				ImmutableMap.<String, String>builder()
				
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B")
   				.build(),
   				"n > 0 and l = *"),

			Arguments.of(2, 5, 1, -1, 
				ImmutableMap.<String, String>builder()
				
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B_r1C")
   				.build(),
   				"n > 0 and l = *"),   			
   			
   			Arguments.of(2, 5, 2, -1, 
				ImmutableMap.<String, String>builder()
				
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B_r2C")
   				.build(),
   				"n > 0 and l = *"),
   				
   			Arguments.of(2, 5, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_r2B_f5B")
   				.build(),
   				"n > 0 and k = l"),

   			Arguments.of(2, 5, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_r2B_f5B")
   				.build(),
   				"n > 0 and k = l"),
   
   			//Third set
			Arguments.of(1, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B")
   				.build(),
   				"n > 0 and l < *"),

			Arguments.of(1, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B")
   				.build(),
   				"n > 0 and l < *"),   			
   			
   			Arguments.of(1, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B_r1C")
   				.build(),
   				"n > 0 and l < *"),
   				
   			Arguments.of(1, -1, 2, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B_r2C")
   				.build(),
   				"n > 0 and l < *"),
   					
			Arguments.of(1, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B")
   				.build(),
   				"n > 0 and l = *"),

			Arguments.of(1, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B_r1C")
   				.build(),
   				"n > 0 and l = *"),   			
   			
   			Arguments.of(1, -1, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r1B_r2C")
   				.build(),
   				"n > 0 and l = *"),
   				
   			Arguments.of(1, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
				
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_r1B")
   				
   				.build(),
   				"n > 0 and k = l"),

   			Arguments.of(1, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_r1B")
   				.build(),
   				"n > 0 and k = l"),
   				
     		//Fourth set
			Arguments.of(2, -1, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B")
   				.build(),
   				"n > 0 and l < *"),

			Arguments.of(2, -1, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B")
   				.build(),
   				"n > 0 and l < *"),   			
   			
   			Arguments.of(2, -1, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B_r1C")
   				.build(),
   				"n > 0 and l < *"),
   				
   			Arguments.of(2, -1, 2, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B_r2C")
   				.build(),
   				"n > 0 and l < *"),
   					
			Arguments.of(2, -1, 0, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B")
   				.build(),
   				"n > 0 and l = *"),

			Arguments.of(2, -1, 1, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B_r1C")
   				.build(),
   				"n > 0 and l = *"),   			
   			
   			Arguments.of(2, -1, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_REMOVE", "S_REMOVE_C_edgeCB_edge_REMOVE_r2B_r2C")
   				.build(),
   				"n > 0 and l = *"),
   				
   			Arguments.of(2, -1, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_r2B")
   				.build(),
   				"n > 0 and k = l"),

   			Arguments.of(2, -1, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_CHANGE", "S_REMOVE_C_edgeCB_edge_CHANGE_r2B")
   				.build(),
   				"n > 0 and k = l"),
   				
		//n = m
			
			//First set
			Arguments.of(1, 1, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l < *"),

			Arguments.of(1, 1, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l < *"),   			
   			
   			Arguments.of(1, 1, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l < *"),
   				
   			Arguments.of(1, 1, 2, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l < *"),
   					
			Arguments.of(1, 1, 0, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l = *"),

			Arguments.of(1, 1, 1, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l = *"),   			
   			
   			Arguments.of(1, 1, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l = *"),
   				
   			Arguments.of(1, 1, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and k = l"),

   			Arguments.of(1, 1, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and k = l"),
   				
			
			//Second set
			Arguments.of(2, 2, 0, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l < *"),

			Arguments.of(2, 2, 0, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l < *"),   			
   			
   			Arguments.of(2, 2, 1, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l < *"),
   				
   			Arguments.of(2, 2, 2, 5, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l < *"),
   					
			Arguments.of(2, 2, 0, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l = *"),

			Arguments.of(2, 2, 1, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l = *"),   			
   			
   			Arguments.of(2, 2, 2, -1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and l = *"),
   				
   			Arguments.of(2, 2, 1, 1, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and k = l"),

   			Arguments.of(2, 2, 2, 2, 
				ImmutableMap.<String, String>builder()
				.put("P_REMOVE_C_edgeCB_edge_REMOVE", "P_REMOVE_C_edgeCB_edge_REMOVE")
				.put("S_REMOVE_C_edgeCB_edge_SWAP", "S_REMOVE_C_edgeCB_edge_SWAP")
				.build(),
   				"n = m and k = l")
		);
	}
	
	
	@ParameterizedTest(name = "{index} => Case: {5}  Values: n={0}, m={1}  k={2}, l={3}")
	@MethodSource("removeEdgeMultiplicities")
	@DisplayName("Test remove edge rules and repairs generation")
	def void assertThatTheCorrectRemoveEdgeRulesAreGeneratedForGivenMultiplicities(int n, int m, int k, int l, Map<String, String> rules, String description) {
					
		//Original metamodel with 0..* 0..* multiplicities	
		this.multiplicities.add(new Multiplicity("B", "edgeBC", k, l, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", n, m, this.metamodel));
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val List<RuleSpec> ruleSpecs = #[new RuleSpec("C", "REMOVE")]
		
		val rulesGenerator = new RulesGenerator(this.metamodel,  this.multiplicities, ruleSpecs)
		
		val createdRules = rulesGenerator.generateRules.values.fold(new ArrayList<Module>, [list, modules | 
			list.addAll(modules) return list
		]) 
		
		outputRules(createdRules, rules, "REMOVE", n, m, k, l);
		
		createdRules.forEach[module |
			
			var ruleName = rules.get(module.name)	
			var expectedRule = loadModule(this.metamodelLoader, ruleName)
			assertTrue(isEqual(expectedRule, module), String.format("Assert that %s rule is as expected with %s", module.name, ruleName))
		]
	
		assertEquals(rules.keySet.length, createdRules.length, "The expected number of rules and repairs has been generated.")
	}
	
	
	def void outputRules(List<Module> createdRules, Map<String, String> rules, String action, int n, int m, int k, int l) {
		
		val outputPath = String.format("src/resources/expected/new/%s/A-node-n-%s-m-%s-k-%s-l-%s/", action, n, m, k, l);
		
		createdRules.forEach[ module, index |
			
			var expectedModuleName = if(!rules.containsKey(module.name)) module.name else rules.get(module.name)
			
			writeModel(module, outputPath, expectedModuleName + ".henshin");
		]
	}

}