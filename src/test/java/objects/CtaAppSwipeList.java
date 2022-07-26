package objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CtaAppSwipeList {
	
	@FindBy(xpath = "//android.view.ViewGroup/android.widget.TextView[starts-with(@text, 'Opção')]")
	protected List<WebElement> listTxtViewOpcoes; 
	
	@FindBy(xpath = "//android.widget.TextView[starts-with(@text, 'Opção')]/..")
	protected List<WebElement> listOpcoes; 
	
	@FindBy(xpath = "//android.widget.TextView[@text='(+)']")
	protected WebElement positivo; 
	
	@FindBy(xpath = "//android.widget.TextView[@text='(-)']")
	protected WebElement negativo; 
	
}
