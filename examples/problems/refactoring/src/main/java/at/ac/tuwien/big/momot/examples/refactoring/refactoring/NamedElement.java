/** */
package at.ac.tuwien.big.momot.examples.refactoring.refactoring;

import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link at.ac.tuwien.big.momot.examples.refactoring.refactoring.NamedElement#getName
 *       <em>Name</em>}
 * </ul>
 *
 * @see at.ac.tuwien.big.momot.examples.refactoring.refactoring.RefactoringPackage#getNamedElement()
 * @model
 * @generated
 */
public interface NamedElement extends EObject {
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   *
   * <p>If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of
   * a description here...
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see
   *     at.ac.tuwien.big.momot.examples.refactoring.refactoring.RefactoringPackage#getNamedElement_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link
   * at.ac.tuwien.big.momot.examples.refactoring.refactoring.NamedElement#getName <em>Name</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);
} // NamedElement
