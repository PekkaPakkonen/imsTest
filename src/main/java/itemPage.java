import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class itemPage {

    private WebDriver driver;
    JavascriptExecutor exec;

    private final By techSpecsBtn = By.cssSelector(".nav-link[aria-posinset=\"1\"]");
    private final By descriptionBtn = By.cssSelector(".nav-link[aria-posinset=\"2\"]");
    private final By docsBtn = By.cssSelector(".nav-link[aria-posinset=\"3\"]");
    private final By analoguesBtn = By.cssSelector(".nav-link[aria-posinset=\"4\"]");
    private final By accessoriesBtn = By.cssSelector(".nav-link[aria-posinset=\"5\"]");

    public itemPage(WebDriver driver) {
        this.driver = driver;
        exec = (JavascriptExecutor) driver;
    }

    public WebElement getTechSpecsBtn() {
        return driver.findElement(techSpecsBtn);
    }

    public WebElement getDescriptionBtn() {
        return driver.findElement(descriptionBtn);
    }

    public WebElement getDocsBtn() {
        return driver.findElement(docsBtn);
    }

    public WebElement getAnaloguesBtn() {
        return driver.findElement(analoguesBtn);
    }

    public WebElement getAccessoriesBtn() {
        return driver.findElement(accessoriesBtn);
    }

    public void waitForItemInfoPresence() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".h4.font-weight-bold.d-lg-none")));
    }


}
