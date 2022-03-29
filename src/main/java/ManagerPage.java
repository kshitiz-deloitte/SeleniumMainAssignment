import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ManagerPage {
    WebDriver driver;
    Alert alert;
    WebElement addCustomerBtn, openAccountBtn, customersBtn, firstNameEntry, lastNameEntry, postCodeEntry, addCustomerSubBtn;
    WebElement processBtn, homeBtn, searchBar, customerAccountNumber;
    Select usersList, currenciesList;
    String customerID, accountNumber;
    List<WebElement> List;
    By addCustomer = By.xpath("//div[@class='center']//button[contains(text(),'Add Customer')]");
    By openAccount = By.xpath("//button[contains(text(),'Open Account')]");
    By customers = By.xpath("//button[contains(text(),'Customers')]");
    By firstName = By.xpath("//label[contains(text(),'First Name')]//following-sibling::input");
    By lastName = By.xpath("//label[contains(text(),'Last Name')]//following-sibling::input");
    By postCode = By.xpath("//label[contains(text(),'Post Code')]//following-sibling::input");
    By addCustomerSub = By.xpath("//div[@class='form-group']//following::button[contains(text(),'Add Customer')]");
    By userSelect = By.xpath("//select[@name='userSelect']");
    By currencySelect = By.xpath("//select[@name='currency']");
    By process = By.xpath("//button[contains(text(),'Process')]");
    By search = By.xpath("//input[@placeholder='Search Customer']");
    By customerAccount = By.xpath("//td[@class='ng-binding']//following::td[3]//span");
    By home = By.xpath("//button[contains(text(),'Home')]");

    public ManagerPage(WebDriver driver){
        this.driver = driver;
    }
    //Click add customer button to add Customer
    public void clickAddCustomer(){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addCustomer));
        addCustomerBtn = driver.findElement(addCustomer);
        addCustomerBtn.click();
    }
    //Click open account button to open account
    public void clickOpenAccount() throws InterruptedException {
        openAccountBtn = driver.findElement(openAccount);
        openAccountBtn.click();
    }
    //Click Customers button to check the Customers list
    public void clickCustomer(){
        customersBtn = driver.findElement(customers);
        customersBtn.click();
    }
    //Enter the First Name of the Customer
    public void enterFirstName(CustomerDetail customerDetail){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(firstName));
        firstNameEntry = driver.findElement(firstName);
        firstNameEntry.sendKeys(customerDetail.getFirstName());
    }
    //Enter the Last Name of the Customer
    public void enterLastName(CustomerDetail customerDetail) throws InterruptedException {
        lastNameEntry = driver.findElement(lastName);
        lastNameEntry.sendKeys(customerDetail.getLastName());
    }
    //Enter the Post Code of the Customer
    public void enterPostCode(CustomerDetail customerDetail){
        postCodeEntry = driver.findElement(postCode);
        postCodeEntry.sendKeys(customerDetail.getAddress());
    }
    //Click add customer button to add the customer name to Customer List
    public void clickAddCustomerSub(){
        addCustomerSubBtn = driver.findElement(addCustomerSub);
        addCustomerSubBtn.click();
    }
    //Get the ID of the Customer Added
    public void getCustomerId(CustomerDetail customerDetail){
        alert = driver.switchTo().alert();
        customerID = alert.getText().replaceAll("[^\\d.]", "");
        customerDetail.setCustomerID(customerID);
        System.out.println(customerID);
    }
    public void acceptAlert(){
        alert.accept();
    }
    //Selects the Customer's name from the list of Customers name from Dropdown
    public void selectUser(CustomerDetail customerDetail) throws InterruptedException {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userSelect));
        usersList = new Select(driver.findElement(userSelect));
        usersList.selectByVisibleText(customerDetail.getFullName());

    }
    //Select currency from dropdown
    public void selectCurrency(){
        currenciesList = new Select(driver.findElement(currencySelect));
        currenciesList.selectByVisibleText("Dollar");
    }
    //Click process to complete the addition of the Customer
    public void clickProcess(){
        processBtn = driver.findElement(process);
        processBtn.click();
    }
    //Get the Account Number of the Customer after successfully adding the Customer
    public void getAccountNumber(AccountDetail accountDetail){
        alert = driver.switchTo().alert();
        accountNumber = alert.getText().replaceAll("[^\\d.]", "");
        accountDetail.setAccountNumber(accountNumber);
    }
    //Click Home to Get to the first page
    public void clickHome(){
        homeBtn = driver.findElement(home);
        homeBtn.click();
    }
    public String getCustomerAccountNumber(CustomerDetail customerDetail){
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(search));
        searchBar = driver.findElement(search);
        searchBar.sendKeys(customerDetail.getLastName());
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(customerAccount));
        customerAccountNumber = driver.findElement(customerAccount);
        return customerAccountNumber.getText();
    }
}
