package com.pwc.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Assert;

public class HomePage extends Page {

	@FindBy(xpath = "//a[contains(text(), 'Insights')]")
	public WebElement insightsLink;

	@FindBy(xpath = "//a[@class='flex-next']")
	public WebElement nextLink;
	
	@FindBy(xpath = "//a[@class='flex-prev']")
	public WebElement previousLink;
	
	private final WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		System.out.println("start" + this.getClass().getName() + "load()");
	}

	@Override
	protected void isLoaded() throws Error {
		System.out.println(this.getClass().toString() + " isLoaded()");
		new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(insightsLink));

	}

	// problem with the logic of this is that there should only be 3 visible but 12
	// match the xpath
	public List<String> isCarouselLoaded(int numOfArticles) {
		// String xpath =
		// "//div[@class='flex-viewport']//a[@class='featured-image-link']";
		String xpath = "//div[@class='flex-viewport']//div[@class='inner']";
		List<WebElement> carousel = driver.findElements(By.xpath(xpath));
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		
		List<String> articleTitles = new ArrayList<String>();

		int numOfImgLinks = carousel.size();
		System.out.println("number of img link elements in carousel " + numOfImgLinks);
		for (int i = 0; i < carousel.size(); i++) {
			Boolean isDisplayed = ((WebElement) carousel.get(i)).isDisplayed();
			System.out.println("isDisplayed " + isDisplayed);
			String text = ((WebElement) carousel.get(i)).getText();
			System.out.println("text " + text);
			if (isDisplayed) {
				articleTitles.add(text);
			}
		}
		Assert.assertEquals(articleTitles.size(), numOfArticles);
		return articleTitles;
	}
	
	
	public List<String> validateCarouselUpdated(List<String> currentArticleTitles, int numOfArticlesAfterUpdate) {
		
		
		// NOTE introduce a hack of 5 seconds to allow the carousel to update in response to click next or previous 
		// OR build a custom expected conditions that contains this logic that the current articles have changed ( basically
		// move this code into the custom expected condition that includes the wait until as opposed to hard coded wait 5 seconds
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
				
		String xpath = "//div[@class='flex-viewport']//div[@class='inner']";
		List<WebElement> carousel = driver.findElements(By.xpath(xpath));
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		
		List<String> updatedArticleTitles = new ArrayList<String>();

		int numOfImgLinks = carousel.size();
		System.out.println("number of img link elements in carousel " + numOfImgLinks);
		for (int i = 0; i < carousel.size(); i++) {
			Boolean isDisplayed = ((WebElement) carousel.get(i)).isDisplayed();
			System.out.println("isDisplayed " + isDisplayed);
			String text = ((WebElement) carousel.get(i)).getText();
			System.out.println("text " + text);
			if (isDisplayed) {
				updatedArticleTitles.add(text);
			}
		}
		
		// for example if click next and only 2 more articles load in carousel ( note the num expected is passed in feature file ) 
		Assert.assertEquals(updatedArticleTitles.size(), numOfArticlesAfterUpdate);  				
		// TODO research better assert methods for list / array comparison 
		Assert.assertNotEquals(updatedArticleTitles.toArray(), currentArticleTitles.toArray());		
		return updatedArticleTitles;
	}
	
	

}
