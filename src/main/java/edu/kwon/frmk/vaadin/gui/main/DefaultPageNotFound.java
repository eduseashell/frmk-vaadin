package edu.kwon.frmk.vaadin.gui.main;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import ru.xpoft.vaadin.VaadinView;
import edu.kwon.frmk.vaadin.component.factory.VaadinFactory;
import edu.kwon.frmk.vaadin.gui.layout.crud.VerticalViewLayout;

/**
 * Display Page not found message
 * @author eduseashell
 */
@Component
@Scope("prototype")
@VaadinView(DefaultPageNotFound.VIEW_NAME)
public class DefaultPageNotFound extends VerticalViewLayout {
	
	private static final long serialVersionUID = 2347902886418333127L;
	
	public static final String VIEW_NAME = "page_not_found";
	
	public DefaultPageNotFound() {
		init();
	}
	
	protected void init() {
		Label lblPageNotFound = VaadinFactory.getLabel("page.not.found");
		lblPageNotFound.setSizeUndefined();
		lblPageNotFound.addStyleName(ValoTheme.LABEL_H1);
		lblPageNotFound.addStyleName(ValoTheme.LABEL_COLORED);
		addComponent(lblPageNotFound);
		setComponentAlignment(lblPageNotFound, Alignment.MIDDLE_CENTER);
		setHeight(300, Unit.PIXELS);
	}

}
