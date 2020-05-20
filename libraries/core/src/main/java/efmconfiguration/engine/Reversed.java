package efmconfiguration.engine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * cc by-sa 4.0; via
 * https://stackoverflow.com/questions/1098117/can-one-do-a-for-each-loop-in-java-in-reverse-order
 * 
 * @author Vsevolod Golovanov
 *
 */
public class Reversed<T> implements Iterable<T> {
	private final List<T> original;

	public Reversed(Collection<T> original) {
		this.original = new ArrayList<T>(original);
	}

	public Iterator<T> iterator() {
		final ListIterator<T> i = original.listIterator(original.size());

		return new Iterator<T>() {
			public boolean hasNext() {
				return i.hasPrevious();
			}

			public T next() {
				return i.previous();
			}

			public void remove() {
				i.remove();
			}
		};
	}

	public static <T> Reversed<T> reversed(Collection<T> original) {
		return new Reversed<T>(original);
	}
}