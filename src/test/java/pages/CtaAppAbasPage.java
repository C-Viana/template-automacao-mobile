package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import objects.CtaAppAbas;

public class CtaAppAbasPage extends CtaAppAbas {
	
	public CtaAppAbasPage() {
		PageFactory.initElements(Driver.get(), this);
	}
	
	public void clicarAba1() {
		BasePage.waitToBeVisible(aba1, 10);
		aba1.click();
		BasePage.waitFor(2);
	}
	
	public void clicarAba2() {
		BasePage.waitToBeVisible(aba2, 10);
		aba2.click();
		BasePage.waitFor(2);
	}
	
	public WebElement getAbaBodyText() {
		BasePage.waitToBeVisible(txtAba, 10);
		return txtAba;
	}
	
	public String getTxtEsperadoAba1() {
		return textoAba1;
	}
	
	public String getTxtEsperadoAba2() {
		return textoAba2;
	}
	
}
