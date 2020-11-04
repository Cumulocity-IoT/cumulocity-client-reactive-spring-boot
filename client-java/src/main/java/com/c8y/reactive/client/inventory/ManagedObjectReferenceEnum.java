package com.c8y.reactive.client.inventory;

public enum ManagedObjectReferenceEnum {
	CHILD_DEVICES("childDevices"), CHILD_ASSETS("childAssets"), CHILD_ADDITIONS("childAdditions");
	
	private String referenceName;
	
	private ManagedObjectReferenceEnum(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getReferenceName() {
		return referenceName;
	}
	
}
