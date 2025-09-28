package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HoversPage extends BasePage {

    public HoversPage(WebDriver driver) { super(driver); }

    @FindBy(css = ".figure")
    private List<WebElement> figures;

    private By captionBy = By.cssSelector(".figcaption");
    private By captionLinkBy = By.cssSelector(".figcaption a");

    private WebElement card(int index1to3) {
        if (index1to3 < 1 || index1to3 > 3) throw new IllegalArgumentException("index must be 1..3");
        return figures.get(index1to3 - 1);
    }

    public HoversPage hoverCard(int index1to3) {
        WebElement el = card(index1to3);
        // На всякий — прокрутка к элементу, чтобы hover сработал стабильно
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        new Actions(driver).moveToElement(el).perform();
        return this;
    }

    public boolean isCaptionVisible(int index1to3) {
        return card(index1to3).findElement(captionBy).isDisplayed();
    }

    public String getCaptionText(int index1to3) {
        return card(index1to3).findElement(captionBy).getText().trim();
    }

    public String getProfileHref(int index1to3) {
        return card(index1to3).findElement(captionLinkBy).getAttribute("href");
    }

    public void clickProfile(int index1to3) {
        hoverCard(index1to3); // подпись кликабельна только после hover
        card(index1to3).findElement(captionLinkBy).click();
    }
}