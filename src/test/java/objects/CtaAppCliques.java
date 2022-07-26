package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CtaAppCliques {
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clique Longo']")
	protected WebElement btnCliqueLongo;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clique duplo']")
	protected WebElement btnCliqueDuplo;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Clique duplo lento']")
	protected WebElement btnCliqueDuploLento;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Limpar']")
	protected WebElement btnLimpar;
	
	@FindBy(xpath = "//android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.TextView[2]")
	protected WebElement txtResultado;
}
