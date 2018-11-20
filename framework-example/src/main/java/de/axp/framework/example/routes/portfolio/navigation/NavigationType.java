package de.axp.framework.example.routes.portfolio.navigation;

public enum NavigationType {
	SIMPLE, LIST;

	public NavigationType getNext() {
		return NavigationType.values()[(ordinal() + 1) % NavigationType.values().length];
	}
}
