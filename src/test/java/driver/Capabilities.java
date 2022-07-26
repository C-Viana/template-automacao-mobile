package driver;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities {
	
	private DesiredCapabilities caps = null;
//	private final String ANDROID_SETTINGS_PACKAGE = "com.android.settings";
//	private final String ANDROID_SETTINGS_ACTIVITY = "com.android.settings.Settings";
	private final String ANDROID_CTA_PACKAGE = "com.ctappium";
	private final String ANDROID_CTA_ACTIVITY = "com.ctappium.MainActivity";
	
	private DesiredCapabilities setDefaultAndroidCapabilities() {
		caps = new DesiredCapabilities();
		caps.setCapability( CapabilityType.PLATFORM_NAME, "Android");
		caps.setCapability( "DeviceName", "Android DUT");
		caps.setCapability( "automationName", "uiautomator2");
		caps.setCapability( "appPackage", ANDROID_CTA_PACKAGE);
		caps.setCapability( "appActivity", ANDROID_CTA_ACTIVITY);
		caps.setCapability( "newCommandTimeout", 120);
		caps.setCapability( "appWaitPackage", "30");
		caps.setCapability( "autoGrantPermissions", true);
		caps.setCapability( "noReset", true);
//		caps.setCapability( "app", StaticResources.APK_LOCAL_PATH);
//		caps.setCapability( "browserName", "Chrome");
//		caps.setCapability( "chromedriverExecutable", StaticResources.CHROME_DRIVER );
		return caps;
	}
	
	public DesiredCapabilities setAndroidCapabilities(  ) {
		if( caps == null ) {
			caps = new DesiredCapabilities();
//			caps.setCapability("automationName", "Espresso");
//			caps.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
			caps.setCapability("appPackage", "com.android.chrome");
			caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
			caps.setCapability("noReset", true);
			caps.setCapability("autoGrantPermissions", true);
			caps.setCapability("platformName", "Android");
			caps.setCapability("deviceName", "DUT");
			caps.setCapability("unlockType", "pin");
			caps.setCapability("unlockKey", "1234");
			caps.setCapability("language", "pt");
			caps.setCapability("locale", "BR");
		}
		return caps;
	}
	
	public DesiredCapabilities getDefaultAndroidCapabilities() {
		return this.setDefaultAndroidCapabilities();
	}
	
}
