package edu.kwon.frmk.vaadin.gui.main;

import ru.xpoft.vaadin.SpringApplicationContext;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

/**
 * Provide and error view if navigate to an unknown view
 * @author eduseashell
 */
public class DefaultErrorViewProvider implements ViewProvider {

	private static final long serialVersionUID = 3626466347920680929L;
	
	private String errorViewName;
	private Class<?> errorViewClass;

	@Override
	public String getViewName(String viewAndParameters) {
		return getErrorViewName();
	}

	@Override
	public View getView(String viewName) {
		if (getErrorViewClass() == null) {
			throw new IllegalArgumentException("Error view class cannot be null!");
		}
		
		Object view = SpringApplicationContext.getApplicationContext().getBean(getErrorViewClass());
		if (!(view instanceof View)) {
			String msg = "The class " + view.getClass().getName() + " must implement " + View.class.getName();
			throw new IllegalArgumentException(msg);
		}
		return (View) view;
	}

	public String getErrorViewName() {
		return errorViewName;
	}

	public void setErrorViewName(String errorViewName) {
		this.errorViewName = errorViewName;
	}

	public Class<?> getErrorViewClass() {
		return errorViewClass;
	}

	public void setErrorViewClass(Class<?> errorViewClass) {
		this.errorViewClass = errorViewClass;
	}

}
