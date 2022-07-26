package pages;


import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import objects.CtaAppHome;

public class CtaAppHomePage extends CtaAppHome {
	
	public CtaAppHomePage() {
		PageFactory.initElements(Driver.get(), this);
	}
	
	public void acessarTela(String nomeNaLista) {
		BasePage.waitToBeVisible(itemFormulario, 10);
		if( !BasePage.elementExistsByText(nomeNaLista) )
			BasePage.scrollToElement_byText(nomeNaLista);
		BasePage.getElementByText(nomeNaLista).click();
	}
	
	public void acessarTelaFormulario() {
		BasePage.waitToBeVisible(itemFormulario, 10);
		itemFormulario.click();
	}
	
	public void acessarTelaSplash() {
		BasePage.waitToBeVisible(itemSplash, 10);
		itemSplash.click();
	}
	
	public void acessarTelaAlerta() {
		BasePage.waitToBeVisible(itemAlertas, 10);
		itemAlertas.click();
	}
	
	public void acessarTelaAbas() {
		BasePage.waitToBeVisible(itemAbas, 10);
		itemAbas.click();
	}
	
	public void acessarTelaCliques() {
		BasePage.waitToBeVisible(itemCliques, 10);
		itemCliques.click();
	}
	
	public void acessarTelaSwipeList() {
		BasePage.waitToBeVisible(itemSwipeList, 10);
		itemSwipeList.click();
	}
	
	public void acessarDragAndDrop() {
		BasePage.waitToBeVisible(itemDragAndDrop, 10);
		itemDragAndDrop.click();
	}
	
	public void acessarSeuBarrigaHibrido() {
		BasePage.waitToBeVisible(itemHibrido, 10);
		itemHibrido.click();
	}
	
}
