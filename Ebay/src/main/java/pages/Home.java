package pages;


import org.openqa.selenium.By;
import base.Base;


public class Home extends Base
{
   
   By searchinput=By.xpath("//input[@placeholder='Search for anything']");
   By searchbtn=By.xpath("//input[@value='Search']");
   
  
   public void openUrl()
   {
	   driver.get(prop.getProperty("url"));	   
   }  
   public void search()
   {
	   driver.findElement(searchinput).sendKeys("Outdoor toys");
	   driver.findElement(searchbtn).click();   
	}
}
