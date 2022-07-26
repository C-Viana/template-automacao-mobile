package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CtaAppAbas {
	
	
	@FindBy( xpath = "//android.widget.HorizontalScrollView//android.widget.TextView[@text='ABA 1']" )
	protected WebElement aba1;
	
	@FindBy( xpath = "//android.widget.HorizontalScrollView//android.widget.TextView[@text='ABA 2']" )
	protected WebElement aba2;
	
	@FindBy( xpath = "//android.support.v4.view.ViewPager//android.widget.TextView" )
	protected WebElement txtAba;
	
	protected String textoAba1 = "Este é o conteúdo da Aba 1";
	protected String textoAba2 = "Este é o conteúdo da Aba 2";
	
}
