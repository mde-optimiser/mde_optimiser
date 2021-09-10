/** */
package models.cra.fitness.architectureCRA.util;

import models.cra.fitness.architectureCRA.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 *
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy. It supports the call {@link
 * #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of
 * the model, starting with the actual class of the object and proceeding up the inheritance
 * hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 *
 * @see models.cra.fitness.architectureCRA.ArchitectureCRAPackage
 * @generated
 */
public class ArchitectureCRASwitch<T> extends Switch<T> {
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected static ArchitectureCRAPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public ArchitectureCRASwitch() {
    if (modelPackage == null) {
      modelPackage = ArchitectureCRAPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage) {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it
   * yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case ArchitectureCRAPackage.CLASS_MODEL:
        {
          ClassModel classModel = (ClassModel) theEObject;
          T result = caseClassModel(classModel);
          if (result == null) result = caseNamedElement(classModel);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
      case ArchitectureCRAPackage.CLASS:
        {
          models.cra.fitness.architectureCRA.Class class_ =
              (models.cra.fitness.architectureCRA.Class) theEObject;
          T result = caseClass(class_);
          if (result == null) result = caseNamedElement(class_);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
      case ArchitectureCRAPackage.ATTRIBUTE:
        {
          Attribute attribute = (Attribute) theEObject;
          T result = caseAttribute(attribute);
          if (result == null) result = caseFeature(attribute);
          if (result == null) result = caseNamedElement(attribute);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
      case ArchitectureCRAPackage.METHOD:
        {
          Method method = (Method) theEObject;
          T result = caseMethod(method);
          if (result == null) result = caseFeature(method);
          if (result == null) result = caseNamedElement(method);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
      case ArchitectureCRAPackage.FEATURE:
        {
          Feature feature = (Feature) theEObject;
          T result = caseFeature(feature);
          if (result == null) result = caseNamedElement(feature);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
      case ArchitectureCRAPackage.NAMED_ELEMENT:
        {
          NamedElement namedElement = (NamedElement) theEObject;
          T result = caseNamedElement(namedElement);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
      default:
        return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Class Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Class Model</em>'.
   * @see #doSwitch(EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClassModel(ClassModel object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Class</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Class</em>'.
   * @see #doSwitch(EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClass(models.cra.fitness.architectureCRA.Class object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
   * @see #doSwitch(EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAttribute(Attribute object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Method</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Method</em>'.
   * @see #doSwitch(EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMethod(Method object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
   * @see #doSwitch(EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeature(Feature object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
   * @see #doSwitch(EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedElement(NamedElement object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; returning a non-null result will terminate the switch, but
   * this is the last case anyway.
   * <!-- end-user-doc -->
   *
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object) {
    return null;
  }
} // ArchitectureCRASwitch
