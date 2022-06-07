package domaci0606;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class AppointmentTest {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private LoginPage loginPage;
    private AppointmentPage appointmentPage;
    private AppointmentSummary appointmentSummary;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver, driverWait);
        appointmentPage = new AppointmentPage(driver, driverWait);
        appointmentSummary = new AppointmentSummary(driver, driverWait);

        driver.navigate().to("https://katalon-demo-cura.herokuapp.com/");
    }

    @Test(priority = 1)
    public void loginWithDemo() {
        loginPage.makeAppoitnmentClick();
        loginPage.userLogin();
        Assert.assertTrue(appointmentPage.isFormPresented());
    }

    @Test(priority = 2)
    public void checkInput() {
        appointmentPage.selectFacilityList("Hongkong CURA Healthcare Center");
        appointmentPage.checkApplyForHospitalReadmission(true);
        appointmentPage.chosehealthCareProgram("Medicaid");
        appointmentPage.inputVisitDate("20/07/2022");
        appointmentPage.inputComment("No comment");
        appointmentPage.clickBookAppointmentButton();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"summary\"]/div/div/div[1]/h2")).getText(), "Appointment Confirmation");
    }

    @Test(priority = 3)
    public void checkInputData() {
        Assert.assertEquals(appointmentSummary.getFacility(), "Hongkong CURA Healthcare Center");
        Assert.assertEquals(appointmentSummary.getHospitalReadmission(), true);
        Assert.assertEquals(appointmentSummary.getProgram(), "Medicaid");
        Assert.assertEquals(appointmentSummary.getVisitDate(), "20/07/2022");
        Assert.assertEquals(appointmentSummary.getComment(), "No comment");
    }

    @AfterClass
    public void afterClass(){
        driver.close();
    }
}
