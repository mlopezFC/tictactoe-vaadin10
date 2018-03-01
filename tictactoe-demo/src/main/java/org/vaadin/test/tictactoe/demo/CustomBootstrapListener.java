/**
 * 
 */
package org.vaadin.test.tictactoe.demo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.vaadin.flow.server.BootstrapListener;
import com.vaadin.flow.server.BootstrapPageResponse;

/**
 * @author mlope
 *
 */
@SuppressWarnings("serial")
public class CustomBootstrapListener implements BootstrapListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.server.BootstrapListener#modifyBootstrapPage(com.vaadin.server.
	 * BootstrapPageResponse)
	 */
	@Override
	public void modifyBootstrapPage(BootstrapPageResponse response) {
		Document document = response.getDocument();

		Element head = document.head();

		head.appendChild(createMeta(document, "theme-color", "#3f51b5"));
		head.appendChild(createMeta(document, "mobile-web-app-capable", "yes"));
		head.appendChild(createMeta(document, "application-name", "TicTacToe Demo"));
		head.appendChild(createMeta(document, "apple-mobile-web-app-capable", "yes"));
		head.appendChild(createMeta(document, "apple-mobile-web-app-status-bar-style", "black-translucent"));
		head.appendChild(createMeta(document, "apple-mobile-web-app-title", "TicTacToe Demo"));
		head.appendChild(createMeta(document, "msapplication-TileImage", "images/manifest/icon-144x144.png"));
		head.appendChild(createMeta(document, "msapplication-TileColor", "#3f51b5"));
		head.appendChild(createMeta(document, "msapplication-tap-highlight", "no"));
		head.appendChild(createMeta(document, "viewport", "width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes"));
		
		head.appendChild(createLink(document, "icon", "images/favicon.ico"));
		head.appendChild(createLink(document, "manifest", "manifest.json"));
		head.appendChild(createLink(document, "apple-touch-icon", "images/manifest/icon-48x48.png"));
		head.appendChild(createLink(document, "apple-touch-icon", "images/manifest/icon-72x72.png", "72x72"));
		head.appendChild(createLink(document, "apple-touch-icon", "images/manifest/icon-96x96.png", "96x96"));
		head.appendChild(createLink(document, "apple-touch-icon", "images/manifest/icon-144x144.png", "144x144"));
		head.appendChild(createLink(document, "apple-touch-icon", "images/manifest/icon-192x192.png", "192x192"));
		
		
	}

	private Element createLink(Document document, String rel, String href) {
		return (createLink(document, rel, href, null));
	}
	
	private Element createLink(Document document, String rel, String href, String sizes) {
		Element link = document.createElement("link");
		if (sizes!=null)
			link.attr("sizes", sizes);
		link.attr("rel", rel);
		link.attr("href", href);
		return link;
	}
	
	private Element createMeta(Document document, String property, String content) {
		Element meta = document.createElement("meta");
		meta.attr("name", property);
		meta.attr("content", content);
		return meta;
	}
}
