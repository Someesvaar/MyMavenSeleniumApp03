package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws InterruptedException {

	WebDriver driver = new ChromeDriver();
        // Open Products Page
        driver.get("https://automationexercise.com/products");
        // Maximize Browser Window
        driver.manage().window().maximize();
        // Add Product 4 to Cart
        driver.findElement(By.cssSelector("[data-product-id='4']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();

        // Add Product 5 to Cart
        driver.findElement(By.cssSelector("[data-product-id='5']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();

        // Add Product 6 to Cart
        driver.findElement(By.cssSelector("[data-product-id='6']")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector(".btn.btn-success.close-modal.btn-block")).click();

        // Open Cart Page
        driver.get("https://automationexercise.com/view_cart");

        // Refresh Cart Page
        driver.navigate().refresh();

        // Wait for 10 seconds
        Thread.sleep(10000);

        // Close Browser
        driver.quit();
    }
}
