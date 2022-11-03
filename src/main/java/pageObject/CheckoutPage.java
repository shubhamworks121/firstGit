package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	// Clicking on country search
	@FindBy(xpath="(//button[@type='button'])[2]")
	WebElement country;
	
	// Selecting suggestive country
	@FindBy(css="button[class*='ta-item']:last-of-type")
	WebElement countrySelect;
	
	// Place Order button
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submit;
	
	
	// res-- After entering india wait for the suggestive options to be appear
	By res=By.cssSelector(".ta-results");
	
	public void SelectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country,"india").build().perform();
		//Wait untill all options get visible
		waitForElementToAppear(res);
		// Click on country
		countrySelect.click()	;
	}
	public ConfirmationPage submitOrder()
	{
		submit.click();//this will land on confirmation page
		// so return Confirmation page
		return new ConfirmationPage(driver);
	}
	
}
