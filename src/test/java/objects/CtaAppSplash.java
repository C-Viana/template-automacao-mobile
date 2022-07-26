package objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CtaAppSplash {
	
	@FindBy(className = "android.widget.TextView")
	protected List<WebElement> textoSplashScreen;
	
}
