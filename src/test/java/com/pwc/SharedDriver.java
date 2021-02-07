package com.pwc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;


// TODO this code is out of date even if it does work 
// research upgrading this e.g. see https://grasshopper.tech/732/
public class SharedDriver extends EventFiringWebDriver {
 
	private static WebDriver REAL_DRIVER = null;
	
	

	public static WebDriver getDriverInstance() {
//		if(REAL_DRIVER == null) {
			REAL_DRIVER = initialiseDriver();
//		 }
		return REAL_DRIVER;
	}

	
	//private static WebDriver initialiseDriver()
	private static WebDriver initialiseDriver() {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("is the browser session id null" + ((RemoteWebDriver) driver).getSessionId().toString());
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		return driver;
	}

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
        	System.out.println("CLOSE THEAD");
            REAL_DRIVER.close();
        }
    };

    static {
    	System.out.println("add shutdown hook");
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);       
    }

	public SharedDriver() {
//		super(REAL_DRIVER);
		super(getDriverInstance());		
		System.out.println("Shared driver constructor");
	}

	@Before
	public void beforeScenario() {
		System.out.println("Cucumber Before hook called");
		manage().deleteAllCookies();
	}

	@Before
	public void afterScenario() {
		System.out.println("Cucumber After hook called");
		// TODO close browser session		
	}

}
