package org.vaadin.test.tictactoe;

import com.vaadin.flow.model.TemplateModel;
import com.vaadin.ui.Tag;
import com.vaadin.ui.common.HtmlImport;
import com.vaadin.ui.polymertemplate.PolymerTemplate;

@Tag("tictactoe-cell")
@HtmlImport("frontend://tictactoe/tictactoe-cell.html")
public class TicTacToeCell extends PolymerTemplate<org.vaadin.test.tictactoe.TicTacToeCell.TicTacToeCellModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String X_VALUE = "X";
	private static final String O_VALUE = "O";
	private static final String EMPTY_VALUE = "-";

	public TicTacToeCell() {
		this.setCellValue(TicTacToeEnum.EMPTY);
	}

	public TicTacToeCell(TicTacToeEnum value) {
		this.setCellValue(value);
	}

	public enum TicTacToeEnum {
		CELL_X, 
		CELL_O, 
		EMPTY
	}

	private void setInternalCellValue(String value) {
		getModel().setCellValue(value);
	}
	
	public void setCellValue(TicTacToeEnum value) {
		switch (value) {
		case CELL_X:
			setInternalCellValue(X_VALUE);
		case CELL_O:
			setInternalCellValue(O_VALUE);
		default:
			setInternalCellValue(EMPTY_VALUE);
		}
	}
	
	public TicTacToeEnum getCellValue() {
		String value = getModel().getCellValue();
		TicTacToeEnum result;
		switch (value) {
		case X_VALUE:
			result = TicTacToeEnum.CELL_X;
		case O_VALUE:
			result = TicTacToeEnum.CELL_O;
		default:
			result = TicTacToeEnum.EMPTY;
		}
		return result;
	}

	/**
	 * A model interface that defined the data that is communicated between the
	 * server and the client.
	 */
	public interface TicTacToeCellModel extends TemplateModel {
		/*
		 * The name shown in the input is updated from the client.
		 */
		String getCellValue();

		void setCellValue(String cellValue);
	}

}
