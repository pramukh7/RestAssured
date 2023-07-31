package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.endpoints.UserEndPointsWithPropertiesFile;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestsUsingPropertiesFileEndpoints {
	
	private UserEndPointsWithPropertiesFile userEndPointsWithPropertiesFile;
	private User user;
	
	@BeforeClass
	public void beforeClass()
	{
		user = new User();
		user.setFirstName("Swaminarayan");
		user.setLastName("Bhagwan");
		user.setEmail("swaminarayan@baps.org");
		user.setPhone("1234567890");
		user.setUsername("swaminarayan");
		user.setPassword("swaminarayan");
	}
	
	@BeforeMethod
	public void setup()
	{
		userEndPointsWithPropertiesFile = new UserEndPointsWithPropertiesFile();
	}
	
	@Test(priority = 1)
	public void addUser()
	{
		Response response = userEndPointsWithPropertiesFile.createUser(user);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void getUser()
	{
		Response response = userEndPointsWithPropertiesFile.getUser(user.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3, dependsOnMethods = "addUser")
	public void updateUser()
	{
		user.setFirstName("Sarvopari Swaminarayan");
		Response response = userEndPointsWithPropertiesFile.updateUser(user.getUsername(), user);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4, dependsOnMethods = "updateUser")
	public void deleteUser()
	{
		Response response = userEndPointsWithPropertiesFile.deleteUser(user.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
