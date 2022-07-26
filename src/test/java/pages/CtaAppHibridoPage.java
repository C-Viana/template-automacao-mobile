package pages;

import java.util.Set;

import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import objects.CtaAppHibrido;

public class CtaAppHibridoPage extends CtaAppHibrido {
	
	public CtaAppHibridoPage() {
		PageFactory.initElements(Driver.get(), this);
	}
	
	public Set<String> getWebviews() {
		BasePage.waitFor(5);
		return BasePage.getWebviewContexts();
	}
	
	public void setWebviewContext() {
		BasePage.waitFor(5);
		BasePage.setWebviewContext("WEBVIEW_com.ctappium");
	}
	
	public void setEmail() {
		inputEmail.sendKeys(mail);
	}
	
	public void setSenha() {
		inputSenha.sendKeys(pass);
	}
	
	public void clickEntrar() {
		btnEntrar.click();
	}
	
	public String getAlertText() {
		BasePage.waitToBeVisible(alert, 10);
		return alert.getText().trim();
	}

}
