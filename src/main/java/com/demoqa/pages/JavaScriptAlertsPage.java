package com.demoqa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class JavaScriptAlertsPage extends BasePage {
    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button[onclick='jsAlert()']")
    WebElement btnJsAlert;

    @FindBy(css = "button[onclick='jsConfirm()']")
    WebElement btnJsConfirm;

    @FindBy(css = "button[onclick='jsPrompt()']")
    WebElement btnJsPrompt;

    @FindBy(id = "result")
    WebElement result;

    // --- Actions ---
    public JavaScriptAlertsPage clickJsAlertAndAccept() {
        click(btnJsAlert);
        Alert alert = driver.switchTo().alert();
        // alert text = "I am a JS Alert"
        alert.accept(); // <- это клик по окнку алерта (т.е. по кнопке ок.... уточнить!) ----------- уточнить! ----------
        return this;
    }
    public JavaScriptAlertsPage clickJsConfirmAndAccept() {
        click(btnJsConfirm);
        driver.switchTo().alert().accept();
        return this;
    }
    public JavaScriptAlertsPage clickJsConfirmAndDismiss() {
        click(btnJsConfirm);
        driver.switchTo().alert().dismiss();
        return this;
    }

    public JavaScriptAlertsPage clickJsPromptAndSubmit(String text, boolean accept) {
        click(btnJsPrompt);
        Alert alert = driver.switchTo().alert();
        if (text != null) {
            alert.sendKeys(text);
        }
        if (accept) alert.accept(); else alert.dismiss();
        return this;
    }

    public String getResultText() {
        return result.getText().trim();
    }
}
