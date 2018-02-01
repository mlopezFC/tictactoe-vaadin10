package org.vaadin.test.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.vaadin.test.tictactoe.TicTacToeCell.TicTacToeEnum;

import com.vaadin.router.HasUrlParameter;
import com.vaadin.router.OptionalParameter;
import com.vaadin.router.event.BeforeNavigationEvent;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Composite;
import com.vaadin.ui.button.Button;
import com.vaadin.ui.combobox.ComboBox;
import com.vaadin.ui.event.ComponentEvent;
import com.vaadin.ui.event.ComponentEventListener;
import com.vaadin.ui.html.Div;
import com.vaadin.ui.layout.FlexLayout.Alignment;
import com.vaadin.ui.layout.HorizontalLayout;
import com.vaadin.ui.layout.VerticalLayout;
import com.vaadin.ui.textfield.TextField;

public class TicTacToeBoard extends Composite<Div> implements HasUrlParameter<String> {

	/**
	 * O
	 */
	private static final long serialVersionUID = 1L;

	private TicTacToeCell[][] cells;
	private TicTacToeCell[][] transposedCells;
	private List<TicTacToeCell> cellsList;
	private int xSize;
	private int ySize;
	private TicTacToeCell.TicTacToeEnum currentUser;

	public TicTacToeBoard(int xSize, int ySize) throws TicTacToeException {
		initializeBoard(xSize, ySize);
	}

	private void initializeBoard(int xSize, int ySize) throws TicTacToeException {
		
		getContent().removeAll();
		cellsList = new ArrayList<TicTacToeCell>();
		this.setBoardSize(xSize, ySize);
		this.setCurrentUser(TicTacToeCell.TicTacToeEnum.CELL_X);
		cells = new TicTacToeCell[xSize][ySize];
		transposedCells = new TicTacToeCell[ySize][xSize];

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		
		// now let's create the board
		for (int y = 0; y < ySize; y++) {
			HorizontalLayout hl = new HorizontalLayout();
			hl.setHeight("129px");
			for (int x = 0; x < xSize; x++) {
				TicTacToeCell cell = new TicTacToeCell(this);
				cell.addCellClickListener(e -> {
					TicTacToeEnum newValue = e.getNewValue();
					if (!newValue.equals(TicTacToeEnum.EMPTY)) {
						for (TicTacToeCell ticTacToeCell : cellsList) {
							ticTacToeCell.setTemporalCellValue(TicTacToeEnum.EMPTY);
						}
						e.getSource().setTemporalCellValue(newValue);
					}
				});
				hl.add(cell);
				cells[x][y] = cell;
				transposedCells[y][x] = cell;
				cellsList.add(cell);
			}
			mainLayout.add(hl);
		}
		
		TextField tf = new TextField(getCurrentUser().toString());
		tf.setReadonly(true);
		tf.setLabel("Current User");

		ComboBox<String> cb = new ComboBox<String>("Board size", Arrays.asList(new String[] {"3x3","4x4","5x5","6x6"}));
		cb.addValueChangeListener(e->{
			try {
				if (e.getValue()!=null && !e.getValue().equals("")) {
					Integer size = Integer.valueOf(e.getValue().split("x")[0]);
					initializeBoard(size, size);
				}
			} catch (NumberFormatException | TicTacToeException e1) {
				e1.printStackTrace();
			}
		});
		
		HorizontalLayout hl = new HorizontalLayout(tf, cb);
		hl.getStyle().set("margin", "10px");
		hl.setWidth("100%");
		
		hl.setFlexGrow(1, tf,cb);
		
		Button button = new Button("Next User");
		button.setWidth("100%");
		
		button.addClickListener(e -> {
			for (TicTacToeCell ticTacToeCell : cellsList) {
				if (!ticTacToeCell.getTemporalCellValue().equals(TicTacToeEnum.EMPTY)) {
					ticTacToeCell.setCellValue(ticTacToeCell.getTemporalCellValue());
					ticTacToeCell.setTemporalCellValue(TicTacToeEnum.EMPTY);
				}
			}
			if (!currentUserWon()) {
				setCurrentUser(getCurrentUser().equals(TicTacToeEnum.CELL_X)?TicTacToeEnum.CELL_O:TicTacToeEnum.CELL_X);
				tf.setValue(getCurrentUser().toString());
				fireEvent(new UserMoveEvent(this, false));
			} else {
				Button restartButton = new Button("Restart Game");
				restartButton.setWidth("100%");
				restartButton.addClickListener(e2->{
					try {
						this.initializeBoard(xSize, ySize);
						fireEvent(new ResetEvent(this,false));
					} catch (TicTacToeException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				
				this.getContent().remove(button);
				this.getContent().add(restartButton);
				
			}
						
		});
		

		getContent().add(hl, mainLayout, button);
		
	}
	
	private boolean currentUserWon() {
		// first we check horizontally
		for (TicTacToeCell[] cells : cells) {
			if (checkRow(cells)) return true;
		}
		
		// next we check vertically
		for (TicTacToeCell[] cells : transposedCells) {
			if (checkRow(cells)) return true;
		}
		
		// TODO: Check diagonals
		
		return false;
	}

	private boolean checkRow(TicTacToeCell[] horizontalCells) {
		List<TicTacToeCell> winningCells = new ArrayList<TicTacToeCell>();
		for (TicTacToeCell ticTacToeCell : horizontalCells) {
			if (getCurrentUser().equals(ticTacToeCell.getCellValue()))
				winningCells.add(ticTacToeCell);
			else
				winningCells.clear();
			if (winningCells.size()==3) {
				fireEvent(new UserWinEvent(this,false,winningCells));
				return true;
			}
		}
		return false;
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

	public Registration addUserWinListener(ComponentEventListener<UserWinEvent> listener) {
		return addListener(UserWinEvent.class, listener);
	}

	public Registration addResetListener(ComponentEventListener<ResetEvent> listener) {
		return addListener(ResetEvent.class, listener);
	}

	@SuppressWarnings("serial")
	public class UserMoveEvent extends ComponentEvent<TicTacToeBoard> {
		public UserMoveEvent(TicTacToeBoard source, boolean fromClient) {
			super(source, fromClient);
		}
	}
	
	@SuppressWarnings("serial")
	public class UserWinEvent extends ComponentEvent<TicTacToeBoard> {
		
		private List<TicTacToeCell> winningCells;
		
		public UserWinEvent(TicTacToeBoard source, boolean fromClient, List<TicTacToeCell> winningCells) {
			super(source, fromClient);
			this.setWinningCells(winningCells);
		}
		
		public List<TicTacToeCell> getWinningCells() {
			return winningCells;
		}
		
		public void setWinningCells(List<TicTacToeCell> winningCells) {
			this.winningCells = winningCells;
		}
		
	}

	@SuppressWarnings("serial")
	public class ResetEvent extends ComponentEvent<TicTacToeBoard> {
		public ResetEvent(TicTacToeBoard source, boolean fromClient) {
			super(source, fromClient);
		}
	}

	@Override
	public void setParameter(BeforeNavigationEvent arg0, @OptionalParameter String parameter) {
        if(parameter == null) {
        	// there is no game id present, create a new one and then navigate to that url
        	
        } else {
        	// there is a game id present, if it can be converted to an integer, find and set the game
        	// if the game is non-existant or if the parameter is not an integer, create a new one and redirect to that one

        }		
	}
	
}
