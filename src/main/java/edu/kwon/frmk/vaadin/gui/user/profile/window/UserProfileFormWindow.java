package edu.kwon.frmk.vaadin.gui.user.profile.window;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.FormLayout;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.data.jpa.repository.profile.Profile;
import edu.kwon.frmk.common.data.jpa.repository.profile.ProfileService;
import edu.kwon.frmk.common.data.jpa.repository.user.User;
import edu.kwon.frmk.common.data.jpa.repository.user.profile.UserProfile;
import edu.kwon.frmk.common.data.jpa.repository.user.profile.UserProfileService;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.combobox.ComboBox;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractFormWindow;
import edu.kwon.frmk.vaadin.util.Validator;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserProfileFormWindow extends AbstractFormWindow<UserProfile> {

	private static final long serialVersionUID = 744930626410810273L;
	
	@Autowired
	private UserProfileService userProfileService;
	@Autowired
	private ProfileService profileService;
	private User user;
	
	private ComboBox<Profile> cbxProfile;
	
	@Override
	protected void init() {
		super.init();
		setCaption(I18N.string("profile"));
	}

	@Override
	protected AbstractComponentContainer initGUI() {
		cbxProfile = VaadinFactory.getComboBox("profile", s -> profileService.findAll(s), p -> p.getDesc());
		
		FormLayout content = new FormLayout();
		content.setSizeUndefined();
		content.addComponent(cbxProfile);
		
		return content;
	}

	@Override
	public void assignValues(Long entityId) {
		reset();
		if (entityId != null) {
			entity = userProfileService.findById(entityId);
			fillDataToControls();
		}
	}

	@Override
	protected void fillDataToControls() {
		cbxProfile.setEntity(entity.getProfile());
	}

	@Override
	protected void fillDataToEntity() {
		if (entity == null) {
			entity = new UserProfile();
		}
		entity.setUser(user);
		entity.setProfile(cbxProfile.getEntity());
	}

	@Override
	protected boolean validate() {
		if (user == null) {
			throw new IllegalStateException("User cannot be null");
		}
		boolean valid = true;
		valid &= Validator.validateRequiredSelectField(cbxProfile);
		return valid;
	}
	
	@Override
	public void reset() {
		super.reset();
		user = null;
		cbxProfile.setEntity(null);
	}

	@Override
	protected AuditEntityService<UserProfile> getService() {
		return userProfileService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
