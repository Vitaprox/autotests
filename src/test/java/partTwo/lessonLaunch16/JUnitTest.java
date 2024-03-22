package partTwo.lessonLaunch16;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class JUnitTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @Disabled
    @ParameterizedTest(name = "Search - {0}")
    @ValueSource(strings = {"selenium", "selenoid","Liga"})
    @Tag("search")
    @Timeout(value = 5, unit = TimeUnit.MILLISECONDS)
    public void testGoogleSelenium(String value){
        driver.get("https://www.google.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q"))).clear();
        driver.findElement(By.name("q")).sendKeys(value);
        driver.findElement(By.name("btnK")).click();
        driver.quit();
    }

    @Test
    public void testGoogleSelenoid(){
        driver.get("https://www.google.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q"))).clear();
        driver.findElement(By.name("q")).sendKeys("selenoid");
        driver.findElement(By.name("btnK")).click();
    }

    @Test
    public void testGoogleJira(){
        driver.get("https://www.google.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q"))).clear();
        driver.findElement(By.name("q")).sendKeys("Jira");
        driver.findElement(By.name("btnK")).click();
        driver.quit();
    }

}
