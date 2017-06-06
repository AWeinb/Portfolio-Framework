package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkCommandInterface;
import de.axp.portfolio.framework.FrameworkSessionInterface;

interface CommandPacket {

	FrameworkSessionInterface.FrameworkSession getFrameworkSession();

	FrameworkCommandInterface.Command.CommandMessage getCommandMessage();

	class CommandPacketBuilder {

		private FrameworkSessionInterface.FrameworkSession frameworkSession;
		private FrameworkCommandInterface.Command.CommandMessage commandMessage;

		CommandPacket build() {
			CommandPacket commandPacket = new CommandPacket() {

				private FrameworkSessionInterface.FrameworkSession frameworkSession = CommandPacketBuilder.this.frameworkSession;
				private FrameworkCommandInterface.Command.CommandMessage commandMessage = CommandPacketBuilder.this.commandMessage;

				@Override
				public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
					return frameworkSession;
				}

				@Override
				public FrameworkCommandInterface.Command.CommandMessage getCommandMessage() {
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

		void setCommandMessage(FrameworkCommandInterface.Command.CommandMessage commandMessage) {
			this.commandMessage = commandMessage;
		}
	}
}
