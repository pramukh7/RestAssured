package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


//Extent Report 5.X

public class ExtentReportManager implements ITestListener{
	
	private ExtentSparkReporter sparkReporter;
	private ExtentReports extentReport;
	private ExtentTest extentTest;
	
	private String reportName;
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());
		reportName = "Test-Report" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
		sparkReporter.config().setDocumentTitle("Rest Assured Automation Project");
		sparkReporter.config().setReportName("Pet Store User API");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Application", "Pet Store User API");
		extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("user", "Sanjay Patel");
	}
	
	public void onTestSuccess(ITestResult result)
	{
		extentTest = extentReport.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.PASS, "Test Passed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		extentTest = extentReport.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.FAIL, "Test Failed");
		extentTest.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void onTestSkipped(ITestResult result)
	{
		extentTest = extentReport.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.SKIP, "Test Skipped");
		extentTest.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extentReport.flush();
	}

}
