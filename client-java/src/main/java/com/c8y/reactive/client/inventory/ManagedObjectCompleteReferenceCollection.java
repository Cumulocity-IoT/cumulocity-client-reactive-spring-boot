package com.c8y.reactive.client.inventory;

import java.util.List;

import com.c8y.reactive.client.core.CollectionPage;

public class ManagedObjectCompleteReferenceCollection extends CollectionPage {
	/**
	 * References list of type ManagedObjectReference
	 */
	private List<ManagedObjectCompleteReference> references;
	
	public List<ManagedObjectCompleteReference> getReferences() {
		return references;
	}
	public void setReferences(List<ManagedObjectCompleteReference> references) {
		this.references = references;
	}
}
