package models.knapsack.generator;

import java.util.AbstractMap;
import java.util.HashMap;
import models.knapsack.ecore.EcoreFactory;
import models.knapsack.ecore.Item;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.ResourceSetWrapper;

public class InstanceGenerator {

  ResourceSetWrapper resourceSet;

  public InstanceGenerator() {

    this.resourceSet = new ResourceSetWrapper("src/main/resources/");
  }

  public static void main(String args[]) {
    var app = new InstanceGenerator();
    app.generateCSPLibInstance();
  }

  /**
   * Generate an example instance of this model:
   * http://csplib.org/Problems/prob133/data/sample.param.html
   */
  public void generateCSPLibInstance() {

    var plan = EcoreFactory.eINSTANCE.createPlan();

    // Create a knapsack with capacity 100
    var knapsack = EcoreFactory.eINSTANCE.createKnapsack();
    knapsack.setCapacity(100d);

    plan.getKnapsacks().add(knapsack);

    // Create 5 items a, b, c, d, e
    // Map<Item, Tuple<Gain, Weight>>
    var itemsGain = new HashMap<String, AbstractMap.SimpleEntry<Double, Double>>();

    itemsGain.put("a", new AbstractMap.SimpleEntry<>(10d, 15d));
    itemsGain.put("b", new AbstractMap.SimpleEntry<>(20d, 25d));
    itemsGain.put("c", new AbstractMap.SimpleEntry<>(40d, 45d));
    itemsGain.put("d", new AbstractMap.SimpleEntry<>(40d, 50d));
    itemsGain.put("e", new AbstractMap.SimpleEntry<>(50d, 60d));

    itemsGain.keySet().stream()
        .forEach(
            name -> {
              plan.getItems()
                  .add(
                      makeItem(name, itemsGain.get(name).getKey(), itemsGain.get(name).getValue()));
            });

    this.resourceSet.writeModel(plan, "csplib-example.xmi");
  }

  public Item makeItem(String name, Double gain, Double weight) {

    var item = EcoreFactory.eINSTANCE.createItem();
    item.setName(name);
    item.setGain(gain);
    item.setWeight(weight);

    return item;
  }
}
