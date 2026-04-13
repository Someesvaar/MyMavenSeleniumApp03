package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class App {
    public static void main(String[] args) {

        // ✅ Headless setup (required for Jenkins)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open Products Page
        driver.get("https://automationexercise.com/products");

        // Add products safely
        addProduct(driver, wait, "4");
        addProduct(driver, wait, "5");
        addProduct(driver, wait, "6");

        // Open Cart Page
        driver.get("https://automationexercise.com/view_cart");

        // Wait for page load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        System.out.println("Cart page loaded successfully");

        driver.quit();
    }

    // ✅ Reusable method
    public static void addProduct(WebDriver driver, WebDriverWait wait, String id) {

        By product = By.cssSelector("[data-product-id='" + id + "']");
        By closeBtn = By.cssSelector(".btn.btn-success.close-modal.btn-block");

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(product));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // JS click (avoids interception)
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        // Wait for modal and close
        WebElement close = wait.until(ExpectedConditions.elementToBeClickable(closeBtn));
        close.click();

        // Wait until modal disappears
        wait.until(ExpectedConditions.invisibilityOf(close));
    }
}
