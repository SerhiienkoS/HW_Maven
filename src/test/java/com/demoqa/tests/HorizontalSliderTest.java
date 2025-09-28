package com.demoqa.tests;

import com.demoqa.pages.HomePage;
import com.demoqa.pages.HorizontalSliderPage;
import com.demoqa.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HorizontalSliderTest extends TestBase {

    private HorizontalSliderPage slider;

    @BeforeMethod
    public void precondition() {
        slider = new HomePage(driver).getHorizontalSlider();
    }

    @Test
    public void defaultStateTest() {
        // Проверяем атрибуты
        Assert.assertEquals(slider.getMin(),  "0.0");
        Assert.assertEquals(slider.getMax(),  "5.0");
        Assert.assertEquals(slider.getStep(), "0.5");

        // Значения сравниваем корректно (точка, не запятая)
        Assert.assertEquals(slider.getSliderValue(), 0.0, "input.value должен быть 0.0");
        Assert.assertEquals(slider.getDisplayedText(), "0", "Отрисованный текст должен быть '0'");
    }

    @Test
    public void setTwoPointFiveTest() {
        slider.setValue(2.5);
        Assert.assertEquals(slider.getSliderValue(), 2.5, 0.0, "input.value должен быть 2.5");
        Assert.assertEquals(slider.getDisplayedText(), "2.5", "Спан должен показать '2.5'");
    }

    @Test
    public void setMaxAndBackToZeroTest() {
        slider.setValue(5.0);
        Assert.assertEquals(slider.getSliderValue(), 5.0, 0.0);
        Assert.assertEquals(slider.getDisplayedText(), "5");

        slider.setValue(0.0);
        Assert.assertEquals(slider.getSliderValue(), 0.0, 0.0);
        Assert.assertEquals(slider.getDisplayedText(), "0");
    }
}