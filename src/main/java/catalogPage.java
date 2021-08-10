import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class catalogPage {

    private WebDriver driver;
    private JavascriptExecutor exec;
    private itemTablePage tablePage;

    //clickable dropdown list elements
    private final By l1Buttons = By.cssSelector(".l1> div > .btn .caret");
    private final By l2Buttons = By.cssSelector(".l2> div > .btn .caret");
    private final By l3Buttons = By.cssSelector(".l3> div > .btn .caret");
    private final By l4Buttons = By.cssSelector(".l4> div > .btn .caret");
    //clickable buttons with a link to item charts
    private final By allWhiteButtons = By.cssSelector(".l2 .btn-white span");

    public catalogPage(WebDriver driver) {
        this.driver = driver;
        exec = (JavascriptExecutor) driver;
        tablePage = new itemTablePage(driver);
    }

    private WebElement[] getL1Buttons() {
        return driver.findElements(l1Buttons).toArray(new WebElement[0]);
    }

    private WebElement[] getL2Buttons() {
        return driver.findElements(l2Buttons).toArray(new WebElement[0]);
    }

    private WebElement[] getL3Buttons() {
        return driver.findElements(l3Buttons).toArray(new WebElement[0]);
    }

    private WebElement[] getL4Buttons() {
        return driver.findElements(l4Buttons).toArray(new WebElement[0]);
    }

    private WebElement[] getAllWhiteButtons() {
        return driver.findElements(allWhiteButtons).toArray(new WebElement[0]);
    }

    public void clickAllL1Buttons() {
        WebElement[] l1Buttons = getL1Buttons();

        for(WebElement i: l1Buttons) {
            i.click();
        }
    }

    public void clickAllL2Buttons() {
        WebElement[] l2Buttons = getL2Buttons();

        for(WebElement i: l2Buttons) {
            i.click();
        }
    }

    public void clickAllL3Buttons() {
        WebElement[] l3Buttons = getL3Buttons();

        for(WebElement i: l3Buttons) {
            i.click();
        }
    }

    public void clickAllL4Buttons() {
        WebElement[] l4Buttons = getL4Buttons();

        for(WebElement i: l4Buttons) {
            i.click();
        }
    }

    private void clickAllWhiteButtons(int i) {
        //refreshes button links within every method call to prevent StaleElementReferenceException
        WebElement[] l3WhiteButtons = getAllWhiteButtons();
        l3WhiteButtons[i].click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("short_name"));
        driver.navigate().back();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://ims3.ekf.su/hasura/catalog"));
        waitForCatalogPageToBeClickable();
        jsOpenAllDropdowns();

    }

    private void clickWhiteButtonAndPageLinks(int i) throws InterruptedException {
        final String catalogPageId = driver.getWindowHandle();
        WebElement[] itemLinks;
        WebElement[] l3WhiteButtons;

        jsOpenAllDropdowns();
        l3WhiteButtons = getAllWhiteButtons();
        l3WhiteButtons[i].click();

        while(tablePage.getAllItemLinks().length < 1) { //is needed to change, possible infinite loop
            Thread.sleep(200);
        }

        itemLinks = tablePage.getAllItemLinks(); //if clicks a next link after the last one on page, throws ElementNotInteractableExc;
        //need to click a link to next page beforehand;
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(itemLinks[0]));

        for (WebElement item : itemLinks) {
            item.click();
            driver.switchTo().window(driver.getWindowHandles().toArray(new String[0])[1]);
            driver.close();
            driver.switchTo().window(catalogPageId);
        }
    }

    public void closeOtherWindows() {

    }

    public void refreshAndClickButtons() {
        int buttonsAmount = getAllWhiteButtons().length;

        for(int i = 0; i < buttonsAmount; i++) {
            clickAllWhiteButtons(i);
        }
    }

    public void refreshAndClickPageLinks() throws InterruptedException {
        int buttonsAmount = getAllWhiteButtons().length;

        for(int i = 0; i < buttonsAmount; i++) {
            clickWhiteButtonAndPageLinks(i);
        }
    }


    private void jsOpenAllDropdowns() {
        jsClickElements(driver.findElements(By.cssSelector(".l1> div > .btn .px-2:nth-child(2)")).toArray(new WebElement[0]));
        jsClickElements(driver.findElements(By.cssSelector(".l2> div > .btn .px-2:nth-child(2)")).toArray(new WebElement[0]));
        jsClickElements(driver.findElements(By.cssSelector(".l3> div > .btn .px-2:nth-child(2)")).toArray(new WebElement[0]));
        jsClickElements(driver.findElements(By.cssSelector(".l4> div > .btn .px-2:nth-child(2)")).toArray(new WebElement[0]));
    }

    //uses js script to open inner dropdown lists in catalog dropdown list
    private void jsClickElements(WebElement[] elements) {
        for(WebElement element : elements) {
           exec.executeScript("arguments[0].click();", element);
        }
    }

    public WebElement waitForCatalogPageToBeClickable() {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".l1:first-child > div > .btn")));
    }





}
