// com/demoqa/tests/RedirectionTest.java
package com.demoqa.tests;

import com.demoqa.pages.HomePage;
import com.demoqa.pages.RedirectionPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class RedirectionTest extends TestBase {

    private RedirectionPage page;

    @BeforeMethod
    public void precondition() {
        page = new HomePage(driver).getRedirection();
    }

    @Test(description = "Клик 'here' ведёт на Status Codes")
    public void redirectToStatusCodesTest() {
        page.clickHere();
        Assert.assertTrue(page.url().contains("/status_codes"), "URL должен содержать /status_codes");
        Assert.assertEquals(page.headerText(), "Status Codes", "Ожидаем заголовок 'Status Codes'");
    }

    @Test(description = "Проверяем наличие ссылок 200/301/404/500")
    public void codesPresenceTest() {
        page.clickHere();
        Set<String> codes = page.codesOnPage();
        Assert.assertTrue(codes.containsAll(Set.of("200", "301", "404", "500")),
                "Должны быть 200, 301, 404, 500. Фактически: " + codes);
    }

    @Test(description = "Открываем 404 и проверяем URL")
    public void open404Test() {
        page.clickHere();
        page.openCode("404");
        Assert.assertTrue(page.url().endsWith("/status_codes/404") || page.url().contains("/status_codes/404"),
                "URL должен содержать /status_codes/404");
        page.back(); // опционально вернуться к списку
        Assert.assertEquals(page.headerText(), "Status Codes");
    }
}
