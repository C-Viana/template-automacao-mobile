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
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
	    page.clicarBotaoAlertaConfirm();
	}
	
	@Then("verifico que é possível interagir com os botões deste alerta")
	public void verificoQueEPossivelInteragirComOsBotoesDesteAlerta() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
		Assert.assertEquals( "Info", page.getAlertTitle() );
		Assert.assertEquals( "Confirma a operação?", page.getAlertBody() );
		Assert.assertEquals( "CONFIRMAR", page.getBtnAlertConfirm().getText() );
		page.getBtnAlertConfirm().click();
	}
	
	@And("confirmo operação de confirmação")
	public void confirmoOperacaoDeConfirmacao() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
		Assert.assertEquals( "Info", page.getAlertTitle() );
		Assert.assertEquals( "Confirmado", page.getAlertBody() );
		Assert.assertEquals( "SAIR", page.getBtnAlertCancel().getText() );
		ReportManager.setTestStep("Clicando em \"SAIR\"");
		page.getBtnAlertCancel().click();
		page.waitAlertToClose();
	}
	
}
