package domaci0606;
/*
* ·        AppointmentPage
o   getter za facility select
o   getter za apply for hospital readmission
o   getter za radio dugme (trazeno po value atributu)
§ xpath-u - kao parametar se prima value za trazeno radio dugme
§ //*[@name='programs'][@value='"+ radioInputName +"']
o   getter za visit date input
o   getter za comment input
o   getter za book appointment button


Napomena: Datum se takodje postavlja preko metode sendKeys samo je potrebno datum uneti u formatu
 "dd/mm/yyyy" => npr: "21/09/2020" */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AppointmentPage extends BasePage{

    //String radioInputName;
    private By facilityList = By.id("combo_facility");
    private By applyForHospitalReadmissionCheckBox = By.id("chk_hospotal_readmission");
    private By visitDateInputBox = By.id("txt_visit_date");
    private By commentInputBox = By.id("txt_comment");
    private By bookAppointmentButton = By.id("btn-book-appointment");
    private By appointmentElement = By.id("appointment");
    private List<WebElement> listAppointment;
    private Select facilityListElement;

    public AppointmentPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public void selectFacilityList(String facilityListElementToSelect){
        facilityListElement = new Select(getDriver().findElement(facilityList));
        facilityListElement.selectByVisibleText(facilityListElementToSelect);
    }

    public String getFacilityListElement(){
        return facilityListElement.getFirstSelectedOption().getText();
    }

    public void checkApplyForHospitalReadmission(boolean check){
        if(check)
            getDriver().findElement(applyForHospitalReadmissionCheckBox).click();
    }

    public boolean getApplyForHospitalReadmission(){
        return getDriver().findElement(applyForHospitalReadmissionCheckBox).isSelected();
    }

    public void chosehealthCareProgram(String radioValue){
        //*[@name='programs'][@value='Medicaid']
        getDriver().findElement(By.xpath("//*[@name='programs'][@value='" + radioValue + "']")).click();
    }

    public void inputVisitDate(String date){
        getDriver().findElement(visitDateInputBox).sendKeys(date);
    }

    public void inputComment(String comment){
        getDriver().findElement(commentInputBox).sendKeys(comment);
    }

    public void clickBookAppointmentButton(){
        getDriver().findElement(bookAppointmentButton).click();
    }

    public boolean isFormPresented(){
        listAppointment = getDriver().findElements(appointmentElement);
        if (listAppointment.size() != 0)
            return true;
        else
            return false;
    }
}
