import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class allItemsTest {

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
        driver.get("https://ims3dev.ekf.su/login");
        loginPage.fillLoginField();
        loginPage.fillPasswordField();
        loginPage.clickLoginBtn();
        mainPage.waitForCatalogBtnToBeClickable();
        mainPage.clickCatalogBtn();
        catPage.waitForCatalogPageToBeClickable();
    }

    @Test
    public void checkAllItems() {
        int buttonsAmount = catPage.getAllWhiteButtons().length;
        for(int i = 0; i < buttonsAmount; i++) { //clicks on every white button that opens a new table page
            WebElement[] l3WhiteButtons;
            WebElement[] itemLinks;
            boolean flag = true; // checks if there are several pages with table lists
            catPage.jsOpenAllDropdowns();
            l3WhiteButtons = catPage.getAllWhiteButtons();
            l3WhiteButtons[i].click();//refreshes all links on the catalog page

            try {
                tablePage.waitForTableInfoPresence();
                new WebDriverWait(driver, 10).until(ExpectedConditions
                        .elementToBeClickable(tablePage.getAllItemLinks()[0]));
            } catch (Exception e) {
                flag = false;
                System.out.println("Table page is empty!");
            }

            while(flag) {
                int page = 1;
                flag = tablePage.isNextPageButtonAvailable();
                itemLinks = tablePage.getAllItemLinks();

                if(flag) {
                    //if next table page is available, move to it
                    tablePage.clickNextPageElement();
                    page++;
                    new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("page=" + page));
                    tablePage.waitForTableInfoPresence();
                }
            }
            driver.get("https://ims3dev.ekf.su/hasura/catalog");
            catPage.waitForCatalogPageToBeClickable();
        }
    }



    @AfterClass
    public void quit() {
        driver.quit();
    }
}
