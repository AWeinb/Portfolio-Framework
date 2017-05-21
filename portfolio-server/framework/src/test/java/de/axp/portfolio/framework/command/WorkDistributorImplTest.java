package de.axp.portfolio.framework.command;

import org.junit.Test;

public class WorkDistributorImplTest {

	@Test
	public void testCreation() throws Exception {
		CommandBufferImpl commandBuffer = new CommandBufferImpl();
		ResponseBuffer responseBuffer = new ResponseBuffer();
		CommandNotifier commandNotifier = new CommandNotifier();
		ResponseNotifierImpl responseNotifier = new ResponseNotifierImpl();
		new WorkDistributorImpl(commandBuffer, responseBuffer, commandNotifier, responseNotifier);
	}
}