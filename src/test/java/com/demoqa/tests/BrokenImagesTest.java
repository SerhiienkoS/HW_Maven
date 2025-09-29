// com/demoqa/tests/BrokenImagesTest.java
package com.demoqa.tests;

import com.demoqa.pages.BrokenImagesPage;
import com.demoqa.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BrokenImagesTest extends TestBase {

    private BrokenImagesPage page;

    @BeforeMethod
    public void precondition() {
        page = new HomePage(driver).getBrokenImages();
    }

    @Test(description = "На странице ровно 3 изображения")
    public void imagesCountTest() {
        Assert.assertEquals(page.getAllImages().size(), 3, "Должно быть 3 картинки");
    }

    @Test(description = "Первые две картинки битые, третья — рабочая")
    public void brokenAndOkImagesByIndexTest() {
        List<WebElement> imgs = page.getAllImages();
        Assert.assertFalse(page.isImageLoaded(imgs.get(0)), "1-я должна быть битой");
        Assert.assertFalse(page.isImageLoaded(imgs.get(1)), "2-я должна быть битой");
        Assert.assertTrue(page.isImageLoaded(imgs.get(2)), "3-я должна быть рабочей");
    }

    @Test(description = "Подсчёт: 2 битые и 1 рабочая")
    public void totalsTest() {
        long ok = page.getAllImages().stream().filter(page::isImageLoaded).count();
        long broken = page.getAllImages().size() - ok;
        Assert.assertEquals(ok, 1, "Должна быть 1 рабочая");
        Assert.assertEquals(broken, 2, "Должно быть 2 битые");
    }
}
