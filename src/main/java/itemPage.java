import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

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
    private final By addToCartDisabledBtn = By.cssSelector(".btn.btn-primary[type=\"button\"].disabled");
    private final By minimalOrderAmount = By.cssSelector(".table-stock td:nth-of-type(3)");

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
    public WebElement getTechSpecsBtn() {
        return driver.findElement(techSpecsBtn);
    }

    public WebElement getDescriptionBtn() {
        return driver.findElement(descriptionBtn);
    }

    public WebElement getDocsBtn() {
        return driver.findElement(docsBtn);
    }

    public WebElement getAnaloguesBtn() {
        return driver.findElement(analoguesBtn);
    }

    public WebElement getAccessoriesBtn() {
        return driver.findElement(accessoriesBtn);
    }

    public WebElement getCalculateBtn() {
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

    public WebElement[] getAddToCartDisabledBtn() {
        return driver.findElements(addToCartDisabledBtn).toArray(new WebElement[0]);
    }

    public WebElement[] getMinimumOrderAmount() {
        return driver.findElements(minimalOrderAmount).toArray(new WebElement[0]);
    }

    //click on clickable page elements
    public void clickTechSpecsBtn() {
        getTechSpecsBtn().click();
        driver.manage().window().maximize();
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

    //clicks on every image in image carousel
    public void clickImageCarousel() {
        for(int i = 0; i < getImageCarousel().length; i++) {
            getImageCarousel()[i].click();
            getBigItemImage()[i].click();
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.modal-close .icon")));
            driver.findElement(By.cssSelector(".btn.modal-close .icon")).click();
        }
    }

    //puts value into every order item field
    public void sendAmount(int value) {
        for(WebElement el : getAmountTextField()) {
            el.sendKeys(String.valueOf(value));
        }
    }

    //puts price percent value of discount into text field
    public void sendDiscount(int value) {
        String text = driver.findElement(inputDiscount).getText();
        if(Objects.equals(text, "")) {
            driver.findElement(inputDiscount).sendKeys(String.valueOf(value));
        }

    } //!change method so that discount is put into textfield only once during test execution


    //waits until title of item name appears in user's view
    public void waitForItemInfoPresence() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".h4.font-weight-bold.d-lg-none")));
        try {
            file.write(driver.findElement(By.cssSelector(".h4.font-weight-bold.d-none")).getText() + "\n");
            file.flush();
        } catch (IOException e) {
            System.out.println("no such file");
        }
    }

    //waits until specs tab appears in user's view
    public void waitForTechSpecsPresence() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(techSpecsBtn));
    }

    //waits until discount calculated price appears in user's view
    public void waitForPricePresence() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(getCalculatedPrice()));
    }


}
