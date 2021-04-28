import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTests {

    private AndroidDriver driver;//The AndroidDriver extends the AppiumDriver which eventually extends from the RemoteWebDriver that belongs to Selenium

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();//Set the desired capabilities like in the appium desktop
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appPackage", "com.example.myandroidcalculator");
        desiredCapabilities.setCapability("appActivity", "com.example.myandroidcalculator.MainActivity");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");//The url that the appium sits on

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);//Create new instance of AndroidDriver with the url and desired capabilities
    }

    @Test
    public void enabledTest() {
        MobileElement input = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/num1");//Find element by its id
        Assertions.assertTrue(input.isEnabled());//Check that the element is enabled

        MobileElement input2 = (MobileElement) driver.findElement(By.id("com.example.myandroidcalculator:id/num1"));//Find element by its id (style selenium)
        Assertions.assertTrue(input.isEnabled());
    }

    @Test
    public void sumTest() {
        MobileElement input1 = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/num1");
        input1.sendKeys("1");//Send text or in this case number as text to the selected element
        MobileElement input2 = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/num2");
        input2.sendKeys("2");
        MobileElement plusBtn = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/sum");
        plusBtn.click();

        MobileElement result = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/result");
        Assertions.assertEquals("3",result.getText());
    }

    @Test
    public void multiplyTest() {
        MobileElement input1 = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/num1");
        input1.sendKeys("5");
        MobileElement input2 = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/num2");
        input2.sendKeys("5");
        MobileElement plusBtn = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/mul");
        plusBtn.click();

        MobileElement result = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/result");
        Assertions.assertEquals("25",result.getText());
    }


    @Test
    public void divisionByZeroTest() {
        MobileElement input1 = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/num1");
        input1.sendKeys("5");
        MobileElement input2 = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/num2");
        input2.sendKeys("0");
        MobileElement plusBtn = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/div");
        plusBtn.click();

        MobileElement result = (MobileElement) driver.findElementById("com.example.myandroidcalculator:id/result");
        Assertions.assertEquals("Infinity",result.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
