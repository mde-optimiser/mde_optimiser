package uk.ac.kcl.client;

import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.NumberCell;
import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;

import uk.ac.kcl.client.data.Solution;

public class SolutionsTable extends CellTable<Solution> {
	List<Solution> solutions;

	/**
	 * Creates a specialised <code>CellTable<code> containing all solutions with their  objective/constraint values.
	 * 
	 * @param solutions a list of solutions to be added to the table
	 */
	public SolutionsTable(List<Solution> solutions) {
		this.solutions = solutions;
		initializeColumns();

		// Give the overall composite a style name.
		//setStyleName("solutionsTable");
		
		// Push the data into the widget.
		setRowCount(solutions.size(), true);
		setRowData(0, solutions);
	}
	
	private void initializeColumns() {
		TextColumn<Solution> numColumn = new TextColumn<Solution>() {

		    @Override
		    public String getValue(Solution object) {
		        return Integer.toString(solutions.indexOf(object) + 1);
		    }
		};
		addColumn(numColumn, "");
		
		TextColumn<Solution> SolutionId = new TextColumn<Solution>() {
			@Override
			public String getValue(Solution object) {
				return object.getSolutionId() == null ? "null" : object.getSolutionId().toUpperCase();
			}
		};
		addColumn(SolutionId, "Solution ID");

		addSpecialColumn("o:", solutions.get(0).getObjectives());
		addSpecialColumn("c:", solutions.get(0).getConstraints());
	}
	
	/**
	 * Creates a column for each objective/constraint (also called criterion).
	 * The column header string is criterion type followed by criterion name.
	 * E.g. o:MinimiseCoupling.
	 * 
	 * @param text to be added to the column header, representing criterion type.
	 * It can be "o:" for objective, or "c:" for constraint
	 * @param criteria a map containing all objectives/constraints with their values
	 */
	private void addSpecialColumn(String text, Map<String, Double> criteria) {
		for (String objectiveName : criteria.keySet()) {
			NumberCell numberCell = new NumberCell();
			Column<Solution, Number> column = new Column<Solution, Number>(numberCell) {
				@Override
				public Number getValue(Solution object) {
					return criteria.get(objectiveName);
				}
			};
			addColumn(column, text + objectiveName);
		}
	}
}