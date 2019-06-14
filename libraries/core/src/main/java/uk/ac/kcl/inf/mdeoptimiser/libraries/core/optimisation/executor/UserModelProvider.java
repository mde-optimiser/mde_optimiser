package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IModelInitialiser;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IModelProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class UserModelProvider implements IModelProvider {

  String modelPath;
  HenshinResourceSet resourceSet;
  IModelInitialiser modelInitialiser;

  public UserModelProvider(HenshinResourceSet resourceSet, String userModelPath) {
    this.modelPath = userModelPath;
    this.resourceSet = resourceSet;
  }

  public UserModelProvider(
      IModelInitialiser modelInitialiser, HenshinResourceSet resourceSet, String userModelPath) {
    this(resourceSet, userModelPath);
    this.modelInitialiser = modelInitialiser;
  }

  public Solution loadModel(String path) {
    var resource = resourceSet.createResource(path);
    try {
      resource.load(Collections.EMPTY_MAP);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Unable to load model from: " + path);
    }
    resource.getAllContents().next();

    /**
     * If there is a model initialiser, run it on models after they have been loaded from the
     * resource set.
     */
    if (this.modelInitialiser != null) {
      return modelInitialiser.initialise(resource.getAllContents().next());
    }

    return new Solution(resource.getAllContents().next(), new LinkedList<String>());
  }

  public Iterator<Solution> initialModels(EPackage metamodel) {
    resourceSet.getPackageRegistry().put(metamodel.getNsURI(), metamodel);

    // TODO This seems weird - probably a bug
    if (this.modelInitialiser != null) {

      return Lists.newArrayList(loadModel(modelPath)).iterator();
    }

    return Lists.newArrayList(loadModel(modelPath)).iterator();
  }
}
