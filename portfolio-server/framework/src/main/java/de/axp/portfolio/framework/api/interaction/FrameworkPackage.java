package de.axp.portfolio.framework.api.interaction;

public class FrameworkPackage {

	private String sessionID;
	private String packageID;
	private Object content;
	private FrameworkPromise promise;

	public FrameworkPackage(String sessionID, String packageID, Object content, FrameworkPromise promise) {
		this.sessionID = sessionID;
		this.packageID = packageID;
		this.content = content;
		this.promise = promise;
	}

	public String getSessionID() {
		return sessionID;
	}

	public String getPackageID() {
		return packageID;
	}

	public Object getContent() {
		return content;
	}

	public FrameworkPromise getPromise() {
		return promise;
	}
}