package partOne.database15;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;

public class Homework {
    WebDriver driver;
    private int number = 100 + (int) (Math.random() * 10000);
    private String expectedNoteTitle = "Title" + number;
    private String expectedNoteText = "Text" + number;

    @BeforeEach
    public void initDriver() {
        //На случай, если какой-то другой тест оставит заметки
        executeQuery("DELETE FROM nfaut.notes WHERE user_id=6;");
        //id указано, так как без него не создается
        executeQuery("INSERT INTO nfaut.notes (id, user_id, name, content, priority, archive_flg) VALUES(561, 6, '"
                + expectedNoteTitle + "', '" + expectedNoteText + "', 1, false);");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void closeDrive() {
        executeQuery("DELETE FROM nfaut.notes WHERE user_id=6;");
        driver.close();
    }

    @Test
    public void DBTest() {
        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        driver.findElement(By.id("login-input")).sendKeys("Dtest");
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();

        WebElement noteContainer = driver.findElement(By.xpath("//div[contains(@id, 'note-container')]"));
        Assert.assertTrue("Заметка не отображается", noteContainer.isDisplayed());
        WebElement noteTitle = driver.findElement(By.xpath("//p[contains(@id, 'note-title')]"));
        Assert.assertEquals("Заголовок заметки отличается", expectedNoteTitle, noteTitle.getText());
        WebElement noteText = driver.findElement(By.xpath("//div[contains(@class, 'Card_body')]"));
        Assert.assertEquals("Текст отметки отличается", expectedNoteText, noteText.getText());

    }

    private void executeQuery(String SQL) {
        String url = "jdbc:postgresql://172.24.120.5:5432/postgres";
        String login = "root";
        String password = "root";
        try {
            Connection connection= DriverManager.getConnection(url, login, password);
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(SQL);
                statement.close();
            } finally {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
