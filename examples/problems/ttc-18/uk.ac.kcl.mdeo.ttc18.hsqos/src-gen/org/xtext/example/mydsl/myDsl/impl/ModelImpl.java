/**
 * generated by Xtext 2.12.0
 */
package org.xtext.example.mydsl.myDsl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.xtext.example.mydsl.myDsl.HardwareModel;
import org.xtext.example.mydsl.myDsl.Model;
import org.xtext.example.mydsl.myDsl.MyDslPackage;
import org.xtext.example.mydsl.myDsl.Objective;
import org.xtext.example.mydsl.myDsl.Request;
import org.xtext.example.mydsl.myDsl.SoftwareModel;
import org.xtext.example.mydsl.myDsl.Solution;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.example.mydsl.myDsl.impl.ModelImpl#getHardwareModel <em>Hardware Model</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.myDsl.impl.ModelImpl#getSoftwareModel <em>Software Model</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.myDsl.impl.ModelImpl#getRequests <em>Requests</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.myDsl.impl.ModelImpl#getObjective <em>Objective</em>}</li>
 *   <li>{@link org.xtext.example.mydsl.myDsl.impl.ModelImpl#getSolution <em>Solution</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelImpl extends MinimalEObjectImpl.Container implements Model
{
  /**
   * The cached value of the '{@link #getHardwareModel() <em>Hardware Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHardwareModel()
   * @generated
   * @ordered
   */
  protected HardwareModel hardwareModel;

  /**
   * The cached value of the '{@link #getSoftwareModel() <em>Software Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSoftwareModel()
   * @generated
   * @ordered
   */
  protected SoftwareModel softwareModel;

  /**
   * The cached value of the '{@link #getRequests() <em>Requests</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRequests()
   * @generated
   * @ordered
   */
  protected EList<Request> requests;

  /**
   * The cached value of the '{@link #getObjective() <em>Objective</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObjective()
   * @generated
   * @ordered
   */
  protected Objective objective;

  /**
   * The cached value of the '{@link #getSolution() <em>Solution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSolution()
   * @generated
   * @ordered
   */
  protected Solution solution;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelImpl()
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
    return MyDslPackage.Literals.MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HardwareModel getHardwareModel()
  {
    return hardwareModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHardwareModel(HardwareModel newHardwareModel, NotificationChain msgs)
  {
    HardwareModel oldHardwareModel = hardwareModel;
    hardwareModel = newHardwareModel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MyDslPackage.MODEL__HARDWARE_MODEL, oldHardwareModel, newHardwareModel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHardwareModel(HardwareModel newHardwareModel)
  {
    if (newHardwareModel != hardwareModel)
    {
      NotificationChain msgs = null;
      if (hardwareModel != null)
        msgs = ((InternalEObject)hardwareModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MyDslPackage.MODEL__HARDWARE_MODEL, null, msgs);
      if (newHardwareModel != null)
        msgs = ((InternalEObject)newHardwareModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MyDslPackage.MODEL__HARDWARE_MODEL, null, msgs);
      msgs = basicSetHardwareModel(newHardwareModel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MyDslPackage.MODEL__HARDWARE_MODEL, newHardwareModel, newHardwareModel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SoftwareModel getSoftwareModel()
  {
    return softwareModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSoftwareModel(SoftwareModel newSoftwareModel, NotificationChain msgs)
  {
    SoftwareModel oldSoftwareModel = softwareModel;
    softwareModel = newSoftwareModel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MyDslPackage.MODEL__SOFTWARE_MODEL, oldSoftwareModel, newSoftwareModel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSoftwareModel(SoftwareModel newSoftwareModel)
  {
    if (newSoftwareModel != softwareModel)
    {
      NotificationChain msgs = null;
      if (softwareModel != null)
        msgs = ((InternalEObject)softwareModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MyDslPackage.MODEL__SOFTWARE_MODEL, null, msgs);
      if (newSoftwareModel != null)
        msgs = ((InternalEObject)newSoftwareModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MyDslPackage.MODEL__SOFTWARE_MODEL, null, msgs);
      msgs = basicSetSoftwareModel(newSoftwareModel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MyDslPackage.MODEL__SOFTWARE_MODEL, newSoftwareModel, newSoftwareModel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Request> getRequests()
  {
    if (requests == null)
    {
      requests = new EObjectContainmentEList<Request>(Request.class, this, MyDslPackage.MODEL__REQUESTS);
    }
    return requests;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Objective getObjective()
  {
    return objective;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetObjective(Objective newObjective, NotificationChain msgs)
  {
    Objective oldObjective = objective;
    objective = newObjective;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MyDslPackage.MODEL__OBJECTIVE, oldObjective, newObjective);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObjective(Objective newObjective)
  {
    if (newObjective != objective)
    {
      NotificationChain msgs = null;
      if (objective != null)
        msgs = ((InternalEObject)objective).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MyDslPackage.MODEL__OBJECTIVE, null, msgs);
      if (newObjective != null)
        msgs = ((InternalEObject)newObjective).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MyDslPackage.MODEL__OBJECTIVE, null, msgs);
      msgs = basicSetObjective(newObjective, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MyDslPackage.MODEL__OBJECTIVE, newObjective, newObjective));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Solution getSolution()
  {
    return solution;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSolution(Solution newSolution, NotificationChain msgs)
  {
    Solution oldSolution = solution;
    solution = newSolution;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MyDslPackage.MODEL__SOLUTION, oldSolution, newSolution);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSolution(Solution newSolution)
  {
    if (newSolution != solution)
    {
      NotificationChain msgs = null;
      if (solution != null)
        msgs = ((InternalEObject)solution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MyDslPackage.MODEL__SOLUTION, null, msgs);
      if (newSolution != null)
        msgs = ((InternalEObject)newSolution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MyDslPackage.MODEL__SOLUTION, null, msgs);
      msgs = basicSetSolution(newSolution, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MyDslPackage.MODEL__SOLUTION, newSolution, newSolution));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MyDslPackage.MODEL__HARDWARE_MODEL:
        return basicSetHardwareModel(null, msgs);
      case MyDslPackage.MODEL__SOFTWARE_MODEL:
        return basicSetSoftwareModel(null, msgs);
      case MyDslPackage.MODEL__REQUESTS:
        return ((InternalEList<?>)getRequests()).basicRemove(otherEnd, msgs);
      case MyDslPackage.MODEL__OBJECTIVE:
        return basicSetObjective(null, msgs);
      case MyDslPackage.MODEL__SOLUTION:
        return basicSetSolution(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case MyDslPackage.MODEL__HARDWARE_MODEL:
        return getHardwareModel();
      case MyDslPackage.MODEL__SOFTWARE_MODEL:
        return getSoftwareModel();
      case MyDslPackage.MODEL__REQUESTS:
        return getRequests();
      case MyDslPackage.MODEL__OBJECTIVE:
        return getObjective();
      case MyDslPackage.MODEL__SOLUTION:
        return getSolution();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MyDslPackage.MODEL__HARDWARE_MODEL:
        setHardwareModel((HardwareModel)newValue);
        return;
      case MyDslPackage.MODEL__SOFTWARE_MODEL:
        setSoftwareModel((SoftwareModel)newValue);
        return;
      case MyDslPackage.MODEL__REQUESTS:
        getRequests().clear();
        getRequests().addAll((Collection<? extends Request>)newValue);
        return;
      case MyDslPackage.MODEL__OBJECTIVE:
        setObjective((Objective)newValue);
        return;
      case MyDslPackage.MODEL__SOLUTION:
        setSolution((Solution)newValue);
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
      case MyDslPackage.MODEL__HARDWARE_MODEL:
        setHardwareModel((HardwareModel)null);
        return;
      case MyDslPackage.MODEL__SOFTWARE_MODEL:
        setSoftwareModel((SoftwareModel)null);
        return;
      case MyDslPackage.MODEL__REQUESTS:
        getRequests().clear();
        return;
      case MyDslPackage.MODEL__OBJECTIVE:
        setObjective((Objective)null);
        return;
      case MyDslPackage.MODEL__SOLUTION:
        setSolution((Solution)null);
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
      case MyDslPackage.MODEL__HARDWARE_MODEL:
        return hardwareModel != null;
      case MyDslPackage.MODEL__SOFTWARE_MODEL:
        return softwareModel != null;
      case MyDslPackage.MODEL__REQUESTS:
        return requests != null && !requests.isEmpty();
      case MyDslPackage.MODEL__OBJECTIVE:
        return objective != null;
      case MyDslPackage.MODEL__SOLUTION:
        return solution != null;
    }
    return super.eIsSet(featureID);
  }

} //ModelImpl