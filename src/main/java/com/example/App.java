package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-product-id='4']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success.close-modal.btn-block"))).click();

        // Add Product 5
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-product-id='5']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success.close-modal.btn-block"))).click();

        // Add Product 6
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-product-id='6']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success.close-modal.btn-block"))).click();

        // Open Cart Page
        driver.get("https://automationexercise.com/view_cart");

        // Wait until cart loads (example check)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        System.out.println("Cart page loaded successfully");

        driver.quit();
    }
}
