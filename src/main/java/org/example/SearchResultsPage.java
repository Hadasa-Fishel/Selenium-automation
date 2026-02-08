package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultsPage extends BtlBasePage {

    // הכותרת שמופיעה בראש דף התוצאות
    @FindBy(css = ".path") // שחזור משוער של האלמנט, ייתכן שתצטרכי לעדכן לפי האתר האמיתי
    private WebElement resultsHeader;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    // פונקציה שבודקת האם הטקסט שחיפשנו מופיע בכותרת
    public boolean isResultFound(String text) {
        // בודק אם הכותרת מכילה את הטקסט
        try {
            return driver.getTitle().contains(text) || driver.getPageSource().contains(text);
        } catch (Exception e) {
            return false;
        }
    }
}