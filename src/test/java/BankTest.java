import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class BankTest {

    protected static Logger log;
    protected static WebDriver driver;
    protected static Random rand;
    protected int MAX_VALUE = 10000;
    String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

    protected static LoginPage loginPage;
    protected static ManagerPage managerPage;
    protected static CustomerLoginPage customerLoginPage;
    protected static AccountPage accountPage;
    protected static TransactionsPage transactionsPage;
    protected static CustomerDetail customerDetail;
    protected static AccountDetail accountDetail;
    protected static Transactions transactions;

    @BeforeTest
    public void setup() throws FileNotFoundException {
        System.setProperty("webdriver.chrome.driver", "C:\\Software\\Selenium\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();

        loginPage = new LoginPage(driver);
        managerPage = new ManagerPage(driver);
        customerLoginPage = new CustomerLoginPage(driver);
        accountPage = new AccountPage(driver);
        transactionsPage = new TransactionsPage(driver);

        customerDetail = new CustomerDetail("CustomerDetails.csv");
        accountDetail = new AccountDetail();
        transactions = new Transactions();
        rand = new Random();
        log = LogManager.getLogger(BankTest.class.getName());
        driver.get(url);
    }

    @Test(priority = 1)
    public void addUser() throws InterruptedException {
        loginPage.clickManagerLogin();
        managerPage.clickAddCustomer();
        managerPage.enterFirstName(customerDetail);
        managerPage.enterLastName(customerDetail);
        managerPage.enterPostCode(customerDetail);
        managerPage.clickAddCustomerSub();
        managerPage.getCustomerId(customerDetail);
        managerPage.acceptAlert();
    }

    @Test(priority = 2)
    public void openAccount() throws InterruptedException {
        managerPage.clickOpenAccount();
        managerPage.selectUser(customerDetail);
        managerPage.selectCurrency();
        managerPage.clickProcess();
        managerPage.getAccountNumber(accountDetail);
        managerPage.acceptAlert();
    }
    @Test(priority = 3)
    public void verifyAccountNumber(){
        managerPage.clickCustomer();
        Assert.assertEquals(accountDetail.getAccountNumber(), managerPage.getCustomerAccountNumber(customerDetail));
    }
    @Test(priority = 4)
    public void makeDeposit(){
        int randomVal = rand.nextInt(MAX_VALUE);
        managerPage.clickHome();
        loginPage.clickCusLogin();
        customerLoginPage.selectUser(customerDetail);
        customerLoginPage.clickLogin();
        accountDetail.setBalance(accountPage.getCurrentBalance());
        accountPage.clickDeposit();
        accountPage.enterDepositAmount(randomVal);
        accountPage.clickDepositSub();
        Assert.assertEquals(accountDetail.getBalance()+randomVal,accountPage.getCurrentBalance());
        transactions.setType("Credit");
        transactions.setAmount(randomVal);
        accountDetail.setBalance(accountPage.getCurrentBalance());
    }
    @Test(priority = 5)
    public void makeWithdrawal() throws InterruptedException {
        int randomVal = rand.nextInt(MAX_VALUE);
        accountPage.clickWithdrawal();
        accountPage.enterWithdrawAmount(randomVal);
        accountPage.clickWithdrawalSub();
        if(accountPage.checkWithdrawError()){
            if(accountPage.getErrorMsg().compareTo("Transaction Failed. You can not withdraw amount more than the balance.")==0){
                log.error(accountPage.getErrorMsg());
            }else {
                log.info(accountPage.getErrorMsg());
            }
        }
        Assert.assertEquals(accountDetail.getBalance()-randomVal,accountPage.getCurrentBalance());
        transactions.setType("Debit");
        transactions.setAmount(randomVal);
        accountDetail.setBalance(accountPage.getCurrentBalance());
    }
    @Test(priority = 6)
    public void verifyTransactions(){
        accountPage.clickTransactions();
        Boolean condition = transactionsPage.verifyTransaction(transactions);
        Assert.assertTrue(condition);
    }

//    public void takeScreenShot(String name, String status) throws IOException {
//        TakesScreenshot scrShot =((TakesScreenshot)driver);
//        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
//        File destFile=new File("Screenshots/"+"BankTest/"+status+"/"+name+status+".png");
//        FileHandler.copy(srcFile, destFile);
//    }
//    public void createLogMessage(String methodName, Boolean status){
//        log.info(methodName + ": Succeeded");
//    }
//    public void createExtentReport(String methodName, Boolean status, String details){
//        ExtentTest test = extentReports.createTest(methodName);
//        test.pass(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
//        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
//        test.log(Status.PASS, details);
//    }
//    @AfterMethod
//    public void afterMethod() throws Throwable {
//
//    }

    @AfterClass
    public void afterClass() throws Throwable {
        driver.quit();
    }
}
