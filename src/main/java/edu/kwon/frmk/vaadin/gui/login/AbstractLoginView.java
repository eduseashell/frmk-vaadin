package edu.kwon.frmk.vaadin.gui.login;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;

import edu.kwon.frmk.vaadin.gui.layout.old.AbstractLayout;

/**
 * Abstract Login View:
 * An abstract layout for login functionality
 * @author eduseashell
 * @since 0.0.1
 * @version 0.0.1
 */
public abstract class AbstractLoginView extends AbstractLayout {

	private static final long serialVersionUID = 2689488459356743116L;

	public AbstractLoginView() {
        setSizeFull();

        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }

	/**
	 * Build a login form
	 * @return
	 */
    protected abstract Component buildLoginForm();
    
    /**
     * Sign user in
     * @param user
     * @param password
     */
    protected abstract void signIn(String user, String password);

}
