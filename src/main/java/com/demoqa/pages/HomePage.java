package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href='/javascript_alerts']")

    WebElement javaScriptAlerts;

    public JavaScriptAlertsPage getJavaScriptAlerts(){
        click(javaScriptAlerts);
        return new JavaScriptAlertsPage(driver);
    }
    @FindBy(css = "a[href='/nested_frames']")
    WebElement nestedFrames;
    public NestedFramesPage getNestedFrames(){
        click(nestedFrames);
        return new NestedFramesPage(driver);
    }

}
