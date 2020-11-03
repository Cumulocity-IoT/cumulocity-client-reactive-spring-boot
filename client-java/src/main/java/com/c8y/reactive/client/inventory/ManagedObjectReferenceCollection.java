package com.c8y.reactive.client.inventory;

import java.util.List;

public class ManagedObjectReferenceCollection {
	/**
	 * References list of type ManagedObjectReference
	 */
	private List<ManagedObjectReference> references;
	/**
	 * Link to this resource
	 */
	private String self;
	
	public List<ManagedObjectReference> getReferences() {
		return references;
	}
	public void setReferences(List<ManagedObjectReference> references) {
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
		builder.append("ManagedObjectReferenceCollection [references=");
		builder.append(references);
		builder.append(", self=");
		builder.append(self);
		builder.append("]");
		return builder.toString();
	}
}
