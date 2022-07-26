package pages;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import objects.CtaAppFormulario;

public class CtaAppFormularioPage extends CtaAppFormulario {
	
	public CtaAppFormularioPage() {
		PageFactory.initElements(Driver.get(), this);
	}
	
	public void escreverTextoNoCampoNome(String texto) {
		BasePage.waitToBeVisible(inputNome, 5);
		inputNome.sendKeys(texto);
	}
	
	public String getInputNomeText() {
		return inputNome.getText();
	}
	
	public String selecionarOpcaoComboConsole(String opt) {
		selectConsole.click();
		BasePage.waitToBeVisible(selectConsoleOptXbox, 10);
		switch (opt.toLowerCase()) {
		case "xbox one":
		case "xbox":
			selectConsoleOptXbox.click();
			break;
		case "ps4":
			selectConsoleOptPS4.click();
			break;
		case "nintendo switch":
		case "nintendo":
		case "switch":
			selectConsoleOptNintendo.click();
			break;
		default:
			return "Nenhuma opção encontrada com o texto " + opt;
		}
		return selectConsole.getText().trim();
	}
	
	public String getExpectedTxtComboConsole( String opt ) {
		switch (opt.toLowerCase()) {
		case "xbox one":
		case "xbox":
			return "XBox One";
		case "ps4":
			return "PS4";
		case "nintendo switch":
		case "nintendo":
		case "switch":
			return "Nintendo Switch";
		default:
			return "Nenhuma opção encontrada com o texto " + opt;
		}
	}
	
	public void alterarChechbox() {
		checkbox.click();
	}
	
	public boolean getChechboxStatus() {
		return checkbox.getAttribute("checked").equals("true");
	}
	
	public void alterarSwitch() {
		switcher.click();
	}
	
	public boolean getSwitchStatus() {
		return switcher.getAttribute("checked").equals("true");
	}
	
	public void alterarSeekbar( int seekBarNumber) {
		Point location = seekbar.getLocation();
		Dimension size = seekbar.getSize();
		BasePage.waitFor(2);
		int targetXPos = new Random().nextInt(size.width) + location.x;
		BasePage.tap(targetXPos, location.y);
	}
	
	public void alterarData( String dataDesejada ) {
		String dia = dataDesejada.split("/")[0];
		String mes = dataDesejada.split("/")[1];
		String ano = dataDesejada.split("/")[2];
		String dataIngles = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.US).format(LocalDate.parse(ano+"-"+mes+"-"+dia));
		data.click();
		BasePage.waitFor(2);
		BasePage.getElementById("android:id/date_picker_header_year").click();
		
		while( !BasePage.elementExistsByText(ano) ) {
			if(Integer.parseInt(ano) > 2000)
				BasePage.swipeVertically(0.65, 0.25);
			else if (Integer.parseInt(ano) < 2000)
				BasePage.swipeVertically(0.55, 0.90);
		}
		BasePage.getElementByXpath("//android.widget.ListView/android.widget.TextView[@text='"+ano+"']").click();
		BasePage.getElementById("android:id/prev").click();
		while( !BasePage.elementExistsByContentDesc(dataIngles) ) {
			BasePage.getElementById("android:id/next").click();
		}
		BasePage.getElementByContentDesc(dataIngles).click();
		BasePage.getElementById("android:id/button1").click();
	}
	
	public void alterarHora(String horaDesejada) {
		hora.click();
		BasePage.waitFor(2);
		BasePage.getElementById("android:id/toggle_mode").click();
		BasePage.waitFor(2);
		BasePage.getElementById("android:id/input_hour").sendKeys(horaDesejada.split(":")[0]);
		BasePage.getElementById("android:id/input_minute").sendKeys(horaDesejada.split(":")[1]);
		BasePage.getElementById("android:id/button1").click();
	}
	
	public void salvar() {
		BasePage.waitToBeVisible(btnSalvar, 5);
		btnSalvar.click();
	}
	
	public String getTextoConsoleResultado(String consoleEscolhido) {
		switch (consoleEscolhido.toLowerCase()) {
		case "xbox one":
		case "xbox":
			return "xone";
		case "playstation 4":
		case "playstation":
		case "ps4":
			return "ps4";
		case "nintendo switch":
		case "nintendo":
		case "switch":
			return "switch";
		default:
			return null;
		}
	}
	
	public void removerElementoTituloDoResultado() {
		List<WebElement> removable = new ArrayList<>();
		txtResultados.stream().filter( e -> e.getText().equals("CT Appium") ).forEach( e -> { removable.add(e); });
		txtResultados.removeAll(removable);
	}
	
	public List<WebElement> getResultadosDoFormulario() {
		BasePage.waitToBeVisible(txtResultados.get(0), 5);
		return txtResultados;
	}

}
