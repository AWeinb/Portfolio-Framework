package de.axp.portfolio.vaadin.internal.services.pages.portfolio;

import de.axp.portfolio.vaadin.api.services.UiService;

public final class PortfolioPageState {

	private final UiService uiService;

	private String rootSegment;
	private String pageSegment;
	private int partIndexMin;
	private int partIndexMax;
	private int partIndex;

	public PortfolioPageState(UiService uiService) {
		this.uiService = uiService;
	}

	public void update(String rootSegment, String pageSegment, int partIndex) {
		partIndexMin = 0;
		partIndexMax = uiService.getPortfolioDefinition(pageSegment).getPortfolioParts().size() - 1;
		this.rootSegment = rootSegment;
		this.pageSegment = pageSegment;
		this.partIndex = wrapPartIndex(partIndex);
	}

	public String getRootSegment() {
		return rootSegment;
	}

	public String getPageSegment() {
		return pageSegment;
	}

	public int getPageIndex() {
		return partIndex;
	}

	public int getPreviousPartIndex() {
		return wrapPartIndex(partIndex - 1);
	}

	public int getNextPartIndex() {
		return wrapPartIndex(partIndex + 1);
	}

	private int wrapPartIndex(int i) {
		i = i < partIndexMin ? partIndexMin : i;
		i = i > partIndexMax ? partIndexMax : i;
		return i;
	}
}
