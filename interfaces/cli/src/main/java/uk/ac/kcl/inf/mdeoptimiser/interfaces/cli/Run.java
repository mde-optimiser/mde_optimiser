package uk.ac.kcl.inf.mdeoptimiser.interfaces.cli;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import uk.ac.kcl.inf.mdeoptimiser.languages.MoptStandaloneSetup;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.OptimisationInterpreter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOResultsOutput;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.ParameterSearch;

public class Run {

  static Injector injector = new MoptStandaloneSetup().createInjectorAndDoEMFRegistration();

  @Inject Provider<ResourceSet> resourceSetProvider;

  Options commandLineOptions;

  /**
   * Static method invoked by the MDEOptimiser launch configuration
   *
   * @param args configured mopt file path to run the optimisation from
   */
  public static void main(String[] args) {
    var app = injector.getInstance(Run.class);

    var parser = new BasicParser();
    CommandLine command;

    try {

      command = parser.parse(app.getCommandLineOptions(), args);

      var projectPath = command.getOptionValue("projectPath");
      var moptPath = command.getOptionValue("moptPath");

      if (!app.hasValidParameters(projectPath, moptPath)) {
        System.out.println("Invalid parameters provided. Search was not executed.");
        System.out.println();
        app.help();
        System.exit(0);
      }

      if (command.hasOption("batch")) {
        var batchId = Integer.parseInt(command.getOptionValue("batch"));
        app.run(projectPath, moptPath, batchId);
      } else {
        app.run(projectPath, moptPath);
      }

    } catch (ParseException e) {
      app.help();
      System.exit(0);
    }
  }

  public void help() {
    var formatter = new HelpFormatter();

    var header = "Run MOPT specs from the command line.\n\n";
    var footer = "\nPlease report issues at https://github.com/mde-optimiser/mde_optimiser/issues";

    formatter.printHelp("mdeo", header, this.getCommandLineOptions(), footer, true);
  }

  public Options getCommandLineOptions() {

    if (this.commandLineOptions == null) {

      this.commandLineOptions = new Options();

      var projectPath =
          new Option("p", "projectPath", true, "use given path as the root of the tool");
      projectPath.setRequired(true);
      projectPath.setArgs(1);

      var moptPath = new Option("m", "moptPath", true, "use given MOPT configuration file");
      moptPath.setRequired(true);
      moptPath.setArgs(1);

      commandLineOptions.addOption(projectPath);
      commandLineOptions.addOption(moptPath);
      commandLineOptions.addOption("b", "batch", true, "run a single batch with this numeric ID");
    }

    return this.commandLineOptions;
  }

  public void run(String moptProjectPath, String configuredMoptFilePath) {
    this.run(moptProjectPath, configuredMoptFilePath, null);
  }

  public void run(String moptRootPath, String moptFilePath, Integer batch) {

    var optimisationModel = loadOptimisationSpec(moptFilePath);

    if (optimisationModel == null) {
      return;
    }

    var mdeoResultsOutput =
        new MDEOResultsOutput(
            new Date(), Paths.get(moptRootPath), Paths.get(moptFilePath), optimisationModel);

    if (optimisationModel.getSolver().getParameterSearch() != null) {
      runParameterSearch(mdeoResultsOutput, moptRootPath, optimisationModel);
    } else {
      runSearch(mdeoResultsOutput, moptRootPath, optimisationModel, batch);
    }
  }

  public boolean hasValidParameters(String moptRootPath, String moptFilePath) {

    if (moptFilePath == null || moptFilePath.isEmpty()) {
      System.out.println("Received a null or empty mopt file path.");
      return false;
    }

    if (moptRootPath == null || moptRootPath.isEmpty()) {
      System.out.println("Received a null or empty project path.");
      return false;
    }

    return true;
  }

  /**
   * Load the Optimisation spec from the provided mopt file path. Perform basic validation before
   * the loading process is done. TODO Add a syntax checker here to show errors when running MDEO in
   * headless mode
   *
   * @return optimisation spec instance
   */
  public Optimisation loadOptimisationSpec(String moptFilePath) {

    if (resourceSetProvider == null) {
      System.out.println("Empty ResourceSetProvider");

      return null;
    }

    var moptFile = new File(moptFilePath);

    if (!moptFile.exists()) {
      System.out.println("Could not find selected mopt file: " + moptFilePath);

      return null;
    }

    var resource =
        resourceSetProvider.get().getResource(URI.createFileURI(moptFile.getAbsolutePath()), true);
    var optimisationModel = (Optimisation) resource.getContents().get(0);

    if (optimisationModel == null) {
      System.out.println("Could not parse the mopt file. ");

      return null;
    }

    return optimisationModel;
  }

  /**
   * Run the configured parameter search and ignore the other configured search parameters. During
   * this search only the optimised parameters are changed for the configured algorithm, the rest of
   * the parameters are left as defined by the user.
   */
  public void runParameterSearch(
      MDEOResultsOutput mdeoResultsOutput, String moptProjectPath, Optimisation optimisation) {

    var parameterSearch = new ParameterSearch();
    var parameterSearchResult = parameterSearch.search(moptProjectPath, optimisation);

    mdeoResultsOutput.saveParameterSearchOutcome(parameterSearchResult);
  }

  /** Run the configured search either as an entire set of batches or as a single batch */
  public void runSearch(
      MDEOResultsOutput mdeoResultsOutput,
      String moptProjectPath,
      Optimisation optimisationModel,
      Integer batch) {

    if (batch == null) {
      // Run all the batches from the MOPT file
      var experimentId = 0;

      do {
        // TODO Output generated mutation operators for each experiment? or each batch?
        mdeoResultsOutput.logBatch(
            runBatch(moptProjectPath, optimisationModel, experimentId, false));
        experimentId++;

      } while (experimentId < optimisationModel.getSolver().getAlgorithmBatches());

      mdeoResultsOutput.saveOutcome();

    } else {
      // Run a specific batch only
      mdeoResultsOutput.logBatch(runBatch(moptProjectPath, optimisationModel, batch, true));
      mdeoResultsOutput.saveOutcome(batch);
    }
  }

  /**
   * Run a single MDEOptimiser search batch
   *
   * @return single batch outcome
   */
  public MDEOBatch runBatch(
      String moptProjectPath, Optimisation optimisationModel, Integer batch, boolean singleBatch) {

    var optimisationInterpreter = new OptimisationInterpreter(moptProjectPath, optimisationModel);

    var startTime = System.nanoTime();
    var searchResult = optimisationInterpreter.start();
    var endTime = System.nanoTime();

    var experimentDuration = (endTime - startTime) / 1000000;
    var generatedRules = optimisationInterpreter.getSearchSpecification().getRulegenOperators();
    return new MDEOBatch(batch, experimentDuration, searchResult, generatedRules, singleBatch);
  }
}
