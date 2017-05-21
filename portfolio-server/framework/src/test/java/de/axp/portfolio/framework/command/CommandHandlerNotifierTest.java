package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandManagement.CommandHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandHandlerNotifierTest {

	private CommandHandlerNotifier commandHandlerNotifier;

	@Before
	public void setUp() throws Exception {
		commandHandlerNotifier = new CommandHandlerNotifier();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowException_WhenAddingNull() throws Exception {
		commandHandlerNotifier.addCommandHandler(null);
	}

	@Test
	public void shouldNotifyAddedListeners() throws Exception {
		CommandHandler commandHandlerOne = mock(CommandHandler.class);
		CommandHandler commandHandlerTwo = mock(CommandHandler.class);
		commandHandlerNotifier.addCommandHandler(commandHandlerOne);
		commandHandlerNotifier.addCommandHandler(commandHandlerTwo);

		commandHandlerNotifier.notifyListeners("A");

		verify(commandHandlerOne).handleCommand("A");
		verify(commandHandlerTwo).handleCommand("A");
	}
}