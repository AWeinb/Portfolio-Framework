package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandNotifier.CommandListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandNotifierTest {

	private CommandNotifierImpl commandNotifier;

	@Before
	public void setUp() throws Exception {
		commandNotifier = new CommandNotifierImpl();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowException_WhenAddingNull() throws Exception {
		commandNotifier.addCommandListener(null);
	}

	@Test
	public void shouldNotifyAddedListeners() throws Exception {
		CommandListener commandListenerOne = mock(CommandListener.class);
		CommandListener commandListenerTwo = mock(CommandListener.class);
		commandNotifier.addCommandListener(commandListenerOne);
		commandNotifier.addCommandListener(commandListenerTwo);

		commandNotifier.notifyListeners("A");

		verify(commandListenerOne).onCommand("A");
		verify(commandListenerTwo).onCommand("A");
	}
}