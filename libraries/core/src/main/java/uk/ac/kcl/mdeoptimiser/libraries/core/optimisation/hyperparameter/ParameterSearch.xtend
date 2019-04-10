package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter

import java.util.Properties
import java.util.concurrent.TimeUnit
import org.deeplearning4j.arbiter.optimize.api.termination.MaxCandidatesCondition
import org.deeplearning4j.arbiter.optimize.api.termination.MaxTimeCondition
import org.deeplearning4j.arbiter.optimize.config.OptimizationConfiguration
import org.deeplearning4j.arbiter.optimize.generator.RandomSearchGenerator
import org.deeplearning4j.arbiter.optimize.parameter.integer.IntegerParameterSpace
import org.deeplearning4j.arbiter.optimize.runner.LocalOptimizationRunner
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaOptimisation
import org.deeplearning4j.ui.storage.FileStatsStorage
import org.deeplearning4j.arbiter.ui.listener.ArbiterStatusListener
import org.deeplearning4j.ui.api.UIServer
import java.io.File
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOParameterSpace
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOScoreFunction
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOTaskCreator
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaFrameworkAlgorithmConfiguration
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator
import org.deeplearning4j.arbiter.saver.local.FileModelSaver

class ParameterSearch {
	
	def Properties search(Optimisation optimisationSpec, SolutionGenerator solutionGenerator) {
		
		//Work out what interfaces we need to implement from the arbiter example in BasicHyperparameterOptimizationExample.java
		//https://github.com/deeplearning4j/dl4j-examples/tree/master/dl4j-examples
		//First: Set up the hyperparameter configuration space. This is like a MultiLayerConfiguration, but can have either
        // fixed values or values to optimize, for each hyperparameter
		var evolutionsHyperparam = new IntegerParameterSpace(100, 200)
		var populationSizeHyperparam = new IntegerParameterSpace(10, 30)
		
		var mdeoParameterSpace = new MDEOParameterSpace();
		mdeoParameterSpace.setPopulationSize(populationSizeHyperparam)
		mdeoParameterSpace.setEvolutions(evolutionsHyperparam);
		
		 //Now: We need to define a few configuration options
        // (a) How are we going to generate candidates? (random search or grid search)
		var candidateGenerator = new RandomSearchGenerator(mdeoParameterSpace, null);
		
		// (b) How are going to provide data? We'll use a simple data source that returns MNIST data
        // Note that we set the number of epochs in MultiLayerSpace above
		// We don't need data at the moment, but could be interesting to find best parameters across a
		// number of models and configurations for the same case study?
				
		
		// (c) How we are going to save the models that are generated and tested?
        //     In this example, let's save them to disk the working directory
        //     This will result in examples being saved to arbiterExample/0/, arbiterExample/1/, arbiterExample/2/, ...
//		var baseSaveDirectory = "arbiterExample/";
//        var f = new File(baseSaveDirectory);
//        if (f.exists()) f.delete();
//        f.mkdir();
//        var modelSaver = new FileModelSaver(baseSaveDirectory);

		// (d) What are we actually trying to optimize?
        //     In this example, let's use classification accuracy on the test set
        //     See also ScoreFunctions.testSetF1(), ScoreFunctions.testSetRegression(regressionValue) etc
        var scoreFunction = new MDEOScoreFunction(optimisationSpec);
		
		
		// (e) When should we stop searching? Specify this with termination conditions
        //     For this example, we are stopping the search at 15 minutes or 10 candidates - whichever comes first
        var terminationConditions = #[new MaxCandidatesCondition(10)]
		
		
		//Given these configuration options, let's put them all together:
        var configuration = new OptimizationConfiguration.Builder()
            .candidateGenerator(candidateGenerator)
            .scoreFunction(scoreFunction)
            //.modelSaver(modelSaver)
            .terminationConditions(terminationConditions)
            .build();
		
		//And set up execution locally on this machine:
        var runner = new LocalOptimizationRunner(configuration, new MDEOTaskCreator(optimisationSpec, solutionGenerator));
		
		
		//Start the UI. Arbiter uses the same storage and persistence approach as DL4J's UI
        //Access at http://localhost:9000/arbiter
        var ss = new FileStatsStorage(new File("arbiterExampleUiStats.dl4j"));
        runner.addListeners(new ArbiterStatusListener(ss));
        UIServer.getInstance().attach(ss);


        //Start the hyperparameter optimization
        runner.execute();
        
        
        //Print out some basic stats regarding the optimization procedure
        var s = "Best score: " + runner.bestScore() + "\n" +
            "Index of model with best score: " + runner.bestScoreCandidateIndex() + "\n" +
            "Number of configurations evaluated: " + runner.numCandidatesCompleted() + "\n";
        System.out.println(s);
        
		        //Get all results, and print out details of the best result:
        var indexOfBestResult = runner.bestScoreCandidateIndex();
        var allResults = runner.getResults();

        var bestResult = allResults.get(indexOfBestResult).getResult();
        var bestModel =  bestResult.getResultReference().getResultModel();


        //Wait a while before exiting
        Thread.sleep(60000);
        UIServer.getInstance().stop();
        		
		return null
	}
	
}