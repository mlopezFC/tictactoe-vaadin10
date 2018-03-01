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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.commons.io.IOUtils;

import com.vaadin.flow.server.RequestHandler;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.SessionInitEvent;
import com.vaadin.flow.server.SessionInitListener;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinResponse;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletConfiguration;
import com.vaadin.flow.server.VaadinSession;

/**
 * The main servlet for the application.
 * <p>
 * It is not mandatory to have the Servlet, since Flow will automatically register a Servlet to any app with at least one {@code @Route} to server root context.
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/*", name = "UIServlet", asyncSupported = true)
@VaadinServletConfiguration(usingNewRouting = true, productionMode = false)
public class Servlet extends VaadinServlet {
	
	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();

			getService().addSessionInitListener(new SessionInitListener() {

				@Override
				public void sessionInit(SessionInitEvent event) throws ServiceException {
					event.getSession().addRequestHandler(new RequestHandler() {

						@Override
						public boolean handleRequest(VaadinSession session, VaadinRequest request,
								VaadinResponse response) throws IOException {

							String pathInfo = request.getPathInfo();
							InputStream in = null;

							if (pathInfo.endsWith("sw.js")) {
								response.setContentType("application/javascript");
								in = getClass().getResourceAsStream("/sw.js");
							}

						if (in != null) {
							OutputStream out = response.getOutputStream();
								IOUtils.copy(in, out);
								in.close();
								out.close();

								return true;
							} else {

								return false;
							}
						}
					});
				}
			});
		}
}
