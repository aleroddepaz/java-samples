package org.arp.servlet;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public abstract class BaseIntegrationTest {

    protected static String baseUrl;
    protected static WebDriver driver;

    @BeforeClass
    public static void openBrowser() {
        String port = System.getProperty("testPort");
        String contextPath = System.getProperty("testContextPath");
        if (!contextPath.endsWith("/")) {
            contextPath += "/";
        }
        baseUrl = "http://localhost:" + port + contextPath;
        driver = new HtmlUnitDriver(true);
    }

    @AfterClass
    public static void closeBrowser() {
        driver.close();
    }

}
