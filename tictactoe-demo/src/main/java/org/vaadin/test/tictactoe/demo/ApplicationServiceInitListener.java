package org.vaadin.test.tictactoe.demo;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

@SuppressWarnings("serial")
public class ApplicationServiceInitListener implements VaadinServiceInitListener {

	@Override
    public void serviceInit(ServiceInitEvent event) {
        event.addBootstrapListener(new CustomBootstrapListener());

        event.addDependencyFilter((dependencies, filterContext) -> {
            // DependencyFilter to add/remove/change dependencies sent to
            // the client
            return dependencies;
        });

        event.addRequestHandler((session, request, response) -> {
            // RequestHandler to change how responses are handled
            return false;
        });
    }

}