package models.argumentation.fitness.tests

import org.junit.Test
import models.argumentation.fitness.EcoreScenarioParser

import static org.junit.Assert.*;
import argumentation.scenario.generator.ScenarioGenerator
import argumentation.model.generator.ScenarioModelGenerator
import org.junit.Before
import org.eclipse.emf.ecore.EObject
import java.util.List
import org.eclipse.emf.common.util.EList
import java.util.ArrayList
import argumentation.scenario.generator.CycleGenerator
import java.util.LinkedList
import net.sf.tweety.arg.dung.syntax.Argument
import java.util.Collections

class EcoreScenarioParserTests {

	private EObject inputModel;

	@Before
	def void beforeTest() {
		this.inputModel = new TestModelLoader().loadModel("scenario-parser-test-input.xmi")
	}

	@Test
	def void assertThatTheGeneratedScenarioPAFsAndTheParsedScenarioPAFsHaveTheSameAttacks() {

		var graphGenerator = new CycleGenerator();
		var scenarioGenerator = new ScenarioGenerator(graphGenerator.generate(11));
		var scenario = scenarioGenerator.generate(1, 9);

		val scenarioModelGenerator = new ScenarioModelGenerator(scenario)

		val parsedAttacks = scenarioModelGenerator.getParsedScenario.result.getList("hasArgument").fold(0) [ result, argument |
			return result + argument.getList("attacks").size
		]

		val scenarioParser = new EcoreScenarioParser(scenarioModelGenerator.getParsedScenario.result);

		val parsedFrameworkArguments = new LinkedList<Argument>()

		scenarioParser.argumentationFrameworkArguments.forEach [ p1, p2 |
			parsedFrameworkArguments.add(p2);
		]

		var scenarioArguments = scenario.persuadersFramework.nodes.filter[node|node instanceof Argument].toList;

		Collections.sort(scenarioArguments, new ArgumentsComparator);
		Collections.sort(parsedFrameworkArguments, new ArgumentsComparator)

		assertEquals(
			"Assert that scenario and parsed scenario have the same number of attacks",
			scenario.persuadersFramework.attacks.size,
			parsedAttacks
		)

		assertEquals("Assert that scenario paf attacks are the same in the parsed scenario",
			scenario.persuadeeArgumentationFrameworks.head,
			scenarioParser.persuadeeArgumentationFrameworksFromSolution.head)

			assertEquals(
				"Assert that scenario arguments are the same",
				scenarioArguments,
				parsedFrameworkArguments
			)
		}

		@Test
		def void assertThatTheCorrectNumberOfArgumentationFrameworkArgumentsAreReturned() {

			val scenarioParser = new EcoreScenarioParser(this.inputModel);

			assertEquals(200, scenarioParser.argumentationFrameworkArguments.size)
		}

		@Test
		def void assertThatTheCorrectNumberOfArgumentsPutForwardAreReturned() {

			var scenarioParser = new EcoreScenarioParser(this.inputModel);

			var argumentationFrameworkArguments = scenarioParser.getArgumentationFrameworkArguments()

			var argumentsPutForward = scenarioParser.getArgumentsPutForward(argumentationFrameworkArguments)

			assertEquals(1, argumentsPutForward.size)
		}

		@Test
		def void assertThatTheCorrectNumberOfPersuadeeArgumentationFrameworksIsReturned() {

			var scenarioParser = new EcoreScenarioParser(this.inputModel);

			var persuadeeArgumentationFrameworks = scenarioParser.getPersuadeeArgumentationFrameworksFromSolution()

			assertEquals(50, persuadeeArgumentationFrameworks.size)
		}

		@Test
		def void assertThatTheCorrectTopicArgumentIsReturned() {

			var scenarioParser = new EcoreScenarioParser(this.inputModel);

			var topic = scenarioParser.topic

			assertEquals("a159", topic.name)

		}

		@Test
		def void assertThatTheCorrectNumberOfAcceptableTopicsIsReturnedWithPreferredReasoner() {

			var scenarioParser = new EcoreScenarioParser(inputModel);

			var acceptableTopics = scenarioParser.countAcceptableTopicsArgMatSat

			assertEquals(21, acceptableTopics, 0)
		}


		@Test
		def void assertThatTheNumberOfAcceptableTopicsIsAsExpectedForSpecifiedModel(){
			
			
			this.inputModel = new TestModelLoader().loadModel("C2-032A72C4.xmi")
			
			var scenarioParser = new EcoreScenarioParser(this.inputModel)
			
			var acceptableTopics = scenarioParser.countAcceptableTopicsArgMatSat
			
			assertEquals(21, acceptableTopics, 0)
		}
		
		@Test
		def void assertThatTheNumberOfAcceptableTopicsIsAsExpectedForSpecifiedModelInputModel(){
			
			
			this.inputModel = new TestModelLoader().loadModel("C2-PF-Ladder-51-args-Audience-10-Members-PAF-size-40-args.xmi")
			
			var scenarioParser = new EcoreScenarioParser(this.inputModel)
			
			var acceptableTopics = scenarioParser.countAcceptableTopicsArgMatSat
			
			assertEquals(21, acceptableTopics, 0)
		}
		
		

