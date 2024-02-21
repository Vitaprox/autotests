package lessonFifteen;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.Duration;

public class Database {

    WebDriver driver;
    private int number = 100 + (int) (Math.random() * 10000);
    private String userLogin = "autotest" + number;
    private int userId;

    @BeforeEach
    public void initDriver() {
        executeQuery("INSERT INTO nfaut.users (login, password) VALUES('" + userLogin +
                "', '$2a$10$pjP3DvSE./3OsTLxAC/b/OM43JDpp4N0.hlkLM0vH7QQWPwHpxtqC')");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    //INSERT INTO nfaut.users (login, password) VALUES('user2024', '$2a$10$pjP3DvSE./3OsTLxAC/b/OM43JDpp4N0.hlkLM0vH7QQWPwHpxtqC')

    @AfterEach
    public void closeDrive() {
        executeQuery("DELETE FROM nfaut.users WHERE login = '" + userLogin + "'");
        driver.close();
    }

    @Test
    public void firstTest() {

        //Открытие стенда
        driver.get("http://172.24.120.5:8081/login");
        System.out.println(userLogin);
        driver.findElement(By.id("login-input")).sendKeys(userLogin);
        //Ввод пароля
        driver.findElement(By.id("password-input")).sendKeys("Dtest");
        //Клик по кнопке Войти
        driver.findElement(By.id("form_auth_button")).click();
        Assert.assertTrue(driver.findElement(By.id("logout-btn")).isDisplayed());


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
