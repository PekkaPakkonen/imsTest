import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileWriter;
import java.io.IOException;

public class itemPage {

    private WebDriver driver;
    private JavascriptExecutor exec;
    private FileWriter file;

    private final By techSpecsBtn = By.cssSelector(".nav-link[aria-posinset=\"1\"]");
    private final By descriptionBtn = By.cssSelector(".nav-link[aria-posinset=\"2\"]");
    private final By docsBtn = By.cssSelector(".nav-link[aria-posinset=\"3\"]");
    private final By analoguesBtn = By.cssSelector(".nav-link[aria-posinset=\"4\"]");
    private final By accessoriesBtn = By.cssSelector(".nav-link[aria-posinset=\"5\"]");
    private final By inputDiscount = By.cssSelector(".col-md-6 .form-control.form-control-sm");
    private final By calculatedPrice = By.cssSelector(".row .col-sm-6:nth-child(2)");
    private final By amountTextField = By.cssSelector(".text-center.no-appearance.form-control");
    private final By imageCarousel = By.cssSelector(".swiper-catalog-item-thumbs .embed-responsive-1by1:not(.border)  .embed-responsive-item:not(.sprite-icons)");
    private final By calculateBtn = By.cssSelector(".px-1 .btn.btn-secondary");
    private final By bigItemImage = By.cssSelector(".border .embed-responsive-item:not(.item)"); //css selector without 360 degree panorama
    private final By addToCartBtn = By.cssSelector(".btn.btn-primary[type=\"button\"]");

    public itemPage(WebDriver driver) {
        this.driver = driver;
        exec = (JavascriptExecutor) driver;
        try {
            file = new FileWriter("log.txt");
        } catch (IOException e) {
            System.out.println("IO Error");
        }
    }

    // get page elements as WebElement objects
    private WebElement getTechSpecsBtn() {
        return driver.findElement(techSpecsBtn);
    }

    private WebElement getDescriptionBtn() {
        return driver.findElement(descriptionBtn);
    }

    private WebElement getDocsBtn() {
        return driver.findElement(docsBtn);
    }

    private WebElement getAnaloguesBtn() {
        return driver.findElement(analoguesBtn);
    }

    private WebElement getAccessoriesBtn() {
        return driver.findElement(accessoriesBtn);
    }

    private WebElement getCalculateBtn() {
        return driver.findElement(calculateBtn);
    }

    public WebElement getCalculatedPrice() {
        return driver.findElement(calculatedPrice);
    }

    public WebElement[] getAmountTextField() {
        return driver.findElements(amountTextField).toArray(new WebElement[0]);
    }

    public WebElement[] getImageCarousel() {
        return driver.findElements(imageCarousel).toArray(new WebElement[0]);
    }

    public WebElement[] getBigItemImage() {
        return driver.findElements(bigItemImage).toArray(new WebElement[0]);
    }

    public WebElement[] getAddToCartBtn() {
        return driver.findElements(addToCartBtn).toArray(new WebElement[0]);
    }

    //click on clickable page elements
    public void clickTechSpecsBtn() {
        getTechSpecsBtn().click();
    }

    public void clickDescriptionBtn() {
        getDescriptionBtn().click();
    }

    public void clickDocsBtn() {
        getDocsBtn().click();
    }

    public void clickAnaloguesBtn() {
        getAnaloguesBtn().click();
    }

    public void clickCalculateBtn() {
        getCalculateBtn().click();
    }

    public void clickAccessoriesBtn() {
        getAccessoriesBtn().click();
    }

    //click every image in image carousel
    public void clickImageCarousel() {
        for(int i = 0; i < getImageCarousel().length; i++) {
            getImageCarousel()[i].click();
            getBigItemImage()[i].click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.modal-close .icon")));
            driver.findElement(By.cssSelector(".btn.modal-close .icon")).click();
        }
    }

    //put value into every text field to order item
    public void sendAmount(int value) {
        for(WebElement element : getAmountTextField()) {
            element.sendKeys(String.valueOf(value));
        }
    }

    public void checkForEnablerAddToCartBtn() throws Exception {
        if(getAddToCartBtn().length > 0)
            throw new Exception("Add to cart button should be enabled!");
    }

    //put price discount value (as percents value) into text field
    public void sendDiscount(int value) {
        driver.findElement(inputDiscount).sendKeys(String.valueOf(value));
    }

    //wait until title item name appears in users view
    public void waitForItemInfoPresence() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".h4.font-weight-bold.d-lg-none")));
        try {
            file.write(driver.findElement(By.cssSelector(".h4.font-weight-bold.d-none")).getText() + "\n");
            file.flush();
        } catch (IOException e) {
            System.out.println("no such file");
        }
    }

    //wait until specs tab appears
    public void waitForTechSpecsPresence() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(techSpecsBtn));
    }

    //wait until discount calculated price appears
    public void waitForPricePresence() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(getCalculatedPrice()));
    }


}
