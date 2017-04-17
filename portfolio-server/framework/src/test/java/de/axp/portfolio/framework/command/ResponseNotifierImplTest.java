package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.FrameworkInterface.FrameworkResponseListener;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponseNotifierImplTest {

	private ResponseNotifierImpl responseNotifier;

	@Before
	public void setUp() throws Exception {
		responseNotifier = new ResponseNotifierImpl();
	}

	@Test
	public void testAddingOfListeners() throws Exception {
		FrameworkResponseListener responseListener = new TestResponseListener();
		responseNotifier.addResponseListener(responseListener);

		FrameworkResponseListener responseListenerTwo = new TestResponseListener();
		responseNotifier.addResponseListener(responseListenerTwo);

		assertEquals(responseListener, responseNotifier.getResponseListeners().get(0));
		assertEquals(responseListenerTwo, responseNotifier.getResponseListeners().get(1));
	}

	@Test
	public void testNotificationOfListeners() throws Exception {
		TestResponseListener responseListenerOne = new TestResponseListener();
		TestResponseListener responseListenerTwo = new TestResponseListener();
		responseNotifier.addResponseListener(responseListenerOne);
		responseNotifier.addResponseListener(responseListenerTwo);

		responseNotifier.notifyListeners("response");

		assertTrue(responseListenerOne.executed);
		assertTrue(responseListenerTwo.executed);
		assertEquals("response", responseListenerOne.response);
		assertEquals("response", responseListenerTwo.response);
	}

	private final class TestResponseListener implements FrameworkResponseListener {

		String response;
		boolean executed;

		@Override
		public void onResponse(String response) {
			this.response = response;
			executed = true;
		}
	}
}