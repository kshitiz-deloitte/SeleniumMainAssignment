import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebElement cusLoginBtn, managerLoginBtn;
    By cusLogin = By.xpath("//button[contains(text(),'Customer Login')]");
    By managerLogin = By.xpath("//button[contains(text(),'Bank Manager Login')]");


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void clickCusLogin(){

        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cusLogin));
        cusLoginBtn = driver.findElement(cusLogin);
        cusLoginBtn.click();
    }
    public void clickManagerLogin(){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(managerLogin));
        managerLoginBtn = driver.findElement(managerLogin);
        managerLoginBtn.click();
    }
}
