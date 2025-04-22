package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    // ThreadLocal to ensure each test gets its own WebDriver instance
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initializeDriver(String browser, String executionMode) throws MalformedURLException {

        switch (executionMode.toLowerCase()) {
            case "remote":
                driver.set(initializeRemoteDriver(browser));
                break;
            case "local":
            default:
                driver.set(initializeLocalDriver(browser));
                break;
        }

        driver.get().manage().window().maximize();
        return driver.get();
    }

    private static WebDriver initializeLocalDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }

    private static WebDriver initializeRemoteDriver(String browser) throws MalformedURLException {
        WebDriver remoteDriver;
        String gridUrl = ConfigReader.getProperty("selenium.grid.url");

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    remoteDriver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    remoteDriver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                    break;
                default:
                    throw new RuntimeException("Unsupported browser for remote execution: " + browser);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL: " + gridUrl, e);
        }

        return remoteDriver;
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
