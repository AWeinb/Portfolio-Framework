package de.axp.portfolio.framework.command;

import org.junit.Test;

public class WorkDistributorImplTest {

	@Test
	public void testCreation() throws Exception {
		CommandBufferImpl commandBuffer = new CommandBufferImpl();
		ResponseBuffer responseBuffer = new ResponseBuffer();
		CommandListenerNotifier commandListenerNotifier = new CommandListenerNotifier();
		ResponseNotifierImpl responseNotifier = new ResponseNotifierImpl();
		new WorkDistributorImpl(commandBuffer, responseBuffer, commandListenerNotifier, responseNotifier);
	}
}