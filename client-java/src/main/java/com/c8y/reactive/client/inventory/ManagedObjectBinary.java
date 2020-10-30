package com.c8y.reactive.client.inventory;

import com.c8y.reactive.client.core.EmptyObject;

public class ManagedObjectBinary extends ManagedObject {
	/**
	 * Mark managed object as binary.
	 */
	private Object c8y_IsBinary = new EmptyObject();
	/**
	 * File size
	 */
	private Long length;
	/**
	 * File name
	 */
	private String name;
	/**
	 * File type: For example "application/pdf", "application/xml" etc.
	 */
	private String type;
	/**
	 * Additional content type of file: For example "plain/text", "application/xml" etc.
	 */
	private String contentType;

	public Object getC8y_IsBinary() {
		return c8y_IsBinary;
	}

	public void setC8y_IsBinary(Object c8y_IsBinary) {
		this.c8y_IsBinary = c8y_IsBinary;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ManagedObjectBinary [c8y_IsBinary=");
		builder.append(c8y_IsBinary);
		builder.append(", length=");
		builder.append(length);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", contentType=");
		builder.append(contentType);
		builder.append("]");
		return builder.toString();
	}
}