import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class itemTablePage {

    private WebDriver driver;
    private JavascriptExecutor exec;

    private By AllItemLinks = By.cssSelector(".text-left .text-reset");
    private By goToNextPageBtn = By.cssSelector(".page-link[aria-label=\"Go to next page\"]");

    public itemTablePage(WebDriver driver) {
        this.driver = driver;
        exec = (JavascriptExecutor) driver;
    }

    public WebElement getNextPageBtn() {
        return driver.findElement(goToNextPageBtn);
    }

    public WebElement[] getAllItemLinks() {
        return driver.findElements(AllItemLinks).toArray(new WebElement[0]);
    }


    public void waitForTableInfoPresence() {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-left .text-reset")));
        } catch (TimeoutException e) {

        }

    }

    public void clickNextPageElement() {
        new Actions(driver).moveToElement(driver.findElement(goToNextPageBtn));
        driver.findElement(goToNextPageBtn).click();
    }

    public Boolean isNextPageButtonAvailable() {
        try {
            return !getNextPageBtn().getAttribute("aria-disabled").equals("true");
        } catch (NullPointerException e) {
            return true;
        }
    }
}
