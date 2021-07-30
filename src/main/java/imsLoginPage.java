import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class imsLoginPage {

    private WebDriver driver;

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

    public void fillLoginField() {
        driver.findElement(loginField).sendKeys("testovtt");
    }

    public void fillPasswordField() {
        driver.findElement(passwordField).sendKeys("377533");
    }


}
