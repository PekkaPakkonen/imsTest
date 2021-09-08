import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class marketingPage {

    private WebDriver driver;

    private By table = By.cssSelector(".has-toolbar .table");

    public marketingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForTable() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(table));
    }


}
