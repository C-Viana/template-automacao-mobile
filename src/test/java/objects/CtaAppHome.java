package objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CtaAppHome {
	
	// -------------------- HOME LIST --------------------
	@FindBy(xpath = "")
	protected WebElement itemAbout;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Formulário']")
	protected WebElement itemFormulario;
	
	@FindBy(xpath = "")
	protected WebElement itemNativo;
	
	@FindBy(xpath = "//android.widget.TextView[@text='SeuBarriga Híbrido']")
	protected WebElement itemHibrido;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Alertas']")
	protected WebElement itemAlertas;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Splash']")
	protected WebElement itemSplash;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Abas']")
	protected WebElement itemAbas;
	
	@FindBy(xpath = "")
	protected WebElement itemAccordion;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Cliques']")
	protected WebElement itemCliques;
	
	@FindBy(xpath = "")
	protected WebElement itemSwipe;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Swipe List']")
	protected WebElement itemSwipeList;
	
	@FindBy(xpath = "//android.widget.TextView[@text='Drag and drop']")
	protected WebElement itemDragAndDrop;
	
	@FindBy(xpath = "")
	protected WebElement itemOpcaoBemEscondida;
	
	
}
