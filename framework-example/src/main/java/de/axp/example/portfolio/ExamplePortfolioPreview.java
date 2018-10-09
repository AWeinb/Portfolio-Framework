package de.axp.example.portfolio;

import de.axp.portfolio.vaadin.GenericPortfolioPreview;

class ExamplePortfolioPreview extends GenericPortfolioPreview {

	ExamplePortfolioPreview() {
		super("frontend/images/music_record.svg", //
				"Portfolio X", //
				"Shows names of classes, methods, fields, and keywords within the visibility scope.");
	}
}
