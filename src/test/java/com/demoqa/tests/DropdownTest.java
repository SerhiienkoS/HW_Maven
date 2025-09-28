package com.demoqa.tests;

import com.demoqa.pages.DropdownPage;
import com.demoqa.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DropdownTest extends TestBase {

    private DropdownPage dropdown;

    @BeforeMethod
    public void precondition() {
        dropdown = new HomePage(driver).getDropdown();
    }

    @Test(description = "Дефолтное состояние: выбран плейсхолдер, он disabled, список опций корректен")
    public void defaultStateTest() {
        Assert.assertEquals(dropdown.getSelectedText(), "Please select an option",
                "По умолчанию должен быть выбран плейсхолдер");
        Assert.assertTrue(dropdown.isDefaultOptionDisabled(),
                "Первая опция-плейсхолдер должна быть disabled");

        List<String> texts = dropdown.getAllOptionsText();
        Assert.assertEquals(texts.size(), 3, "Должно быть 3 элемента в списке");
        Assert.assertEquals(texts.get(1), "Option 1");
        Assert.assertEquals(texts.get(2), "Option 2");
    }

    @Test(description = "Выбор Option 1 обновляет выбранный элемент и атрибут selected у опции value=1")
    public void selectOption1Test() {
        dropdown.chooseByVisibleText("Option 1");
        Assert.assertEquals(dropdown.getSelectedText(), "Option 1");
        Assert.assertEquals(dropdown.getSelectedValue(), "1");
        Assert.assertTrue(dropdown.isOptionAttrSelectedByValue("1"),
                "У Option 1 должен стоять атрибут selected (скрипт страницы)");
    }

    @Test(description = "Переключение на Option 2 работает и проставляет selected у value=2")
    public void selectOption2Test() {
        dropdown.chooseByVisibleText("Option 2");
        Assert.assertEquals(dropdown.getSelectedText(), "Option 2");
        Assert.assertEquals(dropdown.getSelectedValue(), "2");
        Assert.assertTrue(dropdown.isOptionAttrSelectedByValue("2"),
                "У Option 2 должен стоять атрибут selected (скрипт страницы)");
    }
}