package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DragAndDropPage extends BasePage {

    public DragAndDropPage(WebDriver driver) { super(driver); }

    @FindBy(id = "column-a")
    private WebElement colA;

    @FindBy(id = "column-b")
    private WebElement colB;

    public String getColumnAText() {
        return colA.findElement(By.tagName("header")).getText().trim();
    }

    public String getColumnBText() {
        return colB.findElement(By.tagName("header")).getText().trim();
    }

    public DragAndDropPage dragAtoB() {
        html5DragAndDrop(colA, colB);
        waitTextSwapped("B", "A");
        return this;
    }

    public DragAndDropPage dragBtoA() {
        html5DragAndDrop(colB, colA);
        waitTextSwapped("A", "B");
        return this;
    }

    private void waitTextSwapped(String expectedA, String expectedB) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until((ExpectedCondition<Boolean>) d ->
                        expectedA.equals(getColumnAText()) && expectedB.equals(getColumnBText()));
    }

    /** Надёжная эмуляция HTML5 DnD через события (работает на этой странице стабильно) */
    private void html5DragAndDrop(WebElement source, WebElement target) {
        js.executeScript(
                "function fire(type, elem, dataTransfer){\n" +
                        "  var evt = document.createEvent('CustomEvent');\n" +
                        "  evt.initCustomEvent(type, true, true, null);\n" +
                        "  evt.dataTransfer = dataTransfer;\n" +
                        "  elem.dispatchEvent(evt);\n" +
                        "}\n" +
                        "var src = arguments[0], tgt = arguments[1];\n" +
                        "var dt = {data:{}, setData:function(k,v){this.data[k]=v;}, getData:function(k){return this.data[k];}, dropEffect:'move', effectAllowed:'all'};\n" +
                        "fire('dragstart', src, dt);\n" +
                        "fire('dragenter', tgt, dt);\n" +
                        "fire('dragover', tgt, dt);\n" +
                        "fire('drop', tgt, dt);\n" +
                        "fire('dragend', src, dt);",
                source, target
        );
    }
}
