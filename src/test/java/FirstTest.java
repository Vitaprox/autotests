import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class FirstTest {

    @Test
    public void testGoogle() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://172.24.120.5:8081/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-input"))).sendKeys("Text");
        Thread.sleep(2000); //Чтоб увидеть вписалось или нет
        driver.quit();
    }

}
