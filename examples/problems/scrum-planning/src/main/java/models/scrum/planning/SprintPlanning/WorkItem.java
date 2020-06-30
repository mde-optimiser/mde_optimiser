/** */
package models.scrum.planning.SprintPlanning;

import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.scrum.planning.SprintPlanning.WorkItem#getIsPlannedFor <em>Is Planned
 *       For</em>}
 *   <li>{@link models.scrum.planning.SprintPlanning.WorkItem#getImportance <em>Importance</em>}
 *   <li>{@link models.scrum.planning.SprintPlanning.WorkItem#getEffort <em>Effort</em>}
 *   <li>{@link models.scrum.planning.SprintPlanning.WorkItem#getStakeholder <em>Stakeholder</em>}
 * </ul>
 *
 * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getWorkItem()
 * @model
 * @generated
 */
public interface WorkItem extends EObject {
  /**
   * Returns the value of the '<em><b>Is Planned For</b></em>' reference. It is bidirectional and
   * its opposite is '{@link models.scrum.planning.SprintPlanning.Sprint#getCommittedItem
   * <em>Committed Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Is Planned For</em>' reference.
   * @see #setIsPlannedFor(Sprint)
   * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getWorkItem_IsPlannedFor()
   * @see models.scrum.planning.SprintPlanning.Sprint#getCommittedItem
   * @model opposite="committedItem"
   * @generated
   */
  Sprint getIsPlannedFor();

  /**
   * Sets the value of the '{@link models.scrum.planning.SprintPlanning.WorkItem#getIsPlannedFor
   * <em>Is Planned For</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Is Planned For</em>' reference.
   * @see #getIsPlannedFor()
   * @generated
   */
  void setIsPlannedFor(Sprint value);

  /**
   * Returns the value of the '<em><b>Importance</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Importance</em>' attribute.
   * @see #setImportance(int)
   * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getWorkItem_Importance()
   * @model
   * @generated
   */
  int getImportance();

  /**
   * Sets the value of the '{@link models.scrum.planning.SprintPlanning.WorkItem#getImportance
   * <em>Importance</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Importance</em>' attribute.
   * @see #getImportance()
   * @generated
   */
  void setImportance(int value);

  /**
   * Returns the value of the '<em><b>Effort</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Effort</em>' attribute.
   * @see #setEffort(int)
   * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getWorkItem_Effort()
   * @model
   * @generated
   */
  int getEffort();

  /**
   * Sets the value of the '{@link models.scrum.planning.SprintPlanning.WorkItem#getEffort
   * <em>Effort</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Effort</em>' attribute.
   * @see #getEffort()
   * @generated
   */
  void setEffort(int value);

  /**
   * Returns the value of the '<em><b>Stakeholder</b></em>' reference. It is bidirectional and its
   * opposite is '{@link models.scrum.planning.SprintPlanning.Stakeholder#getWorkitem
   * <em>Workitem</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Stakeholder</em>' reference.
   * @see #setStakeholder(Stakeholder)
   * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getWorkItem_Stakeholder()
   * @see models.scrum.planning.SprintPlanning.Stakeholder#getWorkitem
   * @model opposite="workitem" required="true"
   * @generated
   */
  Stakeholder getStakeholder();

  /**
   * Sets the value of the '{@link models.scrum.planning.SprintPlanning.WorkItem#getStakeholder
   * <em>Stakeholder</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Stakeholder</em>' reference.
   * @see #getStakeholder()
   * @generated
   */
  void setStakeholder(Stakeholder value);
} // WorkItem
