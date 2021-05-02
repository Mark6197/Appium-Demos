import io.appium.java_client.windows.WindowsDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class CalcTests {
    private static WindowsDriver CalculatorSession = null;
    private static WebElement CalculatorResult = null;

    @BeforeAll
    public static void setup() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //capabilities.setCapability("platformName", "Windows"); //Optional (not a must)
            //capabilities.setCapability("deviceName", "WindowsPC"); //Optional (not a must)
            capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");//Set the application id
            CalculatorSession = new WindowsDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

            CalculatorResult = CalculatorSession.findElementByAccessibilityId("CalculatorResults");
            Assertions.assertNotNull(CalculatorResult);

        }catch(Exception e){
            e.printStackTrace();
        } finally {
        }
    }

    @BeforeEach
    public void Clear()
    {
        CalculatorSession.findElementByName("Clear").click();
        Assertions.assertEquals("0", _GetCalculatorResultText());
    }

    @AfterAll
    public static void TearDown()
    {
        CalculatorResult = null;
        if (CalculatorSession != null) {
            CalculatorSession.quit();
        }
        CalculatorSession = null;
    }

    @Test
    public void Addition()
    {
        CalculatorSession.findElementByName("One").click();
        CalculatorSession.findElementByName("Plus").click();
        CalculatorSession.findElementByName("Seven").click();
        CalculatorSession.findElementByName("Equals").click();
        Assertions.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Combination()
    {
        CalculatorSession.findElementByName("Seven").click();
        CalculatorSession.findElementByName("Multiply by").click();
        CalculatorSession.findElementByName("Nine").click();
        CalculatorSession.findElementByName("Plus").click();
        CalculatorSession.findElementByName("One").click();
        CalculatorSession.findElementByName("Equals").click();
        CalculatorSession.findElementByName("Divide by").click();
        CalculatorSession.findElementByName("Eight").click();
        CalculatorSession.findElementByName("Equals").click();
        Assertions.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Division()
    {
        CalculatorSession.findElementByName("Eight").click();
        CalculatorSession.findElementByName("Eight").click();
        CalculatorSession.findElementByName("Divide by").click();
        CalculatorSession.findElementByName("One").click();
        CalculatorSession.findElementByName("One").click();
        CalculatorSession.findElementByName("Equals").click();
        Assertions.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Multiplication()
    {
        CalculatorSession.findElementByName("Nine").click();
        CalculatorSession.findElementByName("Multiply by").click();
        CalculatorSession.findElementByName("Nine").click();
        CalculatorSession.findElementByName("Equals").click();
        Assertions.assertEquals("81", _GetCalculatorResultText());
    }

    @Test
    public void Subtraction()
    {
        CalculatorSession.findElementByName("Nine").click();
        CalculatorSession.findElementByName("Minus").click();
        CalculatorSession.findElementByName("One").click();
        CalculatorSession.findElementByName("Equals").click();
        Assertions.assertEquals("8", _GetCalculatorResultText());
    }

    protected String _GetCalculatorResultText()
    {
        // trim extra text and whitespace off of the display value
        return CalculatorResult.getText().replace("Display is", "").trim();
    }

}
