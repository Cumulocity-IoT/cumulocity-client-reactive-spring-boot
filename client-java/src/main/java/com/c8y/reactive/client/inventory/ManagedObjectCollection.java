package com.c8y.reactive.client.inventory;

import java.util.List;

import com.c8y.reactive.client.core.CollectionPage;

public class ManagedObjectCollection extends CollectionPage{

	private List<ManagedObject> managedObjects;

	public List<ManagedObject> getManagedObjects() {
		return managedObjects;
	}

	public void setManagedObjects(List<ManagedObject> managedObjects) {
		this.managedObjects = managedObjects;
	}
}
