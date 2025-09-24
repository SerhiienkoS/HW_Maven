package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage extends BasePage {

    private final By body = By.tagName("body");

    public NestedFramesPage(WebDriver driver) {
        super(driver);
    }

    private String bodyText() {
        return driver.findElement(body).getText().trim();
    }

    public String getLeftText() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        String text = bodyText();
        driver.switchTo().defaultContent();
        return text;
    }

    public String getMiddleText() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        String text = bodyText();
        driver.switchTo().defaultContent();
        return text;
    }

    public String getRightText() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-right");
        String text = bodyText();
        driver.switchTo().defaultContent();
        return text;
    }

    public String getBottomText() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        String text = bodyText();
        driver.switchTo().defaultContent();
        return text;
    }
}
