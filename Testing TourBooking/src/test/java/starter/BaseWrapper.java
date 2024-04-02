package starter;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseWrapper extends PageObject {


    int time = 0;
    Duration timer = Duration.ofMinutes(1);
    public void FactoryShadowRootObjectInvisibility(String locator) throws InterruptedException {
        Thread.sleep(1000);
        new WebDriverWait(getDriver(), timer).until(ExpectedConditions
                .invisibilityOf((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator)));
        Thread.sleep(1000);
    }

    public void ScrollDownPage() throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void ScrollUpPage() throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,-document.body.scrollHeight || -document.documentElement.scrollHeight)");

    }

    public void ClickShadowRootObject(String objeto) throws InterruptedException {
        new WebDriverWait(getDriver(), timer)
                .until(ExpectedConditions.elementToBeClickable((WebElement) ((JavascriptExecutor) getDriver()).executeScript(objeto)))
                .click();
        Thread.sleep(1000);
    }

    public boolean FactoryShadowRootObjectVisibility(String locator) throws InterruptedException {
        new WebDriverWait(getDriver(), timer).until(ExpectedConditions
                .visibilityOf((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator)));
        Thread.sleep(1000);
        return true;
    }
    public void Scroll(int a) throws InterruptedException {
        Thread.sleep(1000);
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0," + a + ")");
    }

    public void FactoryShadowRootScroll(String locator, int x, int y) throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript(locator+".scrollBy(0,"+y+")");
    }

    public String FactoryShadowRootGetText(String locator) throws InterruptedException {
        Thread.sleep(1000);
        return new WebDriverWait(getDriver(), timer)
                .until(ExpectedConditions
                        .elementToBeClickable((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator)))
                .getText();
    }

    public void FactoryShadowRootSendKeys(String locator, String value) throws InterruptedException {
        FactoryShadowRootClear(locator);
        new WebDriverWait(getDriver(), timer)
                .until(ExpectedConditions
                        .elementToBeClickable((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator)))
                .sendKeys(value);
        Thread.sleep(1000);
    }
    public void FactoryShadowRootSendKeysNoErase(String locator, String value) throws InterruptedException {
        new WebDriverWait(getDriver(), timer)
                .until(ExpectedConditions
                        .elementToBeClickable((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator)))
                .sendKeys(value);
        Thread.sleep(1000);
    }

    public void FactoryShadowRootClear(String locator) throws InterruptedException {
        new WebDriverWait(getDriver(), timer)
                .until(ExpectedConditions
                        .elementToBeClickable((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator)))
                .clear();
        Thread.sleep(1000);
    }

    public void UpDocumen(String locator, String valu) throws InterruptedException {
        WebElement btn = (WebElement) evaluateJavascript(locator);
        btn.sendKeys(valu);
        Thread.sleep(1000);
    }

    public void WaitVisibilete (String locator){
        waitForCondition().until(ExpectedConditions.visibilityOf(((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator))));
    }

    public void WaitToClickable (String locator){
        waitForCondition().until(ExpectedConditions.elementToBeClickable(((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator))));
    }
    public void WaitInVisibilete(String locator){
        waitForCondition().until(ExpectedConditions.invisibilityOf(((WebElement) ((JavascriptExecutor) getDriver()).executeScript(locator))));
    }

}
