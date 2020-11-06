package com.c8y.reactive.client.inventory;

import java.util.List;

import com.c8y.reactive.client.core.CollectionPage;

public class ManagedObjectReferenceCollectionPage extends CollectionPage {
	/**
	 * References list of type ManagedObjectReference
	 */
	private List<ManagedObjectReference> references;
	
	public List<ManagedObjectReference> getReferences() {
		return references;
	}
	public void setReferences(List<ManagedObjectReference> references) {
		this.references = references;
	}
}
