/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.test.tictactoe.demo;

import org.vaadin.test.tictactoe.TicTacToeBoard;
import org.vaadin.test.tictactoe.TicTacToeException;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The main of the game
 */
@SuppressWarnings("serial")
@HtmlImport("styles.html")
@Route(value="",layout=AppLayout.class)
@JavaScript("../js/app.js")
public class GameView extends VerticalLayout {
	
	private Div gameLog;

    public GameView() {
    	
    	try {
			setWidth("100%");
			
			TicTacToeBoard board = new TicTacToeBoard(3, 3);
			
			gameLog = new Div();
			gameLog.addClassName("gameLog");
			
			addLogLine("GameView Started.");
			
			board.addUserMoveListener(e -> addLogLine("User " + e.getSource().getCurrentUser() + " turn."));
			
			board.addUserWinListener(e -> addLogLine("User " + board.getCurrentUser() + " won!"));
			
			board.addResetListener(e -> gameLog.removeAll());

			add(board,gameLog);
			
			setHorizontalComponentAlignment(Alignment.CENTER, board);
			setHorizontalComponentAlignment(Alignment.CENTER, gameLog);
			
		} catch (TicTacToeException e) {
			e.printStackTrace();
		}
        
    }

	private void addLogLine(String string) {
		gameLog.add(new H4(string));
	}

}
