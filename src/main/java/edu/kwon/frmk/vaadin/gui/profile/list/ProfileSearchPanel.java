package edu.kwon.frmk.vaadin.gui.profile.list;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import edu.kwon.frmk.common.data.jpa.repository.entities.base.BaseSpecification;
import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootSpecification;
import edu.kwon.frmk.common.data.jpa.repository.profile.Profile;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractSearchPanel;

/**
 * @author eduseashell
 */
@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProfileSearchPanel extends AbstractSearchPanel<Profile> {

	private static final long serialVersionUID = 5299434165591979835L;
	
	private TextField txtCode;

	@Override
	protected Component initGUI() {
		Label lblCode = VaadinFactory.getLabel("code");
		txtCode = VaadinFactory.getTextField();
		
		GridLayout content = new GridLayout(2, 1);
		content.setSpacing(true);
		content.addComponent(lblCode);
		content.addComponent(txtCode);
		content.setComponentAlignment(lblCode, Alignment.MIDDLE_LEFT);
		
		return content;
	}

	@Override
	protected RootSpecification<Profile> getSpecification() {
		BaseSpecification<Profile> spec = new BaseSpecification<>();
		spec.setCode(txtCode.getValue());
		return spec;
	}

	@Override
	protected void reset() {
		txtCode.setValue(null);
	}

}
