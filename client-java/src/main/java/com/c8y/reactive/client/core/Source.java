package com.c8y.reactive.client.core;

public class Source {
	/**
	 * Unique identifier
	 */
	private String id;
	/**
	 * Link to the resource
	 */
	private String self;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Source [id=");
		builder.append(id);
		builder.append(", self=");
		builder.append(self);
		builder.append("]");
		return builder.toString();
	}

}