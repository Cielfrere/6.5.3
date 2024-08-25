package ui_test;

import options.Options;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwitchWindow {
    private static final String URL = "http://the-internet.herokuapp.com/windows";
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = Options.option();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void windowScript() {
        driver.get(URL);

        String originalWindow = driver.getWindowHandle();

        WebElement buttonToNextLink = driver.findElement(By.linkText("Click Here"));
        buttonToNextLink.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebElement textNewWindow = driver.findElement(By.tagName("h3"));
        assertEquals("New Window", textNewWindow.getText());

        driver.close();

        driver.switchTo().window(originalWindow);

        WebElement originalPageText = driver.findElement(By.tagName("h3"));
        assertEquals("Opening a new window", originalPageText.getText());
    }
}