import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class imsMainPage {

    private WebDriver driver;

    private By catalogBtn = By.cssSelector(".nav-link.catalog[href=\"/hasura/catalog\"]");
    private By exitBtn = By.cssSelector(".navbar-nav .nav-item:nth-child(1)");
    private By marketingBtn = By.xpath("//span[contains(text(),'Маркетинг')]");
    private By advertisementBtn = By.xpath("//li/div/ul/li[4]/a[contains(text(),'Рекламные материалы')]");
    private By createRequestBtn = By.cssSelector(".btn.px-30");
    private By formAIRtn = By.cssSelector(".btn.mb-30");

    ////span[contains(text(),'Маркетинг')]
    //li/div/ul/li[4]/a[contains(text(),'Рекламные материалы')]

    public imsMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getAdvertisementBtn() {
        return advertisementBtn;
    }

    public void clickCatalogBtn() {
        driver.findElement(catalogBtn).click();
    }

    public void clickExitBtn() {
        driver.findElement(exitBtn).click();
    }

    public void clickMarketingBtn() {
        driver.findElement(marketingBtn).click();
    }

    public void clickAdvertisementBtn() {
        driver.findElement(advertisementBtn).click();
    }

    public By getCatalogBtn() {
        return catalogBtn;
    }

    public WebElement waitForCatalogBtnToBeClickable() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(getCatalogBtn()));
    }


}
