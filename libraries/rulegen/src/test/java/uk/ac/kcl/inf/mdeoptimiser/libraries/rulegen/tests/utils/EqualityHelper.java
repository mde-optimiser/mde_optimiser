package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import java.util.List;
import org.eclipse.emf.henshin.model.Rule;

final class EqualityHelper extends EcoreUtil.EqualityHelper {

//  @Override
//	public boolean haveEqualReference(EObject eObject1, EObject eObject2, EReference reference) {
//
//		Object value1 = eObject1.eGet(reference);
//
//		Object value2 = eObject2.eGet(reference);
//
//		if (reference.isMany()) {
//			equalsUnordered((List<Rule>) value1, (List<Rule>) value2);
//		} else {
//			equals((EObject) value1, (EObject) value2);
//		}
//	}
//
//	public boolean equalsUnordered(List<Rule> l1, List<Rule> l2) {
//
//		(l1.size() == l2.size())
//            && l1.foreach(eo -> l2.stream().findAny(eo2 -> equals(eo, eo2)));
//            && l2.forall [eo | l1.exists[eo2|equals(eo, eo2)]])
//	}
}
