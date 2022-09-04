package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FbLogin 
{
   
   ChromeDriver driver;
	
   public void openUrl()
   {
	   WebDriverManager.chromedriver().setup();
       driver=new ChromeDriver();
       driver.get("http://www.facebook.com");
   }
   public void login(String uid,String pwd)
   {
	   driver.findElement(By.name("email")).sendKeys(uid);
       driver.findElement(By.name("pass")).sendKeys(pwd);
       driver.findElement(By.name("login")).click();
       System.out.println("hello");
	   
   }
   public void validateErrormsg()
   {
	   System.out.println("code to read error message and validate");     
   }
   public void validatesuccesslogin()
   {
	   System.out.println("check the home is displayed");
   }
}
