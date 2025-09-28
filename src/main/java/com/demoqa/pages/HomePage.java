package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href='/javascript_alerts']")

    WebElement javaScriptAlerts;

    public JavaScriptAlertsPage getJavaScriptAlerts() {
        click(javaScriptAlerts);
        return new JavaScriptAlertsPage(driver);
    }

    @FindBy(css = "a[href='/nested_frames']")
    WebElement nestedFrames;

    public NestedFramesPage getNestedFrames() {
        click(nestedFrames);
        return new NestedFramesPage(driver);
    }

    @FindBy(css = "a[href='/windows']")
    WebElement multipleWindowsLink;

    public MultipleWindowsPage getMultipleWindows() {
        click(multipleWindowsLink);
        return new MultipleWindowsPage(driver);
    }

    @FindBy(css = "a[href='/horizontal_slider']")
    WebElement horizontalSlider;

    public HorizontalSliderPage getHorizontalSlider() {
        click(horizontalSlider);
        return new HorizontalSliderPage(driver);
    }

    @FindBy(css = "a[href='/hovers']")
    WebElement hovers;

    public HoversPage getHovers() {
        click(hovers);
        return new HoversPage(driver);
    }
    @FindBy(css = "a[href='/context_menu']")
    WebElement contextMenu;

    public ContextMenuPage getContextMenu() {
        click(contextMenu);
        return new ContextMenuPage(driver);
    }
    @FindBy(css = "a[href='/dropdown']")
    WebElement dropdown;

    public DropdownPage getDropdown() {
        click(dropdown);
        return new DropdownPage(driver);
    }
    @FindBy(css = "a[href='/drag_and_drop']")
    WebElement dragAndDrop;

    public DragAndDropPage getDragAndDrop() {
        click(dragAndDrop);
        return new DragAndDropPage(driver);
    }

}
