package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommandNotifierTest {

	private CommandNotifier commandNotifier;

	@Before
	public void setUp() throws Exception {
		commandNotifier = new CommandNotifier();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowException_WhenAddingNull() throws Exception {
		commandNotifier.addListener(null);
	}

	@Test
	public void shouldNotifyAddedListeners() throws Exception {
		CommandListener commandListenerOne = mock(CommandListener.class);
		CommandListener commandListenerTwo = mock(CommandListener.class);
		commandNotifier.addListener(commandListenerOne);
		commandNotifier.addListener(commandListenerTwo);

		commandNotifier.notifyListeners("A");

		verify(commandListenerOne).onCommand("A");
		verify(commandListenerTwo).onCommand("A");
	}
}