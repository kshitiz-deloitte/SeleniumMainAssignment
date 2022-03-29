import com.aventstack.extentreports.ExtentReports;
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

public class BankTest2 {
//    protected Logger log1;
    protected WebDriver driver;
    protected Random rand;
    protected ExtentReports extentReports;
    protected ExtentSparkReporter sparkReporter;
    protected int MAX_VALUE = 10000;
    protected String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

    protected static LoginPage loginPage2;
    protected static CustomerLoginPage customerLoginPage2;
    protected static AccountPage accountPage2;
    protected static AccountDetail accountDetail;
    protected static Transactions transactions;


    @BeforeTest
    public void setup() throws FileNotFoundException {
        System.setProperty("webdriver.chrome.driver", "C:\\Software\\Selenium\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        extentReports = new ExtentReports();
        sparkReporter = new ExtentSparkReporter("BankTest2.html");
        extentReports.attachReporter(sparkReporter);

        loginPage2 = new LoginPage(driver);
        customerLoginPage2 = new CustomerLoginPage(driver);
        accountPage2 = new AccountPage(driver);

        rand = new Random();
//        log1 = LogManager.getLogger(BankTest2.class.getName());
        driver.get(url);
    }
    @Test
    public void makeDeposit(){
        int randomVal = rand.nextInt(MAX_VALUE);
        loginPage2.clickCusLogin();
        customerLoginPage2.selectUser();
        customerLoginPage2.clickLogin();
        accountPage2.clickDeposit();
        accountPage2.enterDepositAmount(randomVal);
        accountPage2.clickDepositSub();
    }
    @Test
    public void makeWithdrawal() throws InterruptedException {
        int randomVal = rand.nextInt(MAX_VALUE);
        accountPage2.clickWithdrawal();
        accountPage2.enterWithdrawAmount(randomVal);
        accountPage2.clickWithdrawalSub();

    }

//    public void takeScreenShot(String name, String status) throws IOException {
//        TakesScreenshot scrShot =((TakesScreenshot)driver);
//        File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
//        File destFile=new File("Screenshots/"+"BankTest2/"+status+"/"+name+status+".png");
//        FileHandler.copy(srcFile, destFile);
//    }
    @AfterClass
    public void afterClass() throws Throwable {
        extentReports.flush();
        driver.quit();
    }
}
