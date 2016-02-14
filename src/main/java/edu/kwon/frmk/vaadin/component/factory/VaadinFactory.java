package edu.kwon.frmk.vaadin.component.factory;

import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootSpecification;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.combobox.ComboBox;
import edu.kwon.frmk.vaadin.component.combobox.ComboBox.RenderingListener;

/**
 * A factory creating Vaadin component
 * 
 * @author eduseashell
 * @since 0.0.1
 * @version 0.0.1
 */
public class VaadinFactory {
	
	private static final float TEXTFIELD_WIDTH = 180;
	private static final float DATEFIELD_WIDTH = TEXTFIELD_WIDTH;
	private static final float COMBOBOX_WIDTH = TEXTFIELD_WIDTH;
	
	private static final int NOTI_DELAY_MS = 3000;

    private VaadinFactory() { }

    //=================================================================
    //							Label
    //=================================================================

    /**
     * Create Label with caption
     * 
     * @param caption The label caption
     * @return Label
     */
    public static Label getLabel(String caption) {
    	return getLabel(caption, ContentMode.TEXT);
    }
    
    public static Label getLabel(String caption, ContentMode mode) {
    	return new Label(I18N.string(caption), mode);
    }

    //=================================================================
    //							Button
    //=================================================================

    /**
     * Create small button with caption
     * 
     * @param caption The button caption
     * @return Button
     */
    public static Button getButton(String caption) {
        Button btn = new Button(I18N.string(caption));
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

    //=================================================================
    //							TextField
    //=================================================================
    
    /**
     * Create TextField without caption
     * @return
     */
    public static TextField getTextField() {
    	return getTextField(null);
    }
    
    /**
     * Create TextField with caption
     * @param caption TextField caption
     * @return
     */
    public static TextField getTextField(String caption) {
        return getTextField(caption, TEXTFIELD_WIDTH);
    }

    /**
     * Create TextField with caption and width
     * @param caption
     * @param width TextField width in pixels
     * @return
     */
    public static TextField getTextField(String caption, float width) {
        return getTextField(caption, width, false);
    }
    
    public static TextField getTextField(String caption, boolean required) {
    	return getTextField(caption, TEXTFIELD_WIDTH, required);
    }
    
    public static TextField getTextField(String caption, float width, Resource icon) {
    	return getTextField(caption, width, false, icon);
    }

    /**
     * Create TextField with caption and width
     * @param caption
     * @param width TextField width in pixels
     * @param required True if the TextField is required, otherwise false
     * @return
     */
    public static TextField getTextField(String caption, float width, boolean required) {
        return getTextField(caption, width, required, null);
    }
    
    public static TextField getTextField(String caption, float width, boolean required, Resource icon) {
    	TextField txt = new TextField();
        txt.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        txt.setNullRepresentation(StringUtils.EMPTY);
        txt.setCaption(I18N.string(caption));
        txt.setWidth(width, Unit.PIXELS);
        txt.setRequired(required);
        if (icon != null) {
        	txt.setIcon(icon);
        	txt.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        }
        return txt;
    }
    
    //=================================================================
    //						PasswordField
    //=================================================================
    
    /**
     * Create passwordfield with caption
     * @param caption
     * @return
     */
    public static PasswordField getPasswordField(String caption) {
        return getPasswordField(caption, TEXTFIELD_WIDTH);
    }

    /**
     * Create passwordfield with caption and width
     * @param caption
     * @param width The width of passwordfield in pixels
     * @return
     */
    public static PasswordField getPasswordField(String caption, float width) {
        return getPasswordField(caption, width, false);
    }
    
    public static PasswordField getPasswordField(String caption, boolean required) {
    	return getPasswordField(caption, TEXTFIELD_WIDTH, required);
    }
    
    public static PasswordField getPasswordField(String caption, float width, Resource icon) {
    	return getPasswordField(caption, width, false, icon);
    }

    /**
     * Create passwordfield with caption
     * @param caption
     * @param width Create passwordfield with caption
     * @param required True if the passwordfield is required, otherwise false
     * @return
     */
    public static PasswordField getPasswordField(String caption, float width, boolean required) {
        return getPasswordField(caption, width, required, null);
    }
    
    public static PasswordField getPasswordField(String caption, float width, boolean required, Resource icon) {
    	PasswordField txt = new PasswordField(I18N.string(caption));
        txt.addStyleName(ValoTheme.TEXTFIELD_SMALL);
        txt.setNullRepresentation(StringUtils.EMPTY);
        txt.setWidth(width, Unit.PIXELS);
        txt.setRequired(required);
        if (icon != null) {
        	txt.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        	txt.setIcon(icon);
        }
        return txt;
    }
    
    //=================================================================
    //							Image
    //=================================================================
    
    public static Image getImage(String path, String alt) {
        return getImage(null, path, alt);
    }
    
    public static Image getImage(String caption, String path, String alt) {
        Image img = new Image(caption, new ThemeResource(path));
        img.setAlternateText(I18N.string(alt));
        return img;
    }
    
    //=================================================================
    //							Notification
    //=================================================================
    
    public static Notification getNotification(String caption, String desc) {
        return getNotification(caption, desc, Type.HUMANIZED_MESSAGE);
    }
    
    public static Notification getNotification(String caption, String desc, Type type) {
        return getNotification(caption, desc, type, null);
    }
    
    public static Notification getNotification(String caption, String desc, Type type, Resource icon) {
    	Notification noti = new Notification(caption, desc, type);
    	noti.setDelayMsec(NOTI_DELAY_MS);
    	noti.setIcon(icon);
    	return noti;
    }
    
    //=================================================================
    //							DateField
    //=================================================================

    public static DateField getDateField(String caption) {
        return getDateField(caption, DATEFIELD_WIDTH);
    }
    
    public static DateField getDateField(String caption, float width) {
        return getDateField(caption, width, false);
    }
    
    public static DateField getDateField(String caption, boolean required) {
    	return getDateField(caption, DATEFIELD_WIDTH, required);
    }
    
    public static DateField getDateField(String caption, float width, boolean required) {
        DateField df = new DateField(I18N.string(caption));
        df.addStyleName(ValoTheme.DATEFIELD_SMALL);
        df.setWidth(width, Unit.PIXELS);
        df.setRequired(required);
        return df;
    }
    
    //=================================================================
    //							Panel
    //=================================================================
    
    public static Panel getPanel() {
        return getPanel(null);
    }
    
    public static Panel getPanel(String caption) {
        Panel p = new Panel();
        if (StringUtils.isNotBlank(caption)) p.setCaption(I18N.string(caption));
        return p;
    }
    
    //=================================================================
    //							CheckBox
    //=================================================================
    
    public static CheckBox getCheckBox(String caption) {
    	return getCheckBox(caption, false);
    }
    
    public static CheckBox getCheckBox(String caption, boolean value) {
    	return new CheckBox(I18N.string(caption), value);
    }
    
    //=================================================================
    //							ComboBox
    //=================================================================
    
    public static <T extends RootEntity> ComboBox<T> getComboBox() {
    	return getComboBox(null);
    }
    
    public static <T extends RootEntity> ComboBox<T> getComboBox(String captionKey) {
    	ComboBox<T> cbo = new ComboBox<T>(captionKey);
    	cbo.setWidth(COMBOBOX_WIDTH, Unit.PIXELS);
    	return cbo;
    }
    
    public static <T extends RootEntity> ComboBox<T> getComboBox(String captionKey, Function<RootSpecification<T>, List<T>> loader, RenderingListener<T> renderingListener) {
    	ComboBox<T> cbo = new ComboBox<T>(captionKey, loader, renderingListener);
    	cbo.setWidth(COMBOBOX_WIDTH, Unit.PIXELS);
    	return cbo;
    }
    
    public static <T extends RootEntity> ComboBox<T> getComboBox(String captionKey, List<T> values, RenderingListener<T> renderingListener) {
    	ComboBox<T> cbo = new ComboBox<T>(captionKey, values, renderingListener);
    	cbo.setWidth(COMBOBOX_WIDTH, Unit.PIXELS);
    	return cbo;
    }
    
}
