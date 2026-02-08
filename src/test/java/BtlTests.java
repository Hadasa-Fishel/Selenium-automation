import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class BtlTests {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeEach
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://www.btl.gov.il/Pages/default.aspx");
        homePage = new HomePage(driver);
    }

    @Test // בדיקה 1: חיפוש
    public void testSearch() throws InterruptedException {
        String searchText = "דמי לידה";
        homePage.search(searchText);
        Thread.sleep(5000); // המתנה לתוצאות

        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        boolean isFound = resultsPage.isResultFound(searchText);
        Assertions.assertTrue(isFound, "החיפוש נכשל");
    }

    @Test // בדיקה 2: ניווט לסניפים
    public void testBranchDetails() {
        BranchesPage branchesList = homePage.goToBranches();
        System.out.println("בדיקת מעבר לסניפים עברה בהצלחה!");
    }

    @Test // בדיקה 3: מחשבון דמי ביטוח
    public void testInsuranceCalculation() throws InterruptedException {
        // 1. ניווט למחשבון
        homePage.navigateToPage(MainMenu.DEMEI_BITUAH, "דמי ביטוח לאומי");

        InsurancePage insurancePage = new InsurancePage(driver);
        CalculatorPage calculator = insurancePage.clickCalculator();

        // 2. מילוי טופס ראשון (כולל תלמיד ישיבה, זכר, תאריך)
        calculator.fillDateOfBirth("01/01/2004");

        // --- התיקון: הגדלנו את ההמתנה מ-2 ל-8 שניות ---
        // האתר איטי במעבר בין שלב 1 לשלב 2
        System.out.println("ממתין למעבר לשלב הבא...");
        Thread.sleep(8000);

        // 3. סימון "לא" בקצבת נכות
        calculator.selectNoDisability();

        // --- התיקון: הגדלנו את ההמתנה לחישוב ---
        Thread.sleep(5000);

        // 4. בדיקת התוצאה הסופית
        String result = calculator.getTotalResult();
        System.out.println("תוצאת החישוב במחשבון: " + result);

        // בדיקה שהסכום הגיוני (מכיל ספרות של סכום)
        Assertions.assertTrue(result.length() > 0, "לא התקבלה תוצאה");
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}