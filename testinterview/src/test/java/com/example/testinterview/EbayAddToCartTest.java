package com.example.testinterview;


import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EbayAddToCartTest {
    public static void main(String[] args) {
        // Set up ChromeDriver path if needed
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        
        try {
            // 1. Open browser and 2. Navigate to ebay.com
            driver.get("https://www.ebay.com");

            // 3. Search for 'book'
            WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("gh-ac"))
            );
            searchBox.sendKeys("book");
            driver.findElement(By.className("gh-search-button__label")).click();

            // 4. Click on the first book in the list
            WebElement firstBook = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("s-card__image"))
            );
            firstBook.click();

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            driver.manage().window().maximize();
            
            // 5. Click 'Add to cart'
            WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("atcBtn_btn_1"))
            );
            addToCartBtn.click();
            System.out.println("added to cart");
            
            // 6. Close popup
            WebElement popup = wait.until(
                ExpectedConditions.elementToBeClickable(By.className("icon-btn lightbox-dialog__close"))
            );
            popup.click();
            
            WebElement cartCount = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("badge gh-badge"))
                );
            
            int count = Integer.parseInt(cartCount.getText());
            if (count >= 1) {
                System.out.println("Test Passed: Item added to cart.");
            } else {
                System.out.println("Test Failed: Cart not updated.");
            }
        } finally {
            driver.quit();
        }
    }
}
