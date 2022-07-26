package pages;

import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import objects.CtaAppCliques;

public class CtaAppCliquesPage extends CtaAppCliques {
	
	public CtaAppCliquesPage() {
		PageFactory.initElements(Driver.get(), this);
	}
	
	public void confirmarCarregamentoDaTela() {
		BasePage.waitToBeVisible(btnLimpar, 10);
	}
	
	public void realizarCliqueLongo() {
		BasePage.tap(btnCliqueLongo, 2000);
	}
	
	public void realizarCliqueDuplo() {
		BasePage.doubleTap(btnCliqueDuplo);
	}
	
	public void realizarCliqueDuploLento() {
		BasePage.doubleTap(btnCliqueDuploLento, 1500);
	}
	
	public void limparCampo() {
		btnLimpar.click();
	}
	
	public String getResultado() {
		return txtResultado.getText().trim();
	}

}
