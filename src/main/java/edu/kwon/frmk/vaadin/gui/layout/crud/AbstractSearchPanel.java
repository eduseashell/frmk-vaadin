package edu.kwon.frmk.vaadin.gui.layout.crud;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickListener;

import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.factory.VaadinFactory;

public abstract class AbstractSearchPanel extends Panel {

	private static final long serialVersionUID = -1788720241817053229L;
	
	private Button btnSearch;
	private Button btnReset;
	
	public AbstractSearchPanel() {
		init();
	}

	protected void init() {
		setCaption(I18N.string("search"));
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSpacing(true);
		setContent(content);

		content.addComponent(initGUI());
		content.addComponent(buildSearchAndResetLayout());
	}
	
	private HorizontalLayout buildSearchAndResetLayout() {
		btnSearch = VaadinFactory.getButtonPrimary("search");
		btnSearch.setIcon(FontAwesome.SEARCH);
		btnSearch.setClickShortcut(KeyCode.ENTER);
		btnSearch.addClickListener(onSearchActionClicked());
		
		btnReset = VaadinFactory.getButton("reset");
		btnReset.setIcon(FontAwesome.REFRESH);
		btnReset.addClickListener(e -> reset());

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);
		buttonLayout.addComponent(btnSearch);
		buttonLayout.addComponent(btnReset);

		return buttonLayout;
	}
	
	protected abstract Component initGUI();
	protected abstract ClickListener onSearchActionClicked();
	public abstract void reset();
	
}
