import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class catalogPage {

    private WebDriver driver;
    private JavascriptExecutor exec;

    private final By l1Buttons = By.cssSelector(".l1> div > .btn .caret");
    private final By l2Buttons = By.cssSelector(".l2> div > .btn .caret");
    private final By l3Buttons = By.cssSelector(".l3> div > .btn .caret");
    private final By l4Buttons = By.cssSelector(".l4> div > .btn .caret");
    private final By l3WhiteButtons = By.cssSelector(".l3 .btn-white span");

    public catalogPage(WebDriver driver) {
        this.driver = driver;
        exec = (JavascriptExecutor) driver;
    }

    public void clickAllL1Buttons() {

        WebElement[] l1Buttons = getL1Buttons();

        for(WebElement i: l1Buttons) {
            i.click();

        }

    }

    public void clickAllL2Buttons() {

        WebElement[] l2Buttons = getL2Buttons();

        for(WebElement i: l2Buttons) {
            i.click();
        }

    }

    public void clickAllL3Buttons() {
        WebElement[] l3Buttons = getL3Buttons();

        for(WebElement i: l3Buttons) {
            i.click();
        }

    }

    public void clickAllL4Buttons() {

        WebElement[] l4Buttons = getL4Buttons();

        for(WebElement i: l4Buttons) {
            i.click();
        }

    }

    public void clickL3WhiteButtons() throws Exception {
        WebElement[] l1Buttons = getL1Buttons();
        WebElement[] l2Buttons = getL2Buttons();
        WebElement[] l3Buttons = getL3Buttons();
        WebElement[] l4Buttons = getL4Buttons();
        WebElement[] l3WhiteButtons = getL3WhiteButtons();

        for(WebElement i: l3WhiteButtons) {
            i.click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("short_name"));
            driver.navigate().back();
            new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://ims3.ekf.su/hasura/catalog"));

            for(WebElement j : l1Buttons) {
               JavascriptExecutor js = (JavascriptExecutor) driver;
               js.executeScript("arguments[0].click();", j);
            }

            for(WebElement j : getL2Buttons()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", j);
            }

            for(WebElement j : getL3Buttons()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", j);
            }

            for(WebElement j : getL4Buttons()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", j);
            }
        }

    }

    public WebElement[] getL1Buttons() {
        return driver.findElements(l1Buttons).toArray(new WebElement[0]);
    }

    public WebElement[] getL2Buttons() {
        return driver.findElements(l2Buttons).toArray(new WebElement[0]);
    }

    public WebElement[] getL3Buttons() {
        return driver.findElements(l3Buttons).toArray(new WebElement[0]);
    }

    public WebElement[] getL4Buttons() {
        return driver.findElements(l4Buttons).toArray(new WebElement[0]);
    }

    public WebElement[] getL3WhiteButtons() {
        return driver.findElements(l3WhiteButtons).toArray(new WebElement[0]);
    }

}
