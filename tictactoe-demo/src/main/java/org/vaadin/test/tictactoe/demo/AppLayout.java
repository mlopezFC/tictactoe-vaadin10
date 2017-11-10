package org.vaadin.test.tictactoe.demo;

import org.vaadin.test.tictactoe.demo.AppLayout.AppLayoutModel;

import com.vaadin.flow.model.TemplateModel;
import com.vaadin.router.RouterLayout;
import com.vaadin.ui.Tag;
import com.vaadin.ui.common.HasComponents;
import com.vaadin.ui.common.HtmlImport;
import com.vaadin.ui.polymertemplate.PolymerTemplate;

@SuppressWarnings("serial")
@Tag("app-layout")
@HtmlImport("frontend://app-layout/app-layout.html")
public class AppLayout extends PolymerTemplate<AppLayoutModel> implements HasComponents, RouterLayout {

	/**
	 * A model interface that defined the data that is communicated between the
	 * server and the client.
	 */
	public interface AppLayoutModel extends TemplateModel {
		
		String getNarrow();
		
		void setNarrow();

	}
	
}
