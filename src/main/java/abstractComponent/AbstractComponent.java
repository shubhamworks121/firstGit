package abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.CartPage;
import pageObject.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink='/dashboard/cart']")
	WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
		System.out.println("Running from Git");
		System.out.println("Another changes -- Running from Git");
		System.out.println("Running from Develop branch to understand branching in GIT");
		System.out.println("Running again and again");

		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrderPage()
	{
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}


}
