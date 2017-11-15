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

import com.vaadin.router.Route;
import com.vaadin.ui.common.HtmlImport;
import com.vaadin.ui.layout.VerticalLayout;

/**
 * The main of the game
 */
@SuppressWarnings("serial")
@HtmlImport("styles.html")
@Route(value="view2",layout=AppLayout.class)
public class MainView2 extends VerticalLayout {

    public MainView2() {
    	setSizeFull();
    	
    	TicTacToeBoard board;
		try {
			board = new TicTacToeBoard(4, 4);
	        add(board);
	        setHorizontalComponentAlignment(Alignment.CENTER, board);
		} catch (TicTacToeException e) {
			e.printStackTrace();
		}

        
        
    }

}
