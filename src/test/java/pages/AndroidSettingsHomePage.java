package pages;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.BasePage;
import common.StaticResources;
import driver.Driver;
import objects.AndroidSettingsHome;
import reporter.ReportManager;

public class AndroidSettingsHomePage extends AndroidSettingsHome {
	
	public AndroidSettingsHomePage() {
		PageFactory.initElements(Driver.get(), this);
		BasePage.setScreenDimensionsLocalVariable();
	}
	
	public WebElement getInSearchBar() {
		BasePage.waitToBeVisible(inputSearch, 30);
		return inputSearch;
	}
	
	public WebElement getInSearchBackButton() {
		return inputSearchBackButton;
	}
	
	public List<WebElement> getListTitles() {
		return listOptions;
	}
	
	public List<String> getTitlesExpectedTexts() {
		return itemTitle;
	}
	
	public boolean isTitleOnScreen(int index) {
		return BasePage.elementExistsByText(itemTitle.get(index));
	}
	
	public boolean isLastTitleOnScreen() {
		return listOptions.get(listOptions.size()-1).getText().trim() == itemTitle.get(itemTitle.size()-1);
	}
	
	public void searchSettingsItem(int index) {
		while( !isTitleOnScreen(index) && !isLastTitleOnScreen() ){
			BasePage.swipeVertically( 0.8, 0.3 );
			BasePage.waitFor(2);
		}
	}
	
	public void retornarAoTopo() {
		BasePage.scrollToElement_byText(itemTitle.get(0));
	}
	
	public void ajustarElementoNaTela(WebElement elementoParaAjustar) {
		BasePage.setScreenDimensionsLocalVariable();
		if( elementoParaAjustar.getLocation().y > BasePage.getScreenDimensions().height-130 ) {
			BasePage.swipeVertically( 0.8, 0.3 );
			BasePage.waitFor(2);
		}
	}
	
	public boolean validarIcones(WebElement iconeParaValidar, int indexDoIconeNaLista, double percetualDeDiferencaTolerado) {
		//BasePage.getAndSaveImageFromObject(iconeParaValidar, StaticResources.IMAGE_RESOURCES_DIR, itemTitle.get(indexDoIconeNaLista));
		BasePage.getAndSaveImageFromObject(iconeParaValidar, StaticResources.IMAGE_SCREENSHOTS, itemTitle.get(indexDoIconeNaLista));
		double result = 0;
		try {
			result = BasePage.compareImages(
					BasePage.getImageFile(StaticResources.IMAGE_SCREENSHOTS +"\\"+ itemTitle.get(indexDoIconeNaLista)+".png"), 
					BasePage.getImageFile(StaticResources.IMAGE_RESOURCES_DIR +"\\"+ itemTitle.get(indexDoIconeNaLista)+".png")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( result > percetualDeDiferencaTolerado) {
			ReportManager.setTestLogAndScreenshot("Erro ao comparar a imagem "+itemTitle.get(indexDoIconeNaLista)+".<br>Diferença tolerável (%): " + percetualDeDiferencaTolerado+"<br>Diferença encontrada (%): "+result);
		}
		return result <= percetualDeDiferencaTolerado;
	}
	
	public WebElement getElementIcone( int index ) {
		return Driver.get().findElement(By.xpath("//android.widget.TextView[@text='"+itemTitle.get(index)+"']/../..//android.widget.ImageView[@resource-id='android:id/icon']"));
	}
	
	public List<String> getAllRecyclerViewItemsText(List<WebElement> source) {
		List<String> tempList = new ArrayList<String>();
		
		for (int i = 0; i < 3; i++) {
			for (WebElement each : source) {
				tempList.add(each.getText());
			}
			BasePage.swipeDown();
		}
		
		return new ArrayList<String>(new LinkedHashSet<String>(tempList));
	}
	
}
