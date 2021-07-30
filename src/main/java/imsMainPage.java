import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class imsMainPage {

    private WebDriver driver;

    private By catalogBtn = By.cssSelector(".nav-link.catalog[href=\"/hasura/catalog\"]");

    public imsMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCatalogBtn() {
        driver.findElement(catalogBtn).click();
    }

    public By getCatalogBtn() {
        return catalogBtn;
    }
}
