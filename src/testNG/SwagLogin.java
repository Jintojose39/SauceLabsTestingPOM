package testNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import graphql.Assert;

public class SwagLogin {
	static WebDriver driver;
	
	@Parameters("browserName")
	@BeforeTest
	public void SetUpBrowser(String browserName) {
		switch(browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			driver =new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get("https://www.saucedemo.com/");
			break;
			
		case "edge":
			System.setProperty("webdriver.chrome.driver","edgedriver.exe");
			driver =new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get("https://www.saucedemo.com/");
			break;
			
			
		case "firefox":
			System.setProperty("webdriver.gecko.driver","geckodriver.exe");
			driver =new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			break;
			
			default:
				System.out.println("Browsername is Invalid");
				break;
	}
	
	}

	@Parameters("url")
	@Test(priority=1)
	public void LaunchURL(String url) {
		driver.get("https://www.saucedemo.com/");
		
	}

	@Parameters({"username","password"})
	@Test(priority=2)
	public void EnterDetails(String username, String password) {
		
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.cssSelector("#password")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		
		boolean lg =driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]")).isDisplayed();
		Assert.assertTrue(lg);
		
		System.out.println(lg);
	
	}
	
	@Test(priority=3)
	public void ProductClick() throws InterruptedException {
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("#item_4_img_link > img")).click();
	
		driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.cssSelector("#remove-sauce-labs-backpack")).click();
		
		
	}

	@Test(priority=4)
	public void BackActions() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#back-to-products")).click();
		
		
	}	
	
	@Test(priority=5)
	public void LogOutAction() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
		
		driver.findElement(By.xpath("//*[@id=\"logout_sidebar_link\"]")).click();
		
		Thread.sleep(2000);
		
		
	}


	@Test(dataProvider="data",priority=6)
	
	public void Invaliddata(String username1,String password1) throws InterruptedException {
		
		driver.findElement(By.id("user-name")).sendKeys(username1);
		driver.findElement(By.cssSelector("#password")).sendKeys(password1);
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		
		
		Thread.sleep(2000);
		driver.findElement(By.id("user-name")).clear();
		driver.findElement(By.cssSelector("#password")).clear();
		
		
		//String act =driver.findElement(By.xpath("//*[@id=\\\"login_button_container\\\"]/div/form/div[3]/h3")).getText();
		
		//Assert.assertValidName(act);
		//System.out.println(act);
		
		
		//driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
		
		
	}

	

	//@AfterMethod
	@AfterTest
	
	public void tearDown() {
		
		driver.quit();
		
	}
	


	@DataProvider(name = "data")
	public Object[][] userdata(){
		return new Object[][] {{"lockedk_out_user","secret_sauce"},
			   {"tyy","secret_sauce"},
			   {"adb","secret_sauce"}};

			
	}
	}
	

