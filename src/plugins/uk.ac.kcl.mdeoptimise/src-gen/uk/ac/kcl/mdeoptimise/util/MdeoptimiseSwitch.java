/**
 * generated by Xtext 2.10.0
 */
package uk.ac.kcl.mdeoptimise.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import uk.ac.kcl.mdeoptimise.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see uk.ac.kcl.mdeoptimise.MdeoptimisePackage
 * @generated
 */
public class MdeoptimiseSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MdeoptimisePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdeoptimiseSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = MdeoptimisePackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case MdeoptimisePackage.OPTIMISATION:
      {
        Optimisation optimisation = (Optimisation)theEObject;
        T result = caseOptimisation(optimisation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdeoptimisePackage.BASEPATH_SPEC:
      {
        BasepathSpec basepathSpec = (BasepathSpec)theEObject;
        T result = caseBasepathSpec(basepathSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdeoptimisePackage.META_MODEL_SPEC:
      {
        MetaModelSpec metaModelSpec = (MetaModelSpec)theEObject;
        T result = caseMetaModelSpec(metaModelSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdeoptimisePackage.MODEL_PATH_SPEC:
      {
        ModelPathSpec modelPathSpec = (ModelPathSpec)theEObject;
        T result = caseModelPathSpec(modelPathSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdeoptimisePackage.OBJECTIVE_INTERPRETER_SPEC:
      {
        ObjectiveInterpreterSpec objectiveInterpreterSpec = (ObjectiveInterpreterSpec)theEObject;
        T result = caseObjectiveInterpreterSpec(objectiveInterpreterSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdeoptimisePackage.CONSTRAINT_INTERPRETER_SPEC:
      {
        ConstraintInterpreterSpec constraintInterpreterSpec = (ConstraintInterpreterSpec)theEObject;
        T result = caseConstraintInterpreterSpec(constraintInterpreterSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdeoptimisePackage.EVOLVER_SPEC:
      {
        EvolverSpec evolverSpec = (EvolverSpec)theEObject;
        T result = caseEvolverSpec(evolverSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdeoptimisePackage.EVOLVER_PARAMETER:
      {
        EvolverParameter evolverParameter = (EvolverParameter)theEObject;
        T result = caseEvolverParameter(evolverParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdeoptimisePackage.OPTIMISATION_SPEC:
      {
        OptimisationSpec optimisationSpec = (OptimisationSpec)theEObject;
        T result = caseOptimisationSpec(optimisationSpec);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Optimisation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Optimisation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOptimisation(Optimisation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Basepath Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Basepath Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBasepathSpec(BasepathSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Meta Model Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Meta Model Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMetaModelSpec(MetaModelSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Path Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Path Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelPathSpec(ModelPathSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Objective Interpreter Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Objective Interpreter Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseObjectiveInterpreterSpec(ObjectiveInterpreterSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constraint Interpreter Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraint Interpreter Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstraintInterpreterSpec(ConstraintInterpreterSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Evolver Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Evolver Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEvolverSpec(EvolverSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Evolver Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Evolver Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEvolverParameter(EvolverParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Optimisation Spec</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Optimisation Spec</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOptimisationSpec(OptimisationSpec object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //MdeoptimiseSwitch
