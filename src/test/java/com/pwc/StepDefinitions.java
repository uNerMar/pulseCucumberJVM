package com.pwc;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.pwc.pageobjects.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	private final WebDriver driver;
	private HomePage homePage;
	private List<String> firstSetOfArticleTitles;
	private List<String> secondSetOfArticleTitles;
	private List<String> thirdSetOfArticleTitles;

	// NOTE You're not allowed to extend classes that define Step Definitions or
	// hooks so use dependency injection
	// for the driver
	public StepDefinitions(SharedDriver webDriver) {
		this.driver = webDriver;
	}

	@Given("I navigate to the PwC Digital Pulse website")
	public void i_navigate_to_the_pw_c_digital_pulse_website() {
		driver.get("https://www.digitalpulse.pwc.com.au");
	}

	@When("I am viewing the ‘Home’ page")
	public void i_am_viewing_the_home_page() {
		homePage = (HomePage) new HomePage(driver).get();
	}

	@Then("I am presented with a carousel displaying {int} featured articles")
	public void i_am_presented_with_a_carousel_displaying_featured_articles(Integer numOfArticles) {
		firstSetOfArticleTitles = homePage.isCarouselLoaded(numOfArticles);

		// example output

//		Human behaviours: Understanding decision making for a successful cyber strategy
//		isDisplayed true
//		text Featured
//		Leading the way through a digital transformation
//		isDisplayed true
//		text Featured
//		101: Predicting the future with digital twin technology

	}
	
	@Then("Clicking the ‘Next’ button on the carousel will load the next {int} featured articles")
	public void clicking_the_next_button_on_the_carousel_will_load_the_next_featured_articles(Integer numOfArticlesAfterUpdate) {
		homePage.nextLink.click();
		// problem is how to determine the wait time for carousel to load next 3 titles
		// ?
		// with no wait time noted output was ( and note the 1st in list is same as last
		// in previous step )

//		101: Predicting the future with digital twin technology
//		isDisplayed true
//		text Featured
//		How to redefine employee experience for the post-pandemic world
//		isDisplayed true
//		text Featured
//		Being brave: Customer behaviour, consumption and expectations and COVID-19
//		isDisplayed true
//		text Featured
//		Preventative measures: How your tech, legal and risk functions should prepare for a cyber attack
//		isDisplayed false

		secondSetOfArticleTitles = homePage.validateCarouselUpdated(firstSetOfArticleTitles, numOfArticlesAfterUpdate);

	}

	@Then("Clicking the ‘Previous’ button on the carousel will load the previous {int} featured articles")
	public void clicking_the_previous_button_on_the_carousel_will_load_the_previous_featured_articles(Integer numOfArticlesAfterUpdate) {
		homePage.previousLink.click();
		// same problem / comment as with click next
		thirdSetOfArticleTitles = homePage.validateCarouselUpdated(secondSetOfArticleTitles, numOfArticlesAfterUpdate);
		Assert.assertArrayEquals(thirdSetOfArticleTitles.toArray(), firstSetOfArticleTitles.toArray());

	}

}
