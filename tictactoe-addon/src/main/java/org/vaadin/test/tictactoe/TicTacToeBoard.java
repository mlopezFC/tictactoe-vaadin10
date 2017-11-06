package org.vaadin.test.tictactoe;

import com.vaadin.ui.Composite;
import com.vaadin.ui.html.Div;
import com.vaadin.ui.layout.HorizontalLayout;
import com.vaadin.ui.layout.VerticalLayout;

public class TicTacToeBoard extends Composite<Div> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TicTacToeCell[][] cells;
	private int xSize;
	private int ySize;
	private TicTacToeCell.TicTacToeEnum currentUser;
	
	public TicTacToeBoard(int xSize, int ySize) {
		this.xSize = xSize;
		this.ySize = ySize;
		this.setCurrentUser(TicTacToeCell.TicTacToeEnum.CELL_X);
		cells = new TicTacToeCell[xSize][ySize];
		
		VerticalLayout mainLayout = new VerticalLayout();
		
		// now let's create the board
		for (int y = 0;y<ySize;y++) {
			HorizontalLayout hl = new HorizontalLayout();
			for (int x = 0;x<xSize;x++) {
				TicTacToeCell cell = new TicTacToeCell(this);
				hl.add(cell);
				cells[x][y] = cell;
			}
			mainLayout.add(hl);
		}
		
		getContent().add(mainLayout);
	}

	public TicTacToeCell.TicTacToeEnum getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(TicTacToeCell.TicTacToeEnum currentUser) {
		this.currentUser = currentUser;
	}

}
