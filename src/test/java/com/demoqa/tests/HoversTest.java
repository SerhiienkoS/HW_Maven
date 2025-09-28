package com.demoqa.tests;

import com.demoqa.pages.HomePage;
import com.demoqa.pages.HoversPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HoversTest extends TestBase {

    private HoversPage hovers;

    @BeforeMethod
    public void precondition() {
        hovers = new HomePage(driver).getHovers();
    }

    @Test(description = "user1: подпись скрыта до наведения и видна после hover; текст и ссылка корректные")
    public void hoverUser1Test() {
        // до наведения подпись у первой карточки скрыта
        Assert.assertFalse(hovers.isCaptionVisible(1), "Caption у user1 должен быть скрыт до hover");

        hovers.hoverCard(1);
        Assert.assertTrue(hovers.isCaptionVisible(1), "Caption у user1 должен стать видимым после hover");

        String text = hovers.getCaptionText(1);
        Assert.assertTrue(text.contains("name: user1") && text.contains("View profile"),
                "Текст подписи должен содержать 'name: user1' и 'View profile'");

        String href = hovers.getProfileHref(1);
        Assert.assertTrue(href.endsWith("/users/1"), "Ссылка у user1 должна вести на /users/1");
    }

    @Test(description = "user2: корректность подписи и ссылки после hover")
    public void hoverUser2Test() {
        hovers.hoverCard(2);
        Assert.assertTrue(hovers.isCaptionVisible(2), "Caption у user2 должен быть видимым");
        Assert.assertTrue(hovers.getCaptionText(2).contains("name: user2"), "Подпись должна содержать 'name: user2'");
        Assert.assertTrue(hovers.getProfileHref(2).endsWith("/users/2"), "Ссылка у user2 должна вести на /users/2");
    }

    @Test(description = "Клик по 'View profile' у user3 открывает /users/3")
    public void openProfileUser3Test() {
        hovers.clickProfile(3);
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.endsWith("/users/3") || url.contains("/users/3"),
                "Должны перейти на URL, содержащий /users/3");
        // Доп. проверка (на реальном сайте обычно 'Not Found'):
        // String body = driver.findElement(By.tagName("body")).getText();
        // Assert.assertTrue(body.contains("Not Found"));
    }
}
