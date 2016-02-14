package edu.kwon.frmk.vaadin.component.combobox;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootEntity;
import edu.kwon.frmk.common.data.jpa.repository.entities.root.RootSpecification;

/**
 * @author eduseashell
 *
 * @param <T>
 */
public class ComboBox<T extends RootEntity> extends com.vaadin.ui.ComboBox {

	private static final long serialVersionUID = 3532999575018882896L;
	
	private Map<Long, T> entities;
	private RootSpecification<T> specification;
	private boolean excludeInactive = true;
	private RenderingListener<T> renderingListener;
	
	public ComboBox() {
		super();
	}
	
	public ComboBox(String caption) {
		super(caption);
	}
	
	public ComboBox(String caption, RenderingListener<T> renderingListener) {
		this(caption);
		setRenderingListener(renderingListener);
	}
	
	public ComboBox(String caption, RootSpecification<T> spec) {
		this(caption);
		setSpecification(spec);
	}
	
	public ComboBox(String caption, RootSpecification<T> spec, RenderingListener<T> renderingListener) {
		this(caption);
		setRenderingListener(renderingListener);
		setSpecification(spec);
	}
	
	public ComboBox(String caption, List<T> values, RenderingListener<T> renderingListener) {
		this(caption);
		setRenderingListener(renderingListener);
		setEntities(values);
	}
	
	public ComboBox(String caption, Function<RootSpecification<T>, List<T>> loader, RenderingListener<T> renderingListener) {
		this(caption);
		setRenderingListener(renderingListener);
		load(loader);
	}
	
	public T getEntity() {
		if (getEntities() != null) {
			return getEntities().get(getValue());
		}
		return null;
	}
	
	public void setEntity(T value) {
		setValue(value == null ? value : value.getId());
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
	
	/**
	 * Load the data from Specification
	 * @param loader
	 */
	public ComboBox<T> load(Function<RootSpecification<T>, List<T>> loader) {
		if (excludeInactive) {
			getSpecification().setActive(true);
		}
		List<T> datas = loader.apply(getSpecification());
		render(datas);
		return this;
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
	
	public RootSpecification<T> getSpecification() {
		if (specification == null) {
			specification = new RootSpecification<T>();
		}
		return specification;
	}

	public ComboBox<T> setSpecification(RootSpecification<T> specification) {
		this.specification = specification;
		return this;
	}

	public boolean isExcludeInactive() {
		return excludeInactive;
	}

	public ComboBox<T> excludeInactive(boolean excludeInactive) {
		this.excludeInactive = excludeInactive;
		return this;
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
