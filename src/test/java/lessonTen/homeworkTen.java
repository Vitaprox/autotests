package lessonTen;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class homeworkTen {

    WebDriver driver;

    @BeforeEach
    public void initDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        //Ввод логина
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();
    }

    @AfterEach
    public void closeDrive() {
        driver.close();
    }

    @Test
    @DisplayName(value = "Тест проверка заголовки заметки")
    public void checkTitleTest () throws InterruptedException {

        //Убираем заметки, если есть
        ArrayList<WebElement> deleteButtons = (ArrayList<WebElement>) driver.findElements(By.xpath("//img[contains(@id, 'delete-btn')]"));
        for (int i = deleteButtons.size() - 1; i >= 0; i--) {
            deleteButtons.get(i).click();
            driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]")).click();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //Создаем заметки
        WebElement addButton = driver.findElement(By.xpath("//div[contains(@class, 'Card_containerNew')]"));
        for (int i = 0; i < 3; i++) {
            addButton.click();
            driver.findElement(By.xpath("//div[contains(@id, 'note-modal-title')]")).sendKeys("Заголовок" + i);
            driver.findElement(By.xpath("//div[contains(@id, 'note-modal-content')]")).sendKeys("Содержание" + i);
            driver.findElement(By.id("palette-btn-new_empty")).click();
            driver.findElement(By.id("palette-color-#d7aefb")).click();
            WebElement okButton = driver.findElement(By.xpath("//button[text()='Ок']"));
            okButton.click();
            wait.until(ExpectedConditions.invisibilityOf(okButton));
        }

//        Thread.sleep(2000);

        for (int i = 0; i < 3; i++) {
            String title = driver.findElement(By.xpath("//div[contains(@id, 'note-container')][" + (i+1) + "]//p[contains(@id, 'note-title')]")).getText();
            String text = driver.findElement(By.xpath("//div[contains(@id, 'note-container')][" + (i+1) + "]//div[contains(@class, 'Card_body')]")).getText();
            System.out.println(title + " " + text);
        }

//        Thread.sleep(2000);
        ArrayList<WebElement> editButtons = (ArrayList<WebElement>) driver.findElements(By.xpath("//img[contains(@id, 'edit-btn')]"));
        for (int i = 0; i < 3; i++) {
//            Thread.sleep(500);
            editButtons.get(i).click();
            driver.findElement(By.xpath("//div[contains(@id, 'note-modal-title')]")).sendKeys("new");
            driver.findElement(By.xpath("//div[contains(@id, 'note-modal-content')]")).sendKeys("new");
            driver.findElement(By.xpath("//button[text()='Ок']")).click();
        }

//        Thread.sleep(2000);

        for (int i = 0; i < 3; i++) {
            String title = driver.findElement(By.xpath("//div[contains(@id, 'note-container')][" + (i+1) + "]//p[contains(@id, 'note-title')]")).getText();
            String text = driver.findElement(By.xpath("//div[contains(@id, 'note-container')][" + (i+1) + "]//div[contains(@class, 'Card_body')]")).getText();
            System.out.println(title + " " + text);
        }

//        Thread.sleep(2000000);
    }

}
