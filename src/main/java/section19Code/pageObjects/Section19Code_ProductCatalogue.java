package section19Code.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import section19Code.abstractComponents.Section19Code_AbstractComponents;

public class Section19Code_ProductCatalogue extends Section19Code_AbstractComponents {

	WebDriver webDriver;

	// PageFactory Pattern
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By toastBy = By.cssSelector("#toast-container");

	public Section19Code_ProductCatalogue(WebDriver webDriver) {

		// Instance initialisation
		super(webDriver);
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement finalProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return finalProduct;
	}

	public void addProducttoCart(String productName) throws InterruptedException {
		WebElement product = getProductByName(productName);
		product.findElement(addToCartBy).click();
		waitForElementToAppear(toastBy);
		waitForElementToDisappear(spinner);

	}

}
