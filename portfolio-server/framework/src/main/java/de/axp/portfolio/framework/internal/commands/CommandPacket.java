package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;

interface CommandPacket {

	FrameworkSessionInterface.FrameworkSession getFrameworkSession();

	FrameworkNotice.Message getCommandMessage();

	class CommandPacketBuilder {

		private FrameworkSessionInterface.FrameworkSession frameworkSession;
		private FrameworkNotice.Message commandMessage;

		CommandPacket build() {
			CommandPacket commandPacket = new CommandPacket() {

				private FrameworkSessionInterface.FrameworkSession frameworkSession = CommandPacketBuilder.this.frameworkSession;
				private FrameworkNotice.Message commandMessage = CommandPacketBuilder.this.commandMessage;

				@Override
				public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
					return frameworkSession;
				}

				@Override
				public FrameworkNotice.Message getCommandMessage() {
					return commandMessage;
				}
			};

			this.frameworkSession = null;
			this.commandMessage = null;

			return commandPacket;
		}

		void setFrameworkSession(FrameworkSessionInterface.FrameworkSession frameworkSession) {
			this.frameworkSession = frameworkSession;
		}

		void setCommandMessage(FrameworkNotice.Message commandMessage) {
			this.commandMessage = commandMessage;
		}
	}
}
