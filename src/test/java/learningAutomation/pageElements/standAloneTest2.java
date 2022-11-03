package learningAutomation.pageElements;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.CartPage;
import pageObject.CheckoutPage;
import pageObject.ConfirmationPage;
import pageObject.LandingPage;
import pageObject.OrderPage;
import pageObject.ProductCatalogue;

public class standAloneTest2 extends BaseTest {
	String productName="ZARA COAT 3";
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		
		ProductCatalogue productCatalogue =landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement>products=productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.SelectCountry("india");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmationMsg=confirmationPage.getConfMsg();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue =landingPage.loginApplication("shivamyadav6797@gmail.com","54321Ss#");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	public String getScreenShot(String testCaseName) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"//reports"+testCaseName+".png");
		FileUtils.copyFile(source,dest)	;
		return System.getProperty("user.dir")+"//reports"+testCaseName+".png";
	}
	
	
	
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//HashMap<String,String> map=new HashMap<String,String>();
		//map.put("email", "anshika@gmail.com");
		//map.put("password", "Iamking@000");
		//map.put("product", "ZARA COAT 3");
		//HashMap<Object,Object> map1=new HashMap<Object,Object>();
		//map1.put("email", "shivamyadav6797@gmail.com");
		//map1.put("password", "54321Ss#");
		//map1.put("product", "ADIDAS");
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"F:\\\\Selenium+Java_Udemy\\\\likeProjectName\\src\\test\\java\\dataJson\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
