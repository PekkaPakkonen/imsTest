import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class documentsPage {

    private final WebDriver driver;

    private final By noDocumentsAlert = By.cssSelector("[colspan=\"2\"] .text-center.text-muted");

    public documentsPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getNoDocumentsAlert() {
        return noDocumentsAlert;
    }
}
