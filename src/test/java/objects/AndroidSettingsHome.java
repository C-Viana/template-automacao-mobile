package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AndroidSettingsHome {
	
	@FindBy(id = "com.android.settings:id/search_action_bar")
	protected WebElement inputSearch;
	
	@FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
	protected WebElement inputSearchBackButton;
	
	@FindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/list_container']//android.widget.TextView[@resource-id='android:id/title']")
	protected List<WebElement> listOptions;
	
	protected List<String> itemTitle;
	
	public AndroidSettingsHome() {
		itemTitle = new ArrayList<>();
		itemTitle.addAll(Arrays.asList(
				"Network & internet",
				"Connected devices",
				"Apps & notifications",
				"Battery",
				"Display",
				"Sound",
				"Storage",
				"Privacy",
				"Location",
				"Security",
				"Accounts",
				"Accessibility",
				"Digital Wellbeing & parental controls",
				"Google",
				"System",
				"About emulated device"));
	}
	
	@FindBy(id = "android:id/icon")
	protected List<WebElement> listIcons;
	
	
}
