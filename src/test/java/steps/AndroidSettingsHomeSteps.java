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
		page.getInSearchBar();
		ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Dado que acessei a tela de configurações");
	}
	
	@Then("valido a lista de menus disponível")
	public void validoListaMenisDisponiveis() {
		List<String> titles = page.getAllRecyclerViewItemsText(page.getListTitles());
		for (int i = 0; i < page.getTitlesExpectedTexts().size(); i++) {
			Assert.assertEquals(page.getTitlesExpectedTexts().get(i), titles.get(i));
		}
		ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Então valido a lista de menus disponível");
	}
	
	@And("identificar todos os ícones da lista de configurações")
	public void identificarTodasOsIconesDaListaDeConfiguracoes() {
		icons = new ArrayList<WebElement>();
		ReportManager.setTestInfoStep("Verificando ícones na lista...");
		for (int i = 0; i < page.getTitlesExpectedTexts().size(); i++) {
			//General.scrollToElement_byText(page.getTitlesExpectedTexts().get(i));
			page.searchSettingsItem(i);
			ReportManager.setTestInfoStep("Verificando ícones na lista...");
			icons.add( page.getElementIcone(i) );
		}
		ReportManager.setTestStep(ReportManager.getScenarioStatus(), "E identificar todos os ícones da lista de configurações");
	}
	
	@Then("valido os ícones estão de acordo com o esperado")
	public void validoOsÍconesEstaoDeAcordoComOEsperado() {
		page.retornarAoTopo();
		ReportManager.setTestLog("Validando imagens da lista com os arquivos de referência.");
		for (int i = 0; i < icons.size(); i++) {
			page.ajustarElementoNaTela(icons.get(i));
			Assert.assertTrue( "A imagem "+page.getTitlesExpectedTexts().get(i)+" não é equivalente à imagem de referência", page.validarIcones(icons.get(i), i, 0.0) );
		}
		ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Então valido os ícones estão de acordo com o esperado");
	}
	
	
}
