package ebay_bdd;

import org.junit.runner.RunWith;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.junit.runners.SerenityRunner;
import pages.FbLogin;



public class Login 
{

	FbLogin fblogin=new FbLogin();

	@Given("^user is on the login page$")
	public void user_is_on_the_login_page() 
	{
		fblogin.openUrl();     
	}

	@When("^user enters valid username, invalid password and click login$")
	public void user_enters_valid_username_invalid_password_and_click_login() 
	{
		fblogin.login("naveen4@gmail.com","sdfdfdf");
	}

	@Then("^should display an error message$")
	public void should_display_an_error_message() 
	{
		fblogin.validateErrormsg();
	}
	@When("^user enters valid username and valid password and click login button$")
	public void user_enters_valid_username_and_valid_password_and_click_login_button() 
	{
		fblogin.login("naveen4@gmail.com","password@1234");
	}

	@Then("^user should login successfully$")
	public void user_should_login_successfully() 
	{
		fblogin.validatesuccesslogin();
	}
}
