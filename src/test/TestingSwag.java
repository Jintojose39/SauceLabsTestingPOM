package test;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.LoginPageSwags;


public class TestingSwag {
	
	static WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		
		driver =new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	
	
	}
	
	@Test
	public void loginTest() {
		LoginPageSwags lg =new LoginPageSwags(driver);
		
		lg.Login();
		
		
	}


@Test
public void tearDown() {
	driver.quit();
}

}
	
	
	

	

