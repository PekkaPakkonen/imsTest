import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class imsLoginPage {

    private WebDriver driver;
    private final String login = "testovtt";
    private final String password = "377533";

    private final By loginField = By.cssSelector(".form-control[type = \"text\"]");
    private final By passwordField = By.cssSelector(".form-control[type = \"password\"]");
    private final By loginBtn = By.cssSelector(".btn.float-right");

    public imsLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillLoginField(String key) {
        driver.findElement(loginField).sendKeys(key);
    }

    public void fillPasswordField(String key) {
        driver.findElement(passwordField).sendKeys(key);
    }

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
    }

    public void fillPasswordField() {
        driver.findElement(passwordField).sendKeys(password);

    }

    public void fillLoginField() {
        driver.findElement(loginField).sendKeys(login);
    }

    public void waitForLoginField() {
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(loginField));
    }



}
