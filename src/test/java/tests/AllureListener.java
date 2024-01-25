package tests;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.listener.TestLifecycleListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import utilities.Driver;
import utilities.ExcelUtils;
import utilities.Log;
import utilities.ReusableMethods;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureListener implements ITestListener {
    public static WebDriver driver = Driver.getDriver();

    @Override
    public void onStart(ITestContext context) {
        Log.info("Test Suite started: " + context.getName());
        Log.startTestCase(context.getName());
        context.setAttribute("Webdriver", Driver.getDriver());
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Test Suite finished: " + context.getName());
        Log.endTestCase(context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("Test started: " + result.getName());
        Log.info(getTestMethodName(result) + " test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("The Test : " + getTestMethodName(result)+"terminated successfully ");
        ExcelUtils.logToExcel(result.getName(),"Passed","Test executed successfully");
        ReusableMethods.logToPdf(result.getName(),"Passed","Test executed successfully");
        ReusableMethods.createPdf();

    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ReusableMethods.getScreenshot(result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.info(getTestMethodName(result) + " test is failed.");
        ExcelUtils.logToExcel(result.getName(),"Failed","Test not executed successfully");
        ReusableMethods.logToPdf(result.getName(),"Failed","Test not executed successfully");
        ReusableMethods.createPdf();
        Object testClass = result.getInstance();
        WebDriver driver = Driver.getDriver();

        if (driver instanceof WebDriver) {

            Log.info(getTestMethodName(result) + " Testi için ScrrenShot Alindi ");

            takeScreenshot(driver);
        }
        saveTextLog(getTestMethodName(result)+ " test fail oldu ve Screen Shot Alindi");
    }




    @Override
    public void onTestSkipped(ITestResult result) {
        Log.warn("Test skipped: " + result.getName());
        ExcelUtils.logToExcel(result.getName(),"Skipped","Test not executed ");
        ReusableMethods.logToPdf(result.getName(),"Skipped","Test not executed ");
        ReusableMethods.createPdf();

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("Test failed but within success percentage: " + result.getName());
        takeScreenshot(driver);
        ExcelUtils.logToExcel(result.getName(),"Test failed but within success percentage","Test executed not successfully");
        ReusableMethods.logToPdf(result.getName(),"Test failed but within success percentage","Test executed not successfully");
        ReusableMethods.createPdf();

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        Log.info("Test timed out: " + result.getName());
        takeScreenshot(driver);
    }

    public void onTestFailedWithTimeout(ITestResult result, long timeout){
        Log.info("Test timed out: " + result.getName() + ", Timeout: " + timeout + " milliseconds");
        takeScreenshot(driver);
    }

    @Step("{stepDescription}")
    public static void logStep(String stepDescription) {
        Log.info(getCurrentTime() + " - " + stepDescription);
    }


    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {
       return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }


    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }
    @Attachment(value = "{0}",type = "text/plain")
    public static String saveTextLog(String message){
        return message;
    }
}