package com.demoqa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HorizontalSliderPage extends BasePage {

    public HorizontalSliderPage(WebDriver driver) { super(driver); }

    @FindBy(css = "input[type='range']")
    private WebElement slider;

    @FindBy(id = "range")
    private WebElement display; // <span id="range">

    /** Текст в правом спане (то, что видит пользователь) */
    public String getDisplayedText() {
        return display.getText().trim(); // "0", "2.5", "5"
    }

    /** Реальное значение инпута */
    public double getSliderValue() {
        return Double.parseDouble(slider.getAttribute("value")); // "0", "2.5", ...
    }

    public String getMin()  { return slider.getAttribute("min"); }   // "0.0"
    public String getMax()  { return slider.getAttribute("max"); }   // "5.0"
    public String getStep() { return slider.getAttribute("step"); }  // "0.5"

    /** Надёжно устанавливает значение 0..5 (шаг 0.5) стрелками + TAB (триггерит change) */
    public HorizontalSliderPage setValue(double target) {
        target = Math.round(target * 2) / 2.0; // нормализуем к шагу 0.5
        slider.click();                         // фокус на инпуте
        double current = getSliderValue();
        int steps = (int)Math.round((target - current) / 0.5);

        Keys key = steps > 0 ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;
        for (int i = 0; i < Math.abs(steps); i++) {
            slider.sendKeys(key);
        }
        // Разфокус — чтобы сработал onchange и обновился <span id="range">
        slider.sendKeys(Keys.TAB);
        return this;
    }

    /** Альтернатива: устанавливаем напрямую через JS и диспатчим 'change' */
    public HorizontalSliderPage setValueJs(double target) {
        target = Math.round(target * 2) / 2.0;
        js.executeScript(
                "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                slider, String.valueOf(target)
        );
        return this;
    }
}
