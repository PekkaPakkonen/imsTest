import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class checkPolicyTest {

    private WebDriver driver;
    private imsLoginPage loginPage;
    private imsMainPage mainPage;
    private documentsPage docsPage;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        loginPage = new imsLoginPage(driver);
        mainPage = new imsMainPage(driver);
        docsPage = new documentsPage(driver);
        driver.get("https://ims3dev.ekf.su/login");

    }

    @Test
    public void docsCheck() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("users2.csv"));
        reader.readLine();
        writer.write("login;password;policy docs access\n");
        writer.flush();

        while(reader.ready()) {
            String[] str = reader.readLine().split(";"); //";" for MS, "," for LibreOffice
            loginPage.fillLoginField(str[0]);
            loginPage.fillPasswordField(str[1]);
            loginPage.clickLoginBtn();
            try {
                mainPage.waitForCatalogBtnToBeClickable();
            } catch (TimeoutException e) {
                writer.write(String.format("%s;%s;wrong login or password\n", str[0], str[1]));
                driver.navigate().refresh();
                continue;
            }

            try {
                mainPage.clickDocumentsBtn();
                new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".col .page-title")));
                driver.findElement(By.cssSelector("[colspan=\"2\"] .text-center.text-muted span")).getText();
                writer.write(String.format("%s;%s;unavailable\n",str[0],str[1]));
            } catch (NoSuchElementException e) {
                writer.write(String.format("%s;%s;available\n", str[0], str[1]));
            } catch (TimeoutException e) {
                writer.write(String.format("%s;%s;without docs tab at all\n", str[0], str[1]));
            }
            writer.flush();
            mainPage.clickExitBtn();
            loginPage.waitForLoginField();

        }

    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}