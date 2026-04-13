package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public static void addProduct(WebDriver driver, WebDriverWait wait, String id) {

    By product = By.cssSelector("[data-product-id='" + id + "']");
    By closeBtn = By.cssSelector(".btn.btn-success.close-modal.btn-block");

    // Wait for product
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(product));

    // Scroll to element
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    // JS click (bypasses overlay issues)
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    // Wait for modal and close
    WebElement close = wait.until(ExpectedConditions.elementToBeClickable(closeBtn));
    close.click();

    // 🔥 IMPORTANT: wait until modal disappears
    wait.until(ExpectedConditions.invisibilityOf(close));
}

public class App {
    public static void main(String[] args) throws InterruptedException {

	// ✅ Headless setup (MANDATORY for Jenkins)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open Products Page
        driver.get("https://automationexercise.com/products");

        // Add Product 4
		addProduct(driver, wait, "4");

		// Add Product 5
		addProduct(driver, wait, "5");

		// Add Product 6
		addProduct(driver, wait, "6");

        // Open Cart Page
        driver.get("https://automationexercise.com/view_cart");

        // Wait until cart loads (example check)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        System.out.println("Cart page loaded successfully");

        driver.quit();
    }
}
