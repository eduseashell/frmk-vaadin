package edu.kwon.frmk.vaadin.gui.layout.crud;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;

import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;

/**
 * @author eduseashell
 */
public class ActionBar extends HorizontalLayout {

	private static final long serialVersionUID = 4400273822406705424L;
	
	public ActionBar() {
		setSpacing(true);
	}
	
	public void addButton(Button button) {
		addComponent(button);
	}
	
	public Button addButton(String caption, ClickListener listener) {
		return addButton(caption, null, listener);
	}
	
	public Button addButton(String caption, Resource icon, ClickListener listener) {
		Button btn = getButton(caption);
		btn.addClickListener(listener);
		btn.setIcon(icon);
		addComponent(btn);
		return btn;
	}
	
	protected Button getButton(String caption) {
		return VaadinFactory.getButton(caption);
	}
	
	public void clear() {
		removeAllComponents();
	}

}
