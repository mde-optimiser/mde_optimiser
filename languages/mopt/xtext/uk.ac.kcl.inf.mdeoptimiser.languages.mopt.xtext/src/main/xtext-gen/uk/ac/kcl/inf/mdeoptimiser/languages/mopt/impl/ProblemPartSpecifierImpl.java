/**
 * generated by Xtext 2.17.0
 */
package uk.ac.kcl.inf.mdeoptimiser.languages.mopt.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.MoptPackage;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ProblemPartSpecifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Problem Part Specifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.kcl.inf.mdeoptimiser.languages.mopt.impl.ProblemPartSpecifierImpl#getProblemPartSpecifier <em>Problem Part Specifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProblemPartSpecifierImpl extends MinimalEObjectImpl.Container implements ProblemPartSpecifier
{
  /**
   * The default value of the '{@link #getProblemPartSpecifier() <em>Problem Part Specifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProblemPartSpecifier()
   * @generated
   * @ordered
   */
  protected static final String PROBLEM_PART_SPECIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProblemPartSpecifier() <em>Problem Part Specifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProblemPartSpecifier()
   * @generated
   * @ordered
   */
  protected String problemPartSpecifier = PROBLEM_PART_SPECIFIER_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ProblemPartSpecifierImpl()
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
    return MoptPackage.Literals.PROBLEM_PART_SPECIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getProblemPartSpecifier()
  {
    return problemPartSpecifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProblemPartSpecifier(String newProblemPartSpecifier)
  {
    String oldProblemPartSpecifier = problemPartSpecifier;
    problemPartSpecifier = newProblemPartSpecifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MoptPackage.PROBLEM_PART_SPECIFIER__PROBLEM_PART_SPECIFIER, oldProblemPartSpecifier, problemPartSpecifier));
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
      case MoptPackage.PROBLEM_PART_SPECIFIER__PROBLEM_PART_SPECIFIER:
        return getProblemPartSpecifier();
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
      case MoptPackage.PROBLEM_PART_SPECIFIER__PROBLEM_PART_SPECIFIER:
        setProblemPartSpecifier((String)newValue);
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
      case MoptPackage.PROBLEM_PART_SPECIFIER__PROBLEM_PART_SPECIFIER:
        setProblemPartSpecifier(PROBLEM_PART_SPECIFIER_EDEFAULT);
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
      case MoptPackage.PROBLEM_PART_SPECIFIER__PROBLEM_PART_SPECIFIER:
        return PROBLEM_PART_SPECIFIER_EDEFAULT == null ? problemPartSpecifier != null : !PROBLEM_PART_SPECIFIER_EDEFAULT.equals(problemPartSpecifier);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (problemPartSpecifier: ");
    result.append(problemPartSpecifier);
    result.append(')');
    return result.toString();
  }

} //ProblemPartSpecifierImpl