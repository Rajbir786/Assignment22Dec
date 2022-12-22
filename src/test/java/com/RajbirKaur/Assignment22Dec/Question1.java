package com.RajbirKaur.Assignment22Dec;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


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

		// wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// load webpage
		wd.get("https://www.demoblaze.com/index.html");// navigation to page

	}

	@Test
	public void validateUserAblePurchase() {

		verifyTitleOfPage();
		clickProductOfStorePage();
		validateDetailsandAddToCartClick();
		verifyAlert();
		verifyDetailsAndPlaceOrder();
		validateDetailsandPurchase();
		validateHighlightedDetails();
		verifyTitleOfPage();
		

	}

	public void verifyTitleOfPage() {
		
		String homePage = wd.getTitle();

		sf.assertEquals(homePage, "STORE");
		System.out.println("Title of homepage is verified");

	}

	public void clickProductOfStorePage() {
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector(("div.col-lg-4.col-md-6.mb-4:first-of-type h4.card-title a"))));
		WebElement productSelectlink = wd
				.findElement(By.cssSelector("div.col-lg-4.col-md-6.mb-4:first-of-type h4.card-title a"));
		sf.assertEquals(productSelectlink.getText(), "Samsung galaxy s6");
		productSelectlink.click();
	}
	public void validateDetailsandAddToCartClick() {
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(("#tbodyid h2"))));
		WebElement productSelectedDetailName = wd.findElement(By.cssSelector("#tbodyid h2"));
		String productName = productSelectedDetailName.getText();
		sf.assertEquals(productName, "Samsung galaxy s6");
		WebElement productSelectedDetailPrice = wd.findElement(By.cssSelector("#tbodyid h3"));
		String productPrice = productSelectedDetailPrice.getText();
		sf.assertEquals(productPrice, "$360 *includes tax");

		WebElement addToCartLink = wd.findElement(By.cssSelector("div.col-sm-12.col-md-6.col-lg-6 a"));
		addToCartLink.click();
		sf.assertAll();
	}

	public void verifyAlert() {

		// acceptAlert
		wdwait.until(ExpectedConditions.alertIsPresent());// wait untill expected conditions like alert is present or
															// not

		wd.switchTo().alert().accept();

	}

	public void verifyDetailsAndPlaceOrder() {
		WebElement cartLink = wd.findElement(By.cssSelector(" ul li:nth-of-type(4) a"));
		cartLink.click();
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(("tr.success td:nth-of-type(2)"))));

		WebElement productSelectedOnPlaceOrderPage = wd.findElement(By.cssSelector("tr.success td:nth-of-type(2)"));
		String productDetl = productSelectedOnPlaceOrderPage.getText();
		sf.assertEquals(productDetl, "Samsung galaxy s6");

		WebElement productSelectedPriceOnPlaceOrderPage = wd
				.findElement(By.cssSelector("tr.success td:nth-of-type(3)"));
		String productDetlPric = productSelectedPriceOnPlaceOrderPage.getText();
		sf.assertEquals(productDetlPric, "360");
		WebElement productSelectedTotalPriceOnPlaceOrderPage = wd.findElement(By.cssSelector("div.panel-heading h3"));
		String productDetlTotalPric = productSelectedTotalPriceOnPlaceOrderPage.getText();
		sf.assertEquals(productDetlTotalPric, "360");
		WebElement placeOrderButton = wd.findElement(By.cssSelector("div.row div.col-lg-1 button"));
		placeOrderButton.click();

	}
	public void validateDetailsandPurchase() {
		
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(("#name"))));
		WebElement firstNameField = wd
				.findElement(By.cssSelector("#name"));
		WebElement countryNameField = wd
				.findElement(By.cssSelector("#country"));
		WebElement cityNameField = wd
				.findElement(By.cssSelector("#city"));
		WebElement creditCardField = wd
				.findElement(By.cssSelector("#card"));
		WebElement monthField = wd
				.findElement(By.cssSelector("#month"));
		WebElement yearField = wd
				.findElement(By.cssSelector("#year"));

		firstNameField.sendKeys("Rajbir");
		 countryNameField.sendKeys("Canada");
		 cityNameField.sendKeys("Vancouver");
		 creditCardField.sendKeys("981501471291");
		 monthField.sendKeys("12");
		 yearField.sendKeys("2022");
		
		 WebElement purchaseBtn = wd
					.findElement(By.cssSelector("div.modal-footer button[onclick='purchaseOrder()']"));
		 
		 purchaseBtn.click();
	}
	public void validateHighlightedDetails() {
		wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(("div.sweet-alert.showSweetAlert.visible h2"))));
		
		 WebElement thanksForPurchaseHighlighed = wd
					.findElement(By.cssSelector("div.sweet-alert.showSweetAlert.visible h2"));
		 
		  sf.assertEquals(thanksForPurchaseHighlighed.getText(),"Thank you for your purchase!");
			 WebElement amountHighlighed = wd
						.findElement(By.cssSelector("p.lead.text-muted br:first-of-type"));
			 
			  sf.assertEquals(amountHighlighed.getText(),"Amount: 360 USD");
			  WebElement cardNumberHighlighed = wd
						.findElement(By.cssSelector("p.lead.text-muted br:nth-of-type(2)"));
			 
			  sf.assertEquals(cardNumberHighlighed.getText(),"Card Number: 981501471291");
			  WebElement NameHighlighed = wd
						.findElement(By.cssSelector("p.lead.text-muted br:nth-of-type(3)"));
			 
			  sf.assertEquals(NameHighlighed.getText(),"Name: Rajbir");
			  WebElement okBtn  = wd
						.findElement(By.cssSelector("div.sa-confirm-button-container button"));
			  okBtn.click();
			 
			 
			  
		  
	}
	
	@AfterMethod
	public void tearDown() {
		//closing the browser
		 wd.quit();
	}

}





