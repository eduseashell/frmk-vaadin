package edu.kwon.frmk.vaadin.gui.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.ui.AbstractComponentContainer;

import edu.kwon.frmk.common.data.jpa.repository.user.User;
import edu.kwon.frmk.common.data.jpa.repository.user.UserService;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractMainLayout.TableDoubleClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractTabSheetLayout;
import edu.kwon.frmk.vaadin.gui.user.form.UserFormLayout;
import edu.kwon.frmk.vaadin.gui.user.list.UserMainLayout;
import edu.kwon.frmk.vaadin.gui.user.profile.list.UserProfileMainFormLayout;

/**
 * User Tab Sheet
 * @author eduseashell
 *
 */
@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(UserTabSheet.VIEW_NAME)
public class UserTabSheet extends AbstractTabSheetLayout<User> implements TableDoubleClickListener {

	private static final long serialVersionUID = -5259413296535848102L;
	
	public static final String VIEW_NAME = "views/users";
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserMainLayout mainLayout;
	@Autowired
	private UserFormLayout formLayout;
	@Autowired
	private UserProfileMainFormLayout profileLayout;

	@Override
	public void postConstruct() {
		super.postConstruct();
		mainLayout.setTableDoubleClickListener(this);
		formLayout.setMainTabSheet(this);
	}
	
	@Override
	protected AbstractComponentContainer buildMainLayout() {
		mainLayout.getActionBar().setNewClickListener(this);
		mainLayout.getActionBar().setEditClickListener(this);
		mainLayout.getActionBar().setDeleteClickListener(this);
		mainLayout.getActionBar().setRefreshClickListener(this);
		return mainLayout;
	}

	@Override
	public void onNewActionClicked() {
		formLayout.reset();
		addFormLayout(formLayout);
	}

	@Override
	protected void onEditItem(Long id) {
		formLayout.assignValues(id);
		profileLayout.assignValues(id);
		addFormLayout(formLayout);
		addFormLayout(profileLayout);
		setSelectedTab(formLayout);
	}
	
	@Override
	protected void onRefreshMainLayout() {
		mainLayout.refresh();
	}

	@Override
	protected Long getSelectedItemId() {
		return mainLayout.getSelectedItemId();
	}

	@Override
	protected UserService getService() {
		return userService;
	}

	@Override
	public void onTableDoubleClick(Long selectedItemId) {
		onEditItem(selectedItemId);
	}

}
