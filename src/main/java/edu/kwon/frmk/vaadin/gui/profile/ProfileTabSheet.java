package edu.kwon.frmk.vaadin.gui.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;

import com.vaadin.ui.AbstractComponentContainer;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.data.jpa.repository.profile.Profile;
import edu.kwon.frmk.common.data.jpa.repository.profile.ProfileService;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractMainLayout.TableDoubleClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractTabSheetLayout;
import edu.kwon.frmk.vaadin.gui.profile.form.ProfileFormLayout;
import edu.kwon.frmk.vaadin.gui.profile.list.ProfileMainLayout;

/**
 * @author eduseashell
 */
@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@VaadinView(ProfileTabSheet.VIEW_NAME)
public class ProfileTabSheet extends AbstractTabSheetLayout<Profile> implements TableDoubleClickListener {

	private static final long serialVersionUID = 6348434881741030672L;
	public static final String VIEW_NAME = "views/profiles";
	
	@Autowired
	private ProfileService profileService;
	@Autowired
	private ProfileMainLayout mainLayout;
	@Autowired
	private ProfileFormLayout formLayout;
	
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
		addFormLayout(formLayout);
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
	protected AuditEntityService<Profile> getService() {
		return profileService;
	}

	@Override
	public void onTableDoubleClick(Long selectedItemId) {
		onEditItem(selectedItemId);
	}

}
