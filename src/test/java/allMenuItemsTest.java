import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class allMenuItemsTest {

    private WebDriver driver;
    private imsLoginPage loginPage;
    private imsMainPage mainPage;
    private catalogPage catPage;
    private itemTablePage tablePage;
    private itemPage itPage;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        loginPage = new imsLoginPage(driver);
        mainPage = new imsMainPage(driver);
        catPage = new catalogPage(driver);
        tablePage = new itemTablePage(driver);
        itPage = new itemPage(driver);
        driver.get("https://ims3.ekf.su/login");
        loginPage.fillLoginField();
        loginPage.fillPasswordField();
        loginPage.clickLoginBtn();
        mainPage.waitForCatalogBtnToBeClickable();
        mainPage.clickCatalogBtn();
    }

    @Test
    public void checkAllItems() throws InterruptedException {
        catPage.waitForCatalogPageToBeClickable();
        int buttonsAmount = catPage.getAllWhiteButtons().length;

        for(int i = 0; i < buttonsAmount; i++) {
            String catalogPageId = driver.getWindowHandle();
            WebElement[] l3WhiteButtons;
            WebElement[] itemLinks;
            boolean flag = true;

            catPage.jsOpenAllDropdowns();
            l3WhiteButtons = catPage.getAllWhiteButtons();
            l3WhiteButtons[i].click();

            tablePage.waitForTableInfoPresence();

            new WebDriverWait(driver, 10).until(ExpectedConditions
                    .elementToBeClickable(tablePage.getAllItemLinks()[0]));

            while(flag) {
                flag = tablePage.isNextPageButtonAvailable();
                itemLinks = tablePage.getAllItemLinks();
                for (WebElement item : itemLinks) {
                    item.click();
                    driver.switchTo().window(driver.getWindowHandles().toArray(new String[0])[1]);
                    itPage.waitForItemInfoPresence();
                    driver.close();
                    driver.switchTo().window(catalogPageId);
                }
                if(flag) {
                    tablePage.clickNextPageElement();
                    Thread.sleep(3000);
                }
            }
            driver.navigate().back();
            driver.navigate().back();
            catPage.waitForCatalogPageToBeClickable();
        }
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}
