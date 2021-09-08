import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class allMastersTest {

    private WebDriver driver;
    private imsLoginPage loginPage;
    private imsMainPage mainPage;
    private readUsersInfoFile readUsersInfo;
    private marketingPage marketingPage;

    @BeforeClass
    public void setup() throws IOException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        loginPage = new imsLoginPage(driver);
        mainPage = new imsMainPage(driver);
        marketingPage = new marketingPage(driver);
        readUsersInfo = new readUsersInfoFile("kek.csv", "text.txt");
        readUsersInfo.addHeader();
        readUsersInfo.readAndWriteToFile();
        driver.get("https://ims3dev.ekf.su/login");

    }

    @Test
    public void marketingTabCheck() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("kek.csv"));
        reader.readLine();

        while(reader.ready()) {
            String[] str = reader.readLine().split(",");
            loginPage.fillLoginField(str[0]);
            loginPage.fillPasswordField(str[1]);
            loginPage.clickLoginBtn();
            mainPage.waitForCatalogBtnToBeClickable();
            mainPage.clickMarketingBtn();
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(mainPage.getAdvertisementBtn()));
            mainPage.clickAdvertisementBtn();
            marketingPage.waitForTable();
            mainPage.clickExitBtn();
            loginPage.waitForLoginField();
        }

    }
}
