package uk.ac.kcl.mdeoptimise.experiments;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.pmw.tinylog.Logger;

public class ExperimentRunnable implements Runnable {

    private String projectPath;
    private String moptPath;

    ExperimentRunnable(String projectPath, String moptPath){
        this.projectPath = projectPath;
        this.moptPath = moptPath;
    }

    public void run() {
        System.out.println("Running experiment");
        Logger.info("Starting experiment: " + projectPath + " " + moptPath);
        try {
            Logger.info(String.format("Running configuration %s", moptPath));

            String line = String.format("java -jar lib/mdeo.jar %s %s", projectPath, moptPath);

            Logger.info(String.format("Running command: %s", line));

            CommandLine cmdLine = CommandLine.parse(line);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            Logger.info("Running command return value: " + exitValue);
            executor.getWatchdog().destroyProcess();

        } catch(Exception e) {
            Logger.info("Encountered exception: " + e.getMessage());
            Logger.info(e.getStackTrace());
        }
    }
}
