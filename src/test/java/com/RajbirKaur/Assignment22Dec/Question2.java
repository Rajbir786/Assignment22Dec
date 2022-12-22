package com.RajbirKaur.Assignment22Dec;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Question2 {

	public class Question1 {
		WebDriver wd;
		WebDriverWait wdwait;
		Actions action;

		SoftAssert sf = new SoftAssert();

		@BeforeMethod

		public void setUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rajbir\\drivers\\chromedriver.exe");
			// intialise webdriver instance.
			wd = new ChromeDriver();

			wdwait = new WebDriverWait(wd, Duration.ofSeconds(10));

			// load webpage
	wd.get("http://seleniumpractise.blogspot.com/2016/08/how-to-perform-mouse-hover-in-selenium.html");
	action = new Actions(wd);
		}
		@Test
		public void validateWebPagesOpened() {
			validateHighLightText();
			verificationOfHoverMouseSection();
			clickDropDownSeleniumLink();
			 clickDropDownTestNGLink();
			 AppiumLink();
			
			
		}
		public void validateHighLightText(){
			WebElement highlightedTextSeleniumPractiseAtomPage= wd
					.findElement(By.cssSelector("div.post.hentry.uncustomized-post-template h3"));
			
			sf.assertEquals(highlightedTextSeleniumPractiseAtomPage.getText(), "How to perform mouse hover in Selenium Webdriver");
			
		}
		public void verificationOfHoverMouseSection() {

			WebElement AutomationToolBtn = wd.findElement(By.cssSelector("div.dropdown  button"));

			action.moveToElement(AutomationToolBtn).perform();

			}
		public void clickDropDownSeleniumLink() {
			String parentWindowHandle = wd.getWindowHandle();
			System.out.println(parentWindowHandle);
			WebElement SeleniumLink= wd.findElement(By.cssSelector("div.dropdown-content:first-of-type a"));
			SeleniumLink.click();
			
		Set<String> allWindowHandles = wd.getWindowHandles();//set of strings from collection handles provided find child own your own
			
               for (String handle : allWindowHandles) {
				if (!handle.equals(parentWindowHandle)) {
					wd.switchTo().window(handle);
					System.out.println("Child handle is " + handle);
					
				}
				  
			}
               sf.assertEquals(wd.getTitle(),"Selenium Webdriver Tutorial - Selenium Tutorial for Beginners");
               System.out.println("title of page is verified");
               wd.switchTo().window(parentWindowHandle);	
		
			
		}
		public void clickDropDownTestNGLink() {
			WebElement TestNGLink= wd.findElement(By.cssSelector("div.dropdown-content a:nth-of-type(2)"));
		
			TestNGLink.click();
			sf.assertEquals(wd.getTitle(),"TestNG Tutorials for Selenium Webdriver with Real Time Examples");
			wd.navigate().back();
			
		}
		public void AppiumLink() {
			WebElement AppiumLink= wd.findElement(By.cssSelector("div.dropdown-content a:nth-of-type(3)"));
			AppiumLink.click();
			sf.assertEquals(wd.getTitle(),"Complete Ultimate Appium tutorial for beginners using JAVA for Selenium");
			wd.navigate().back();
			
		}
		@AfterMethod
		public void tearDown() {
			// closing the browser
			wd.quit();
		}

	}
}