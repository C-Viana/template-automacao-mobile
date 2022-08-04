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
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString().replace("{int}", String.valueOf(abaId)));
		if( abaId == 1 )
			Assert.assertEquals( page.getTxtEsperadoAba1(), page.getAbaBodyText().getText().trim() );
		else
			Assert.assertEquals( page.getTxtEsperadoAba2(), page.getAbaBodyText().getText().trim() );
	}
	
	@Then("troco para a aba {int}")
	public void trocoParaA(Integer abaId) {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString().replace("{int}", String.valueOf(abaId)));
		if( abaId == 1 ){
			page.clicarAba1();
			Assert.assertEquals( page.getTxtEsperadoAba1(), page.getAbaBodyText().getText().trim() );
		}
		else{
			page.clicarAba2();
			Assert.assertEquals( page.getTxtEsperadoAba2(), page.getAbaBodyText().getText().trim() );
		}
	}
	
}
