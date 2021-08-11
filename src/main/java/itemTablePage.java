import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class itemTablePage {

    private WebDriver driver;
    private JavascriptExecutor exec;

    private By AllItemLinks = By.cssSelector(".text-left .text-reset");
    private By nextGoToNextPageBtn = By.cssSelector(".pagination:nth-child(4) > .page-item:nth-child(5) > .page-link");

    public itemTablePage(WebDriver driver) {
        this.driver = driver;
        exec = (JavascriptExecutor) driver;
    }

    public WebElement[] getAllItemLinks() {
        return driver.findElements(AllItemLinks).toArray(new WebElement[0]);
    }

    public void waitForItemInfoPresence() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".h4.font-weight-bold.d-lg-none")));
    }

    public void waitForTableInfoPresence() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-left .text-reset")));
    }

    public void clickNextPageElement() {
        new Actions(driver).moveToElement(driver.findElement(nextGoToNextPageBtn));
        driver.findElement(nextGoToNextPageBtn).click();
    }
}
