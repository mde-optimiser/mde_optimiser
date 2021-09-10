package efmconfiguration.engine;

import static efmconfiguration.engine.Reversed.reversed;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import org.eclipse.emf.henshin.model.Node;

public class CombinationProvider {

  /**
   * Increment "node2pointer". Finds the first element that can be incremented (starting from the
   * end, proceeding in reverse order). Increments this element and sets the indexes of subsequent
   * elements to zero.
   *
   * @return True if the incrementing succeeded, false if it was not possible
   */
  public static boolean countOneUp(
      MatchingInfo bindingInfo, LinkedHashMap<Node, Integer> node2pointer) {
    Node incrementableElement = null;
    for (Entry<Node, Integer> entry : reversed(node2pointer.entrySet())) {
      Node n = entry.getKey();
      int cur = entry.getValue();
      int max = bindingInfo.abstract2concrete.get(n).size();

      if (cur + 1 < max) {
        incrementableElement = n;
        break;
      }
    }

    if (incrementableElement == null) {
      return false;
    } else {
      // do the incrementing
      boolean nulling = false;
      for (Entry<Node, Integer> entry : node2pointer.entrySet()) {
        if (entry.getKey() == incrementableElement) {
          entry.setValue(entry.getValue() + 1);
          nulling = true;
        } else if (nulling) {
          entry.setValue(0);
        }
      }
      return true;
    }
  }

  /**
   * Assign a random, unused combination.
   *
   * @return True if a new random assignment was set, false if that was not possible
   */
  public static boolean getRandomAssignment(
      MatchingInfo bindingInfo,
      LinkedHashMap<Node, Integer> node2pointer,
      long possibleCombinations,
      Set<String> usedCombinations) {
    if (usedCombinations.isEmpty()) {
      usedCombinations.add(encode(node2pointer));
    }

    while (usedCombinations.size() < possibleCombinations) {
      for (Entry<Node, Integer> entry : node2pointer.entrySet()) {
        entry.setValue(getRandomPosition(bindingInfo, entry.getKey()));
      }

      String encoded = encode(node2pointer);
      if (!usedCombinations.contains(encoded)) {
        usedCombinations.add(encoded);
        return true;
      }
    }
    return false;
  }

  public static int getRandomPosition(MatchingInfo bindingInfo, Node node) {
    int max = bindingInfo.abstract2concrete.get(node).size();
    int newValue = (int) (Math.floor(Math.random() * max));
    return newValue;
  }

  private static String encode(LinkedHashMap<Node, Integer> node2pointer) {
    StringBuilder encoded = new StringBuilder();
    node2pointer.values().forEach(v -> encoded.append(v + " "));
    return encoded.toString();
  }
}
