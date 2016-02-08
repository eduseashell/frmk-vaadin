package edu.kwon.frmk.vaadin.gui.profile.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.data.jpa.repository.profile.Profile;
import edu.kwon.frmk.common.data.jpa.repository.profile.ProfileService;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractFormLayout;

/**
 * @author eduseashell
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProfileFormLayout extends AbstractFormLayout<Profile> {

	private static final long serialVersionUID = -8215891236803167616L;
	
	@Autowired
	private ProfileService profileService;
	
	private TextField txtCode;
	private TextField txtDesc;
	
	private CheckBox cbActive;
	
	@Override
	protected void init() {
		super.init();
		setCaption(I18N.string("profile"));
//		setIcon(FontAwesome.USER);
	}

	@Override
	protected AbstractComponentContainer initGUI() {
		initControls();
		
		FormLayout content = new FormLayout();
		content.addComponent(txtCode);
		content.addComponent(txtDesc);
		content.addComponent(cbActive);
		
		return content;
	}
	
	private void initControls() {
		txtCode = VaadinFactory.getTextField("code", true);
		txtDesc = VaadinFactory.getTextField("desc");
		cbActive = VaadinFactory.getCheckBox("active");
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
	protected AuditEntityService<Profile> getService() {
		return profileService;
	}

}
