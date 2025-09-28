package com.demoqa.tests;

import com.demoqa.pages.ContextMenuPage;
import com.demoqa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContextMenuTest extends TestBase {

    private ContextMenuPage context;

    @BeforeMethod
    public void precondition() {
        context = new HomePage(driver).getContextMenu();
    }

    @Test(description = "Правый клик по боксу открывает alert с нужным текстом")
    public void contextClickOpensAlertWithText() {
        String text = context.openContextMenuAndGetAlertText();
        Assert.assertEquals(text, "You selected a context menu",
                "Текст в alert должен быть 'You selected a context menu'");
        Assert.assertFalse(context.isAlertPresent(), "После accept alert не должен оставаться открытым");
    }

    @Test(description = "Левый клик по боксу НЕ открывает alert")
    public void leftClickDoesNotOpenAlert() {
        context.leftClickBox();
        Assert.assertFalse(context.isAlertPresent(), "После ЛКМ alert не должен появляться");
    }

    @Test(description = "Контекстное меню можно вызвать повторно")
    public void canOpenContextMenuTwice() {
        String first = context.openContextMenuAndGetAlertText();
        Assert.assertEquals(first, "You selected a context menu");

        String second = context.openContextMenuAndGetAlertText();
        Assert.assertEquals(second, "You selected a context menu");
    }
}