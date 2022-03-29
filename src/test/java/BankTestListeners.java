import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class BankTestListeners implements ITestListener {
    protected static ExtentReports extentReports;
    protected static Logger log;
    protected static ExtentSparkReporter sparkReporter;
    String TestClassName="Test";

    @Override
    public void onTestStart(ITestResult result) {
        if (sparkReporter==null && TestClassName.compareTo(result.getInstance().getClass().getName())==0){
            sparkReporter = new ExtentSparkReporter(result.getInstance().getClass().getName()+".html");
            extentReports.attachReporter(sparkReporter);
            TestClassName = result.getInstance().getClass().getName();
        }

        log = LogManager.getLogger(result.getInstance().getClass().getName());
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getName() + ": Succeeded");
        ExtentTest test = extentReports.createTest(result.getName());
        test.log(Status.PASS, "Success");
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());


//        try {
//            super.takeScreenShot(result.getName(),"Success");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        log.info(result.getName() + ": Failed");
        ExtentTest test = extentReports.createTest(result.getName());
        test.log(Status.FAIL, "Failure");
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
        test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
//        try {
//            super.takeScreenShot(result.getName(),"Failure");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getName() + ": skipped");
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        extentReports = new ExtentReports();
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        ITestListener.super.onFinish(context);
    }
}
