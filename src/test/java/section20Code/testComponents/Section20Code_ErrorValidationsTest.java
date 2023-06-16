package section20Code.testComponents;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import section19Code.pageObjects.Section19Code_CartPage;
import section19Code.pageObjects.Section19Code_ProductCatalogue;

public class Section20Code_ErrorValidationsTest extends Section20Code_BaseTest {

	@Test(groups = { "ErrorHandling" })
	public void validateLoginError() throws IOException, InterruptedException {

		// Login to site
		Section19Code_ProductCatalogue productCatalogue = landingPage.loginApplication("shellymutugrigg2@gmail.com",
				"gazxHSwK$oBbd*c43t4S2");

		// Verify error message
		AssertJUnit.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void validateProductErrorMessage() throws IOException, InterruptedException {
		// Global variables
		String productNameString = "ZARA COAT 3";

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
		Boolean match = cartPage.verifyProductDisplay(productNameString + "33");
		Assert.assertFalse(match);
	}
}