/**
 * generated by Xtext 2.14.0
 */
package uk.ac.kcl.mdeoptimise;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Initialiser Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.kcl.mdeoptimise.ModelInitialiserSpec#getInitialiser <em>Initialiser</em>}</li>
 * </ul>
 *
 * @see uk.ac.kcl.mdeoptimise.MdeoptimisePackage#getModelInitialiserSpec()
 * @model
 * @generated
 */
public interface ModelInitialiserSpec extends EObject
{
  /**
   * Returns the value of the '<em><b>Initialiser</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initialiser</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initialiser</em>' attribute.
   * @see #setInitialiser(String)
   * @see uk.ac.kcl.mdeoptimise.MdeoptimisePackage#getModelInitialiserSpec_Initialiser()
   * @model
   * @generated
   */
  String getInitialiser();

  /**
   * Sets the value of the '{@link uk.ac.kcl.mdeoptimise.ModelInitialiserSpec#getInitialiser <em>Initialiser</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Initialiser</em>' attribute.
   * @see #getInitialiser()
   * @generated
   */
  void setInitialiser(String value);

} // ModelInitialiserSpec