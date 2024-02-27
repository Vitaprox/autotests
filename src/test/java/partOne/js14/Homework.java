package partOne.js14;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Homework {

    WebDriver driver;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
    }

    @AfterEach
    public void closeDrive() {
        driver.quit();
    }

    @Test
    @DisplayName(value = "Работа с js")
    public void task1(){
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Клик по кнопке Войти
        WebElement loginButton = driver.findElement(By.id("form_auth_button"));
        js.executeScript("arguments[0].click();", loginButton);

        String href = driver.findElement(By.xpath("//a[@rel='noreferrer']")).getAttribute("href");

        js.executeScript("window.location = 'https://www.digitalleague.ru'");
        js.executeScript("window.open('" + href + "', '_blank');");

        //Переключаемся на новую страницу
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

        //Находим самый нижний элемент и прокручиваем до него
        WebElement userResponseDto = driver.findElement(By.id("model-UserResponseDto"));
        js.executeScript("arguments[0].scrollIntoView();", userResponseDto);
    }
}
