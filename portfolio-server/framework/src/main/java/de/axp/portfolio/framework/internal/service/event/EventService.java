package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.internal.FrameworkHandler;
import de.axp.portfolio.framework.internal.FrameworkPackage;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface EventService extends InternalFrameworkService {

	void dispatchEvent(String sessionID, String packageID, Event event) throws InterruptedException;

	interface EventHandler extends FrameworkHandler {

		void execute(String sessionID, String commandID, Object content, FrameworkPromise promiseToResolveOrReject);
	}

	class Event implements FrameworkPackage {

		private String sessionID;
		private String packageID;
		private Object content;
		private FrameworkPromise promise;

		public Event(String sessionID, String packageID, Object content, FrameworkPromise promise) {
			this.sessionID = sessionID;
			this.packageID = packageID;
			this.content = content;
			this.promise = promise;
		}

		@Override
		public String getSessionID() {
			return sessionID;
		}

		@Override
		public String getPackageID() {
			return packageID;
		}

		@Override
		public Object getContent() {
			return content;
		}

		public FrameworkPromise getPromise() {
			return promise;
		}
	}

	class Response implements FrameworkPackage {

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
}
