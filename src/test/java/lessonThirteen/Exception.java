package lessonThirteen;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class Exception {
    WebDriver driver;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
    }

    @AfterEach
    public void closeDrive() {
        driver.close();
    }

    @Test
    @DisplayName(value = "Обработка исключения")
    public void task1() {
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'Card_containerNew')]")));

        } catch (TimeoutException e) {
            throw new TimeoutException("Элемент Создание новой заметки не найден после ожидания");
        } finally {
            System.out.println("Этот код выполнится");
        }

    }

}
