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
	    System.out.println("Inicializando o teste do app CTA");
	    ReportManager.setTestStep("Dado que acessei o app CTA");
	}
	
	@When("selecionar a opção {string}")
	public void selecionarAOpcao(String string) {
	    page.acessarTela( string );
	    ReportManager.setTestStep("E selecionar a opção " + string);
	}
	
	
}
