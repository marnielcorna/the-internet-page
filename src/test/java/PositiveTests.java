import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

    @Test
    public void loginTest() {
        System.out.println("Starting Login Test");

//  Create Driver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        sleep(1000);
// Maximize browser window
        driver.manage().window().maximize();
        System.out.println("Window maximized");

//  Open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened");

//  Enter username
        String sentUsername = "tomsmith";
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys(sentUsername);
        System.out.println(sentUsername + " " + "was inserted");

//  Enter password
        String sentPassword = "SuperSecretPassword!";
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(sentPassword);
        System.out.println("Password was inserted");

//  click login button
        WebElement logInButton = driver.findElement(By.tagName("button"));
        logInButton.click();
        System.out.println("Click login button");

//  Verification
//  new url
        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

//  logout button is visible
        WebElement logOutButton = driver.findElement(By.xpath("//a[contains(@class,'secondary radius')]"));
        Assert.assertTrue(logOutButton.isDisplayed(), "Log out button is not visible");

//  successful login message
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class,'success')]"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = successMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message does not contain expected message. \nActual Message: " + actualMessage + "\nExpected Message: " + expectedMessage);

//  Close Browser
        sleep(2000);
        driver.quit();
    }

    private void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
