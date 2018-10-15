package de.axp.portfolio.vaadin.internal.pages.portfolio;

public final class UrlState {

	private String rootSegment;
	private String pageSegment;
	private int partIndex;
	private int firstPartIndex;
	private int lastPartIndex;

	void setRootSegment(String rootSegment) {
		this.rootSegment = rootSegment;
	}

	public String getRootSegment() {
		return rootSegment;
	}

	void setPageSegment(String pageSegment) {
		this.pageSegment = pageSegment;
	}

	public String getPageSegment() {
		return pageSegment;
	}

	void setPartIndex(int partIndex) {
		this.partIndex = partIndex;
	}

	public int getPartIndex() {
		return partIndex;
	}

	void setFirstPartIndex(int firstPartIndex) {
		this.firstPartIndex = firstPartIndex;
	}

	public int getFirstPartIndex() {
		return firstPartIndex;
	}

	void setLastPartIndex(int lastPartIndex) {
		this.lastPartIndex = lastPartIndex;
	}

	public int getLastPartIndex() {
		return lastPartIndex;
	}
}
