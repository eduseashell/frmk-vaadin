package edu.kwon.frmk.vaadin.gui.user.profile.window;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.FormLayout;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootSpecification;
import edu.kwon.frmk.common.data.jpa.repository.profile.Profile;
import edu.kwon.frmk.common.data.jpa.repository.profile.ProfileService;
import edu.kwon.frmk.common.data.jpa.repository.user.profile.UserProfile;
import edu.kwon.frmk.common.share.spring.context.AppContext;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.combobox.ComboBox;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractFormWindow;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class UserProfileFormWindow extends AbstractFormWindow<UserProfile> {

	private static final long serialVersionUID = 744930626410810273L;
	
	private ComboBox<Profile> cbxProfile;
	
	@Override
	protected void init() {
		super.init();
		setCaption(I18N.string("profile"));
	}

	@Override
	protected AbstractComponentContainer initGUI() {
		// TODO user profile window temp
		ProfileService profileService = AppContext.getAppContext().getBean(ProfileService.class);
		
		cbxProfile = new ComboBox<Profile>();
		cbxProfile.setCaption(I18N.string("profile"));
		cbxProfile.setRenderingListener(profile -> profile.getDesc());
		cbxProfile.setEntities(profileService.findAll(new RootSpecification<Profile>()));
		
		FormLayout content = new FormLayout();
		content.setSizeUndefined();
		content.addComponent(cbxProfile);
		
		return content;
	}

	@Override
	public void assignValues(Long entityId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillDataToControls() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillDataToEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected AuditEntityService<UserProfile> getService() {
		// TODO Auto-generated method stub
		return null;
	}

}
