package com.c8y.reactive.client.core;

public class CollectionPageStatistics {
	private Integer totalPages;
	private int pageSize;
	private int currentPage;

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CollectionPageStatistics [totalPages=");
		builder.append(totalPages);
		builder.append(", pageSize=");
		builder.append(pageSize);
		builder.append(", currentPage=");
		builder.append(currentPage);
		builder.append("]");
		return builder.toString();
	}

}
