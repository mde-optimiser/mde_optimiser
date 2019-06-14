package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

public class ResourceSetWrapper {

  private HenshinResourceSet resourceSet;

  public ResourceSetWrapper(HenshinResourceSet resourceSet) {
    this.resourceSet = resourceSet;
  }

  public ResourceSetWrapper() {
    this.resourceSet = new HenshinResourceSet();
  }

  public ResourceSetWrapper(Path path) {
    this.resourceSet = new HenshinResourceSet(path.toAbsolutePath().toString());
  }

  public Resource loadModel(String path) {

    try {

      var resource = resourceSet.createResource(path);
      resource.load(Collections.emptyMap());
      return resource;

    } catch (IOException e) {
      throw new RuntimeException(String.format("Invalid mode path given: %s", path));
    }
  }

  public void writeModel(EObject model, String path) {
    var resource = this.resourceSet.createResource(URI.createFileURI(path));
    if (resource.isLoaded()) {
      resource.getContents().clear();
    }
    resource.getContents().add(model);
    try {
      resource.save(Collections.EMPTY_MAP);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
