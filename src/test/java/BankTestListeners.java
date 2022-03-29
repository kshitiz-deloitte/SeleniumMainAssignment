import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class BankTestListeners extends BankTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getName() + ": Succeeded");
        ExtentTest test = extentReports.createTest(result.getName());
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
        test.pass(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        test.log(Status.PASS, "Success");

        try {
            super.takeScreenShot(result.getName(),"Success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getName() + ": Failed");
        ExtentTest test = extentReports.createTest(result.getName());
        test.log(Status.FAIL, "Failure");
        test.fail(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
        test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        try {
            super.takeScreenShot(result.getName(),"Failure");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
