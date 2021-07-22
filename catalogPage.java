import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class catalogPage {

    private WebDriver driver;

    private By catalogCollapseBtn = By.id("catalogCollapseToggle");
    private By circuitBreakersBtn= By.cssSelector("#catalogCollapse .l1:first-child > div > button > .px-2:first-child");
    private By av6Btn = By.cssSelector("#catalogCollapse .l1:first-child .collapse .l2:first-child > div > .btn > .px-2:nth-child(2)");
    private By av6BCharBtn = By.cssSelector("#catalogCollapse .l1:first-child .collapse .l2:first-child .l3:first-child > .btn");

    public catalogPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCatalogCollapseBtn() {
        driver.findElement(catalogCollapseBtn).click();
    }

    public void clickCircuitBreakersBtn() {
        driver.findElement(circuitBreakersBtn).click();
    }
    public void clickAv6Btn() {
        driver.findElement(av6Btn).click();
    }

    public void clickAv6BBtn() {
        driver.findElement(av6BCharBtn).click();
    }

    public WebElement getWebElementCircuitBreakersBtn() {
        return driver.findElement(circuitBreakersBtn);
    }

    public WebElement getWebAv6Btn() {
        return driver.findElement(av6Btn);
    }

    public WebElement getWebAv6BCharBtn() {
        return driver.findElement(av6BCharBtn);
    }
}
