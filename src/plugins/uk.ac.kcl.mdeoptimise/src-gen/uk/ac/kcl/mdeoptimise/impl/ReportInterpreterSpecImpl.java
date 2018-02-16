/**
 * generated by Xtext 2.10.0
 */
package uk.ac.kcl.mdeoptimise.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import uk.ac.kcl.mdeoptimise.MdeoptimisePackage;
import uk.ac.kcl.mdeoptimise.ReportInterpreterSpec;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Report Interpreter Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link uk.ac.kcl.mdeoptimise.impl.ReportInterpreterSpecImpl#getReportName <em>Report Name</em>}</li>
 *   <li>{@link uk.ac.kcl.mdeoptimise.impl.ReportInterpreterSpecImpl#getReportSpec <em>Report Spec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReportInterpreterSpecImpl extends MinimalEObjectImpl.Container implements ReportInterpreterSpec
{
  /**
   * The default value of the '{@link #getReportName() <em>Report Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReportName()
   * @generated
   * @ordered
   */
  protected static final String REPORT_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getReportName() <em>Report Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReportName()
   * @generated
   * @ordered
   */
  protected String reportName = REPORT_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getReportSpec() <em>Report Spec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReportSpec()
   * @generated
   * @ordered
   */
  protected static final String REPORT_SPEC_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getReportSpec() <em>Report Spec</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReportSpec()
   * @generated
   * @ordered
   */
  protected String reportSpec = REPORT_SPEC_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ReportInterpreterSpecImpl()
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
    return MdeoptimisePackage.Literals.REPORT_INTERPRETER_SPEC;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getReportName()
  {
    return reportName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReportName(String newReportName)
  {
    String oldReportName = reportName;
    reportName = newReportName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_NAME, oldReportName, reportName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getReportSpec()
  {
    return reportSpec;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReportSpec(String newReportSpec)
  {
    String oldReportSpec = reportSpec;
    reportSpec = newReportSpec;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_SPEC, oldReportSpec, reportSpec));
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
      case MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_NAME:
        return getReportName();
      case MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_SPEC:
        return getReportSpec();
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
      case MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_NAME:
        setReportName((String)newValue);
        return;
      case MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_SPEC:
        setReportSpec((String)newValue);
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
      case MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_NAME:
        setReportName(REPORT_NAME_EDEFAULT);
        return;
      case MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_SPEC:
        setReportSpec(REPORT_SPEC_EDEFAULT);
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
      case MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_NAME:
        return REPORT_NAME_EDEFAULT == null ? reportName != null : !REPORT_NAME_EDEFAULT.equals(reportName);
      case MdeoptimisePackage.REPORT_INTERPRETER_SPEC__REPORT_SPEC:
        return REPORT_SPEC_EDEFAULT == null ? reportSpec != null : !REPORT_SPEC_EDEFAULT.equals(reportSpec);
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
    result.append(" (reportName: ");
    result.append(reportName);
    result.append(", reportSpec: ");
    result.append(reportSpec);
    result.append(')');
    return result.toString();
  }

} //ReportInterpreterSpecImpl
