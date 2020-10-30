package com.c8y.reactive.client.inventory;

import com.c8y.reactive.client.core.Source;

public class ManagedObjectReference {
	/**
	 * ManagedObject data itself with mandantory fragments of [[ISource]] at least
	 */
	private Source managedObject;
	/**
	 * Link to this resource
	 */
	private String self;

	public Source getManagedObject() {
		return managedObject;
	}

	public void setManagedObject(Source managedObject) {
		this.managedObject = managedObject;
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
		builder.append("ManagedObjectReference [managedObject=");
		builder.append(managedObject);
		builder.append(", self=");
		builder.append(self);
		builder.append("]");
		return builder.toString();
	}

}
