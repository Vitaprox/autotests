package lessonSixteenth.tests;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;

public class FirstTest extends BaseTest{

    @Test
    public void DBTest() {

    authorizationPage.goToAuthorizationPage();
    authorizationPage.fillInLogin("Dtest");
    authorizationPage.fillInPassword("Dtest");
    authorizationPage.clickLoginButton();

    Assert.assertTrue("Заметка не отображается", mainPage.notesIsDisplayed());
    }

}
