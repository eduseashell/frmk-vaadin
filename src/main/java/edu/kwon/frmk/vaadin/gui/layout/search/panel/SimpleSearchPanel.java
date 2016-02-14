package edu.kwon.frmk.vaadin.gui.layout.search.panel;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import edu.kwon.frmk.common.data.jpa.repository.entities.base.BaseEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.base.BaseSpecification;
import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootSpecification;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractSearchPanel;

/**
 * @author eduseashell
 * @param <T>
 */
@org.springframework.stereotype.Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SimpleSearchPanel<T extends BaseEntity> extends AbstractSearchPanel<T> {

	private static final long serialVersionUID = -5051741067714638818L;
	
	private TextField txtCode;
	private TextField txtDesc;

	@Override
	protected Component initGUI() {
		txtCode = VaadinFactory.getTextField("code");
		txtDesc = VaadinFactory.getTextField("desc");
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addComponent(txtCode);
		layout.addComponent(txtDesc);
		
		return layout;
	}
	
	@Override
	protected RootSpecification<T> getSpecification() {
		BaseSpecification<T> spec = new BaseSpecification<T>();
		spec.setCode(txtCode.getValue());
		spec.setDesc(txtDesc.getValue());
		return spec;
	}

	@Override
	public void reset() {
		txtCode.setValue(null);
		txtDesc.setValue(null);
	}

}
