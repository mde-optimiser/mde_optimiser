/** */
package models.cra.fitness.architectureCRA;

import org.eclipse.emf.common.util.EList;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.cra.fitness.architectureCRA.ClassModel#getClasses <em>Classes</em>}
 *   <li>{@link models.cra.fitness.architectureCRA.ClassModel#getFeatures <em>Features</em>}
 * </ul>
 *
 * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getClassModel()
 * @model
 * @generated
 */
public interface ClassModel extends NamedElement {
  /**
   * Returns the value of the '<em><b>Classes</b></em>' containment reference list. The list
   * contents are of type {@link models.cra.fitness.architectureCRA.Class}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Classes</em>' containment reference list.
   * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getClassModel_Classes()
   * @model containment="true" ordered="false"
   * @generated
   */
  EList<models.cra.fitness.architectureCRA.Class> getClasses();

  /**
   * Returns the value of the '<em><b>Features</b></em>' containment reference list. The list
   * contents are of type {@link models.cra.fitness.architectureCRA.Feature}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Features</em>' containment reference list.
   * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getClassModel_Features()
   * @model containment="true" ordered="false"
   * @generated
   */
  EList<Feature> getFeatures();
} // ClassModel
