package steps;

import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.CtaAppFormularioPage;
import reporter.ReportManager;

public class CtaAppFormularioSteps {
	
	private CtaAppFormularioPage page;
	private Map<String, String> form = null;
	
	public CtaAppFormularioSteps() {
		page = new CtaAppFormularioPage();
	}
	
	@Then("preencho o campo de texto com o valor {string}")
	public void preenchoOCampoDeTextoComOValor(String texto) {
	    page.escreverTextoNoCampoNome(texto);
	    Assert.assertFalse( "O texto esperado neste campo não foi encontrado!", page.getInputNomeText().isEmpty() );
		Assert.assertEquals(texto, page.getInputNomeText());
	    ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Então preencho o campo de texto com o valor " + texto);
	}
	
	@Then("seleciono a opção {string} no combo")
	public void selecionoAOpcaoOPTIONNoCombo(String opt) {
	    Assert.assertEquals(page.getExpectedTxtComboConsole(opt), page.selecionarOpcaoComboConsole(opt));
	    ReportManager.setTestStep(ReportManager.getScenarioStatus(), "E seleciono a opção "+opt+" no combo");
	}
	
	@Then("valido a ativação do checkbox")
	public void validoAAtivacaoDoCheckbox() {
		page.alterarChechbox();
	    Assert.assertEquals( true, page.getChechboxStatus() );
		page.alterarChechbox();
	    Assert.assertEquals( false, page.getChechboxStatus() );
	    ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Então valido a ativação do checkbox");
	}
	
	@Then("valido a desativação do switch")
	public void validoADesativacaoDoSwitch() {
		page.alterarSwitch();
	    Assert.assertEquals( false, page.getSwitchStatus() );
		page.alterarSwitch();
	    Assert.assertEquals( true, page.getSwitchStatus() );
	    ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Então valido a desativação do switch");
	}
	
	@Then("preencher todo o formulário")
	public void preencherTodoOFormulario(io.cucumber.datatable.DataTable dataTable) {
	    form = dataTable.asMap();
	    page.escreverTextoNoCampoNome(form.get("NOME"));
	    page.alterarSeekbar(Integer.parseInt(form.get("SLIDER")));
	    page.selecionarOpcaoComboConsole(form.get("CONSOLE"));
	    if( Boolean.parseBoolean(form.get("CHECKBOX")) != page.getChechboxStatus() ) page.alterarChechbox();
	    if( Boolean.parseBoolean(form.get("SWITCH")) != page.getSwitchStatus() ) page.alterarSwitch();
	    page.alterarData(form.get("DATA"));
	    page.alterarHora(form.get("HORA"));
	    page.salvar();
	    ReportManager.setTestStep(ReportManager.getScenarioStatus(), "Então preencher todo o formulário");
	}
	
	@And("valido que dados informados foram cadastrados")
	public void validoQueDadosInformadosForamCadastrados() {
	    page.removerElementoTituloDoResultado();
	    Assert.assertEquals("Nome: "+form.get("NOME"), page.getResultadosDoFormulario().get(0).getText());
	    Assert.assertEquals("Console: "+page.getTextoConsoleResultado(form.get("CONSOLE")), page.getResultadosDoFormulario().get(1).getText());
	    Assert.assertTrue( Pattern.matches("Slider: \\d?\\d$", page.getResultadosDoFormulario().get(2).getText()) );
	    Assert.assertEquals("Switch: "+((form.get("SWITCH").toLowerCase().equals("true")) ? "On" : "Off"), page.getResultadosDoFormulario().get(3).getText());
	    Assert.assertEquals("Checkbox: "+((form.get("CHECKBOX").toLowerCase().equals("true")) ? "Marcado" : "Desmarcado"), page.getResultadosDoFormulario().get(4).getText());
	    Assert.assertEquals("Data: "+form.get("DATA").replace("/0", "/"), page.getResultadosDoFormulario().get(5).getText());
	    Assert.assertEquals("Hora: "+form.get("HORA"), page.getResultadosDoFormulario().get(6).getText());
	    ReportManager.setTestStep(ReportManager.getScenarioStatus(), "E valido que dados informados foram cadastrados");
	}

}
