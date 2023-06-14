package section19Code.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import section19Code.abstractComponents.Section19Code_AbstractComponents;

public class Section19Code_CartPage extends Section19Code_AbstractComponents {

	WebDriver webDriver;

	// PageFactory Pattern
	@FindBy(css = ".totalRow button")
	WebElement checkout;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;

	public Section19Code_CartPage(WebDriver webDriver) {

		// Instance initialisation
		super(webDriver);
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public Boolean verifyProductDisplay(String productName) {
		boolean match = productTitles.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

	public Section19Code_CheckoutPage goToCheckOut() {
		checkout.click();
		return new Section19Code_CheckoutPage(webDriver);
	}

}
