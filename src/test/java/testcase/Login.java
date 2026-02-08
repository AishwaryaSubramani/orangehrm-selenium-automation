package testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;

public class Login extends BaseTest {

	@Test(dataProvider = "testdata")
    public void Logintest(String name, String pass) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Always start from login page for each dataset
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Username
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name(loc.getProperty("Username")))).clear();
        driver.findElement(By.name(loc.getProperty("Username"))).sendKeys(name);

        // Password
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name(loc.getProperty("Password")))).clear();
        driver.findElement(By.name(loc.getProperty("Password"))).sendKeys(pass);

        // Click login
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(loc.getProperty("Login_Button")))).click();

        // ✅ Only success is considered PASS
        // If login is invalid, this will time out and test will FAIL
        wait.until(ExpectedConditions.urlContains("/dashboard"));

        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"),
                "Login failed - dashboard not reached for user=" + name);
    }

    @DataProvider(name = "testdata")
    public Object[][] tdata() {
        return new Object[][] {
                { "Admin", "admin12" },   // will FAIL
                { "Admi",  "admin123" },  // will FAIL
                { "Admin", "admin123" }   // will PASS
        };
    }
}

