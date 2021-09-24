import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
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

public class allMastersTest {

    private WebDriver driver;
    private imsLoginPage loginPage;
    private imsMainPage mainPage;
    private marketingPage marketingPage;

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        loginPage = new imsLoginPage(driver);
        mainPage = new imsMainPage(driver);
        marketingPage = new marketingPage(driver);
        driver.get("https://ims3dev.ekf.su/login");

    }

    @Test
    public void marketingTabCheck() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("users2.csv"));
        reader.readLine();
        writer.write("login;password;marketing tab availability\n");
        writer.flush();

        while(reader.ready()) {
            String[] str = reader.readLine().split(";");
            loginPage.fillLoginField(str[0]);
            loginPage.fillPasswordField(str[1]);
            loginPage.clickLoginBtn();
            try {
                mainPage.waitForCatalogBtnToBeClickable();
            } catch (TimeoutException e) {
                writer.write(String.format("%s,%s,wrong login or password\n", str[0], str[1]));
                driver.navigate().refresh();
                continue;
            }

            if(mainPage.isActiveSubDealer()) {
                try {
                    mainPage.clickMarketingBtn();
                    new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(mainPage.getAdvertisementBtn()));
                    mainPage.clickAdvertisementBtn();
                    marketingPage.waitForTable();
                    writer.write(String.format("%s;%s;available\n",str[0],str[1]));
                } catch (NoSuchElementException e) {
                    writer.write(String.format("%s;%s;unavailable\n", str[0], str[1]));
                } catch (TimeoutException e) {
                    writer.write(String.format("%s;%s;\"Advertisement materials\" in \"Marketing\" isn't available\n", str[0], str[1]));
                }
                writer.flush();

            } else {
                writer.write(String.format("%s;%s;not an active subdealer\n", str[0], str[1]));
            }
            mainPage.clickExitBtn();
            loginPage.waitForLoginField();
        }

    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}