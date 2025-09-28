package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ContextMenuPage extends BasePage {

    public ContextMenuPage(WebDriver driver) { super(driver); }

    @FindBy(id = "hot-spot")
    private WebElement box;

    /** Правый клик по боксу → возвращаем текст алерта (и сразу принимаем его) */
    public String openContextMenuAndGetAlertText() {
        // на всякий случай прокрутим к элементу
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", box);
        new Actions(driver).contextClick(box).perform();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    /** ЛКМ по боксу (на этой странице не должен вызывать alert) */
    public void leftClickBox() {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", box);
        box.click();
    }

    /** Удобный хелпер для проверок отсутствия/наличия alert */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
