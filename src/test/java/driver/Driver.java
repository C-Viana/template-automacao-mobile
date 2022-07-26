package driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import common.StaticResources;

public class Driver {
    
    private static AppiumDriver driver = null;

    public static AppiumDriver get() {
        if( driver == null ) {
            try {
				driver = new AndroidDriver( new URL(StaticResources.APPIUM_LOCAL_DRIVER), new Capabilities().getDefaultAndroidCapabilities() );
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
        }
        return driver;
    }

    public static void close() {
        if( driver != null )
            driver.quit();
        driver = null;
    }
    
    public static void setImplicitWaitOf( int timeInSeconds) {
    	driver.manage().timeouts().implicitlyWait( Duration.ofMillis(timeInSeconds) );
    }

}
