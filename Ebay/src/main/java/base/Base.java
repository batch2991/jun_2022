package base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{
  
	 protected static WebDriver driver;
	
	 protected static Properties prop;
	 
	 public static ExtentHtmlReporter htmlreport;
	 public static ExtentReports report;
	 public static ExtentTest test;
	 
	 @BeforeSuite
	 public void setUp() throws Exception
	 {
	   prop=new Properties();
	   prop.load(new FileInputStream("./src/main/java/config/config.properties"));	   
	   
	   htmlreport = new ExtentHtmlReporter(prop.getProperty("reportlocation"));
	   report = new ExtentReports();
 	   report.attachReporter(htmlreport);
 	   report.setSystemInfo("Host Name", "TestSystem");  //name of thesystem
 	   report.setSystemInfo("Environment", "Test Env");
 	   report.setSystemInfo("User Name", "venkatgn");
 	   
 	   htmlreport.config().setDocumentTitle("Ebay");
 	   htmlreport.config().setReportName("EBay Functional Testing");
 	   htmlreport.config().setTestViewChartLocation(ChartLocation.TOP);
 	   htmlreport.config().setTheme(Theme.STANDARD);
	 }
	 @BeforeTest
	 @Parameters({"browser"})
	 public void initialize(String br)
	 {
		 if(br.matches("firefox"))
		 {
		     WebDriverManager.firefoxdriver().setup();
		     driver=new FirefoxDriver();
		 }
		 if(br.matches("chrome"))
		 {
			 WebDriverManager.chromedriver().setup();
			 driver=new ChromeDriver();
		 }
		 driver.manage().window().maximize();
		 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	 }
	 public void takescreenshot(String imagename) throws Exception
	 {
		 File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(f,new File(prop.getProperty("imagelocation")+imagename));
		 test.addScreenCaptureFromPath(prop.getProperty("imagelocation")+imagename);
	 }
	 public void validateTitle(String expvalue) throws Exception
	 {
	   String title=driver.getTitle();
	   if(title.contains(expvalue))
	   {
		   //Reporter.log("<font color='green'><b>Title matches as expected </b></font>"+expvalue);  //write status in testng report
		   test=report.createTest("Validate Title");
		   test.log(Status.PASS,"Title matched with exp value "+expvalue);
		   takescreenshot("Home.png");  
	   }
	   else
	   {
		  //Reporter.log("<font color='red'><b>Title NOT matcing as expected </b></font>"+expvalue);
		   test=report.createTest("Validate Title");
		   test.log(Status.FAIL,"Title NOT as exp value "+expvalue);
		   takescreenshot("Home.png");
	   }
	 }
	 @AfterTest
	 public void closeBrowser()
	 {
		 driver.close();
	 }
	 @AfterSuite
	 public void tearDown()
	 {
		 report.flush();  //save the report
	 }
}
