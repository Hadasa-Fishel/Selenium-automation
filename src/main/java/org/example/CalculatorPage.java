package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorPage extends BtlBasePage {

    // --- אלמנטים ---

    // כפתור רדיו: תלמיד ישיבה
    @FindBy(css = "input[id$='rdb_employeType_1']")
    private WebElement yeshivaStudentRadio;

    // כפתור רדיו: מין זכר
    @FindBy(css = "input[id$='rdb_Gender_0']")
    private WebElement maleGenderRadio;

    // שדה תאריך לידה
    @FindBy(css = "input[id$='BirthDate_Date']")
    private WebElement dateOfBirthField;

    // כפתור "המשך"
    @FindBy(css = "input[value='המשך']")
    private WebElement continueButton;

    // כפתור רדיו: קצבת נכות - "לא"
    @FindBy(css = "input[id$='rdb_GetNechut_1']")
    private WebElement disabilityNoRadio;

    // --- התיקון: זיהוי התוצאה לפי ה-div שמצאנו בצילום ---
    @FindBy(css = "div[id$='_div_Result']")
    private WebElement totalAmountContainer;


    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    // --- פונקציית עזר ללחיצה חזקה (JS Click) ---
    private void clickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    // --- פעולות ---

    public void fillDateOfBirth(String date) {
        clickWithJS(yeshivaStudentRadio);
        clickWithJS(maleGenderRadio);

        dateOfBirthField.clear();
        dateOfBirthField.sendKeys(date);

        continueButton.click();
    }

    public void selectNoDisability() {
        clickWithJS(disabilityNoRadio);
        continueButton.click();
    }

    public String getTotalResult() {
        // מחזיר את כל הטקסט בתיבת התוצאה (כולל הסכום)
        return totalAmountContainer.getText();
    }
}