/** */
package models.nrp.nextReleaseProblem;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.Requirement#getName <em>Name</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.Requirement#getValuations <em>Valuations</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.Requirement#getRealisations <em>Realisations</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.Requirement#getCombines <em>Combines</em>}
 * </ul>
 *
 * @see models.nrp.nextReleaseProblem.EcorePackage#getRequirement()
 * @model
 * @generated
 */
public interface Requirement extends EObject {
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see models.nrp.nextReleaseProblem.EcorePackage#getRequirement_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link models.nrp.nextReleaseProblem.Requirement#getName <em>Name</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Valuations</b></em>' containment reference list. The list
   * contents are of type {@link models.nrp.nextReleaseProblem.Valuation}. It is bidirectional and
   * its opposite is '{@link models.nrp.nextReleaseProblem.Valuation#getRequirement
   * <em>Requirement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Valuations</em>' containment reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getRequirement_Valuations()
   * @see models.nrp.nextReleaseProblem.Valuation#getRequirement
   * @model opposite="requirement" containment="true" required="true"
   * @generated
   */
  EList<Valuation> getValuations();

  /**
   * Returns the value of the '<em><b>Realisations</b></em>' containment reference list. The list
   * contents are of type {@link models.nrp.nextReleaseProblem.RequirementRealisation}. It is
   * bidirectional and its opposite is '{@link
   * models.nrp.nextReleaseProblem.RequirementRealisation#getRequirement <em>Requirement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Realisations</em>' containment reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getRequirement_Realisations()
   * @see models.nrp.nextReleaseProblem.RequirementRealisation#getRequirement
   * @model opposite="requirement" containment="true"
   * @generated
   */
  EList<RequirementRealisation> getRealisations();

  /**
   * Returns the value of the '<em><b>Combines</b></em>' reference list. The list contents are of
   * type {@link models.nrp.nextReleaseProblem.Valuation}. It is bidirectional and its opposite is
   * '{@link models.nrp.nextReleaseProblem.Valuation#getContributesTo <em>Contributes To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Combines</em>' reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getRequirement_Combines()
   * @see models.nrp.nextReleaseProblem.Valuation#getContributesTo
   * @model opposite="contributesTo"
   * @generated
   */
  EList<Valuation> getCombines();
} // Requirement
