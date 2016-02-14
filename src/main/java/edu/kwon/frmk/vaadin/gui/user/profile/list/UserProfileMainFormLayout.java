package edu.kwon.frmk.vaadin.gui.user.profile.list;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.data.Item;
import com.vaadin.server.Page;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootSpecification;
import edu.kwon.frmk.common.data.jpa.repository.profile.Profile;
import edu.kwon.frmk.common.data.jpa.repository.user.UserService;
import edu.kwon.frmk.common.data.jpa.repository.user.profile.UserProfile;
import edu.kwon.frmk.common.data.jpa.repository.user.profile.UserProfileService;
import edu.kwon.frmk.common.data.jpa.repository.user.profile.UserProfileSpecification;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.component.table.Column;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractMainLayout;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractSearchPanel;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.DeleteClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.EditClickListener;
import edu.kwon.frmk.vaadin.gui.layout.crud.listener.NewClickListener;
import edu.kwon.frmk.vaadin.gui.user.profile.window.UserProfileFormWindow;
import edu.kwon.frmk.vaadin.util.helper.StringHelper;

/**
 * List of profiles of user
 * @author eduseashell
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserProfileMainFormLayout extends AbstractMainLayout<UserProfile> implements NewClickListener, EditClickListener, DeleteClickListener {

	private static final long serialVersionUID = 7035143453511397972L;
	
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserProfileFormWindow formWindow;
	
	private Long userId;
	
	@Override
	protected void init() {
		super.init();
		setCaption(I18N.string("profiles"));
		formWindow.setPostSaveListener(() -> refresh());
		getActionBar().setNewClickListener(this);
		getActionBar().setEditClickListener(this);
		getActionBar().setDeleteClickListener(this);
		getActionBar().setRefreshClickListener(() -> refresh());
	}

	@Override
	protected List<Column> buildTableColumn() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(UserProfile.ID, I18N.string("id"), Long.class, 70));
		columns.add(new Column(UserProfile.PROFILE + Profile.CODE, I18N.string("code"), String.class, 200));
		columns.add(new Column(UserProfile.PROFILE + Profile.DESC, I18N.string("desc"), String.class, 300));
		columns.add(new Column(UserProfile.ACTIVE, I18N.string("status"), String.class, 70));
		return columns;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void renderRow(Item item, UserProfile userProfile) {
		Profile profile = userProfile.getProfile();
		item.getItemProperty(UserProfile.ID).setValue(userProfile.getId());
		item.getItemProperty(UserProfile.PROFILE + Profile.CODE).setValue(profile.getCode());
		item.getItemProperty(UserProfile.PROFILE + Profile.DESC).setValue(profile.getDesc());
		item.getItemProperty(UserProfile.ACTIVE).setValue(StringHelper.toActiveMsg(userProfile.getActive()));
	}
	
	public void assignValues(Long userId) {
		reset();
		if (userId != null) {
			this.userId = userId;
		}
	}
	
	public void reset() {
		this.userId = null;
		table.removeAllItems();
	}
	
	@Override
	protected AbstractSearchPanel<UserProfile> onCreateSearchPanel() {
		return null;
	}
	
	@Override
	protected RootSpecification<UserProfile> getSpecification() {
		UserProfileSpecification spec = new UserProfileSpecification();
		spec.setUserId(userId);
		return spec;
	}
	
	@Override
	public void onNewActionClicked() {
		formWindow.reset();
		formWindow.setUser(userService.findById(userId));
		formWindow.show();
	}

	@Override
	public void onEditActionClicked() {
		if (getSelectedItemId() == null) {
			VaadinFactory.getNotification(I18N.string("edit"), I18N.string("msg.info.to.edit"))
				.show(Page.getCurrent());
		} else {
			formWindow.assignValues(getSelectedItemId());
			formWindow.setUser(userService.findById(userId));
			formWindow.show();
		}
	}

	@Override
	public void onDeleteActionClicked() {
		if (getSelectedItemId() == null) {
			VaadinFactory.getNotification(I18N.string("delete"), I18N.string("msg.info.to.delete"))
				.show(Page.getCurrent());
		} else {
			getService().delete(getSelectedItemId());
			refresh();
		}
	}
	
	@Override
	protected AuditEntityService<UserProfile> getService() {
		return userProfileService;
	}

}
