package steps;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.CtaAppAbasPage;
import reporter.ReportManager;

public class CtaAppAbasSteps {
	
	private CtaAppAbasPage page;
	
	public CtaAppAbasSteps() {
		page = new CtaAppAbasPage();
	}
	
	@And("confirmar que a tela inicial é da aba {int}")
	public void confirmarQueATelaInicialÉDaAba(Integer abaId) {
		if( abaId == 1 )
			Assert.assertEquals( page.getTxtEsperadoAba1(), page.getAbaBodyText().getText().trim() );
		else
			Assert.assertEquals( page.getTxtEsperadoAba2(), page.getAbaBodyText().getText().trim() );
			ReportManager.setTestStep("E confirmar que a tela inicial é da aba " + abaId);
	}
	
	@Then("troco para a aba {int}")
	public void trocoParaA(Integer abaId) {
		if( abaId == 1 ){
			page.clicarAba1();
			Assert.assertEquals( page.getTxtEsperadoAba1(), page.getAbaBodyText().getText().trim() );
		}
		else{
			page.clicarAba2();
			Assert.assertEquals( page.getTxtEsperadoAba2(), page.getAbaBodyText().getText().trim() );
		}
		ReportManager.setTestStep("Então troco para a aba " + abaId);
	}
	
}