		@Test
		def void assertThatTheCorrectNumberOfAcceptableTopicsIsReturnedWithGroundReasoner() {

			var scenarioParser = new EcoreScenarioParser(inputModel);

			var acceptableTopics = scenarioParser.countAcceptableTopicsGroundReasoner

			assertEquals(7, acceptableTopics, 0)
		}

		@Test
		def void assertThatGetTopicReturnsTheCorrectTopic() {

			val scenarioGenerator = new ScenarioGenerator();
			val scenario = scenarioGenerator.generate(10, 20);

			val scenarioModel = new ScenarioModelGenerator(scenario);

			val scenarioParser = new EcoreScenarioParser(scenarioModel.getParsedScenario.result);

			assertEquals(
				"Assert that parser returns the correct topic",
				scenario.topic.name,
				scenarioParser.topic.name
			)
		}

		@Test
		def void assertThatTheGeneratedEObjectHasTheSameNumberOfAttacksAsTheScenario() {

			var scenarioGenerator = new ScenarioGenerator();

			var scenario = scenarioGenerator.generate(25, 20);

			val scenarioModelGenerator = new ScenarioModelGenerator(scenario)

			val parsedAttacks = scenarioModelGenerator.getParsedScenario.result.getList("hasArgument").fold(0) [ result, argument |
				return result + argument.getList("attacks").size

			]

			assertEquals(
				"Assert that scenario and parsed scenario have the same number of attacks",
				scenario.persuadersFramework.attacks.size,
				parsedAttacks
			)
		}
		
		@Test
		def void assertThatGeneratedScenarioAndParsedScenarioAreIdentical(){
			
			
			val scenarioGenerator = new ScenarioGenerator();
			val scenario = scenarioGenerator.generate(10, 10);

			val scenarioModel = new ScenarioModelGenerator(scenario);

			val scenarioParser = new EcoreScenarioParser(scenarioModel.getParsedScenario.result);
			
			val parsedScenario = scenarioParser.scenario
			
			assertEquals("Generated and parsed scenarios are the same", scenario.toString, parsedScenario.toString)
			
		}

		@Test
		def void asserThatTheParsedArgumentationFrameworksAreAsDefinedInTheScenario() {

			val scenarioGenerator = new ScenarioGenerator();
			val scenario = scenarioGenerator.generate(10, 10);

			val scenarioModel = new ScenarioModelGenerator(scenario);

			val scenarioParser = new EcoreScenarioParser(scenarioModel.getParsedScenario.result);

			assertEquals(
				"Assert that the parsed scenario model has the same audience size as the scenario",
				scenario.persuadeeArgumentationFrameworks.size,
				scenarioParser.persuadeeArgumentationFrameworksFromSolution.size
			)

			assertEquals(
				"Assert that the parsed scenario model has the same persuader framework size as the scenario",
				scenario.persuadersFramework.size,
				scenarioParser.argumentationFrameworkArguments.size
			)

			assertEquals(
				"Assert that the parsed scenario model has the same topic as the scenario",
				scenario.topic,
				scenarioParser.topic
			)

			val scenarioPersuadeeArgumentationFrameworks = scenario.persuadeeArgumentationFrameworks;
			val scenarioParserPersuadeeArgumentationFrameworks = scenarioParser.
				persuadeeArgumentationFrameworksFromSolution

			scenarioPersuadeeArgumentationFrameworks.forEach [ scenarioPersuadeeArgumentationFramework, index |

				val parsedPersuadeeArgumentationFramework = scenarioParserPersuadeeArgumentationFrameworks.get(index)

				val scenarioAttacks = scenarioPersuadeeArgumentationFramework.attacks

				val parsedModelAttacks = parsedPersuadeeArgumentationFramework.attacks

				assertEquals(
					String.format(
						"Assert that attacks for framework %s are the same between scenario and parsed model: %s - %s",
						index, scenarioPersuadeeArgumentationFramework.attacks,
						parsedPersuadeeArgumentationFramework.attacks), scenarioAttacks, parsedModelAttacks)

				]

				assertEquals(
					"Assert that the parsed persuadee argumentation framework are the same as the ones in the scenario",
					scenario.persuadeeArgumentationFrameworks,
					scenarioParser.persuadeeArgumentationFrameworksFromSolution
				)

			}

			/**
			 * Helper function getting the value of the named feature (if it exists) for the given EObject.
			 */
			def Object getFeature(EObject o, String feature) {

				if (o === null) {
					println(String.format("Null object given %s", feature));
					return null;
				}

				o.eGet(o.eClass.getEStructuralFeature(feature))

			}

			def EObject getObject(EObject o, String feature) {

				var object = getFeature(o, feature)

				if (object !== null) {
					return object as EObject;
				}

				null;
			}

			def List<EObject> getList(EObject o, String feature) {

				var object = getFeature(o, feature)

				if (object !== null) {
					return object as EList<EObject>;
				}

				new ArrayList<EObject>();
			}

			def String getString(EObject o, String feature) {

				var object = getFeature(o, feature)

				if (object !== null) {
					return object as String;
				}

				null;
			}
		}
		