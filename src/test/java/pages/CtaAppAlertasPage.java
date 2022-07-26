package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import objects.CtaAppAlertas;

public class CtaAppAlertasPage extends CtaAppAlertas {
	
	public CtaAppAlertasPage() {
		PageFactory.initElements(Driver.get(), this);
	}
	
	public void clicarBotaoAlertaConfirm() {
		BasePage.waitToBeVisible(btnAlertaConfirm, 15);
		btnAlertaConfirm.click();
	}
	
	public String getAlertTitle() {
		BasePage.waitToBeVisible(alertaTitle, 15);
		return alertaTitle.getText().trim();
	}
	
	public String getAlertBody() {
		return alertaBody.getText().trim();
	}
	
	public WebElement getBtnAlertConfirm() {
		return alertaBtnConfirm;
	}
	
	public WebElement getBtnAlertCancel() {
		return alertaBtnCancel;
	}
	
	public void waitAlertToClose() {
		BasePage.waitUntilNotVisible(alertaTitle, 10);
	}

}
