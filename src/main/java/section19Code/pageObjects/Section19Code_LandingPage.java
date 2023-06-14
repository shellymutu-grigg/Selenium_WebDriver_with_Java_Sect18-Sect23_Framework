package section19Code.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import section19Code.abstractComponents.Section19Code_AbstractComponents;

public class Section19Code_LandingPage extends Section19Code_AbstractComponents {

	WebDriver webDriver;

	// PageFactory Pattern
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;

	public Section19Code_LandingPage(WebDriver webDriver) {
		super(webDriver);
		// Instance initialisation
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public Section19Code_ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		Section19Code_ProductCatalogue productCatalogue = new Section19Code_ProductCatalogue(webDriver);
		return productCatalogue;
	}

	public void navigate() {
		webDriver.get("https://rahulshettyacademy.com/client/");
	}

}
