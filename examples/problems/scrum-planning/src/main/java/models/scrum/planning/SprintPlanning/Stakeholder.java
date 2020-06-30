/** */
package models.scrum.planning.SprintPlanning;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stakeholder</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.scrum.planning.SprintPlanning.Stakeholder#getWorkitem <em>Workitem</em>}
 * </ul>
 *
 * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getStakeholder()
 * @model
 * @generated
 */
public interface Stakeholder extends EObject {
  /**
   * Returns the value of the '<em><b>Workitem</b></em>' reference list. The list contents are of
   * type {@link models.scrum.planning.SprintPlanning.WorkItem}. It is bidirectional and its
   * opposite is '{@link models.scrum.planning.SprintPlanning.WorkItem#getStakeholder
   * <em>Stakeholder</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Workitem</em>' reference list.
   * @see models.scrum.planning.SprintPlanning.SprintPlanningPackage#getStakeholder_Workitem()
   * @see models.scrum.planning.SprintPlanning.WorkItem#getStakeholder
   * @model opposite="stakeholder" required="true"
   * @generated
   */
  EList<WorkItem> getWorkitem();
} // Stakeholder
