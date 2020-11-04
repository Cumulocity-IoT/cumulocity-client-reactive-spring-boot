package com.c8y.reactive.client.core;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ManagedObjectSource {
	/**
	 * Unique identifier
	 */
	private String id;
	/**
	 * Link to the resource
	 */
	private String self;
	/**
	 * Additional fragments
	 */
	private Map<String, Object> fragments;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	@JsonAnyGetter
	public Map<String, Object> getFragments() {
		return fragments;
	}

	public Object get(String fragmentName) {
		return fragments.get(fragmentName);
	}

	@JsonAnySetter
	public void set(String fragmentName, Object value) {
		synchronized (fragmentName) {
			if(fragments == null) {
				this.fragments = new HashMap<String, Object>();
			}
			this.fragments.put(fragmentName, value);
		}
	}

	public void set(String fragmentName) {
		synchronized (fragmentName) {
			if(fragments == null) {
				this.fragments = new HashMap<String, Object>();
			}
			this.fragments.put(fragmentName, new EmptyObject());
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Source [id=");
		builder.append(id);
		builder.append(", self=");
		builder.append(self);
		builder.append(", fragments=");
		builder.append(fragments);
		builder.append("]");
		return builder.toString();
	}
	
}