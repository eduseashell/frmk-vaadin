package edu.kwon.frmk.vaadin.gui.layout.crud;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

/**
 * A VerticalLayout which implement Vaadin View
 * @author eduseashell
 */
public class VerticalViewLayout extends VerticalLayout implements View {

	private static final long serialVersionUID = 8146036181570649016L;
	
	public VerticalViewLayout() {
		setMargin(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// To implement view enter
	}

}
