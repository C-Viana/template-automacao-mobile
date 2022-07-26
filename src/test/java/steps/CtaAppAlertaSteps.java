package steps;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.CtaAppAlertasPage;
import reporter.ReportManager;

public class CtaAppAlertaSteps {
	
	private CtaAppAlertasPage page;
	
	public CtaAppAlertaSteps() {
		page = new CtaAppAlertasPage();
	}
	
	@And("visualizar o alerta de confirmação")
	public void visualizarOAlertaDeConfirmacao() {
	    page.clicarBotaoAlertaConfirm();
	    ReportManager.setTestStep(ReportManager.getScenarioStatus(), "E visualizar o alerta de confirmação");
	}
	
	@Then("verifico que é possível interagir com os botões deste alerta")
	public void verificoQueEPossivelInteragirComOsBotoesDesteAlerta() {
		Assert.assertEquals( "Info", page.getAlertTitle() );
		Assert.assertEquals( "Confirma a operação?", page.getAlertBody() );
		Assert.assertEquals( "CONFIRMAR", page.getBtnAlertConfirm().getText() );
		page.getBtnAlertConfirm().click();
		ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Então verifico que é possível interagir com os botões deste alerta");
	}
	
	@And("confirmo operação de confirmação")
	public void confirmoOperacaoDeConfirmacao() {
		Assert.assertEquals( "Info", page.getAlertTitle() );
		Assert.assertEquals( "Confirmado", page.getAlertBody() );
		Assert.assertEquals( "SAIR", page.getBtnAlertCancel().getText() );
		ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Clicando em \"SAIR\"");
		page.getBtnAlertCancel().click();
		page.waitAlertToClose();
		ReportManager.setTestStep(ReportManager.getScenarioStatus(), "E confirmo operação de confirmação");
	}
	
}
