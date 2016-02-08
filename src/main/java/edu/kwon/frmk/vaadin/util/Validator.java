package edu.kwon.frmk.vaadin.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.DateField;

import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.common.share.util.DateUtil;

/**
 * Use to validate Vaadin component
 * @author eduseashell
 * @since v-0.0.1
 * @version 0.0.1
 */
public class Validator {
	
	private Validator() { }
	
	//==========================================================
	// 			Required Validation
	//==========================================================
	
	/**
	 * Validate the TextField with default error message:
	 *  "Component's caption is required!"
	 * @param textField
	 * @return
	 */
	public static boolean validateRequiredTextField(AbstractTextField textField) {
		String fieldName = StringUtils.isNotBlank(textField.getCaption()) ? textField.getCaption() : I18N.string("the.field");
		return validateRequiredTextField(textField, fieldName + I18N.string("is.required"));
	}
	
	/**
	 * Validate the TextField with custom error message
	 * @param textField
	 * @param errorMessage
	 * @return
	 */
	public static boolean validateRequiredTextField(AbstractTextField textField, String errorMessage) {
		boolean valid = false;
		String value = textField.getValue();
		if (StringUtils.isNotBlank(value)) {
			valid = true;
			textField.setComponentError(null);
		} else {
			textField.setComponentError(new UserError(errorMessage));
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
		String fieldName = StringUtils.isNotBlank(selectField.getCaption()) ? selectField.getCaption() : I18N.string("the.selection");
		return validateRequiredSelectField(selectField, fieldName + I18N.string("is.required"));
	}
	
	/**
	 * Validate SelectField with custom error message
	 * @param selectField
	 * @param errorMessage
	 * @return
	 */
	public static boolean validateRequiredSelectField(AbstractSelect selectField, String errorMessage) {
		boolean valid = false;
		Object value = selectField.getValue();
		if (value != null) {
			valid = true;
			selectField.setComponentError(null);
		} else {
			selectField.setComponentError(new UserError(errorMessage));
		}
		return valid;
	}
	
	/**
	 * Validate DateField with default message:
	 *  "DateField's caption is required!"
	 * @param field
	 * @return
	 */
	public static boolean validateRequiredDateField(DateField field) {
		String fieldName = StringUtils.isNotBlank(field.getCaption()) ? field.getCaption() : I18N.string("the.datefield");
		return validateRequiredDateField(field, fieldName + I18N.string("is.required"));
	}
	
	/**
	 * Validate DateField with custom error message
	 * @param field
	 * @param errorMessage
	 * @return
	 */
	public static boolean validateRequiredDateField(DateField field, String errorMessage) {
		boolean valid = false;
		Date value = field.getValue();
		if (value != null) {
			valid = true;
			field.setComponentError(null);
		} else {
			field.setComponentError(new UserError(errorMessage));
		}
		return valid;
	}
	
	//==========================================================
	// 			Length, Range, String Validation
	//==========================================================
	
	/**
	 * Validate min length of field value with default error message
	 * @param field
	 * @param min
	 * @return
	 */
	public static boolean validateMinStringLength(AbstractTextField field, int min) {
		String msg = "min.length.allowed";
		return validateMinStringLength(field, min, I18N.string(msg, String.valueOf(min)));
	}
	
	/**
	 * Validate min length of field value
	 * @param field
	 * @param min
	 * @param errorMsg
	 * @return
	 */
	public static boolean validateMinStringLength(AbstractTextField field, int min, String errorMsg) {
		boolean valid = false;
		int value = StringUtils.isNotEmpty(field.getValue()) ? field.getValue().length() : 0;
		if (value >= min) {
			valid = true;
			field.setComponentError(null);
		} else {
			field.setComponentError(new UserError(errorMsg));
		}
		return valid;
	}
	
	/**
	 * Validate whether field1 and field2 has equal value
	 * @param field1
	 * @param field2
	 * @param errorMsg
	 * @return
	 */
	public static boolean validateEqualString(AbstractTextField field1, AbstractTextField field2, String errorMsg) {
		boolean valid = false;
		String value1 = field1.getValue();
		String value2 = field2.getValue();
		if (value1.equals(value2)) {
			valid = true;
			field1.setComponentError(null);
			field2.setComponentError(null);
		} else {
			UserError error = new UserError(errorMsg);
			field1.setComponentError(error);
			field2.setComponentError(error);
		}
		return valid;
	}
	
	/**
	 * Validate if the DateField is before the date provided
	 * @param field
	 * @param date
	 * @return
	 */
	public static boolean validateDateFieldBefore(DateField field, Date date) {
		String msg = I18N.string("date.field.must.before", DateUtil.format(date));
		return validateDateFieldBefore(field, date, msg);
	}
	
	/**
	 * Validate whether the DateField is before the date provided
	 * @param field
	 * @param date
	 * @param errorMsg
	 * @return
	 */
	public static boolean validateDateFieldBefore(DateField field, Date date, String errorMsg) {
		boolean valid = false;
		Date value = field.getValue();
		if (value.before(date)) {
			valid = true;
			field.setComponentError(null);
		} else {
			field.setComponentError(new UserError(errorMsg));
		}
		return valid;
	}
	
	//==========================================================
	// 			E-Mail Validation
	//==========================================================
	
	/**
	 * Validate whether E-Mail is valid. Empty return true
	 * @param field
	 * @return
	 */
	public static boolean validateEMailTextField(AbstractTextField field) {
		String msg = I18N.string("invalid.email.address");
		return validateEMailTextField(field, msg);
	}
	
	/**
	 * Validate whether E-Mail is valid. Empty return true
	 * @param field
	 * @param errorMsg
	 * @return
	 */
	public static boolean validateEMailTextField(AbstractTextField field, String errorMsg) {
		boolean valid = false;
		String value = field.getValue();
		EmailValidator validator = EmailValidator.getInstance();
		if (StringUtils.isEmpty(value) || validator.isValid(value)) {
			valid = true;
			field.setComponentError(null);
		} else {
			field.setComponentError(new UserError(errorMsg));
		}
		return valid;
	}

}
