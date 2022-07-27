package steps;

import common.StaticResources;
import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
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
        ReportManager.setResultPath(scenario.getUri().toString(), ReportManager.getScenario().getName());
        ReportManager.startReport();
        ReportManager.startTest(ReportManager.getScenario().getName());
    }

    @After
    public void endTest(Scenario scenario) {
        if( ReportManager.getScenarioStatus() == Status.FAILED )
        	ReportManager.setTestStep( "Erro!");
        
        ReportManager.endTest();
        if (!StaticResources.suite_test)
            ReportManager.endReport();
        
        Driver.close();
    }
}
