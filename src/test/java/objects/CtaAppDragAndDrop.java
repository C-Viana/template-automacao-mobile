package objects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CtaAppDragAndDrop {
	
	@FindBy(xpath = "//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
	protected List<WebElement> itens;
	
	protected List<String> textos = Arrays.asList(
			"Esta", 
			"é uma lista", 
			"Drag em Drop!", 
			"Faça um clique longo,", 
			"e arraste para", 
			"qualquer local desejado."
			);
}
