import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerLoginPage {
    WebDriver driver;
    Select userSelectE;
    WebElement loginBtn;
    By userSelect = By.xpath("//select[@name='userSelect']");
    By login = By.xpath("//button[contains(text(),'Login')]");
    CustomerLoginPage(WebDriver driver){
        this.driver = driver;
    }
    //Selects Customer form Dropdown
    public void selectUser(CustomerDetail customerDetail){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userSelect));
        userSelectE = new Select(driver.findElement(userSelect));
        String customerName = (customerDetail.getFullName());
        userSelectE.selectByVisibleText(customerName);
    }
    public void selectUser(){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userSelect));
        userSelectE = new Select(driver.findElement(userSelect));
        userSelectE.selectByIndex(2);
    }
    //Clicks login button
    public void clickLogin(){
        loginBtn = driver.findElement(login);
        loginBtn.click();
    }

}
