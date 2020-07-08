package models.tsp.generator;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import models.tsp.fitness.TSP.City;
import models.tsp.fitness.TSP.TSPFactory;
import models.tsp.fitness.TSP.TSPPackage;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class InstanceGenerator {

  static String TSP_LIB_MODELS = "";
  static Injector injector = Guice.createInjector();
  ResourceSet resourceSet;

  // Register the metamodel
  public static void main(String args[]) {

    var app = injector.getInstance(InstanceGenerator.class);

    try {
      app.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() throws Exception {

    // Do EMF stuff
    this.resourceSet = new ResourceSetImpl();
    // Register the appropriate resource factory to handle all file extensions.
    //
    resourceSet
        .getResourceFactoryRegistry()
        .getExtensionToFactoryMap()
        .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

    // Register the package to ensure it is available during loading.
    //
    resourceSet.getPackageRegistry().put(TSPPackage.eNS_URI, TSPPackage.eINSTANCE);

    var atspModels = this.loadProblemModels();

    for (var model : atspModels) {
      System.out.println(String.format("Processing model: %s", model.getFileName()));
      var size = this.getModelSize(model);
      var matrix = this.getMatrix(model);
      this.generateEcoreModel(size, matrix, model);
    }

    // Load and serialise
    // serialiseModels(readModelsFromCSV());
  }

  /**
   * Generate some nice cities with fake names.
   *
   * @param number
   * @return map of index and cities
   */
  public Map<Integer, City> generateCities(int number) {

    var faker = new Faker();
    var cities = new HashMap<Integer, City>();

    for (int i = 0; i < number; i++) {
      var city = TSPFactory.eINSTANCE.createCity();
      city.setName(faker.address().cityName());
      cities.put(i, city);
    }

    return cities;
  }

  public void generateEcoreModel(int size, List<Integer> matrix, Path model) throws IOException {

    var resource =
        resourceSet.createResource(
            URI.createURI(
                String.format("src/main/resources/models/tsp/%s.xmi", model.getFileName())));
    var region = TSPFactory.eINSTANCE.createRegion();

    var cities = this.generateCities(size);
    region.getCities().addAll(cities.values());

    for (int j = 0; j < matrix.size(); j++) {

      var row = j / size;
      var distanceValue = matrix.get(j);
      var column = j % size;

      if (row != column && distanceValue != 0) {
        var distance = TSPFactory.eINSTANCE.createDistance();
        distance.setFrom(cities.get(row));
        distance.setTo(cities.get(column));
        distance.setDistance(distanceValue.doubleValue());
        region.getDistances().add(distance);
      }
    }

    //    var start = TSPFactory.eINSTANCE.createStop();
    //    start.setLocation(cities.get(0));
    //    region.getStops().add(start);
    //    var start = TSPFactory.eINSTANCE.createStop();
    cities.get(0).setStart(true);

    resource.getContents().add(region);
    resource.save(Collections.EMPTY_MAP);
  }

  public List<Path> loadProblemModels() {

    var files = new ArrayList<Path>();

    try {
      files.addAll(
          Files.list(Path.of("src/main/resources/models/tsplib/")).collect(Collectors.toList()));
    } catch (Exception e) {

    }

    return files;
  }

  public int getModelSize(Path sourceModel) throws IOException {

    int size = 0;

    try (var lines = Files.lines(sourceModel, StandardCharsets.UTF_8)) {
      for (String line : (Iterable<String>) lines::iterator) {
        if (line.contains("DIMENSION")) {
          size = Integer.parseInt(line.replaceAll("[^\\d.]", ""));
        }
      }
    }

    return size;
  }

  public List<Integer> getMatrix(Path sourceModel) throws IOException {
    var fileContents = Files.readString(sourceModel, StandardCharsets.UTF_8);
    var coordinatesString =
        StringUtils.substringBetween(fileContents, "EDGE_WEIGHT_SECTION", "EOF")
            .replaceAll("[^-?0-9]+", " ");
    return Arrays.stream(coordinatesString.trim().split(" "))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }
}
