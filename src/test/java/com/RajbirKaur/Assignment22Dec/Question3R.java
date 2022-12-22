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

public class Question3R {
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
		wd.get("http://seleniumpractise.blogspot.com/2017/07/multiple-window-examples.html");
		action = new Actions(wd);
	}

	@Test
	public void validateTitleOfPagesOpenInMultipleTabs() {
		validateHighlightedText();
		VerifyTitlesOfAllLinks();

	}

	public void validateHighlightedText() {
		WebElement textHighlighed = wd.findElement(By.cssSelector("div.post.hentry.uncustomized-post-template h3"));
		sf.assertEquals(textHighlighed.getText(), "Multiple window examples");

	}

	public void VerifyTitlesOfAllLinks() {
		String parentWindowHandle = wd.getWindowHandle();
		System.out.println(parentWindowHandle);
		WebElement firstLink = wd.findElement(By.cssSelector("div.post-body.entry-content a:first-of-type"));
		firstLink.click();
		Set<String> set1 = wd.getWindowHandles();
		for (String handle : set1) {
			if (!handle.equals(parentWindowHandle)) {
				wd.switchTo().window(handle);
				System.out.println("Child handle is " + handle);

			}

		}
		sf.assertEquals(wd.getTitle(), "Google");
		System.out.println("title of google page is verified");
		wd.switchTo().window(parentWindowHandle);

		parentWindowHandle = wd.getWindowHandle();
		System.out.println(parentWindowHandle);
		WebElement secondLink = wd.findElement(By.cssSelector("div.post-body.entry-content a:nth-of-type(2)"));
		secondLink.click();
		Set<String> set2 = wd.getWindowHandles();

		set2.removeAll(set1);
		for (String handle : set2) {
			if (!handle.equals(parentWindowHandle)) {
				wd.switchTo().window(handle);
				System.out.println("Child handle is " + handle);

			}

		}

		sf.assertEquals(wd.getTitle(), "Facebook - log in or sign up");
		System.out.println("title of facebook page is verified");
		wd.switchTo().window(parentWindowHandle);
		System.out.println(parentWindowHandle);
	

	WebElement thirdLink = wd
			.findElement(By.cssSelector("div.post-body.entry-content a:nth-of-type(3)"));
	thirdLink.click();
	Set<String> set3 = wd.getWindowHandles();
	set3.removeAll(set2);
	set3.removeAll(set1);
	for(String handle2:set3)
	{
		if (!handle2.equals(parentWindowHandle)) {
			wd.switchTo().window(handle2);
			System.out.println("Child handle is " + handle2);

		}
	}sf.assertEquals(wd.getTitle(),"Yahoo | Mail, Weather, Search, News, Finance, Sports, Shopping, Entertainment, Video");
	System.out.println("title of  yahoo page is verified");
	wd.switchTo().window(parentWindowHandle);
	parentWindowHandle=wd.getWindowHandle();
	System.out.println(parentWindowHandle);
	WebElement fourthLink = wd.findElement(By.cssSelector("div.post-body.entry-content a:nth-of-type(4)"));
	fourthLink.click();
	sf.assertEquals(wd.getTitle(),"Facebook - log in or sign up");
	System.out.println("title of  facebook page is verified");
	wd.navigate().back();
	}

	@AfterMethod
	public void tearDown() {
		// closing the browser
		wd.quit();
	}
}
