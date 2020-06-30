/**
 */
package models.tsp.fitness.TSP;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>City</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link models.tsp.fitness.TSP.City#getName <em>Name</em>}</li>
 *   <li>{@link models.tsp.fitness.TSP.City#isStart <em>Start</em>}</li>
 *   <li>{@link models.tsp.fitness.TSP.City#getOrigin <em>Origin</em>}</li>
 *   <li>{@link models.tsp.fitness.TSP.City#getDestination <em>Destination</em>}</li>
 *   <li>{@link models.tsp.fitness.TSP.City#getNext <em>Next</em>}</li>
 * </ul>
 *
 * @see models.tsp.fitness.TSP.TSPPackage#getCity()
 * @model
 * @generated
 */
public interface City extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see models.tsp.fitness.TSP.TSPPackage#getCity_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link models.tsp.fitness.TSP.City#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(boolean)
	 * @see models.tsp.fitness.TSP.TSPPackage#getCity_Start()
	 * @model required="true"
	 * @generated
	 */
	boolean isStart();

	/**
	 * Sets the value of the '{@link models.tsp.fitness.TSP.City#isStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #isStart()
	 * @generated
	 */
	void setStart(boolean value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' reference list.
	 * The list contents are of type {@link models.tsp.fitness.TSP.Distance}.
	 * It is bidirectional and its opposite is '{@link models.tsp.fitness.TSP.Distance#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' reference list.
	 * @see models.tsp.fitness.TSP.TSPPackage#getCity_Origin()
	 * @see models.tsp.fitness.TSP.Distance#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<Distance> getOrigin();

	/**
	 * Returns the value of the '<em><b>Destination</b></em>' reference list.
	 * The list contents are of type {@link models.tsp.fitness.TSP.Distance}.
	 * It is bidirectional and its opposite is '{@link models.tsp.fitness.TSP.Distance#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination</em>' reference list.
	 * @see models.tsp.fitness.TSP.TSPPackage#getCity_Destination()
	 * @see models.tsp.fitness.TSP.Distance#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<Distance> getDestination();

	/**
	 * Returns the value of the '<em><b>Next</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' reference.
	 * @see #setNext(City)
	 * @see models.tsp.fitness.TSP.TSPPackage#getCity_Next()
	 * @model
	 * @generated
	 */
	City getNext();

	/**
	 * Sets the value of the '{@link models.tsp.fitness.TSP.City#getNext <em>Next</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next</em>' reference.
	 * @see #getNext()
	 * @generated
	 */
	void setNext(City value);

} // City
