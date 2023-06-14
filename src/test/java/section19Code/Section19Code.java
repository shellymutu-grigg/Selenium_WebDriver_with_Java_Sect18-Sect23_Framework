package section19Code;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import section19Code.pageObjects.Section19Code_CartPage;
import section19Code.pageObjects.Section19Code_CheckoutPage;
import section19Code.pageObjects.Section19Code_ConfirmationPage;
import section19Code.pageObjects.Section19Code_LandingPage;
import section19Code.pageObjects.Section19Code_ProductCatalogue;

public class Section19Code {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		webDriver.manage().window().maximize();

		// Global variables
		String productNameString = "ZARA COAT 3";

		// Navigate to page
		Section19Code_LandingPage landingPage = new Section19Code_LandingPage(webDriver);
		landingPage.navigate();

		// Login to site
		Section19Code_ProductCatalogue productCatalogue = landingPage.loginApplication("shellymutugrigg@gmail.com",
				"gazxHSwK$oBbd*c43t4S");

		// Generate list of products on page
		List<WebElement> products = productCatalogue.getProductList();

		// Find product and add to cart
		productCatalogue.addProducttoCart(productNameString);

		// Navigate to cart
		Section19Code_CartPage cartPage = productCatalogue.goToCartPage();

		// Manage cart page actions
		Boolean match = cartPage.verifyProductDisplay(productNameString);
		Assert.assertTrue(match);
		Section19Code_CheckoutPage checkoutPage = cartPage.goToCheckOut();

		// Manage checkout page actions
		checkoutPage.selectCountry("india");
		Section19Code_ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessageString = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessageString.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		webDriver.close();

	}

}
