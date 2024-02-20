package wait4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitTest {

    WebDriver driver;

    @BeforeAll
    public static void start() {
        System.out.println("Начало тестирования");
    }

    @AfterAll
    public static void end() {
        System.out.println("Окончание тестирования");
    }

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void closeDrive() {
        driver.close();
    }


    @Test
    @DisplayName(value = "Клик с явными ожиданиями по корзине")
    public void clickTrash1 () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();
        //Явное ожидание и клик по корзине
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("trash-btn"))).click();
    }

    @Test
    @DisplayName(value = "Клик с неявными ожиданиями по корзине")
    public void clickTrash2 () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();
        //Неявное ожидание и клик по корзине
        driver.findElement(By.id("trash-btn")).click();
    }

}
