package de.axp.portfolio.framework.command;

import org.junit.Test;

public class WorkDistributorImplTest {

	@Test
	public void testCreation() throws Exception {
		CommandBufferImpl commandBuffer = new CommandBufferImpl();
		ResponseBuffer responseBuffer = new ResponseBuffer();
		CommandNotifierImpl commandNotifier = new CommandNotifierImpl();
		ResponseNotifierImpl responseNotifier = new ResponseNotifierImpl();
		new WorkDistributorImpl(commandBuffer, responseBuffer, commandNotifier, responseNotifier);
	}
}