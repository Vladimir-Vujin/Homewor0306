package domaci0606;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppointmentSummary extends BasePage {

    private By facility = By.id("facility");
    private By hospitalReadmission = By.id("hospital_readmission");
    private By program = By.id("program");
    private By visitDate = By.id("visit_date");
    private By comment = By.id("comment");

    public AppointmentSummary(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public String getFacility() {
        return getDriver().findElement(facility).getText();
    }

    public boolean getHospitalReadmission() {
        if (getDriver().findElement(hospitalReadmission).getText().equals("Yes"))
            return true;
        else
            return false;
    }

    public String getProgram() {
        return getDriver().findElement(program).getText();
    }

    public String getVisitDate() {
        return getDriver().findElement(visitDate).getText();
    }

    public String getComment() {
        return getDriver().findElement(comment).getText();
    }

}
