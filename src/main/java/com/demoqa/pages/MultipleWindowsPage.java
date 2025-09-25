package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class MultipleWindowsPage extends BasePage {

    private final By clickHereLink = By.cssSelector("a[href='/windows/new']");
    private final By headerH3 = By.tagName("h3");
    private final WebDriverWait wait;
    private final String originHandle;

    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.originHandle = driver.getWindowHandle();
    }

    public String getCurrentHeaderText() {
        return driver.findElement(headerH3).getText().trim();
    }

    public int windowCount() {
        return driver.getWindowHandles().size();
    }

    /** Кликает по "Click Here", ждёт появления нового окна и переключается на него. Возвращает handle нового окна. */
    public String openNewWindowAndSwitch() {
        Set<String> before = new HashSet<>(driver.getWindowHandles());
        driver.findElement(clickHereLink).click();
        wait.until(d -> d.getWindowHandles().size() > before.size());
        Set<String> after = new HashSet<>(driver.getWindowHandles());
        after.removeAll(before);
        String newHandle = after.iterator().next();
        driver.switchTo().window(newHandle);
        return newHandle;
    }

    public MultipleWindowsPage switchToOriginal() {
        driver.switchTo().window(originHandle);
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public MultipleWindowsPage closeCurrentWindowAndReturnToOriginal() {
        driver.close();
        driver.switchTo().window(originHandle);
        return this;
    }
}
