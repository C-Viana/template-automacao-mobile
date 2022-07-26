package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CtaAppHibrido {
	
	/*http://seubarriga.wcaquino.me:4001*/
	protected String name = "cv test";
	protected String mail = "cv.test@test.com.br";
	protected String pass = "123456";
	
	@FindBy(id = "email")
	protected WebElement inputEmail;
	
	@FindBy(id = "senha")
	protected WebElement inputSenha;
	
	@FindBy(tagName = "button")
	protected WebElement btnEntrar;
	
	@FindBy(css = "div[role=\"alert\"]")
	protected WebElement alert;
	
}
