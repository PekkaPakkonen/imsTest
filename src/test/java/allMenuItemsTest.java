import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Tests if all information about items ia available
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
        driver.get("https://ims3dev.ekf.su/login");
        loginPage.fillLoginField();
        loginPage.fillPasswordField();
        loginPage.clickLoginBtn();
        mainPage.waitForCatalogBtnToBeClickable();
        mainPage.clickCatalogBtn();
        catPage.waitForCatalogPageToBeClickable();
    }

    @Test
    public void checkAllItems() throws InterruptedException {
        int buttonsAmount = catPage.getAllWhiteButtons().length;
        boolean secflag = true;
        for(int i = 0; i < buttonsAmount; i++) { //clicks on every white button that opens a new table page
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
                int page = 1;
                flag = tablePage.isNextPageButtonAvailable();
                itemLinks = tablePage.getAllItemLinks();
                for(int j = 0; j < itemLinks.length;j++) {
                    //for (WebElement item : itemLinks) {
                    itemLinks[j].click();
                    driver.switchTo().window(driver.getWindowHandles().toArray(new String[0])[1]);
                    itPage.waitForItemInfoPresence();
                    //checks for discount price operation
                    if(secflag) {
                        itPage.sendDiscount(50);
                        secflag = false;
                    }
                    itPage.clickCalculateBtn();

                    //checks for image carousel operation
                    itPage.clickImageCarousel();

                    //checks for cart text field operation
                    itPage.sendAmount(1);
                    //!need to check if entered item amount is more than minimum possible ordered amount

                    //checks for page tabs operation
                    itPage.clickDescriptionBtn();
                    itPage.clickDocsBtn();
                    itPage.clickAnaloguesBtn();
                    itPage.clickAccessoriesBtn();
                    itPage.clickTechSpecsBtn();

                    //checks if all "add to cart" buttons have been enabled after item amount have been entered into text field
                    Assert.assertEquals(itPage.getAddToCartDisabledBtn().length, 0);

                    //closes current tab and switch into tablePage page
                    driver.close();
                    driver.switchTo().window(catalogPageId);
                }

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