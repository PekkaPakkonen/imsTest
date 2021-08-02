import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class imsMainPage {

    private WebDriver driver;

    private By catalogBtn = By.cssSelector(".nav-link.catalog[href=\"/hasura/catalog\"]");
    private By createRequestBtn = By.cssSelector(".btn.px-30");
    private By formAIRtn = By.cssSelector(".btn.mb-30");

    public imsMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCatalogBtn() {
        driver.findElement(catalogBtn).click();
    }

    public By getCatalogBtn() {
        return catalogBtn;
    }

    public WebElement waitForCatalogBtnToBeClickable() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(getCatalogBtn()));
    }
}
