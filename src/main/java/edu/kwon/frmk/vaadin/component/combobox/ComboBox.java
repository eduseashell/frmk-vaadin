package edu.kwon.frmk.vaadin.component.combobox;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootEntity;

public class ComboBox<T extends RootEntity> extends com.vaadin.ui.ComboBox {

	private static final long serialVersionUID = 3532999575018882896L;
	
	private Map<Long, T> entities;
	private RenderingListener<T> renderingListener;
	
	// TODO rendering value form specification service...
	
	public T getEntity() {
		if (getEntities() != null) {
			return getEntities().get(getValue());
		}
		return null;
	}
	
	public void setEntity(T value) {
		setValue(value);
	}
	
//	public boolean addEnity(T value) {} TODO add new entity value to ComboBox

	public Map<Long, T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		removeAllItems();
		if (entities != null && !entities.isEmpty()) {
			render(entities);
		}
	}
	
	private void render(List<T> entities) {
		this.entities = new HashMap<>();
		for (T t : entities) {
			this.entities.put(t.getId(), t);
			RenderingListener<T> lst = renderingListener;
			String caption = lst != null ? lst.renderCaption(t) : String.valueOf(t.getId());
			
			addItem(t.getId());
			setItemCaption(t.getId(), caption);
		}
	}
	
	@FunctionalInterface
	public interface RenderingListener<T> extends Serializable {
		
		String renderCaption(T t);
		
	}
	
	public RenderingListener<T> getRenderingListener() {
		return renderingListener;
	}

	public void setRenderingListener(final RenderingListener<T> renderingListener) {
		this.renderingListener = renderingListener;
	}
	
}
