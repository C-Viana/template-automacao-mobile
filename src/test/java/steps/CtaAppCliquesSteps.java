package steps;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import pages.CtaAppCliquesPage;
import reporter.ReportManager;

public class CtaAppCliquesSteps {
	
	private CtaAppCliquesPage page;
	
	public CtaAppCliquesSteps() {
		page = new CtaAppCliquesPage();
	}
	
	@Then("realizo as devidas interações de clique")
	public void realizoAsDevidasInteracoesDeClique() {
	    page.confirmarCarregamentoDaTela();
	    ReportManager.setTestStep("Tela Aberta");
	    page.realizarCliqueLongo();
	    Assert.assertEquals("Clique Longo", page.getResultado());
	    ReportManager.setTestStep("Clique longo realizado com sucesso");
	    page.realizarCliqueDuplo();
	    Assert.assertEquals("Duplo Clique", page.getResultado());
	    ReportManager.setTestStep("Clique duplo realizado com sucesso");
	    page.realizarCliqueDuploLento();
	    Assert.assertEquals("Duplo Clique lento", page.getResultado());
	    ReportManager.setTestStep("Clique duplo lento realizado com sucesso");
	    page.limparCampo();
	    Assert.assertEquals("", page.getResultado());
	    ReportManager.setTestStep("Então realizo as devidas interações de clique");
	}
}
