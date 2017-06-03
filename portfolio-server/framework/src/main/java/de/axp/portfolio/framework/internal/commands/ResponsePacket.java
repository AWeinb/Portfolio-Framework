package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;

import static de.axp.portfolio.framework.FrameworkCommandInterface.Command;

interface ResponsePacket {

	FrameworkSessionInterface.FrameworkSession getFrameworkSession();

	Command.Message getCommandMessage();

	FrameworkNotice.Message getResponseMessage();

	boolean wasResolved();

	default boolean wasRejected() {
		return !wasResolved();
	}

	class PoisonedResponsePacket implements ResponsePacket {

		@Override
		public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
			return null;
		}

		@Override
		public Command.CommandMessage getCommandMessage() {
			return null;
		}

		@Override
		public FrameworkNotice.Message getResponseMessage() {
			return null;
		}

		@Override
		public boolean wasResolved() {
			return false;
		}
	}

	class ResponsePacketBuilder {

		private FrameworkSessionInterface.FrameworkSession frameworkSession;
		private Command.CommandMessage commandMessage;
		private FrameworkNotice.Message responseMessage;
		private boolean resolved;

		ResponsePacket build() {
			ResponsePacket responsePacket = new ResponsePacket() {

				private FrameworkSessionInterface.FrameworkSession frameworkSession = ResponsePacketBuilder.this.frameworkSession;
				private Command.CommandMessage commandMessage = ResponsePacketBuilder.this.commandMessage;
				private FrameworkNotice.Message responseMessage = ResponsePacketBuilder.this.responseMessage;
				private boolean resolved = ResponsePacketBuilder.this.resolved;

				@Override
				public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
					return this.frameworkSession;
				}

				@Override
				public Command.Message getCommandMessage() {
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

		void setCommandMessage(Command.CommandMessage commandMessage) {
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
