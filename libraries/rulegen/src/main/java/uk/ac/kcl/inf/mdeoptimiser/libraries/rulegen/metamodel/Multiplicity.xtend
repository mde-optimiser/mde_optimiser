package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions.MultiplicityException

class Multiplicity {

	public Integer lower;
	public Integer upper;
	public String source;
	public String edge;
	EReference eReference;
	public EPackage metamodel;

	new(String sourceNode, String edge, Integer lower, Integer upper, EPackage metamodel) {

		this.metamodel = metamodel;

		this.source = sourceNode;
		this.edge = edge;

		if (!checkMultiplicityRangeValidity(lower, upper)) {
			throw new MultiplicityException("Invalid multiplicity values given.", lower, upper);
		}

		setLower(lower);
		setUpper(upper);
	}

	new(EReference edge, EPackage metamodel) {
		this(edge.EType.name, edge.name, edge.lowerBound, edge.upperBound, metamodel);
		this.eReference = edge;
	}

	def EReference getEReference() {

		if (this.eReference === null) {
			// TODO This must be possible to do in a simpler way
			val nodeClassifier = metamodel.EClassifiers.filter [ classifier |
				(classifier as EClass).EAllReferences.filter [ ref |
					ref.name.equals(edge) && ref.EContainingClass.name.equals(this.source)
				].head !== null
			].head

			this.eReference = (nodeClassifier as EClass).EAllReferences.filter [ reference |
				reference.name.equals(edge) && reference.EContainingClass.name.equals(this.source)
			].head
		}

		return this.eReference;
	}

	def EClassifier sourceNode() {
		return this.getEReference().EContainingClass
	}

	def EClassifier targetNode() {
		return this.getEReference().EType
	}

	static def boolean checkMultiplicityRangeValidity(EReference edge) {
		return checkMultiplicityRangeValidity(edge.lowerBound, edge.upperBound);
	}

	static def boolean checkMultiplicityRangeValidity(Integer lower, Integer upper) {

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

	private def void setUpper(Integer upper) {
		this.upper = upper;
	}

	private def void setLower(Integer lower) {
		this.lower = lower;
	}

}
