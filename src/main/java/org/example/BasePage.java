package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

    // בנאי (Constructor) שמקבל את הדרייבר ומאתחל אלמנטים
    public BasePage(WebDriver driver) {
        this.driver = driver;
        // הפקודה הזו קריטית - בלעדיה ה-@FindBy לא יעבוד
        PageFactory.initElements(driver, this);
    }
}