import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SecondHomework {

    private WebDriver driver;

    @Test
    public void testGoogle() {
        driver.get("http://172.24.120.5:8081/login"); //Заходим на тестовый стенд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String headerText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("form_auth_block_head_text"))).getText(); //Получаем текст элемента с текстом “Авторизация”
        WebElement loginInput = driver.findElement(By.id("login-input"));
        loginInput.sendKeys(headerText); //Вводим полученный текст в поле для ввода логина
        String loginValue = loginInput.getAttribute("value"); //Получаем значение атрибута “value” в поле для ввода логина
        WebElement passwordInput = driver.findElement(By.xpath("//input[@type='password']"));
        passwordInput.sendKeys(loginValue); //Вводим полученное значение в поле для ввода пароля
        passwordInput.clear(); //Очищаем поле для ввода пароля
        String logInCss = driver.findElement(By.id("form_auth_button")).getCssValue("display"); //Получаем любое свойство кнопки “Войти”
        passwordInput.sendKeys(logInCss); //Вводит полученное значение в поле для ввода пароля
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void closeDriver() {
        driver.close();
    }
}
