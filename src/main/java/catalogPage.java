import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class catalogPage {

    private WebDriver driver;
    private JavascriptExecutor exec;

    private By l1Buttons = By.cssSelector(".l1 > div > button > .px-2:first-child");
    private By l2Buttons = By.cssSelector(".l2 div .btn-gray-200 .px-2:nth-child(2)"); //don't change the color of buttons!
    private By l3Buttons = By.cssSelector(".l3 > div > .btn-gray-100 .px-2:nth-child(2)");

    public catalogPage(WebDriver driver) {
        this.driver = driver;
        exec = (JavascriptExecutor) driver;
    }

    public void clickAllL1Buttons() throws Exception {

        WebElement[] l1Buttons = getL1Buttons();

        for(WebElement i: l1Buttons) {
            exec.executeScript("arguments[0].click();", i);
        }

    }

    public void clickAllL2Buttons() throws Exception {

        WebElement[] l2Buttons = getL2Buttons();

        for(WebElement i: l2Buttons) {
            exec.executeScript("arguments[0].click();", i);
        }

    }

    public void clickAllL3Buttons() throws Exception {

        WebElement[] l3Buttons = getL3Buttons();

        for(WebElement i: l3Buttons) {
            exec.executeScript("arguments[0].click();", i);
            Thread.sleep(500);
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

}
