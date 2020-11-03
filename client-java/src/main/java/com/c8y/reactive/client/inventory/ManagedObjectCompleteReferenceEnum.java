package com.c8y.reactive.client.inventory;

public enum ManagedObjectCompleteReferenceEnum {
	CHILD_DEVICES("childDevices"), CHILD_ASSETS("childAssets");
	
	private String referenceName;
	
	private ManagedObjectCompleteReferenceEnum(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getReferenceName() {
		return referenceName;
	}
	
}
