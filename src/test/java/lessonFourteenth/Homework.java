package lessonFourteenth;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    public void task1() throws InterruptedException {
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();

        String href = driver.findElement(By.xpath("//a[@rel='noreferrer']")).getAttribute("href");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.location = 'https://www.digitalleague.ru'");
        js.executeScript("window.open('" + href + "', '_blank');");
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        WebElement userResponseDto = driver.findElement(By.id("model-UserResponseDto"));
        js.executeScript("arguments[0].scrollIntoView();", userResponseDto);
        Thread.sleep(1000);

    }
}
