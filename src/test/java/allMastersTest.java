import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;

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
        BufferedReader reader = new BufferedReader(new FileReader("input.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"));
        reader.readLine();
        writer.write("login,password,marketing tab availability\n");
        writer.flush();

        while(reader.ready()) {
            String[] str = reader.readLine().split(",");

         /**/   loginPage.fillLoginField(str[0]);
            loginPage.fillPasswordField(str[1]);
            loginPage.clickLoginBtn();
            try {
                mainPage.waitForCatalogBtnToBeClickable();
            } catch (TimeoutException e) {
                writer.write(String.format("%s,%s,wrong login or password\n", str[0], str[1]));
                driver.navigate().refresh();
                continue;
            }

            try {
                mainPage.clickMarketingBtn();
                new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(mainPage.getAdvertisementBtn()));
                mainPage.clickAdvertisementBtn();
                marketingPage.waitForTable();
                writer.write(String.format("%s,%s\n",str[0],str[1]));
            } catch (NoSuchElementException e) {
                writer.write(String.format("%s,%s,\"Marketing\" tab isn't available\n", str[0], str[1]));
            } catch (TimeoutException e) {
                writer.write(String.format("%s,%s,\"Advertisement materials\" in \"Marketing\" isn't available\n", str[0], str[1]));
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
