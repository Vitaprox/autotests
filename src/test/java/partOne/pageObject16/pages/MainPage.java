package partOne.pageObject16.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    private WebDriver driver;

    @FindBy(id = "logout-btn")
    private WebElement logoutButton;

    @FindAll
            (
                    {
                            @FindBy(xpath = "//div[contains(@id, 'note-container')]")
                    }
            )
    private List<WebElement> notes;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean notesIsDisplayed() {
        boolean flag = true;
        for (WebElement el:notes) {
            if (!el.isDisplayed()) {
                flag = false;
            }
        }
        return flag;
    }

    public boolean logoutButtonIsDisplayed() {
        return logoutButton.isDisplayed();
    }
}
