package section19Code.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import section19Code.pageObjects.Section19Code_CartPage;
import section19Code.pageObjects.Section20Code_OrdersPage;

public class Section19Code_AbstractComponents {

	WebDriver webDriver;

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public Section19Code_AbstractComponents(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public void waitForElementToAppear(By findBy) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		Thread.sleep(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement findBy) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		Thread.sleep(5);
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	protected void waitForElementToDisappear(WebElement spinner) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(spinner));

		// Add in additional delay
		Thread.sleep(2000);
	}

	public Section19Code_CartPage goToCartPage() {
		cartHeader.click();
		Section19Code_CartPage cartPage = new Section19Code_CartPage(webDriver);
		return cartPage;
	}

	public Section20Code_OrdersPage goToOrdersPage() {
		orderHeader.click();
		Section20Code_OrdersPage ordersPage = new Section20Code_OrdersPage(webDriver);
		return ordersPage;
	}

}
