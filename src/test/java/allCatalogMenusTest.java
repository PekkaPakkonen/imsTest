import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class allCatalogMenusTest {

    private WebDriver driver;
    private imsLoginPage loginPage;
    private imsMainPage mainPage;
    private catalogPage catPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        loginPage = new imsLoginPage(driver);
        mainPage = new imsMainPage(driver);
        catPage = new catalogPage(driver);
        driver.get("https://ims3.ekf.su/login");
        loginPage.fillLoginField();
        loginPage.fillPasswordField();
        loginPage.clickLoginBtn();
        //new WebDriverWait(driver, 10).until(ExpectedConditions.urlMatches("https://ims3.ekf.su/"));
        mainPage.clickCatalogBtn();
        //new WebDriverWait(driver, 20).until(ExpectedConditions.urlMatches("https://ims3.ekf.su/hasura/catalog"));
    }

    @Test
    public void checkAllRefs() throws Exception {
        catPage.clickAllL1Buttons();
        catPage.clickAllL2Buttons();
        catPage.clickAllL3Buttons();
        catPage.clickAllL4Buttons();
        catPage.clickL3WhiteButtons();

    }

    @AfterClass
    public void quit() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }

}
