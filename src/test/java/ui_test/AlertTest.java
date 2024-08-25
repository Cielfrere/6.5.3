package ui_test;

import options.Options;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertTest {
    private static final String URL = "http://the-internet.herokuapp.com/javascript_alerts";
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
    public void testAlerts() {
        driver.get(URL);

        WebElement jsAlertButton = driver.findElement(By.xpath
                ("//button[text()='Click for JS Alert']"));
        jsAlertButton.click();

        Alert alert = driver.switchTo().alert();
        assertEquals("I am a JS Alert", alert.getText());

        alert.accept();

        WebElement jsConfirmButton = driver.findElement(By.xpath
                ("//button[text()='Click for JS Confirm']"));
        jsConfirmButton.click();

        alert = driver.switchTo().alert();
        alert.dismiss();

        WebElement jsPromptButton = driver.findElement(By.xpath
                ("//button[text()='Click for JS Prompt']"));
        jsPromptButton.click();

        alert = driver.switchTo().alert();
        alert.sendKeys("Hello World");

        alert.accept();

        WebElement resultText = driver.findElement(By.id("result"));
        assertEquals("You entered: Hello World", resultText.getText());
    }
}