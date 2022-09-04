package ebaytest;

import org.testng.annotations.Test;

import pages.Home;
import pages.Results;

public class FunctionalTest 
{
    Home home=new Home();
    Results results=new Results();
    
    @Test(priority=1)
	public void smokeTest() throws Exception
	{		
		home.openUrl();
		home.validateTitle("Electronics, Cars");
		
	}
    @Test(priority=2)
	public void regressionTest() throws Exception
	{
	    home.search();
	    results.validateTitle("outdoor toys");
	    results.getTotalResult();
	    results.getAllProductNames();
	}
}
