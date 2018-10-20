package ru.javasch.metro.selenium;

import lombok.extern.log4j.Log4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@Log4j
public class AdminIT {

    private String hostUrl;
    WebDriver driver;

    @Before
    public void init() throws Exception {
        System.setProperty("webdriver.chrome.driver",
                "C:\\CHROMEWEBDRIVER\\chromedriver.exe");
        driver = new ChromeDriver();
        hostUrl = "http://localhost:8000/";
        driver.navigate().to(hostUrl + "login");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void testOpenCloseStation() throws Exception {

        driver.findElement(By.cssSelector("#signin-email")).clear();
        driver.findElement(By.cssSelector("#signin-email")).sendKeys("volpert13@gmail.com");
        driver.findElement(By.cssSelector("#signin-password")).clear();
        driver.findElement(By.cssSelector("#signin-password")).sendKeys("password");
        driver.findElement(By.cssSelector(".full-width[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".sign-out")).click();
        driver.findElement(By.linkText("ADMIN PANEL")).click();
        driver.findElement(By.xpath("//a[@href='/dashstation']")).click();
        String nextUrl = driver.getCurrentUrl();
        Assert.assertTrue(nextUrl.equals(hostUrl+"dashstation"));
    }

    @Test
    public void testSchedule() throws Exception {

        driver.findElement(By.cssSelector("#signin-email")).clear();
        driver.findElement(By.cssSelector("#signin-email")).sendKeys("volpert13@gmail.com");
        driver.findElement(By.cssSelector("#signin-password")).clear();
        driver.findElement(By.cssSelector("#signin-password")).sendKeys("password");
        driver.findElement(By.cssSelector(".full-width[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.linkText("SCHEDULE")).click();
        driver.findElement(By.cssSelector("#station1")).clear();
        driver.findElement(By.cssSelector("#station1")).sendKeys("Devyatkino");
        driver.findElement(By.cssSelector("#date")).clear();
        driver.findElement(By.cssSelector("#date")).sendKeys("14.10.2018");
        driver.findElement(By.cssSelector("#submit")).click();
        String nextUrl = driver.getCurrentUrl();
        Assert.assertTrue(nextUrl.equals(hostUrl+"schedule"));
    }

    @Test
    public void testTickets() throws Exception {
        driver.findElement(By.cssSelector("#signin-email")).clear();
        driver.findElement(By.cssSelector("#signin-email")).sendKeys("volpert13@gmail.com");
        driver.findElement(By.cssSelector("#signin-password")).clear();
        driver.findElement(By.cssSelector("#signin-password")).sendKeys("password");
        driver.findElement(By.cssSelector(".full-width[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to(hostUrl + "dash");
        String nextUrl = driver.getCurrentUrl();
        Assert.assertTrue(driver.getTitle().contains("404"));
    }



    @After
    public void quit() throws Exception {
        driver.quit();
    }
}
