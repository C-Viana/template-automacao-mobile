package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.CtaAppHomePage;
import reporter.ReportManager;

public class CtaAppHomeSteps {
	
	private CtaAppHomePage page;
	
	public CtaAppHomeSteps() {
		page = new CtaAppHomePage();
	}
	
	@Given("que acessei o app CTA")
	public void acesseiOAppCTA() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
	    System.out.println("Inicializando o teste do app CTA");
	}
	
	@When("selecionar a opção {string}")
	public void selecionarAOpcao(String string) {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString().replace("{string}", string));
	    page.acessarTela( string );
	}
	
	
}
