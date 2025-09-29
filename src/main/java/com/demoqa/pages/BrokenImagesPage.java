package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrokenImagesPage extends BasePage {

    public BrokenImagesPage(WebDriver driver) { super(driver); }

    @FindBy(css = ".example img")
    private List<WebElement> images;

    public List<WebElement> getAllImages() { return images; }

    /** true = картинка загрузилась; false = битая */
    public boolean isImageLoaded(WebElement img) {
        return (Boolean) js.executeScript(
                "return arguments[0].complete && " +
                        "typeof arguments[0].naturalWidth!='undefined' && arguments[0].naturalWidth>0;", img);
    }
}
