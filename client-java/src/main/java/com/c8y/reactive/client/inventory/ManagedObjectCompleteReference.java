package com.c8y.reactive.client.inventory;

public class ManagedObjectCompleteReference {
	/**
	 * ManagedObject data itself with mandantory fragments of [[Source]] at least
	 */
	private ManagedObject managedObject;
	/**
	 * Link to this resource
	 */
	private String self;

	public ManagedObject getManagedObject() {
		return managedObject;
	}

	public void setManagedObject(ManagedObject managedObject) {
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
		builder.append("ManagedObject [managedObject=");
		builder.append(managedObject);
		builder.append(", self=");
		builder.append(self);
		builder.append("]");
		return builder.toString();
	}

}
