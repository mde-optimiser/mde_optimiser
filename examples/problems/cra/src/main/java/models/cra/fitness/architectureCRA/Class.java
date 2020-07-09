/** */
package models.cra.fitness.architectureCRA;

import org.eclipse.emf.common.util.EList;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.cra.fitness.architectureCRA.Class#getEncapsulates <em>Encapsulates</em>}
 * </ul>
 *
 * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getClass_()
 * @model
 * @generated
 */
public interface Class extends NamedElement {
  /**
   * Returns the value of the '<em><b>Encapsulates</b></em>' reference list. The list contents are
   * of type {@link models.cra.fitness.architectureCRA.Feature}. It is bidirectional and its
   * opposite is '{@link models.cra.fitness.architectureCRA.Feature#getIsEncapsulatedBy <em>Is
   * Encapsulated By</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Encapsulates</em>' reference list.
   * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getClass_Encapsulates()
   * @see models.cra.fitness.architectureCRA.Feature#getIsEncapsulatedBy
   * @model opposite="isEncapsulatedBy" required="true"
   * @generated
   */
  EList<Feature> getEncapsulates();
} // Class
