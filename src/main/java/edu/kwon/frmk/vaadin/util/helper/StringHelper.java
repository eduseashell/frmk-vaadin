package edu.kwon.frmk.vaadin.util.helper;

import edu.kwon.frmk.common.share.spring.util.I18N;

/**
 * @author eduseashell
 */
public class StringHelper {
	
	/**
	 * Active for true otherwise Inactive
	 * @param active
	 * @return
	 */
	public static String toActiveMsg(boolean active) {
		return active ? I18N.string("active") : I18N.string("inactive");
	}

}
