package edu.kwon.frmk.vaadin.gui.login;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import edu.kwon.frmk.vaadin.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.util.SeashellTheme;

/**
 * Base Login Panel
 * @author eduseashell
 * @since 0.0.1
 * @version 0.0.1
 */
public class DefaultLoginPanel extends AbstractLoginView {

	private static final long serialVersionUID = -1538194883374858783L;

	/**
	 * @see edu.kwon.frmk.vaadin.gui.login.AbstractLoginView#buildLoginForm()
	 */
	@Override
	protected Component buildLoginForm() {
		final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName(SeashellTheme.LOGIN_PANEL);

        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        return loginPanel;
	}
	
	private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.addStyleName(SeashellTheme.LOGIN_FIELDS);

        final TextField username = VaadinFactory.getTextField(getUserNameKey());
        username.setIcon(FontAwesome.USER);
        username.focus();

        final PasswordField password = VaadinFactory.getPasswordField(getPasswordKey());
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        final Button signin = VaadinFactory.getButtonPrimary(getSignInKey());
        signin.setClickShortcut(KeyCode.ENTER);

        fields.addComponents(username, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        signin.addClickListener(new ClickListener() {
			private static final long serialVersionUID = -1719928773666434856L;
			public void buttonClick(ClickEvent event) {
				signIn(username.getValue(), password.getValue());
			}
        });
        return fields;
    }
	
    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName(SeashellTheme.LOGIN_LABELS);

        Label welcome = VaadinFactory.getLabel(getLogInWelcomeKey());
        welcome.setSizeUndefined();
        welcome.addStyleName(ValoTheme.LABEL_H4);
        welcome.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(welcome);

        Label title = VaadinFactory.getLabel(getAppNameKey());
        title.setSizeUndefined();
        title.addStyleName(ValoTheme.LABEL_H3);
        title.addStyleName(ValoTheme.LABEL_LIGHT);
        labels.addComponent(title);
        return labels;
    }
    
	@Override
	protected void signIn(String user, String password) {
		
	}
	
	protected String getUserNameKey() {
		return "user.name";
	}
	
	protected String getPasswordKey() {
		return "password";
	}
	
	protected String getSignInKey() {
		return "sign.in";
	}
	
	protected String getLogInWelcomeKey() {
		return "log.in.welcome";
	}
	
	protected String getAppNameKey() {
		return "app.name";
	}

}
