package ru.javasch.metro.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginIT {

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
    public void login() throws Exception {

        driver.findElement(By.cssSelector("#signin-email")).clear();
        driver.findElement(By.cssSelector("#signin-email")).sendKeys("volpert13@gmail.com");
        driver.findElement(By.cssSelector("#signin-password")).clear();
        driver.findElement(By.cssSelector("#signin-password")).sendKeys("password");
        driver.findElement(By.cssSelector(".full-width[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String nextUrl = driver.getCurrentUrl();
        Assert.assertTrue(nextUrl.equals(hostUrl+"schedule"));
    }

    @Test
    public void loginFailed() throws Exception {

        driver.findElement(By.cssSelector("#signin-email")).clear();
        driver.findElement(By.cssSelector("#signin-email")).sendKeys("volpert13@gmail.com");
        driver.findElement(By.cssSelector("#signin-password")).clear();
        driver.findElement(By.cssSelector(".full-width[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String nextUrl = driver.getCurrentUrl();
        Assert.assertFalse(nextUrl.equals(hostUrl+"login"));
    }

    @Test
    public void registrationFailed() throws Exception {
        driver.findElement(By.cssSelector(".cd-signup")).click();
        driver.findElement(By.cssSelector("#signup-firstname")).clear();
        driver.findElement(By.cssSelector("#signup-firstname")).sendKeys("Alexey");
        driver.findElement(By.cssSelector("#signup-lastname")).clear();
        driver.findElement(By.cssSelector("#signup-lastname")).sendKeys("Bystrov");
        driver.findElement(By.cssSelector("#signup-email")).clear();
        driver.findElement(By.cssSelector("#signup-email")).sendKeys("volpert13@gmail.com");
        driver.findElement(By.cssSelector("#signup-password")).clear();
        driver.findElement(By.cssSelector("#signup-password")).sendKeys("password");

        driver.findElement(By.cssSelector(".full-width[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String nextUrl = driver.getCurrentUrl();

        Assert.assertTrue(nextUrl.equals(hostUrl+"registration"));
    }

    @Test
    public void logoutTest() throws Exception {
        driver.findElement(By.cssSelector("#signin-email")).clear();
        driver.findElement(By.cssSelector("#signin-email")).sendKeys("volpert13@gmail.com");
        driver.findElement(By.cssSelector("#signin-password")).clear();
        driver.findElement(By.cssSelector("#signin-password")).sendKeys("password");
        driver.findElement(By.cssSelector(".full-width[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".sign-out")).click();
        driver.findElement(By.linkText("LOG OUT")).click();
        String nextUrl = driver.getCurrentUrl();
        Assert.assertTrue(nextUrl.equals(hostUrl+"login?logout"));
    }



    @After
    public void quit() throws Exception {
        driver.quit();
    }
}
