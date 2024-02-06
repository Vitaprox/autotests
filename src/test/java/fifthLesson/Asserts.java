package fifthLesson;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Asserts {
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
    @DisplayName(value = "Тест проверка заголовки заметки")
    public void checkTitleTest () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();
        driver.findElement(By.className("Card_containerNew__adAai")).click();
        String title = "Заголовок";
        driver.findElement(By.xpath("//div[text()='Заголовок...']")).sendKeys("Заголовок");
        driver.findElement(By.xpath("//div[text()='Содержание...']")).sendKeys("Содержание");
        driver.findElement(By.id("palette-btn-new_empty")).click();
        driver.findElement(By.id("palette-color-#d7aefb")).click();
        driver.findElement(By.xpath("//button[text()='Ок']")).click();
        String actualTitle = driver.findElement(By.xpath("//p[contains(@id,'note-title')][1]")).getText();
        Assertions.assertEquals(title, actualTitle, "Неверный заголовок заметки");
    }

    @Test
    @DisplayName(value = "Тест проверка теста заметки")
    public void checkTextTest () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();
        driver.findElement(By.className("Card_containerNew__adAai")).click();

        String actualText = driver.findElement(By.xpath("//div[contains(@class, 'Card_body')]")).getText();
        Assertions.assertEquals("Содержание", actualText, "Неверный заголовок заметки");
    }

    @Test
    @DisplayName(value = "Тест проверка цвета заметки")
    public void checkColorTest () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();
        driver.findElement(By.className("Card_containerNew__adAai")).click();

        String actualColor = driver.findElement(By.xpath("//div[contains(@id, 'note-container')]")).getAttribute("style");
        Assertions.assertEquals("background-color: rgb(215, 174, 251);", actualColor, "Неверный заголовок заметки");
    }

}
