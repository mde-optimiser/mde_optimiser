/** */
package models.nrp.nextReleaseProblem.impl;

import models.nrp.nextReleaseProblem.Cost;
import models.nrp.nextReleaseProblem.CostType;
import models.nrp.nextReleaseProblem.EcorePackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cost</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link models.nrp.nextReleaseProblem.impl.CostImpl#getType <em>Type</em>}
 *   <li>{@link models.nrp.nextReleaseProblem.impl.CostImpl#getAmount <em>Amount</em>}
 * </ul>
 *
 * @generated
 */
public class CostImpl extends MinimalEObjectImpl.Container implements Cost {
  /**
   * The default value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getType()
   * @generated
   * @ordered
   */
  protected static final CostType TYPE_EDEFAULT = CostType.MONEY;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getType()
   * @generated
   * @ordered
   */
  protected CostType type = TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getAmount() <em>Amount</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getAmount()
   * @generated
   * @ordered
   */
  protected static final double AMOUNT_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getAmount() <em>Amount</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getAmount()
   * @generated
   * @ordered
   */
  protected double amount = AMOUNT_EDEFAULT;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected CostImpl() {
    super();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return EcorePackage.Literals.COST;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public CostType getType() {
    return type;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setType(CostType newType) {
    CostType oldType = type;
    type = newType == null ? TYPE_EDEFAULT : newType;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(this, Notification.SET, EcorePackage.COST__TYPE, oldType, type));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public double getAmount() {
    return amount;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void setAmount(double newAmount) {
    double oldAmount = amount;
    amount = newAmount;
    if (eNotificationRequired())
      eNotify(
          new ENotificationImpl(
              this, Notification.SET, EcorePackage.COST__AMOUNT, oldAmount, amount));
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case EcorePackage.COST__TYPE:
        return getType();
      case EcorePackage.COST__AMOUNT:
        return getAmount();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case EcorePackage.COST__TYPE:
        setType((CostType) newValue);
        return;
      case EcorePackage.COST__AMOUNT:
        setAmount((Double) newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case EcorePackage.COST__TYPE:
        setType(TYPE_EDEFAULT);
        return;
      case EcorePackage.COST__AMOUNT:
        setAmount(AMOUNT_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case EcorePackage.COST__TYPE:
        return type != TYPE_EDEFAULT;
      case EcorePackage.COST__AMOUNT:
        return amount != AMOUNT_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (type: ");
    result.append(type);
    result.append(", amount: ");
    result.append(amount);
    result.append(')');
    return result.toString();
  }
} // CostImpl
