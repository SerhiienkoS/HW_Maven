package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownPage extends BasePage {

    public DropdownPage(WebDriver driver) { super(driver); }

    @FindBy(id = "dropdown")
    private WebElement selectEl;

    private Select select() { return new Select(selectEl); }

    public String getSelectedText() {
        return select().getFirstSelectedOption().getText().trim();
    }

    public String getSelectedValue() {
        return select().getFirstSelectedOption().getAttribute("value");
    }

    public DropdownPage chooseByVisibleText(String text) {
        select().selectByVisibleText(text);
        return this;
    }

    public DropdownPage chooseByValue(String value) {
        select().selectByValue(value);
        return this;
    }

    public boolean isDefaultOptionDisabled() {
        WebElement def = select().getOptions().get(0); // "Please select an option"
        String disabled = def.getAttribute("disabled");
        return disabled != null && (disabled.equals("true") || disabled.equals("disabled"));
    }

    /** JS на странице проставляет/снимает атрибут selected у <option> — проверим именно его */
    public boolean isOptionAttrSelectedByValue(String value) {
        WebElement opt = selectEl.findElement(By.cssSelector("option[value='" + value + "']"));
        String attr = opt.getAttribute("selected");
        return attr != null; // будет "selected" если выбран
    }

    public List<String> getAllOptionsText() {
        return select().getOptions().stream().map(e -> e.getText().trim()).collect(Collectors.toList());
    }
}