/** */
package models.nrp.nextReleaseProblem;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.SoftwareArtifact#getName <em>Name</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.SoftwareArtifact#getRequires <em>Requires</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.SoftwareArtifact#getCosts <em>Costs</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.SoftwareArtifact#getContributesTo <em>Contributes
 *       To</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.SoftwareArtifact#getSolutions <em>Solutions</em>}
 * </ul>
 *
 * @see models.nrp.nextReleaseProblem.EcorePackage#getSoftwareArtifact()
 * @model
 * @generated
 */
public interface SoftwareArtifact extends EObject {
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see models.nrp.nextReleaseProblem.EcorePackage#getSoftwareArtifact_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link models.nrp.nextReleaseProblem.SoftwareArtifact#getName
   * <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Requires</b></em>' reference list. The list contents are of
   * type {@link models.nrp.nextReleaseProblem.SoftwareArtifact}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Requires</em>' reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getSoftwareArtifact_Requires()
   * @model
   * @generated
   */
  EList<SoftwareArtifact> getRequires();

  /**
   * Returns the value of the '<em><b>Costs</b></em>' containment reference list. The list contents
   * are of type {@link models.nrp.nextReleaseProblem.Cost}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Costs</em>' containment reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getSoftwareArtifact_Costs()
   * @model containment="true" required="true"
   * @generated
   */
  EList<Cost> getCosts();

  /**
   * Returns the value of the '<em><b>Contributes To</b></em>' reference list. The list contents are
   * of type {@link models.nrp.nextReleaseProblem.RequirementRealisation}. It is bidirectional and
   * its opposite is '{@link models.nrp.nextReleaseProblem.RequirementRealisation#getDependsOn
   * <em>Depends On</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Contributes To</em>' reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getSoftwareArtifact_ContributesTo()
   * @see models.nrp.nextReleaseProblem.RequirementRealisation#getDependsOn
   * @model opposite="dependsOn" required="true"
   * @generated
   */
  EList<RequirementRealisation> getContributesTo();

  /**
   * Returns the value of the '<em><b>Solutions</b></em>' reference list. The list contents are of
   * type {@link models.nrp.nextReleaseProblem.Solution}. It is bidirectional and its opposite is
   * '{@link models.nrp.nextReleaseProblem.Solution#getSelectedArtifacts <em>Selected
   * Artifacts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Solutions</em>' reference list.
   * @see models.nrp.nextReleaseProblem.EcorePackage#getSoftwareArtifact_Solutions()
   * @see models.nrp.nextReleaseProblem.Solution#getSelectedArtifacts
   * @model opposite="selectedArtifacts"
   * @generated
   */
  EList<Solution> getSolutions();
} // SoftwareArtifact
