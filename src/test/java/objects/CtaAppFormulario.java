package objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class CtaAppFormulario {
	
	// -------------------- Tela Formulário --------------------
	
	@FindBy(className = "android.widget.EditText")
	protected WebElement inputNome;
	
	@FindBy(xpath = "//android.widget.Spinner/android.widget.TextView")
	protected WebElement selectConsole;
	
	@FindBy(xpath = "//android.widget.CheckedTextView[1]")
	protected WebElement selectConsoleOptXbox;
	
	@FindBy(xpath = "//android.widget.CheckedTextView[2]")
	protected WebElement selectConsoleOptPS4;
	
	@FindBy(xpath = "//android.widget.CheckedTextView[3]")
	protected WebElement selectConsoleOptNintendo;
	
	@FindBy(className = "android.widget.SeekBar")
	protected WebElement seekbar;
	
	@FindBy(className = "android.widget.CheckBox")
	protected WebElement checkbox;
	
	@FindBy(className = "android.widget.Switch")
	protected WebElement switcher;
	
	@FindBy(xpath = "//android.widget.Switch/following-sibling::android.view.ViewGroup[1]")
	protected WebElement data;
	
	@FindBy(xpath = "//android.widget.Switch/following-sibling::android.view.ViewGroup[2]")
	protected WebElement hora;
	
	@FindBy(xpath = "//android.widget.TextView[@text='SALVAR']")
	protected WebElement btnSalvar;
	
	@FindBy(xpath = "//android.widget.TextView[@text='SALVAR DEMORADO']")
	protected WebElement btnSalvarDemorado;
	
	@FindBy(xpath = "//android.widget.TextView[@text='LIMPAR']")
	protected WebElement btnLimpar;
	
	
	// -------------------- Tela Formulário - Resultado --------------------
	
	@FindBy(xpath = "//android.widget.TextView[@text='LIMPAR']/../following-sibling::android.widget.TextView")
	protected List<WebElement> txtResultados;
	
	
}
