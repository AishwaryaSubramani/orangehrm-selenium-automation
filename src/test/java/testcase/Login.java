package testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;

public class Login extends BaseTest {

	@Test(dataProvider = "testdata")
	public void Logintest(String name, String pass) throws InterruptedException {
		Thread.sleep(4000);
		driver.findElement(By.name(loc.getProperty("Username"))).clear();
		driver.findElement(By.name(loc.getProperty("Username"))).sendKeys(name);
		Thread.sleep(4000);
		driver.findElement(By.name(loc.getProperty("Password"))).clear();
		driver.findElement(By.name(loc.getProperty("Password"))).sendKeys(pass);
		Thread.sleep(4000);
		driver.findElement(By.xpath(loc.getProperty("Login_Button"))).click();

		boolean expectedSuccess = name.equals("Admin") && pass.equals("admin123");

		if (expectedSuccess) {
			Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"),
					"Expected success login, but dashboard not reached");
		} else {
			Assert.assertTrue(driver.findElement(By.cssSelector(".oxd-alert.oxd-alert--error")).isDisplayed(),
					"Expected error for invalid login, but not shown");
		}

	}

	@DataProvider(name = "testdata")
	public Object[][] tdata() {
		return new Object[][] {

				{ "Admin", "admin12" }, { "Admi", "admin123" }, { "Admin", "admin123" } };
	}
}
