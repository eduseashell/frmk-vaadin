package edu.kwon.frmk.vaadin.gui.user.profile.list;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.data.Item;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.data.jpa.repository.profile.Profile;
import edu.kwon.frmk.common.data.jpa.repository.user.User;
import edu.kwon.frmk.common.data.jpa.repository.user.profile.UserProfile;
import edu.kwon.frmk.common.data.jpa.repository.user.profile.UserProfileService;
import edu.kwon.frmk.common.share.spring.util.I18N;
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
	private UserProfileFormWindow formWindow;
	
	private Long userId;
	
	@Override
	protected void init() {
		super.init();
		setCaption(I18N.string("profiles"));
		getActionBar().setNewClickListener(this);
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
		item.getItemProperty(User.ID).setValue(profile.getId());
		item.getItemProperty(Profile.CODE).setValue(profile.getCode());
		item.getItemProperty(Profile.DESC).setValue(profile.getDesc());
		item.getItemProperty(User.ACTIVE).setValue(StringHelper.toActiveMsg(profile.getActive()));
	}
	
	public void assignValues(Long userId) {
		reset();
		if (userId != null) {
			this.userId = userId;
		}
	}
	
	public void reset() {
		this.userId = null;
	}
	
	@Override
	protected AbstractSearchPanel<UserProfile> onCreateSearchPanel() {
		return null;
	}
	
	@Override
	protected AuditEntityService<UserProfile> getService() {
		return userProfileService;
	}
	
	@Override
	public void onNewActionClicked() {
		formWindow.reset();
		formWindow.show();
	}

	@Override
	public void onEditActionClicked() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeleteActionClicked() {
		// TODO Auto-generated method stub
		
	}

}
