/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation.util;

import models.project.allocation.ecore.StudentProjectMarkerAllocation.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method
 * for each class of the model.
 * <!-- end-user-doc -->
 *
 * @see
 *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage
 * @generated
 */
public class StudentProjectMarkerAllocationAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected static StudentProjectMarkerAllocationPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public StudentProjectMarkerAllocationAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = StudentProjectMarkerAllocationPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is
   * an instance object of the model.
   * <!-- end-user-doc -->
   *
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected StudentProjectMarkerAllocationSwitch<Adapter> modelSwitch =
      new StudentProjectMarkerAllocationSwitch<Adapter>() {
        @Override
        public Adapter caseAcademic(Academic object) {
          return createAcademicAdapter();
        }

        @Override
        public Adapter caseProject(Project object) {
          return createProjectAdapter();
        }

        @Override
        public Adapter caseStudent(Student object) {
          return createStudentAdapter();
        }

        @Override
        public Adapter caseMarkingTeam(MarkingTeam object) {
          return createMarkingTeamAdapter();
        }

        @Override
        public Adapter caseMarkerAllocationModel(MarkerAllocationModel object) {
          return createMarkerAllocationModelAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
          return createEObjectAdapter();
        }
      };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject) target);
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic <em>Academic</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic
   * @generated
   */
  public Adapter createAcademicAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project <em>Project</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project
   * @generated
   */
  public Adapter createProjectAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student <em>Student</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Student
   * @generated
   */
  public Adapter createStudentAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam <em>Marking
   * Team</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam
   * @generated
   */
  public Adapter createMarkingTeamAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel <em>Marker
   * Allocation Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases; it's useful to
   * ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel
   * @generated
   */
  public Adapter createMarkerAllocationModelAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   *
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }
} // StudentProjectMarkerAllocationAdapterFactory
