package testcase;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class Logout extends BaseTest{
	@Test
    public void logoutTest() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // ---------- Login ----------
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name(loc.getProperty("Username")))).clear();
        driver.findElement(By.name(loc.getProperty("Username"))).sendKeys("Admin");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name(loc.getProperty("Password")))).clear();
        driver.findElement(By.name(loc.getProperty("Password"))).sendKeys("admin123");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(loc.getProperty("Login_Button")))).click();

        // Verify dashboard loaded
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"),
                "Login failed, dashboard not loaded");

        // ---------- Click user dropdown ----------
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(loc.getProperty("User_Dropdown")))).click();

        // ---------- Click Logout ----------
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(loc.getProperty("Logout_Link")))).click();

        // ---------- Verify logout success ----------
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name(loc.getProperty("Username"))));

        Assert.assertTrue(
                driver.findElement(By.name(loc.getProperty("Username"))).isDisplayed(),
                "Logout failed, login page not displayed"
        );
    }

}
