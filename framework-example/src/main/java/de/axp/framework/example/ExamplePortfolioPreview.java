package de.axp.framework.example;


import de.axp.framework.example.vaadin.GenericPortfolioPreview;

class ExamplePortfolioPreview extends GenericPortfolioPreview {

	ExamplePortfolioPreview() {
		super("frontend/images/music_record.svg", //
				"Portfolio X", //
				"Shows names of classes, methods, fields, and keywords within the visibility scope.");
	}
}
