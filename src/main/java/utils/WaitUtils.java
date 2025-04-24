package utils;

import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class WaitUtils {

    /**
     * Waits for the presence and visibility of a WebElement using Awaitility.
     *
     * @param driver           The WebDriver instance
     * @param by               The By locator
     * @param timeoutInSeconds The maximum time to wait
     * @return The WebElement once it's found and visible
     */
    public static WebElement waitForElement(WebDriver driver, By by, long timeoutInSeconds) {
        final WebElement[] result = new WebElement[1];

        Awaitility.await()
                .pollInterval(Duration.ofMillis(500))
                .atMost(Duration.ofSeconds(timeoutInSeconds))
                .ignoreExceptions()
                .untilAsserted(() -> {
                    WebElement element = driver.findElement(by);
                    if (!element.isDisplayed()) {
                        throw new AssertionError("Element is not displayed yet");
                    }
                    result[0] = element;
                });

        return result[0];
    }

    /**
     * Waits for the element to be clickable (visible and enabled).
     *
     * @param driver           The WebDriver instance
     * @param by               The By locator
     * @param timeoutInSeconds The maximum time to wait
     * @return The WebElement once it's clickable
     */
    public static WebElement waitForElementClickable(WebDriver driver, By by, long timeoutInSeconds) {
        final WebElement[] result = new WebElement[1];

        Awaitility.await()
                .pollInterval(Duration.ofMillis(500))
                .atMost(Duration.ofSeconds(timeoutInSeconds))
                .ignoreExceptions()
                .untilAsserted(() -> {
                    WebElement element = driver.findElement(by);
                    if (!element.isDisplayed() || !element.isEnabled()) {
                        throw new AssertionError("Element is not clickable yet");
                    }
                    result[0] = element;
                });

        return result[0];
    }
}
