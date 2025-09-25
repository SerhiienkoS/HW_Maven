package com.demoqa.tests;

import com.demoqa.pages.HomePage;
import com.demoqa.pages.MultipleWindowsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class MultipleWindowsTest extends TestBase{
    private MultipleWindowsPage windows;

    @BeforeMethod
    public void precondition() {
        windows = new HomePage(driver).getMultipleWindows();
    }

    @Test(description = "Открытие нового окна: заголовок и title = 'New Window'")
    public void openNewWindowAndVerifyTest() {
        int before = windows.windowCount();
        String newHandle = windows.openNewWindowAndSwitch();

        Assert.assertEquals(windows.getTitle(), "New Window",
                "Title в новом окне должен быть 'New Window'");
        Assert.assertEquals(windows.getCurrentHeaderText(), "New Window",
                "H3 заголовок в новом окне должен быть 'New Window'");

        windows.closeCurrentWindowAndReturnToOriginal();
        Assert.assertEquals(windows.windowCount(), before,
                "После закрытия нового окна должно остаться стартовое количество окон");
    }

    @Test(description = "Переключение назад на оригинальное окно и проверка заголовка")
    public void switchBackToOriginalTest() {
        windows.openNewWindowAndSwitch();
        windows.switchToOriginal();
        Assert.assertEquals(windows.getCurrentHeaderText(), "Opening a new window",
                "На исходной странице ожидается H3 'Opening a new window'");
    }

    @Test(description = "Два новых окна: всего 3 окна, у всех новых title = 'New Window'")
    public void openTwoWindowsAndCountTest() {
        int before = windows.windowCount();

        String h1 = windows.openNewWindowAndSwitch();
        windows.switchToOriginal();
        String h2 = windows.openNewWindowAndSwitch();

        // Проверим, что стало 3 окна
        Assert.assertEquals(windows.windowCount(), before + 2,
                "Должно быть открыто ровно два дополнительных окна");

        // Проверим title в каждом новом окне
        Set<String> handles = driver.getWindowHandles();
        for (String h : handles) {
            if (!h.equals(driver.getWindowHandle())) { /* no-op */ }
        }
        // Переключаемся и проверяем оба новых окна
        driver.switchTo().window(h1);
        Assert.assertEquals(windows.getTitle(), "New Window", "Первое новое окно: неверный title");

        driver.switchTo().window(h2);
        Assert.assertEquals(windows.getTitle(), "New Window", "Второе новое окно: неверный title");

        // Возврат на исходное
        windows.switchToOriginal();
    }
}
