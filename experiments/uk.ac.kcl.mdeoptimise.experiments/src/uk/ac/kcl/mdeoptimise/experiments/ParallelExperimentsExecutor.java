package uk.ac.kcl.mdeoptimise.experiments;

import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.writers.FileWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by alxbrd on 11/25/17.
 */
public class ParallelExperimentsExecutor {


    public static void main(String[] args) {
        Configurator.defaultConfig()
                .writer(new FileWriter("experiments-progress.log"))
                .level(Level.INFO)
                .activate();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 1);
        System.out.println(Runtime.getRuntime().availableProcessors() - 1);

        List<String> configurations = getConfigurations();

        for (int i = 0; i < configurations.size(); i++) {
            executor.execute(new ExperimentRunnable(getBasePath(), configurations.get(i)));
        }

        executor.shutdown();
        System.out.println("Done");
    }

    public static String getBasePath(){
        String myCurrentDir = System.getProperty("user.dir");
        return myCurrentDir;
    }

    public static List<String> getConfigurations(){
        File folder = new File("src/models/configurations/");
        List<String> configurations = new ArrayList<String>();

        File[] configurationFiles = folder.listFiles();

        for(int i = 0; i < configurationFiles.length; i++){
            configurations.add(configurationFiles[i].getAbsolutePath());
        }

        return configurations;
    }

}
