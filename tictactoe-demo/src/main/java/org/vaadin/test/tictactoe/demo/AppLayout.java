package org.vaadin.test.tictactoe.demo;

import org.vaadin.test.tictactoe.demo.AppLayout.AppLayoutModel;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@SuppressWarnings("serial")
@Tag("app-layout")
@HtmlImport("frontend://app-layout/app-layout.html")
@Theme(Lumo.class)
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
