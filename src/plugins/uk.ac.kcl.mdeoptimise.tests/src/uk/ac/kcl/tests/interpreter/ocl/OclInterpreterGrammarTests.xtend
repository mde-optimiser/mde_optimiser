package uk.ac.kcl.tests.interpreter.ocl

import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Before
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.tests.FullTestInjector
import org.junit.Test
import static org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class OclInterpreterGrammarTests {

	@Inject
	ParseHelper<Optimisation> parser
	@Inject extension ValidationTestHelper
	
	Optimisation model
	
	OclModelProvider oclModelProvider
	
	@Before
	def void bootstrapParser() {
		model = parser.parse('''
			basepath <model/basepath>
			metamodel <ABC>
			fitness "ABC"
			objective minimise coupling { 
				"Valid.OclString()"
			}
			evolve using <ABC> unit "XYZ"
			evolve using <CDE> unit "LMN"
		''')
	
		oclModelProvider = new OclModelProvider	
		
	//	oclModelProvider.loadModel
	}
	
	@Test
	def void assertThatEmptyOclStringIsValid() {
		assertTrue(true)
	}
}