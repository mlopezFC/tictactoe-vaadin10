package org.vaadin.test.tictactoe;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("tictactoe-cell")
@HtmlImport("frontend://tictactoe/tictactoe-cell.html")
public class TicTacToeCell extends PolymerTemplate<org.vaadin.test.tictactoe.TicTacToeCell.TicTacToeCellModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TicTacToeBoard board;

	public TicTacToeCell(TicTacToeBoard board) {
		this.setCellValue(TicTacToeEnum.EMPTY);
		this.board = board;
	}

	public TicTacToeCell(TicTacToeBoard board, TicTacToeEnum value) {
		this.setCellValue(value);
		this.board = board;
	}

	public enum TicTacToeEnum {
		CELL_X("X"), CELL_O("O"), EMPTY("_");

		private final String value;

		private static final Map<String, TicTacToeEnum> map = new HashMap<>(values().length, 1);

		static {
			for (TicTacToeEnum c : values())
				map.put(c.value, c);
		}

		private TicTacToeEnum(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		public static TicTacToeEnum of(String value) {
			TicTacToeEnum result = map.get(value);
			if (result == null) {
				throw new IllegalArgumentException("Invalid cell type: " + value);
			}
			return result;
		}

	}

	public void setCellValue(TicTacToeEnum value) {
		getModel().setCellValue(value.toString());
	}

	public void setTemporalCellValue(TicTacToeEnum value) {
		getModel().setTemporalCellValue(value.toString());
	}

	public TicTacToeEnum getCellValue() {
		return TicTacToeEnum.of(getModel().getCellValue());
	}

	public TicTacToeEnum getTemporalCellValue() {
		return TicTacToeEnum.of(getModel().getTemporalCellValue());
	}

	@EventHandler
	private void handleClick() {
		TicTacToeEnum newValue = TicTacToeEnum.EMPTY;
		switch (getCellValue()) {
		case CELL_X:
			if (board.getCurrentUser().equals(TicTacToeEnum.CELL_X))
				newValue = TicTacToeEnum.EMPTY;
			break;
		case CELL_O:
			if (board.getCurrentUser().equals(TicTacToeEnum.CELL_O))
				newValue = TicTacToeEnum.EMPTY;
			break;
		default:
			newValue = board.getCurrentUser();
		}

		setTemporalCellValue(newValue);

		fireEvent(new CellClickEvent(this, false, newValue));
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

		String getTemporalCellValue();

		void setTemporalCellValue(String temporalCellValue);
	}

	public Registration addCellClickListener(ComponentEventListener<CellClickEvent> listener) {
		return addListener(CellClickEvent.class, listener);
	}

	@SuppressWarnings("serial")
	public class CellClickEvent extends ComponentEvent<TicTacToeCell> {
		
		private TicTacToeEnum newValue;
		
		public CellClickEvent(TicTacToeCell source, boolean fromClient, TicTacToeEnum newValue) {
			super(source, fromClient);
			this.newValue = newValue;
		}

		public TicTacToeEnum getNewValue() {
			return newValue;
		}		
		
	}

	@Override
	public String toString() {
		return "[" + getCellValue() + "]";
	}
	
}
