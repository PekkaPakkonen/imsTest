import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class catalogPageTest {

    private WebDriver driver;
    private catalogPage cPage;
    private imsLoginPage imsLPage;
    private imsMainPage imsMain;
    private JavascriptExecutor exec;

    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        imsLPage = new imsLoginPage(driver);
        imsMain = new imsMainPage(driver);
        cPage = new catalogPage(driver);
        driver.get("https://ims3.ekf.su/login");
        Assert.assertEquals(driver.getTitle(), "B2B портал IMS");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(groups ="a")
    public void logIn() {
        imsLPage.fillLoginField("testovtt");
        imsLPage.fillPasswordField("377533");
        imsLPage.clickLoginBtn();
    }


    @Test(groups = "a")
    public void moveToCatalog() {
        imsMain.clickCatalogBtn();
    }

    @Test(dependsOnGroups = "a")
    public void clickOnMenu() {
        exec = (JavascriptExecutor) driver;
        exec.executeScript("arguments[0].click();", cPage.getWebElementCircuitBreakersBtn());
        exec.executeScript("arguments[0].click();", cPage.getWebAv6Btn());
        exec.executeScript("arguments[0].click();", cPage.getWebAv6BCharBtn());
    }

    @AfterClass
    public void quit() throws Exception {
        Thread.sleep(5000);
        driver.quit();

    }
}
