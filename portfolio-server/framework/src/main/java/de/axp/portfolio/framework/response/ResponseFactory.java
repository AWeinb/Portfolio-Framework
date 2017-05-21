package de.axp.portfolio.framework.response;

import de.axp.portfolio.framework.ResponseManagement;

public class ResponseFactory {

	public static ResponseManagement createResponseManagement() {
		ResponseBuffer responseBuffer = new ResponseBuffer();
		ResponseNotifier responseNotifier = new ResponseNotifier();
		return new ResponseManagementImpl(responseBuffer, responseNotifier);
	}
}
