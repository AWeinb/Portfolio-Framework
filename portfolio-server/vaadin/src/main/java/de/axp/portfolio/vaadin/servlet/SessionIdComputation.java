package de.axp.portfolio.vaadin.servlet;

class SessionIdComputation {

	private int sessionId = 0;

	String compute() {
		return String.valueOf(sessionId++);
	}
}