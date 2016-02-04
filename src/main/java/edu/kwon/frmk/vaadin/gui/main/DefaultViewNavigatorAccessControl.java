package edu.kwon.frmk.vaadin.gui.main;

import com.vaadin.navigator.ViewChangeListener;

import edu.kwon.frmk.common.data.jpa.repository.security.auth.AuthenticationService;
import edu.kwon.frmk.common.share.spring.context.AppContext;
import edu.kwon.frmk.vaadin.util.helper.VaadinHelper;

/**
 * View navigation handler
 * @author eduseashell
 * @since 0.0.1
 * @version 0.0.1
 */
public class DefaultViewNavigatorAccessControl implements ViewChangeListener {

	private static final long serialVersionUID = -3789628007247201335L;

	private AuthenticationService authService = AppContext.getBean(AuthenticationService.class);
	private AbstractUI ui;
	
	public DefaultViewNavigatorAccessControl(AbstractUI ui) {
		this.ui = ui;
	}

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		if (AbstractUI.LOGIN_PANEL_NAME == null) {
			throw new IllegalStateException("UI Login Panel Name cannot be null.");
		}
		if (AbstractUI.LOGIN_PANEL_NAME.equals(event.getViewName())) {
			if (isLogIn()) {
				// User already login, go to welcome page
				VaadinHelper.setUriFragment(AbstractUI.WELCOME_PANEL_NAME);
				return false;
			}
			ui.prepareForLoginPanel();
		} else {
			if (!isLogIn()) {
				VaadinHelper.setUriFragment(AbstractUI.LOGIN_PANEL_NAME);
				return false;
			}
			ui.prepareForViewPanel();
		}
		return true;
	}
	
	/**
	 * Whether the user is already login
	 * @return
	 */
	protected boolean isLogIn() {
		return authService.isAuthenticated();
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) { }

}
