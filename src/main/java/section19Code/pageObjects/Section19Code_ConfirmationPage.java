package section19Code.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import section19Code.abstractComponents.Section19Code_AbstractComponents;

public class Section19Code_ConfirmationPage extends Section19Code_AbstractComponents {

	WebDriver webDriver;

	// PageFactory Pattern
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;

	public Section19Code_ConfirmationPage(WebDriver webDriver) {

		// Instance initialisation
		super(webDriver);
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}

}
