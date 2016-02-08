package edu.kwon.frmk.vaadin.gui.menu;

import static edu.kwon.frmk.vaadin.util.helper.VaadinHelper.setUriFragment;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

import edu.kwon.frmk.common.data.jpa.repository.security.auth.AuthenticationService;
import edu.kwon.frmk.common.share.spring.context.AppContext;

/**
 * Abstract Menu Bar
 * @author eduseashell
 * @since 0.0.1
 * @version 0.0.1
 */
public abstract class AbstractMenuBar extends MenuBar {

	private static final long serialVersionUID = -8218648290671101496L;
	
	private AuthenticationService authService = AppContext.getBean(AuthenticationService.class);
	
	private LogOutListener logOutListener;
	
	public AbstractMenuBar() {
		addStyleName(ValoTheme.MENUBAR_BORDERLESS);
		addStyleName(ValoTheme.MENUBAR_SMALL);
		buildMenu();
	}
	
	/**
	 * Build and add menu item
	 */
	protected abstract void buildMenu();
	protected void logOut() {
		authService.logOut();
		LogOutListener listener = getLogOutListener();
		if (listener != null) {
			listener.onLogOutSuccess();
		}
	}
	
	public LogOutListener getLogOutListener() {
		return logOutListener;
	}

	public void setLogOutListener(final LogOutListener logOutListener) {
		this.logOutListener = logOutListener;
	}

	/**
	 * Menu Command
	 * @author eduseashell
	 * @since 0.0.1
	 * @version 0.0.1
	 */
	protected class MenuCommand implements Command {
		
		private static final long serialVersionUID = -4738395107718942614L;
		private String viewName;
		
		public MenuCommand(String viewName) {
			this.viewName = viewName;
		}

		public void menuSelected(MenuItem selectedItem) {
			setUriFragment(viewName);
		}
	}
	
	public interface LogOutListener {
		
		void onLogOutSuccess();
		
	}

}
