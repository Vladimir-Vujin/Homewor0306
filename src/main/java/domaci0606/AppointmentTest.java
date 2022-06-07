package domaci0606;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;

public class AppointmentTest {

    private WebDriver driver;
    private WebDriverWait driverWait;
    private LoginPage loginPage;
    private AppointmentPage appointmentPage;
    private AppointmentSummary appointmentSummary;
    private String facility;
    private boolean applyForHospitalReadmission;

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
        //loginPage.userLogin("John Doe", "ThisIsNotAPassword");
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

        // NAŠAO SAM REŠENJE DA DOK JE STRANICA AKTIVNA SAČUVAM PODATAK U PROMENLJIVOJ
        // PA GA ISKORISTIM U NAREDNOM TESTU U ASSERTU DA UPOREDI UNOS NEKOG ELEMENTA NA
        // APPOINTMENT STRANICI SA REZULTATOM NA APPOINTMENT SUMMARY STARNICI ZA TAJ ELEMENT
        facility = appointmentPage.getFacilityListElement();
        // VIDI KAKO VRATITI OVAJ PODATAK IZ OBJEKTA KADA PREDJE NA DRUGU WEB STRANICU
        applyForHospitalReadmission = appointmentPage.getApplyForHospitalReadmission();

        appointmentPage.clickBookAppointmentButton();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"summary\"]/div/div/div[1]/h2")).getText(), "Appointment Confirmation");
    }

    @Test(priority = 3)
    public void checkInputData() {
        // KADA ODAVDE ZOVEM getApplyForHospitalReadmission() IZBACUJE MI GREŠKU JER TA STRANICA
        // VIŠE NIJE AKTIVNA I NE MOŽE DA PRONAĐE WEB ELEMENT
        // VIDIM DA POSTOJI OBJEKAT appointmentPage ALI NE ZNAM KAKO DA IZ NJEGA IZVUČEM PODATAK
        // ZA KONKRETNO SELEKTOVANI ELEMENT COMBOBOX-A
        Assert.assertEquals(appointmentSummary.getFacility(), facility);
        Assert.assertEquals(appointmentSummary.getHospitalReadmission(), applyForHospitalReadmission);
        // I proverite da li se unosi sa appointment forme uklapaju sa podacima sa summary stranice
        // katalon-demo-cura.herokuapp.com
    }
}
