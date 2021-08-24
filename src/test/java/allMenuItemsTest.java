import org.openqa.selenium.TimeoutException;
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

        for(int i = 294; i < buttonsAmount; i++) { //clicks on every white button that opens a new table page
            String catalogPageId = driver.getWindowHandle();
            WebElement[] l3WhiteButtons;
            WebElement[] itemLinks;
            boolean flag = true; // checks if there are several pages with table lists

            catPage.jsOpenAllDropdowns();
            l3WhiteButtons = catPage.getAllWhiteButtons(); //refreshes all links on the catalog page
            l3WhiteButtons[i].click();

            try { //prevents Exception if table list is empty
                tablePage.waitForTableInfoPresence();
                new WebDriverWait(driver, 10).until(ExpectedConditions
                        .elementToBeClickable(tablePage.getAllItemLinks()[0]));
            } catch (Exception e) {
                flag = false;
                System.out.println("Table page is empty!");
            }


            while(flag) {
                flag = tablePage.isNextPageButtonAvailable();
                itemLinks = tablePage.getAllItemLinks();
                for (WebElement item : itemLinks) {
                    item.click();
                    driver.switchTo().window(driver.getWindowHandles().toArray(new String[0])[1]);
                    itPage.waitForItemInfoPresence();
                    //checks for discount price operation
                    itPage.sendDiscount(50);
                    itPage.clickCalculateBtn();

                    //checks for image carousel operation
                    itPage.clickImageCarousel();

                    //checks for cart text field operation
                    itPage.sendAmount(1);
                    //!need to check if entered amount is more than minimum ordered amount

                    //checks for page tabs operation
                    itPage.clickDescriptionBtn();
                    itPage.clickDocsBtn();
                    itPage.clickAnaloguesBtn();
                    itPage.clickAccessoriesBtn();
                    itPage.clickTechSpecsBtn();

                    //closes current tab and switch into tablePage page
                    driver.close();
                    driver.switchTo().window(catalogPageId);
                }
                if(flag) {
                    //if next table page is available, move to it
                    tablePage.clickNextPageElement();
                    Thread.sleep(2000);
                }
            }
            driver.get("https://ims3.ekf.su/hasura/catalog");
            catPage.waitForCatalogPageToBeClickable();
        }
    }

    @AfterClass
    public void quit() throws Exception {
        Thread.sleep(5000);
        driver.quit();
    }
}
