package de.axp.portfolio.bootstrap.session;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import de.axp.portfolio.framework.FrameworkCommandInterface;
import de.axp.portfolio.framework.FrameworkFactory;

public class SessionInitListener implements com.vaadin.server.SessionInitListener {

	public SessionInitListener() {
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		System.out.println("Session initialized " + event.getSession().getCsrfToken());

		FrameworkCommandInterface frameworkCommandInterface = FrameworkFactory.getFrameworkCommandInterface();
		event.getSession().setAttribute(FrameworkCommandInterface.class.getSimpleName(), frameworkCommandInterface);

		new Thread(() -> {
			try {
				System.err.println("Sleeping 3sec");
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				System.err.println("Commanding Hi");
				frameworkCommandInterface.putCommand("Hi!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
}
