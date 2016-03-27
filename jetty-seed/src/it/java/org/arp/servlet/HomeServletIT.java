package org.arp.servlet;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.openqa.selenium.By;

public class HomeServletIT extends BaseIntegrationTest {

    @Test
    public void testIndex() {
        driver.get(baseUrl);

        String date = driver.findElement(By.id("date")).getText();

        assertFalse(isEmpty(date));
    }

}
