package uk.ac.kcl.client.widgets;

import java.util.List;
import java.util.Set;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;

import uk.ac.kcl.client.data.Solution;

public class SolutionsTable extends CellTable<Solution> {
	private static enum CriterionType {OBJECTIVE, CONSTRAINT}
	private List<Solution> solutions;

	/**
	 * Creates a specialised <code>CellTable<code> containing all solutions with their objective/constraint values.
	 * 
	 * @param solutions a list of solutions to be added to the table
	 */
	public SolutionsTable(List<Solution> solutions) {
		this.solutions = solutions;
		initializeColumns();

		// Give the overall composite a style name.
		//setStyleName("solutionsTable");
		
		// Push the data into the widget.
		setVisibleRange(0, solutions.size());
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
		
		NumberCell numberCell = new NumberCell();
		Column<Solution, Number> evolutionColumn = new Column<Solution, Number>(numberCell) {
			@Override
			public Number getValue(Solution object) {
				return object.getEvolutionNumber();
			}
		};
		addColumn(evolutionColumn, "Evolution n.");

		addSpecialColumn(CriterionType.OBJECTIVE);
		addSpecialColumn(CriterionType.CONSTRAINT);
	}
	
	/**
	 * Creates a column for each objective/constraint (also called criterion).
	 * The column header string is criterion type followed by criterion name.
	 * E.g. o:MinimiseCoupling.
	 * 
	 * @param type criterion type, which can be objective or constraint
	 */
	private void addSpecialColumn(CriterionType type) {
		String text;
		Set<String> criteria;
		switch (type) {
		case OBJECTIVE :
			text = "o:";
			criteria = solutions.get(0).getObjectives().keySet();
			break;
		case CONSTRAINT :
			text = "c:";
			criteria = solutions.get(0).getConstraints().keySet();
			break;
		default :
			text = null;
			criteria = null;
			break;
		}
		
		for (String criterionName : criteria) {
			NumberCell numberCell = new NumberCell();
			Column<Solution, Number> column = new Column<Solution, Number>(numberCell) {
				@Override
				public Number getValue(Solution object) {
					switch (type) {
					case OBJECTIVE :
						return object.getObjectives().get(criterionName);
					case CONSTRAINT :
						return object.getConstraints().get(criterionName);
					}
					return null;
				}
			};
			addColumn(column, text + criterionName);
		}
	}
}