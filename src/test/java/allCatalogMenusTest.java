import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class allCatalogMenusTest {

    private WebDriver driver;
    private imsLoginPage loginPage;
    private imsMainPage mainPage;
    private catalogPage catPage;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        loginPage = new imsLoginPage(driver);
        mainPage = new imsMainPage(driver);
        catPage = new catalogPage(driver);
        driver.get("https://ims3.ekf.su/login");
        loginPage.fillLoginField();
        loginPage.fillPasswordField();
        loginPage.clickLoginBtn();
        mainPage.clickCatalogBtn();
    }

    @Test
    public void checkAllRefs() throws Exception {
        catPage.clickAllL1Buttons();
        catPage.clickAllL2Buttons();
        catPage.clickAllL3Buttons();
            }

    @AfterClass
    public void quit() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }

}
