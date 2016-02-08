package edu.kwon.frmk.vaadin.component.table;

import com.vaadin.ui.Table.Align;

public class Column {
	
	private String propertyId;
	private String columnHeader;
	private Class<?> clazz;
	private Align align;
	private int width;
	private boolean visible;
	
	public Column(String propertyId, String columnHeader, Class<?> clazz) {
		this(propertyId, columnHeader, clazz, 100);
	}
	
	public Column(String propertyId, String columnHeader, Class<?> clazz, int width) {
		this(propertyId, columnHeader, clazz, width, Align.LEFT);
	}
	
	public Column(String propertyId, String columnHeader, Class<?> clazz, int width, Align align) {
		this(propertyId, columnHeader, clazz, width, align, true);
	}
	
	public Column(String propertyId, String columnHeader, Class<?> clazz, int width, Align align, boolean visible) {
		this.propertyId = propertyId;
		this.columnHeader = columnHeader;
		this.clazz = clazz;
		this.align = align;
		this.width = width;
	}

	public String getColumnHeader() {
		return columnHeader;
	}

	public void setColumnHeader(String columnHeader) {
		this.columnHeader = columnHeader;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Align getAlign() {
		return align;
	}

	public void setAlign(Align align) {
		this.align = align;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
