package section19Code.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import section19Code.abstractComponents.Section19Code_AbstractComponents;

public class Section20Code_OrdersPage extends Section19Code_AbstractComponents {

	WebDriver webDriver;

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> ordersProducts;

	public Section20Code_OrdersPage(WebDriver webDriver) {
		// Instance initialisation
		super(webDriver);
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public Boolean verifyOrdersDisplay(String productName) {

		boolean match = ordersProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

}
