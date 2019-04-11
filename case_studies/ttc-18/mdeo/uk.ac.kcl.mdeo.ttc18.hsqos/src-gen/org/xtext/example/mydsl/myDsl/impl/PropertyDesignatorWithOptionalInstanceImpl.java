/**
 * generated by Xtext 2.12.0
 */
package org.xtext.example.mydsl.myDsl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.xtext.example.mydsl.myDsl.Instance;
import org.xtext.example.mydsl.myDsl.MyDslPackage;
import org.xtext.example.mydsl.myDsl.Property;
import org.xtext.example.mydsl.myDsl.PropertyDesignatorWithOptionalInstance;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Designator With Optional Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.myDsl.impl.PropertyDesignatorWithOptionalInstanceImpl#getInstance <em>Instance</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.myDsl.impl.PropertyDesignatorWithOptionalInstanceImpl#getProperty <em>Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyDesignatorWithOptionalInstanceImpl extends MinimalEObjectImpl.Container implements PropertyDesignatorWithOptionalInstance
{
  /**
   * The cached value of the '{@link #getInstance() <em>Instance</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstance()
   * @generated
   * @ordered
   */
  protected Instance instance;

  /**
   * The cached value of the '{@link #getProperty() <em>Property</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProperty()
   * @generated
   * @ordered
   */
  protected Property property;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PropertyDesignatorWithOptionalInstanceImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return MyDslPackage.Literals.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Instance getInstance()
  {
    if (instance != null && instance.eIsProxy())
    {
      InternalEObject oldInstance = (InternalEObject)instance;
      instance = (Instance)eResolveProxy(oldInstance);
      if (instance != oldInstance)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__INSTANCE, oldInstance, instance));
      }
    }
    return instance;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Instance basicGetInstance()
  {
    return instance;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInstance(Instance newInstance)
  {
    Instance oldInstance = instance;
    instance = newInstance;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__INSTANCE, oldInstance, instance));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Property getProperty()
  {
    if (property != null && property.eIsProxy())
    {
      InternalEObject oldProperty = (InternalEObject)property;
      property = (Property)eResolveProxy(oldProperty);
      if (property != oldProperty)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__PROPERTY, oldProperty, property));
      }
    }
    return property;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Property basicGetProperty()
  {
    return property;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProperty(Property newProperty)
  {
    Property oldProperty = property;
    property = newProperty;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__PROPERTY, oldProperty, property));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__INSTANCE:
        if (resolve) return getInstance();
        return basicGetInstance();
      case MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__PROPERTY:
        if (resolve) return getProperty();
        return basicGetProperty();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__INSTANCE:
        setInstance((Instance)newValue);
        return;
      case MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__PROPERTY:
        setProperty((Property)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__INSTANCE:
        setInstance((Instance)null);
        return;
      case MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__PROPERTY:
        setProperty((Property)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__INSTANCE:
        return instance != null;
      case MyDslPackage.PROPERTY_DESIGNATOR_WITH_OPTIONAL_INSTANCE__PROPERTY:
        return property != null;
    }
    return super.eIsSet(featureID);
  }

} //PropertyDesignatorWithOptionalInstanceImpl