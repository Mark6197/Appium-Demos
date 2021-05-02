import io.appium.java_client.windows.WindowsDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class WpfTests {
    private static WindowsDriver driver = null;//The WindowsDriver extends the AppiumDriver which eventually extends Selenium's RemoteWebDriver

    @BeforeAll
    public static void setup() {

            DesiredCapabilities capabilities = new DesiredCapabilities();//Create instance of DesiredCapabilities
            //capabilities.setCapability("platformName", "Windows"); //Optional (not a must)
            //capabilities.setCapability("deviceName", "WindowsPC"); //Optional (not a must)
            capabilities.setCapability("app", "E:\\WpfApp3.exe");//Set the application's execution file path
        try {
            driver = new WindowsDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);//The url that the appium sits on
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void changeText(){
        String text="Hello World";
        WebElement sampleText= driver.findElementByAccessibilityId("SampleText");//Get the element with accessibility id SampleText
        sampleText.clear();//Clear the element's text
        sampleText.sendKeys(text);//Send our "Hello World" String as text to the element
        Assertions.assertEquals(text,driver.findElementByAccessibilityId("VerySmallText").getText());//Get the element with accessibility id VerySmallText
        Assertions.assertEquals(text,driver.findElementByAccessibilityId("SmallText").getText());//Get the element with accessibility id SmallText
        Assertions.assertEquals(text,driver.findElementByAccessibilityId("MediumText").getText());//Get the element with accessibility id MediumText
        Assertions.assertEquals(text,driver.findElementByAccessibilityId("LargeText").getText());//Get the element with accessibility id LargeText
    }


    @AfterAll
    public static void TearDown()
    {
        if (driver != null) {
            driver.quit();//If the driver is not null quit
        }
        driver = null;
    }
}
