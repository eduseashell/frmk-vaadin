package edu.kwon.frmk.vaadin.util;

import com.vaadin.server.Page;

/**
 * @author eduseashell
 */
public class VaadinHelper {
	
	private VaadinHelper() { }
	
	public static void setUriFragment(String uri) {
		Page.getCurrent().setUriFragment("!" + (uri == null ? "" : uri));
	}

}
