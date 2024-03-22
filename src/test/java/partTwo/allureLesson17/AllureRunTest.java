package partTwo.allureLesson17;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AllureRunTest {

    @Test
    public void testOpenBrowser() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://172.24.120.5:8081/login");
        driver.quit();
    }



}
