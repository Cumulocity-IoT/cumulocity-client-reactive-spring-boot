package com.c8y.reactive.client.inventory;

import com.c8y.reactive.client.core.ManagedObjectSource;

public class ManagedObject extends ManagedObjectSource {
	/**
	 * Time when ManagedObject was created in the database
	 */
	private String creationTime;
	/**
	 * The time when the object was last updated
	 */
	private String lastUpdated;
	/**
	 * The owner of the managed object
	 */
	private String owner;
	/**
	 * A collection of references to additional parents objects
	 */
	private ManagedObjectReferenceCollection additionParents;
	/**
	 * A collection of references to device parent objects
	 */
	private ManagedObjectReferenceCollection assetParents;
	/**
	 * A collection of references to child addition objects
	 */
	private ManagedObjectReferenceCollection childAdditions;
	/**
	 * A collection of references to child assets
	 */
	private ManagedObjectReferenceCollection childAssets;
	/**
	 * A collection of references to child devices
	 */
	private ManagedObjectReferenceCollection childDevices;
	/**
	 * A collection of references to device parent objects
	 */
	private ManagedObjectReferenceCollection deviceParents;

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public ManagedObjectReferenceCollection getAssetParents() {
		return assetParents;
	}

	public void setAssetParents(ManagedObjectReferenceCollection assetParents) {
		this.assetParents = assetParents;
	}

	public ManagedObjectReferenceCollection getChildAdditions() {
		return childAdditions;
	}

	public void setChildAdditions(ManagedObjectReferenceCollection childAdditions) {
		this.childAdditions = childAdditions;
	}

	public ManagedObjectReferenceCollection getChildAssets() {
		return childAssets;
	}

	public void setChildAssets(ManagedObjectReferenceCollection childAssets) {
		this.childAssets = childAssets;
	}

	public ManagedObjectReferenceCollection getChildDevices() {
		return childDevices;
	}

	public void setChildDevices(ManagedObjectReferenceCollection childDevices) {
		this.childDevices = childDevices;
	}

	public ManagedObjectReferenceCollection getDeviceParents() {
		return deviceParents;
	}

	public void setDeviceParents(ManagedObjectReferenceCollection deviceParents) {
		this.deviceParents = deviceParents;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ManagedObject [creationTime=");
		builder.append(creationTime);
		builder.append(", id=");
		builder.append(getId());
		builder.append(", lastUpdated=");
		builder.append(lastUpdated);
		builder.append(", owner=");
		builder.append(owner);
		builder.append(", self=");
		builder.append(getSelf());
		builder.append(", additionParents=");
		builder.append(additionParents);
		builder.append(", assetParents=");
		builder.append(assetParents);
		builder.append(", childAdditions=");
		builder.append(childAdditions);
		builder.append(", childAssets=");
		builder.append(childAssets);
		builder.append(", childDevices=");
		builder.append(childDevices);
		builder.append(", deviceParents=");
		builder.append(deviceParents);
		builder.append(", fragments=");
		builder.append(getFragments());
		builder.append("]");
		return builder.toString();
	}

}
