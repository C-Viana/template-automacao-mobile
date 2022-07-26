package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import objects.CtaAppSplash;

public class CtaAppSplashPage extends CtaAppSplash {
	
	public CtaAppSplashPage() {
		PageFactory.initElements(Driver.get(), this);
	}
	
	public String getSplashScreenText() {
		BasePage.waitToBeVisible( BasePage.getElementByText("Splash!"), 15);
		String text = "";
		for (WebElement el : textoSplashScreen) {
			text += el.getText().trim().replace("\n", "");
		}
		return text;
	}
	
	public void retornoParaTelaHome() {
		BasePage.waitUntilNotVisible(textoSplashScreen.get(0), 15);
		BasePage.getElementByText("About...");
	}
	
}
