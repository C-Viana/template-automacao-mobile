package pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import driver.Driver;
import objects.CtaAppSwipeList;

public class CtaAppSwipeListPage extends CtaAppSwipeList {
	
	public CtaAppSwipeListPage() {
		PageFactory.initElements( Driver.get(), this);
		BasePage.setScreenDimensionsLocalVariable();
	}
	
	public void validarTelaCarregada() {
		BasePage.waitToBeVisible(listTxtViewOpcoes.get(0), 10);
	}
	
	public List<String> getTxtListOpcoes() {
		List<String> texts = new ArrayList<String>();
		listTxtViewOpcoes.stream().forEach( el -> {
			texts.add(el.getText().trim());
		});
		return texts;
	}
	
	public int getListSizeOpcoes() {
		return listTxtViewOpcoes.size();
	}
	
	public void swipeLeftOpcao(int optIndex) {
		Point itemLocation = listOpcoes.get(optIndex-1).getLocation();
		Dimension itemSize = listOpcoes.get(optIndex-1).getSize();
		BasePage.swipeHorizontaly( itemSize.width - 20, itemLocation.x, itemLocation.y+(itemSize.height/2));
	}
	
	public void setPositivo() {
		BasePage.tap( positivo.getLocation().x, positivo.getLocation().y );
	}
	
	public void setNegativo() {
		negativo.click();
	}
	
	public void resetItem(int optIndex) {
		Point itemLocation = listOpcoes.get(optIndex-1).getLocation();
		Dimension itemSize = listOpcoes.get(optIndex-1).getSize();
		BasePage.swipeHorizontaly( itemLocation.x + 150, itemSize.width - 20, itemLocation.y+(itemSize.height/2));
	}
	
}
