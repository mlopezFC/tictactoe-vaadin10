package org.vaadin.test.tictactoe;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.test.tictactoe.TicTacToeCell.TicTacToeEnum;

import com.vaadin.shared.Registration;
import com.vaadin.ui.Composite;
import com.vaadin.ui.button.Button;
import com.vaadin.ui.event.ComponentEvent;
import com.vaadin.ui.event.ComponentEventListener;
import com.vaadin.ui.html.Div;
import com.vaadin.ui.layout.HorizontalLayout;
import com.vaadin.ui.layout.VerticalLayout;

public class TicTacToeBoard extends Composite<Div> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TicTacToeCell[][] cells;
	private List<TicTacToeCell> cellsList;
	private int xSize;
	private int ySize;
	private TicTacToeCell.TicTacToeEnum currentUser;

	public TicTacToeBoard(int xSize, int ySize) throws TicTacToeException {
		initializeBoard(xSize, ySize);
	}

	private void initializeBoard(int xSize, int ySize) throws TicTacToeException {
		cellsList = new ArrayList<TicTacToeCell>();
		this.setBoardSize(xSize, ySize);
		this.setCurrentUser(TicTacToeCell.TicTacToeEnum.CELL_X);
		cells = new TicTacToeCell[xSize][ySize];

		VerticalLayout mainLayout = new VerticalLayout();
		
		// now let's create the board
		for (int y = 0; y < ySize; y++) {
			HorizontalLayout hl = new HorizontalLayout();
			for (int x = 0; x < xSize; x++) {
				TicTacToeCell cell = new TicTacToeCell(this);
				cell.addCellClickListener(e -> {
					TicTacToeEnum newValue = e.getNewValue();
					for (TicTacToeCell ticTacToeCell : cellsList) {
						ticTacToeCell.setTemporalCellValue(TicTacToeEnum.EMPTY);
					}
					e.getSource().setTemporalCellValue(newValue);
				});
				hl.add(cell);
				cells[x][y] = cell;
				cellsList.add(cell);
			}
			mainLayout.add(hl);
		}
		
		Button b = new Button("Next User");
		
		b.addClickListener(e -> {
			for (TicTacToeCell ticTacToeCell : cellsList) {
				if (!ticTacToeCell.getTemporalCellValue().equals(TicTacToeEnum.EMPTY)) {
					ticTacToeCell.setCellValue(ticTacToeCell.getTemporalCellValue());
					ticTacToeCell.setTemporalCellValue(TicTacToeEnum.EMPTY);
				}
			}
			setCurrentUser(getCurrentUser().equals(TicTacToeEnum.CELL_X)?TicTacToeEnum.CELL_O:TicTacToeEnum.CELL_X);
			fireEvent(new UserMoveEvent(this, false));
		});

		getContent().add(mainLayout, b);
	}
	
	public void restartGame() throws TicTacToeException {
		initializeBoard(xSize, ySize);
	}
	
	public void changeBoardSize(int xSize, int ySize) throws TicTacToeException {
		setBoardSize(xSize,ySize);
	}

	private void setBoardSize(int xSize2, int ySize2) throws TicTacToeException {
		if (xSize2 < 0 || ySize2 < 0) {
			throw new TicTacToeException("");
		}
	}

	public TicTacToeCell.TicTacToeEnum getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(TicTacToeCell.TicTacToeEnum currentUser) {
		this.currentUser = currentUser;
	}

	public Registration addUserMoveListener(ComponentEventListener<UserMoveEvent> listener) {
		return addListener(UserMoveEvent.class, listener);
	}

	@SuppressWarnings("serial")
	public class UserMoveEvent extends ComponentEvent<TicTacToeBoard> {
		public UserMoveEvent(TicTacToeBoard source, boolean fromClient) {
			super(source, fromClient);
		}
	}

}
