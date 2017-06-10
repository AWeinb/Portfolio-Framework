package de.axp.portfolio.framework.internal.mainloop.commands;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;

interface ResponsePacket {

	FrameworkSessionInterface.FrameworkSession getFrameworkSession();

	FrameworkNotice.Message getCommandMessage();

	FrameworkNotice.Message getResponseMessage();

	boolean wasResolved();

	default boolean wasRejected() {
		return !wasResolved();
	}

	class ResponsePacketBuilder {

		private FrameworkSessionInterface.FrameworkSession frameworkSession;
		private FrameworkNotice.Message commandMessage;
		private FrameworkNotice.Message responseMessage;
		private boolean resolved;

		ResponsePacket build() {
			ResponsePacket responsePacket = new ResponsePacket() {

				private FrameworkSessionInterface.FrameworkSession frameworkSession = ResponsePacketBuilder.this.frameworkSession;
				private FrameworkNotice.Message commandMessage = ResponsePacketBuilder.this.commandMessage;
				private FrameworkNotice.Message responseMessage = ResponsePacketBuilder.this.responseMessage;
				private boolean resolved = ResponsePacketBuilder.this.resolved;

				@Override
				public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
					return this.frameworkSession;
				}

				@Override
				public FrameworkNotice.Message getCommandMessage() {
					return this.commandMessage;
				}

				@Override
				public FrameworkNotice.Message getResponseMessage() {
					return this.responseMessage;
				}

				@Override
				public boolean wasResolved() {
					return this.resolved;
				}
			};

			frameworkSession = null;
			commandMessage = null;
			responseMessage = null;
			resolved = false;

			return responsePacket;
		}

		void setFrameworkSession(FrameworkSessionInterface.FrameworkSession frameworkSession) {
			this.frameworkSession = frameworkSession;
		}

		void setCommandMessage(FrameworkNotice.Message commandMessage) {
			this.commandMessage = commandMessage;
		}

		void setResponseMessage(FrameworkNotice.Message responseMessage) {
			this.responseMessage = responseMessage;
		}

		void setResolved() {
			this.resolved = true;
		}

		void setRejected() {
			this.resolved = false;
		}
	}
}
