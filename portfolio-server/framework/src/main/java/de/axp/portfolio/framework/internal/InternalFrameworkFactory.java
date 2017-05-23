package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.FrameworkCommandInterface;
import de.axp.portfolio.framework.FrameworkInterface;
import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.commandhandling.MessageHandlerInterfaceImpl;
import de.axp.portfolio.framework.internal.commands.CommandFactory;
import de.axp.portfolio.framework.internal.sessions.SessionFactory;

import java.util.HashMap;
import java.util.Map;

public class InternalFrameworkFactory {

	public static Map<Class, FrameworkInterface> createFrameworkInterfaces() {
		HashMap<Class, FrameworkInterface> interfaces = new HashMap<>();
		interfaces.put(FrameworkSessionInterface.class, createFrameworkSessionInterface());
		interfaces.put(FrameworkCommandInterface.class, createFrameworkCommandInterface());

		for (FrameworkInterface frameworkInterface : interfaces.values()) {
			frameworkInterface.initialize();
		}
		return interfaces;
	}

	private static FrameworkSessionInterface createFrameworkSessionInterface() {
		SessionManagement sessionManagement = SessionFactory.createSessionManagement();
		return new FrameworkSessionInterfaceImpl(sessionManagement);
	}

	private static FrameworkCommandInterface createFrameworkCommandInterface() {
		MessageHandlerInterface messageHandlerInterface = new MessageHandlerInterfaceImpl();
		CommandManagement commandManagement = CommandFactory.createCommandManagement(messageHandlerInterface);
		return new FrameworkCommandInterfaceImpl(commandManagement);
	}
}
