package steps;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import pages.CtaAppSplashPage;
import reporter.ReportManager;

public class CtaAppSplashSteps  {
	
	private CtaAppSplashPage page;
	
	public CtaAppSplashSteps() {
		page = new CtaAppSplashPage();
	}
	
	@Then("valido a tela splash e aguardo retorno para a home")
	public void validoATelaSplashEAguardoRetornoParaAHome() {
	    Assert.assertEquals( "Esta é uma tela Splash!", page.getSplashScreenText() );
	    page.retornoParaTelaHome();
	    ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Então valido a tela splash e aguardo retorno para a home");
	}

}