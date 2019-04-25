package models.argumentation.fitness.tests

import java.util.Comparator
import net.sf.tweety.arg.dung.syntax.Argument

class ArgumentsComparator implements Comparator<Argument> {
	
	override compare(Argument left, Argument right) {
		return left.name.compareToIgnoreCase(right.name);
	}
}