package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.FrameworkPackage;

public class Response implements FrameworkPackage {

	private String sessionID;
	private String packageID;
	private Object content;

	Response() {
	}

	Response(String sessionID, String packageID, Object content) {
		this.sessionID = sessionID;
		this.packageID = packageID;
		this.content = content;
	}

	@Override
	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	@Override
	public String getPackageID() {
		return packageID;
	}

	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}

	@Override
	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
