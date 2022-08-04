package steps;

import common.StaticResources;
import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import reporter.ReportManager;

public class _Hooks {
	
    @BeforeAll
    public static void beforeAll() {
        StaticResources.suite_test = true;
    }

    @AfterAll
    public static void afterAll() {
        if (StaticResources.suite_test)
            ReportManager.endReport();
    }

    @Before
    public void startTest(Scenario scenario) {
    	ReportManager.setScenario(scenario);
        ReportManager.setFeature(ReportManager.getFeatureName(scenario.getUri().toString()));
        ReportManager.setResultPath(scenario.getUri().toString(), scenario.getName());
        ReportManager.startReport();
        String prefix = (StaticResources.suite_test) ? ReportManager.getFeature() + " - " : "";
        ReportManager.startTest(prefix + scenario.getName());
    }

    @After
    public void endTest(Scenario scenario) {
        ReportManager.endTest();
        if (!StaticResources.suite_test)
            ReportManager.endReport();
        
        Driver.close();
    }

    @AfterStep
    public void afterStep() {
        ReportManager.setTestStep();
    }
    
}
