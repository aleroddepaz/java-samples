package org.arp.servlet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;

public class LoginServletIT extends BaseIntegrationTest {

    @Test
    public void testCorrectLogin() {
        driver.get(baseUrl + "login");

        driver.findElement(By.name("j_username")).sendKeys("john");
        driver.findElement(By.name("j_password")).sendKeys("welcome1");
        driver.findElement(By.tagName("form")).submit();

        String currentUrl = driver.getCurrentUrl();
        assertEquals(baseUrl, currentUrl);
    }

    @Test
    public void testIncorrectLogin() {
        driver.get(baseUrl + "login");

        driver.findElement(By.name("j_username")).sendKeys("john");
        driver.findElement(By.name("j_password")).sendKeys("john");
        driver.findElement(By.tagName("form")).submit();

        int size = driver.findElements(By.id("login-error")).size();
        assertEquals(1, size);
    }

}
