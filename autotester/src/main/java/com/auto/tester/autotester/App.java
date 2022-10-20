package com.auto.tester.autotester;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.auto.tester.helpers.Suitemanager;
import com.auto.tester.listener.TestListener;
import com.auto.tester.listener.TestSuiteListener;

import io.github.bonigarcia.wdm.WebDriverManager;


@Listeners({TestSuiteListener.class,TestListener.class})
public class App extends Base{
	WebDriver wd = null;
    
	
	
	@BeforeTest
	@Parameters({"env","browser"})
	public void launchbrowser(String runmode,String browser) {
		
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			if(runmode.equalsIgnoreCase("local")) {
				wd = new ChromeDriver();
			}else {
				RemoteWebDriver rwd = null;
				try {
					rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				wd = rwd;
			}
		}else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			if(runmode.equalsIgnoreCase("local")) {
				wd = new FirefoxDriver();
			}else {
				RemoteWebDriver rwd = null;
				try {
					rwd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				wd = rwd;
			}
		}
		wd.manage().deleteAllCookies();
		wd.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wd.manage().window().maximize();
		wd.get("https://www.linkedin.com/login");
		pass("verify chrome browser is launched", "chrome browser is launched suuccessfully");
	}
	
	@Test
	public void loginverify() {
		WebElement ele = wd.findElement(By.tagName("h1"));
		if(ele != null) {
			
			if(ele.getText().equalsIgnoreCase("Sign in")){
				pass("verify Login page displayed with sigin ", "Login page displayed with sigin");
			}else {
				fail("verify Login page displayed with sigin ", "Login page displayed with sigin");
			}
			
		}
	}
	@Test
	public void quitbrowser() {
		if(wd != null) {
			wd.quit();
			info("verify chrome browser is closed", "chrome browser is closed successfully");
		}
	}
	
	
	
}
