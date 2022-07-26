package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import junit.framework.AssertionFailedError;
import objects.CtaAppDragAndDrop;

public class CtaAppDragAndDropPage extends CtaAppDragAndDrop {
	
	public CtaAppDragAndDropPage() {
		PageFactory.initElements( Driver.get(), this );
	}
	
	public void aguardarTelaCaregar() {
		BasePage.waitToBeVisible(itens.get(0), 10);
	}
	
	public List<WebElement> getListItens() {
		return itens;
	}
	
	public List<String> getListTextos() {
		return textos;
	}
	
	public void arrastarElementoAParaElementoB( String textoElementoA, String textoElementoB ) {
		WebElement elA = null;
		WebElement elB = null;
		try {
			for (WebElement i : itens) {
				if(i.getText().trim().toLowerCase().equals(textoElementoA.toLowerCase()))
					elA = i;
				if(i.getText().trim().toLowerCase().equals(textoElementoB.toLowerCase()))
					elB = i;
			}
			BasePage.dragAndDrop(elA, elB);
		} catch (Exception e) {
			String customMsg = "";
			if( elA == null && elB == null) customMsg = "Nenhum elemento em tela possui os textos informados!\n";
			else if( elA == null) customMsg = "Erro com o paràmetro textoElementoA!\nNenhum elemento em tela possui o texto " + textoElementoA + "\n";
			else if( elB == null) customMsg = "Erro com o paràmetro textoElementoB!\\nNenhum elemento (B) em tela possui o texto " + textoElementoB + "\n";
			throw new AssertionFailedError( customMsg );
		}
	}
	
}
