package steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.AndroidSettingsHomePage;
import reporter.ReportManager;

public class AndroidSettingsHomeSteps {
	
	private AndroidSettingsHomePage page = null;
	private List<WebElement> icons = null;
	
	public AndroidSettingsHomeSteps() {
		page = new AndroidSettingsHomePage();
	}
	
	@Given("que acessei a tela de configurações")
	public void acesseiTelaConfiguracoes() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
		page.getInSearchBar();
	}
	
	@Then("valido a lista de menus disponível")
	public void validoListaMenisDisponiveis() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
		List<String> titles = page.getAllRecyclerViewItemsText(page.getListTitles());
		for (int i = 0; i < page.getTitlesExpectedTexts().size(); i++) {
			Assert.assertEquals(page.getTitlesExpectedTexts().get(i), titles.get(i));
		}
	}
	
	@And("identificar todos os ícones da lista de configurações")
	public void identificarTodasOsIconesDaListaDeConfiguracoes() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
		icons = new ArrayList<WebElement>();
		ReportManager.setTestLogAndScreenshot("Verificando ícones na lista...");
		for (int i = 0; i < page.getTitlesExpectedTexts().size(); i++) {
			//General.scrollToElement_byText(page.getTitlesExpectedTexts().get(i));
			page.searchSettingsItem(i);
			ReportManager.setTestLogAndScreenshot("Verificando ícones na lista...");
			icons.add( page.getElementIcone(i) );
		}
	}
	
	@Then("valido os ícones estão de acordo com o esperado")
	public void validoOsÍconesEstaoDeAcordoComOEsperado() {
    	ReportManager.setStepName(new Object(){}.getClass().getEnclosingMethod().getAnnotations()[0].toString());
		page.retornarAoTopo();
		ReportManager.setTestLog("Validando imagens da lista com os arquivos de referência.");
		for (int i = 0; i < icons.size(); i++) {
			page.ajustarElementoNaTela(icons.get(i));
			Assert.assertTrue( "A imagem "+page.getTitlesExpectedTexts().get(i)+" não é equivalente à imagem de referência", page.validarIcones(icons.get(i), i, 0.0) );
		}
	}
	
	
}
