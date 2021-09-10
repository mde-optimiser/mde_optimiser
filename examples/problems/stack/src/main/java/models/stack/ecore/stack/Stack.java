/** */
package models.stack.ecore.stack;

import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stack</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.stack.ecore.stack.Stack#getId <em>Id</em>}
 *   <li>{@link models.stack.ecore.stack.Stack#getLoad <em>Load</em>}
 *   <li>{@link models.stack.ecore.stack.Stack#getLeft <em>Left</em>}
 *   <li>{@link models.stack.ecore.stack.Stack#getRight <em>Right</em>}
 * </ul>
 *
 * @see models.stack.ecore.stack.StackPackage#getStack()
 * @model
 * @generated
 */
public interface Stack extends EObject {
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see models.stack.ecore.stack.StackPackage#getStack_Id()
   * @model required="true"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link models.stack.ecore.stack.Stack#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Load</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Load</em>' attribute.
   * @see #setLoad(int)
   * @see models.stack.ecore.stack.StackPackage#getStack_Load()
   * @model required="true"
   * @generated
   */
  int getLoad();

  /**
   * Sets the value of the '{@link models.stack.ecore.stack.Stack#getLoad <em>Load</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Load</em>' attribute.
   * @see #getLoad()
   * @generated
   */
  void setLoad(int value);

  /**
   * Returns the value of the '<em><b>Left</b></em>' reference. It is bidirectional and its opposite
   * is '{@link models.stack.ecore.stack.Stack#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Left</em>' reference.
   * @see #setLeft(Stack)
   * @see models.stack.ecore.stack.StackPackage#getStack_Left()
   * @see models.stack.ecore.stack.Stack#getRight
   * @model opposite="right" required="true"
   * @generated
   */
  Stack getLeft();

  /**
   * Sets the value of the '{@link models.stack.ecore.stack.Stack#getLeft <em>Left</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Left</em>' reference.
   * @see #getLeft()
   * @generated
   */
  void setLeft(Stack value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' reference. It is bidirectional and its
   * opposite is '{@link models.stack.ecore.stack.Stack#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Right</em>' reference.
   * @see #setRight(Stack)
   * @see models.stack.ecore.stack.StackPackage#getStack_Right()
   * @see models.stack.ecore.stack.Stack#getLeft
   * @model opposite="left" required="true"
   * @generated
   */
  Stack getRight();

  /**
   * Sets the value of the '{@link models.stack.ecore.stack.Stack#getRight <em>Right</em>}'
   * reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Right</em>' reference.
   * @see #getRight()
   * @generated
   */
  void setRight(Stack value);
} // Stack
