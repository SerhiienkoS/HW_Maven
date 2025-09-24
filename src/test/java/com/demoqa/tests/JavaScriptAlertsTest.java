package com.demoqa.tests;

import com.demoqa.pages.HomePage;
import com.demoqa.pages.JavaScriptAlertsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptAlertsTest extends TestBase {

    private JavaScriptAlertsPage alerts;

    @BeforeMethod
    public void precondition(){
        alerts = new HomePage(driver).getJavaScriptAlerts();
    }

    @Test(description = "JS Alert -> OK выводит сообщение об успехе")
    public void clickForJsAlertButtonTest(){
        alerts.clickJsAlertAndAccept();
        Assert.assertEquals(alerts.getResultText(),
                "You successfully clicked an alert",
                "Некорректный текст после JS Alert");
    }

    @Test(description = "JS Confirm -> OK показывает 'You clicked: Ok'")
    public void jsConfirmOkTest(){
        alerts.clickJsConfirmAndAccept();
        Assert.assertEquals(alerts.getResultText(),
                "You clicked: Ok",
                "Некорректный текст после JS Confirm (OK)");
    }

    @Test(description = "JS Prompt -> ввод и Accept показывает введённый текст")
    public void jsPromptAcceptWithTextTest(){
        String text = "hello";
        alerts.clickJsPromptAndSubmit(text, true);
        Assert.assertEquals(alerts.getResultText(),
                "You entered: " + text,
                "Некорректный текст после JS Prompt (Accept)");
    }

    // опционально:
    // @Test
    // public void jsPromptCancelTest(){
    //     alerts.clickJsPromptAndSubmit("ignored", false);
    //     Assert.assertEquals(alerts.getResultText(),
    //             "You entered: null");
    // }
}
