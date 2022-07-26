package steps;

import org.junit.Assert;

import io.cucumber.java.en.Then;
import pages.CtaAppSwipeListPage;
import reporter.ReportManager;

public class CtaAppSwipeListSteps {
	
	private CtaAppSwipeListPage page;
	
	public CtaAppSwipeListSteps() {
		page = new CtaAppSwipeListPage();
	}
	
	@Then("realizo as devidas interações swipe")
	public void realizoAsDevidasInteraçõesSwipe() {
		page.validarTelaCarregada();
		Assert.assertEquals(8, page.getListSizeOpcoes());
		Assert.assertEquals("Opção 2 (+)", page.getTxtListOpcoes().get(1));
		Assert.assertEquals("Opção 5 (-)", page.getTxtListOpcoes().get(4));
		Assert.assertEquals("Opção 6 (+)", page.getTxtListOpcoes().get(5));
		Assert.assertEquals("Opção 8 (-)", page.getTxtListOpcoes().get(7));
	    ReportManager.setTestStep( ReportManager.getScenarioStatus(), "Configurações padrão da tela inicial");
	    
		page.resetItem(2);
		page.resetItem(5);
		page.resetItem(6);
		page.resetItem(8);
	    ReportManager.setTestStep( ReportManager.getScenarioStatus(), "Todas as opções foram redefinidas");
		
		page.swipeLeftOpcao(1);
		page.setPositivo();
		page.swipeLeftOpcao(3);
		page.setNegativo();
		page.swipeLeftOpcao(4);
		page.setPositivo();
		page.swipeLeftOpcao(7);
		page.setNegativo();
	    ReportManager.setTestStep( ReportManager.getScenarioStatus(), "Então realizo as devidas interações swipe");
	}
	
}
