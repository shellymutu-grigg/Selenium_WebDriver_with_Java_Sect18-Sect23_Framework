package section19Code.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import section19Code.abstractComponents.Section19Code_AbstractComponents;

public class Section19Code_CheckoutPage extends Section19Code_AbstractComponents {

	WebDriver webDriver;

	// PageFactory Pattern
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".action__submit")
	WebElement submit;

	@FindBy(css = ".ta-item:nth-of-type(1)")
	WebElement selectCountry;

	By resultsBy = By.cssSelector(".ta-results");

	public Section19Code_CheckoutPage(WebDriver webDriver) {

		// Instance initialisation
		super(webDriver);
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public void selectCountry(String countryName) {
		Actions action = new Actions(webDriver);
		action.sendKeys(country, countryName).build().perform();

		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}

	public Section19Code_ConfirmationPage submitOrder() {
		submit.click();
		return new Section19Code_ConfirmationPage(webDriver);
	}

}
