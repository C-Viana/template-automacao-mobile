package steps;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import pages.CtaAppHibridoPage;
import reporter.ReportManager;

public class CtaAppHibridoSteps {
	
	private CtaAppHibridoPage page;
	
	public CtaAppHibridoSteps() {
		page = new CtaAppHibridoPage();
	}
	
	@Then("realizo login na tela híbrida")
	public void realizoLoginTelaHibrida() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
		page.setWebviewContext();
		page.setEmail();
		page.setSenha();
		ReportManager.setTestStep( "Informações de login inseridas");
		page.clickEntrar();
		Assert.assertTrue( page.getAlertText().startsWith("Bem vindo, ") );
	}
}
