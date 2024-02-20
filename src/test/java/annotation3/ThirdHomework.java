package annotation3;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@DisplayName(value = "Регистрация на тестовом стенде")
public class ThirdHomework {

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
    @DisplayName(value = "Тест регистрации")
    public void registrationTest() {
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Кликаем на нопку Зарегистрироваться
        driver.findElement(By.id("form_register_button")).click();
        //Ввести значение в поле Логин
        driver.findElement(By.xpath("//input[@placeholder='Логин']")).sendKeys("login1");
        //Ввести значение в поле Пароль
        driver.findElement(By.xpath("//input[@placeholder='Пароль']")).sendKeys("passTest");
        //Кликаем по кнопке Создать
        driver.findElement(By.xpath("//button[text()='Создать']")).click();
    }

    @Test
    @DisplayName(value = "Тест входа по логину и паролю")
    public void loginTest() {
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Вводи логина
        driver.findElement(By.id("login-input")).sendKeys("test");
        //Ввод пароля
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("test");
        //Войти
        driver.findElement(By.id("form_auth_button")).click();
    }

}
