// com/demoqa/pages/RedirectionPage.java
package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RedirectionPage extends BasePage {

    public RedirectionPage(WebDriver driver) { super(driver); }

    @FindBy(id = "redirect")            // <a id='redirect' href='redirect'>here</a>
    private WebElement here;

    @FindBy(tagName = "h3")             // после клика заголовок "Status Codes"
    private WebElement header;

    @FindBy(css = "a[href*='status_codes/']")
    private List<WebElement> codeLinks; // 200, 301, 404, 500

    public void clickHere() { click(here); }

    public String headerText() { return header.getText().trim(); }

    public Set<String> codesOnPage() {
        return codeLinks.stream().map(e -> e.getText().trim()).collect(Collectors.toSet());
    }

    public void openCode(String code) {
        codeLinks.stream().filter(e -> e.getText().trim().equals(code)).findFirst().orElseThrow().click();
    }

    public String url() { return driver.getCurrentUrl(); }

    public void back() { driver.navigate().back(); }
}
