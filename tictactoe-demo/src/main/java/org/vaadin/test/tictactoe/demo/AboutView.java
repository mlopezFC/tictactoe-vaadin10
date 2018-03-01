package org.vaadin.test.tictactoe.demo;
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


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main of the game
 */
@SuppressWarnings("serial")
@HtmlImport("styles.html")
@Route(value="about",layout=AppLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
    	setSizeFull();
    	
    	Button play = new Button("Play!");
    	
    	play.addClickListener(e->UI.getCurrent().navigateTo(""));
        
        add(play);
    }

}
