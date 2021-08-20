import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class allCatalogMenusTest {
    // The test checks if all inner dropdown lists inside catalog dropdown list are clickable and all refs are valid.
    private WebDriver driver;
    private imsLoginPage loginPage;
    private imsMainPage mainPage;
    private catalogPage catPage;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        loginPage = new imsLoginPage(driver);
        mainPage = new imsMainPage(driver);
        catPage = new catalogPage(driver);
        driver.get("https://ims3.ekf.su/login");
        loginPage.fillLoginField();
        loginPage.fillPasswordField();
        loginPage.clickLoginBtn();
        mainPage.waitForCatalogBtnToBeClickable();
        mainPage.clickCatalogBtn();
    }

    @Test
    public void checkAllRefs() {
        catPage.waitForCatalogPageToBeClickable();
        catPage.clickAllL1Buttons();
        catPage.clickAllL2Buttons();
        catPage.clickAllL3Buttons();
        catPage.clickAllL4Buttons();
        catPage.refreshAndClickButtons();
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }

}
