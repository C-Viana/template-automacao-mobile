package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CtaAppAlertas {
	
	
	@FindBy(xpath = "//android.widget.TextView[@text='ALERTA SIMPLES']")
	protected WebElement btnAlertaSimples;
	
	@FindBy(xpath = "//android.widget.TextView[@text='ALERTA RESTRITIVO']")
	protected WebElement btnAlertaRestritivo;
	
	@FindBy(xpath = "//android.widget.TextView[@text='ALERTA CONFIRM']")
	protected WebElement btnAlertaConfirm;
	
	@FindBy(id = "android:id/alertTitle")
	protected WebElement alertaTitle;
	
	@FindBy(id = "android:id/message")
	protected WebElement alertaBody;
	
	@FindBy(id = "android:id/button2")
	protected WebElement alertaBtnConfirm;
	
	@FindBy(id = "android:id/button1")
	protected WebElement alertaBtnCancel;
	
	
}
