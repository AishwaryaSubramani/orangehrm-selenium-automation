package testcase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class AddEmployee extends BaseTest{

    @Test
    public void addEmployeeTest() throws InterruptedException {

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

         // ---------- Go to PIM -> Add Employee ----------
         wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath(loc.getProperty("PIM_Menu")))).click();

         // Direct navigation (stable)
         driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee");

         // ---------- Fill Add Employee form ----------
         String firstName = "Aishu";
         String lastName = "Test" + System.currentTimeMillis();

         wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.cssSelector(loc.getProperty("FirstName")))).clear();
         driver.findElement(By.cssSelector(loc.getProperty("FirstName"))).sendKeys(firstName);

         wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.cssSelector(loc.getProperty("LastName")))).clear();
         driver.findElement(By.cssSelector(loc.getProperty("LastName"))).sendKeys(lastName);

         // Save
         wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath(loc.getProperty("Save_Button")))).click();

         // ---------- Verify employee created ----------
         wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.xpath(loc.getProperty("PersonalDetails_Header"))));

         Assert.assertTrue(
                 driver.findElement(By.xpath(loc.getProperty("PersonalDetails_Header"))).isDisplayed(),
                 "Employee not created / Personal Details page not displayed"
         );
    }
}
