package edu.kwon.frmk.vaadin.util;

import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractTextField;

/**
 * Use to validate Vaadin component
 * @author bunlongtaing
 * @since v-0.0.1
 * @version 0.0.1
 */
public class ValidationUtil {
	
	private ValidationUtil() {}
	
	/**
	 * Validate the TextField with default error message:
	 *  "Component's caption is required!"
	 * @param textField
	 * @return
	 */
	public static boolean validateRequiredTextField(AbstractTextField textField) {
		String fieldName = textField != null ? textField.getCaption() : "Field";
		return validateRequiredTextField(textField, fieldName + " is required!");
	}
	
	/**
	 * Validate the TextField with custom error message
	 * @param textField
	 * @param errorMessage
	 * @return
	 */
	public static boolean validateRequiredTextField(AbstractTextField textField, String errorMessage) {
		boolean valid = false;
		if (textField != null) {
			String value = textField.getValue();
			if (value != null && !"".equals(value.trim())) {
				valid = true;
				textField.setComponentError(null);
			} else {
				textField.setComponentError(new UserError(errorMessage));
			}
		}
		return valid;
	}
	
	/**
	 * Validate SelectField with default message:
	 *  "SelectField's caption is required!"
	 * @param selectField
	 * @return
	 */
	public static boolean validateRequiredSelectField(AbstractSelect selectField) {
		String fieldName = selectField != null ? selectField.getCaption() : "Selection";
		return validateRequiredSelectField(selectField, fieldName + " is required!");
	}
	
	/**
	 * Validate SelectField with custom error message
	 * @param selectField
	 * @param errorMessage
	 * @return
	 */
	public static boolean validateRequiredSelectField(AbstractSelect selectField, String errorMessage) {
		boolean valid = false;
		if (selectField != null) {
			Object value = selectField.getValue();
			if (value != null) {
				valid = true;
				selectField.setComponentError(null);
			} else {
				selectField.setComponentError(new UserError(errorMessage));
			}
		}
		return valid;
	}

}
