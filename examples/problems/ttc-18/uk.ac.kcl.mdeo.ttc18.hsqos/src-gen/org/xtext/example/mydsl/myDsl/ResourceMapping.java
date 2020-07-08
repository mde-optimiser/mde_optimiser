/**
 * generated by Xtext 2.12.0
 */
package org.xtext.example.mydsl.myDsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.myDsl.ResourceMapping#getRequiredResource <em>Required Resource</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.myDsl.ResourceMapping#getUsedResource <em>Used Resource</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.myDsl.ResourceMapping#getSubMappings <em>Sub Mappings</em>}</li>
 * </ul>
 *
 * @see org.xtext.example.mydsl.myDsl.MyDslPackage#getResourceMapping()
 * @model
 * @generated
 */
public interface ResourceMapping extends EObject
{
  /**
   * Returns the value of the '<em><b>Required Resource</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Required Resource</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Required Resource</em>' reference.
   * @see #setRequiredResource(Instance)
   * @see org.xtext.example.mydsl.myDsl.MyDslPackage#getResourceMapping_RequiredResource()
   * @model
   * @generated
   */
  Instance getRequiredResource();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.myDsl.ResourceMapping#getRequiredResource <em>Required Resource</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Required Resource</em>' reference.
   * @see #getRequiredResource()
   * @generated
   */
  void setRequiredResource(Instance value);

  /**
   * Returns the value of the '<em><b>Used Resource</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Used Resource</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Used Resource</em>' reference.
   * @see #setUsedResource(Resource)
   * @see org.xtext.example.mydsl.myDsl.MyDslPackage#getResourceMapping_UsedResource()
   * @model
   * @generated
   */
  Resource getUsedResource();

  /**
   * Sets the value of the '{@link org.xtext.example.mydsl.myDsl.ResourceMapping#getUsedResource <em>Used Resource</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Used Resource</em>' reference.
   * @see #getUsedResource()
   * @generated
   */
  void setUsedResource(Resource value);

  /**
   * Returns the value of the '<em><b>Sub Mappings</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.example.mydsl.myDsl.ResourceMapping}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sub Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Mappings</em>' containment reference list.
   * @see org.xtext.example.mydsl.myDsl.MyDslPackage#getResourceMapping_SubMappings()
   * @model containment="true"
   * @generated
   */
  EList<ResourceMapping> getSubMappings();

} // ResourceMapping