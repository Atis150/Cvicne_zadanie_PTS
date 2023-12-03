package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //DRIVER SETUP
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\attil\\Desktop\\UKF\\Magisterske_studium\\2Z\\PTS\\chromedriver-119.0.6\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //OPEN FIRST PAGE BY DRIVER
        driver.get("https://ais2.ukf.sk/ais/start.do");

        try {
            Thread.sleep(1000);
//LOGIND DATA
            String login = "290202";
            String password = "Ukf0002081684";
//EMAIL DATA
            String _to = "simonattila150@gmail.com";
            String _subject = "PTS";

//LOGIN TO AIS
            driver.findElement(
                    By.xpath("//*[@id=\"login\"]")
            ).sendKeys(login);

            driver.findElement(
                    By.xpath("//*[@id=\"heslo\"]")
            ).sendKeys(password);

            driver.findElement(
                    By.id("login-form-submit-btn")
            ).click();
//TAKING SREENSHOT AND SAVE
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenshot, new File("screenshot.png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//OPEN NEW TAB IN CHROME
            ((JavascriptExecutor) driver).executeScript("window.open()");
//TABS ARE SAVED IN ARRAYLIST tabs.get(0..10);
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
//SWITCHING FOCUS
            driver.switchTo().window(tabs.get(1));
            driver.get("https://studentmail.ukf.sk/webmail/?_task=login");
            Thread.sleep(1000);
//LOGIN TO STUDENTMAIL
            driver.findElement(
                    By.id("rcmloginuser")
            ).sendKeys(login);

            driver.findElement(
                    By.id("rcmloginpwd")
            ).sendKeys(password);

            driver.findElement(
                    By.xpath("/html/body/div[2]/div[2]/form/p/input")
            ).click();
            Thread.sleep(1000);
            driver.findElement(
                    By.xpath("/html/body/div[5]/a[2]")
            ).click();
            Thread.sleep(1000);
//FILL THE TO AND SUBJECT
            driver.findElement(
                    By.id("_to")
            ).sendKeys(_to);

            driver.findElement(
                    By.id("compose-subject")
            ).sendKeys(_subject);
//ADD ATTACHMENT
            driver.findElement(
                    By.xpath("/html/body/form/div[1]/a[4]")
            ).click();

            driver.findElement(
                    By.xpath("/html/body/div[4]/form/div[1]/input")
            ).sendKeys("C:\\Users\\attil\\Desktop\\UKF\\Magisterske_studium\\2Z\\PTS\\Cvicne_zadanie_PTS\\screenshot.png");

            driver.findElement(
                    By.xpath("/html/body/div[4]/form/div[3]/input[2]")
            ).click();

            Thread.sleep(1000);
//SEND EMAIL
/*driver.findElement(
        By.xpath("/html/body/form/div[1]/a[2]")
).click();*/
            Thread.sleep(5000);


        }catch (InterruptedException e){
        //PRINT THE ERROR OUT
        e.printStackTrace();
                }

        //CLOSE THE BROWSER
        driver.quit();
    }
}