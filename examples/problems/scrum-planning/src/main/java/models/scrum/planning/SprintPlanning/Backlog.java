/** */
package models.scrum.planning.SprintPlanning;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Backlog</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.scrum.planning.SprintPlanning.Backlog#getWorkitems <em>Workitems</em>}
 * </ul>
 *
 * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getBacklog()
 * @model
 * @generated
 */
public interface Backlog extends EObject {
  /**
   * Returns the value of the '<em><b>Workitems</b></em>' containment reference list. The list
   * contents are of type {@link models.scrum.planning.SprintPlanning.WorkItem}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Workitems</em>' containment reference list.
   * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getBacklog_Workitems()
   * @model containment="true" required="true"
   * @generated
   */
  EList<WorkItem> getWorkitems();
} // Backlog
