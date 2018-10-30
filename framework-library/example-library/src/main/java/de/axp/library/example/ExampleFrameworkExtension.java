package de.axp.library.example;

import de.axp.framework.api.FrameworkExtension;
import de.axp.framework.api.PortfolioFramework;
import de.axp.framework.api.services.DataService;

public class ExampleFrameworkExtension implements FrameworkExtension {

	@Override
	public void install(InstallationContext installationContext) {
		PortfolioFramework framework = installationContext.getFramework();
		DataService dataService = framework.getDataService();
		dataService.set("example-data", "Hello-World");
	}
}
