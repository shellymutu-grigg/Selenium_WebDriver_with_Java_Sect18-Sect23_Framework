package section19Code.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import section19Code.pageObjects.Section19Code_CartPage;
import section19Code.pageObjects.Section19Code_CheckoutPage;
import section19Code.pageObjects.Section19Code_ConfirmationPage;
import section19Code.pageObjects.Section19Code_ProductCatalogue;
import section19Code.pageObjects.Section20Code_OrdersPage;
import section20Code.testComponents.Section20Code_BaseTest;

public class Section19Code_SubmitOrderTest extends Section20Code_BaseTest {

	// Global variables
	String productNameString = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrderTest(HashMap<String, String> input) throws IOException, InterruptedException {

		// Login to site
		Section19Code_ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),
				input.get("password"));

		// Generate list of products on page
		List<WebElement> products = productCatalogue.getProductList();

		// Find product and add to cart
		productCatalogue.addProducttoCart(input.get("productName"));

		// Navigate to cart
		Section19Code_CartPage cartPage = productCatalogue.goToCartPage();

		// Manage cart page actions
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		Section19Code_CheckoutPage checkoutPage = cartPage.goToCheckOut();

		// Manage checkout page actions
		checkoutPage.selectCountry("india");
		Section19Code_ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessageString = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessageString.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void orderHistoryTest() {
		// Login to site
		Section19Code_ProductCatalogue productCatalogue = landingPage.loginApplication("shellymutugrigg@gmail.com",
				"gazxHSwK$oBbd*c43t4S");
		Section20Code_OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrdersDisplay(productNameString));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "shellymutugrigg@gmail.com");
//		map1.put("password", "gazxHSwK$oBbd*c43t4S");
//		map1.put("productName", "ZARA COAT 3");
//
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "shellymutugrigg2@gmail.com");
//		map2.put("password", "gazxHSwK$oBbd*c43t4S2");
//		map2.put("productName", "ADIDAS ORIGINAL");

		List<HashMap<String, String>> dataHashMaps = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//section21Code//data//PurchaseOrder.json");
		return new Object[][] { { dataHashMaps.get(0) }, { dataHashMaps.get(1) } };

	}

	public String getScreenshot(String testCaseName) throws IOException {

		TakesScreenshot screenshotAction = (TakesScreenshot) webDriver;
		File screenshotTakenFile = screenshotAction.getScreenshotAs(OutputType.FILE);
		File screenshOutputFile = new File(System.getProperty("user.dir") + "//Desktop//" + testCaseName + ".png");
		FileUtils.copyFile(screenshotTakenFile, screenshOutputFile);
		return System.getProperty("user.dir") + "//Desktop//" + testCaseName + ".png";

	}
}
