package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils

import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import java.util.List
import org.eclipse.emf.henshin.model.Rule

final class EqualityHelper extends EcoreUtil.EqualityHelper {

	override protected haveEqualReference(EObject eObject1, EObject eObject2, EReference reference) {

		val Object value1 = eObject1.eGet(reference);

		val Object value2 = eObject2.eGet(reference);

		if (reference.many) {
			equalsUnordered(value1 as List<Rule>, value2 as List<Rule>)
		} else {
			equals(value1 as EObject, value2 as EObject)
		}
	}

	def equalsUnordered(List<Rule> l1, List<Rule> l2) {

		(l1.size == l2.size) && l1.forall[eo|l2.exists[eo2|equals(eo, eo2)]] && l2.forall [eo |
			l1.exists[eo2|equals(eo, eo2)]
		]
	}
}
