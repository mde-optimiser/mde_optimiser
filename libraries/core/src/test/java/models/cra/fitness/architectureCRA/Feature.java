/** */
package models.cra.fitness.architectureCRA;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.cra.fitness.architectureCRA.Feature#getIsEncapsulatedBy <em>Is Encapsulated
 *       By</em>}
 * </ul>
 *
 * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getFeature()
 * @model abstract="true"
 * @generated
 */
public interface Feature extends NamedElement {
  /**
   * Returns the value of the '<em><b>Is Encapsulated By</b></em>' reference. It is bidirectional
   * and its opposite is '{@link models.cra.fitness.architectureCRA.Class#getEncapsulates
   * <em>Encapsulates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Is Encapsulated By</em>' reference.
   * @see #setIsEncapsulatedBy(models.cra.fitness.architectureCRA.Class)
   * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage#getFeature_IsEncapsulatedBy()
   * @see models.cra.fitness.architectureCRA.Class#getEncapsulates
   * @model opposite="encapsulates"
   * @generated
   */
  models.cra.fitness.architectureCRA.Class getIsEncapsulatedBy();

  /**
   * Sets the value of the '{@link models.cra.fitness.architectureCRA.Feature#getIsEncapsulatedBy
   * <em>Is Encapsulated By</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Is Encapsulated By</em>' reference.
   * @see #getIsEncapsulatedBy()
   * @generated
   */
  void setIsEncapsulatedBy(models.cra.fitness.architectureCRA.Class value);
} // Feature
