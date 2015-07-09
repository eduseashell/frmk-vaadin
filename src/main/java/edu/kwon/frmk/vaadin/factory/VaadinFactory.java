package edu.kwon.frmk.vaadin.factory;

import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import edu.kwon.frmk.common.share.spring.util.I18NUtil;

/**
 * A factory creating Vaadin component
 * 
 * @author Bunlong Taing
 * @since 0.0.1
 * @version 0.0.1
 */
public class VaadinFactory {

	private VaadinFactory() {
	}

	// ============== Label ================== //

	/**
	 * Create Label with caption
	 * 
	 * @param caption The label caption
	 * @return Label
	 */
	public static Label getLabel(String caption) {
		return new Label(I18NUtil.string(caption));
	}

	// ============== Button ================== //

	/**
	 * Create small button with caption
	 * 
	 * @param caption The button caption
	 * @return Button
	 */
	public static Button getButton(String caption) {
		Button btn = new Button(I18NUtil.string(caption));
		btn.setStyleName(ValoTheme.BUTTON_SMALL);
		return btn;
	}

	/**
	 * Create small button wiht caption and image
	 * 
	 * @param caption
	 * @param image
	 * @return
	 */
	public static Button getButton(String caption, String image) {
		Button btn = getButton(caption);
		btn.setIcon(new ThemeResource(image));
		return btn;
	}

	/**
	 * Create primary button (blue color)
	 * 
	 * @param caption
	 * @return
	 */
	public static Button getButtonPrimary(String caption) {
		Button btn = getButton(caption);
		btn.addStyleName(ValoTheme.BUTTON_PRIMARY);
		return btn;
	}

	/**
	 * Create danger button (red color)
	 * 
	 * @param caption
	 * @return
	 */
	public static Button getButtonDanger(String caption) {
		Button btn = getButton(caption);
		btn.addStyleName(ValoTheme.BUTTON_DANGER);
		return btn;
	}

	/**
	 * Create friendly button (green color)
	 * 
	 * @param caption
	 * @return
	 */
	public static Button getButtonFriendly(String caption) {
		Button btn = getButton(caption);
		btn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		return btn;
	}

	// ============== Textfield ================== //
	
	/**
	 * Create Textfield with caption
	 * @param caption TextField caption
	 * @return
	 */
	public static TextField getTextField(String caption) {
		TextField txt = new TextField(I18NUtil.string(caption));
		txt.addStyleName(ValoTheme.TEXTFIELD_SMALL);
		txt.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		return txt;
	}

	/**
	 * Create Textfield with caption and width
	 * @param caption
	 * @param width Textfield width in pixels
	 * @return
	 */
	public static TextField getTextField(String caption, float width) {
		TextField txt = getTextField(caption);
		txt.setWidth(width, Unit.PIXELS);
		return txt;
	}

	/**
	 * Create Textfield with caption and width
	 * @param caption
	 * @param width Textfield width in pixels
	 * @param required True if the textfield is required, otherwise false
	 * @return
	 */
	public static TextField getTextField(String caption, float width, boolean required) {
		TextField txt = getTextField(caption, width);
		txt.setRequired(required);
		return txt;
	}
	
	// ============== Passwordfield ================== //
	
	/**
	 * Create passwordfield with caption
	 * @param caption
	 * @return
	 */
	public static PasswordField getPasswordField(String caption) {
		PasswordField txt = new PasswordField(I18NUtil.string(caption));
		txt.addStyleName(ValoTheme.TEXTFIELD_SMALL);
		txt.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
		return txt;
	}

	/**
	 * Create passwordfield with caption and width
	 * @param caption
	 * @param width The width of passwordfield in pixels
	 * @return
	 */
	public static PasswordField getPasswordField(String caption, float width) {
		PasswordField txt = getPasswordField(caption);
		txt.setWidth(width, Unit.PIXELS);
		return txt;
	}

	/**
	 * Create passwordfield with caption
	 * @param caption
	 * @param width Create passwordfield with caption
	 * @param required True if the passwordfield is required, otherwise false
	 * @return
	 */
	public static PasswordField getPasswordField(String caption, float width, boolean required) {
		PasswordField txt = getPasswordField(caption, width);
		txt.setRequired(required);
		return txt;
	}
	
	// ============== Image ================== //
	
	public static Image getImage(String path, String alt) {
		return getImage(null, path, alt);
	}
	
	public static Image getImage(String caption, String path, String alt) {
		Image img = new Image(caption, new ThemeResource(path));
		img.setAlternateText(I18NUtil.string(alt));
		return img;
	}

}
