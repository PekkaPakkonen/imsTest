import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class itemTablePage {

    private WebDriver driver;
    private JavascriptExecutor exec;

    private By AllItemLinks = By.cssSelector(".text-left .text-reset");

    public itemTablePage(WebDriver driver) {
        this.driver = driver;
        exec = (JavascriptExecutor) driver;
    }

    public WebElement[] getAllItemLinks() {
        return driver.findElements(AllItemLinks).toArray(new WebElement[0]);
    }
}
