import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AccountPage {
    WebDriver driver;
    WebElement depositBtn, withdrawalBtn, transactionBtn, homeBtn, logoutBtn, depositSubBtn, amountEntry, withdrawalSubBtn, amountEntryW;
    WebElement errorMsgTxt, currentBalance;
    List<WebElement> accountInfoList;
    By deposit = By.xpath("//button[contains(text(),'Deposit')]");
    By withdrawal = By.xpath("//button[contains(text(),'Withdrawl')]");
    By transaction = By.xpath("//button[contains(text(),'Transactions')]");
    By logout = By.xpath("//button[contains(text(),'Logout')]");
    By home = By.xpath("//button[contains(text(),'Home')]");
    By balance = By.xpath("//strong[@class='ng-binding'][2]");
    By amount = By.xpath("//label[contains(text(),'Amount to be Deposited :')]//following-sibling::input");
    By amountW = By.xpath("//label[contains(text(),'Amount to be Withdrawn :')]//following-sibling::input");
    By depositSub = By.xpath("//input[@placeholder='amount']//following::button[contains(text(),'Deposit')]");
    By withdrawalSub = By.xpath("//input[@placeholder='amount']//following::button[contains(text(),'Withdraw')]");
    By errorMsg = By.xpath("//span[@class='error ng-binding']");
    AccountPage(WebDriver driver){
        this.driver = driver;
    }

    //Click deposit button to open deposit section
    public void clickDeposit(){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(deposit));
        depositBtn = driver.findElement(deposit);
        depositBtn.click();
    }
    //Enter the Deposit Amount
    public void enterDepositAmount(int value){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(amount));
        amountEntry = driver.findElement(amount);
        amountEntry.sendKeys(String.valueOf(value));
    }
    //Click Deposit button to complete the deposit
    public void clickDepositSub(){
        depositSubBtn = driver.findElement(depositSub);
        depositSubBtn.click();
    }
    //Click withdraw button to open withdraw section
    public void clickWithdrawal(){
        withdrawalBtn = driver.findElement(withdrawal);
        withdrawalBtn.click();
    }
    //Click withdraw button to complete withdrawal
    public void clickWithdrawalSub() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(withdrawalSub));
        withdrawalSubBtn = driver.findElement(withdrawalSub);
        withdrawalSubBtn.click();
    }
    //Enter Withdrawal Amount
    public void enterWithdrawAmount(int value){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(amountW));
        amountEntryW = driver.findElement(amountW);
        amountEntryW.sendKeys(String.valueOf(value));
    }
    //Checks the Error occurred during Withdrawal
    public boolean checkWithdrawError(){
        try{
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMsg));
            return true;
        }catch (Exception exception){
            return false;
        }
    }
    //Gets Error message
    public String getErrorMsg(){
//        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorMsg));
        errorMsgTxt = driver.findElement(errorMsg);
        return errorMsgTxt.getText();
    }
    //Gets the current balance of the Customer
    public Float getCurrentBalance(){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(balance));
        currentBalance = driver.findElement(balance);
        return Float.parseFloat(currentBalance.getText());
    }
    public void clickTransactions(){
        transactionBtn = driver.findElement(transaction);
        transactionBtn.click();
    }

}
