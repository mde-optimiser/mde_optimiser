package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions.MultiplicityException;

public class Multiplicity {

	public Integer lower;
	public Integer upper;
	public String source;
	public String edge;
	public EReference eReference;
	public EPackage metamodel;

	public Multiplicity(String sourceNode, String edge, Integer lower, Integer upper, EPackage metamodel) {

		this.metamodel = metamodel;

		this.source = sourceNode;
		this.edge = edge;

		if (!checkMultiplicityRangeValidity(lower, upper)) {
			throw new MultiplicityException("Invalid multiplicity values given.", lower, upper);
		}

		setLower(lower);
		setUpper(upper);
	}

	public Multiplicity(EReference edge, EPackage metamodel) {
		this(edge.getEType().getName(), edge.getName(), edge.getLowerBound(), edge.getUpperBound(), metamodel);
		this.eReference = edge;
	}

  public EReference getEReference() {

		if (this.eReference == null) {
			// TODO This must be possible to do in a simpler way
			var nodeClassifier = metamodel.getEClassifiers().stream().filter (classifier ->
        !((EClass) classifier).getEAllReferences().stream().filter(ref ->
                ref.getName().equals(edge) && ref.getEContainingClass().getName().equals(this.source)
        ).findAny().isEmpty()).findFirst().get();

			this.eReference = ((EClass) nodeClassifier).getEAllReferences()
              .stream()
              .filter(reference -> reference.getName().equals(edge)
                      && reference.getEContainingClass().getName().equals(this.source))
              .findFirst().get();
		}

		return this.eReference;
	}

  public EClassifier sourceNode() {
		return this.getEReference().getEContainingClass();
	}

  public EClassifier targetNode() {
		return this.getEReference().getEType();
	}

	public static boolean checkMultiplicityRangeValidity(EReference edge) {
		return checkMultiplicityRangeValidity(edge.getLowerBound(), edge.getUpperBound());
	}

	public static boolean checkMultiplicityRangeValidity(Integer lower, Integer upper) {

		// 0..n case
		if ((lower == 0 && upper > lower)) {
			return true;
		}

		// 1,n..m case case
		if (lower > 0 && upper >= lower) {
			return true;
		}

		// 0,1,n..* case
		if (lower > -1 && upper == -1) {
			return true;
		}

		return false;
	}

	private void setUpper(Integer upper) {
		this.upper = upper;
	}

	private void setLower(Integer lower) {
		this.lower = lower;
	}

}
