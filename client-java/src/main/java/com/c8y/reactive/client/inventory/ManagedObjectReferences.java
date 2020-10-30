package com.c8y.reactive.client.inventory;

import java.util.Arrays;

public class ManagedObjectReferences {
	/**
	 * References array of type [[IManagedObjectReference]]
	 */
	private ManagedObjectReference[] references;
	/**
	 * Link to this resource
	 */
	private String self;

	public ManagedObjectReference[] getReferences() {
		return references;
	}

	public void setReferences(ManagedObjectReference[] references) {
		this.references = references;
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
		builder.append("ManagedObjectReferences [references=");
		builder.append(Arrays.toString(references));
		builder.append(", self=");
		builder.append(self);
		builder.append("]");
		return builder.toString();
	}

}
