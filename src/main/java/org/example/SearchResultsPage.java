package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BtlBasePage {

    // הכותרת שמופיעה בראש דף התוצאות
    @FindBy(css = ".path")
    private WebElement resultsHeader;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    // פונקציה שבודקת האם הטקסט שחיפשנו מופיע בכותרת
    public boolean isResultFound(String text) {
        try {
            // שליפת כל הטקסט מהדף והפיכתו לטקסט רציף ללא ירידות שורה כפולות
            String bodyText = driver.findElement(By.tagName("body")).getText().replace("\n", " ");

            // בדיקה אם המחרוזת קיימת בתוך הטקסט של הדף
            return bodyText.contains(text);
        } catch (Exception e) {
            return false;
        }
    }
}