package com.c8y.reactive.client.core;

public class CollectionPage {
	private String self;
	private CollectionPageStatistics statistics;
	private String prev;
	private String next;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public CollectionPageStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(CollectionPageStatistics statistics) {
		this.statistics = statistics;
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CollectionPage [self=");
		builder.append(self);
		builder.append(", statistics=");
		builder.append(statistics);
		builder.append(", prev=");
		builder.append(prev);
		builder.append(", next=");
		builder.append(next);
		builder.append("]");
		return builder.toString();
	}
}
