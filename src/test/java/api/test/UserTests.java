package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	private UserEndPoints userEndPoints;
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
		userEndPoints = new UserEndPoints();
	}
	
	@Test(priority = 1)
	public void addUser()
	{
		Response response = userEndPoints.createUser(user);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void getUser()
	{
		Response response = userEndPoints.getUser(user.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 3, dependsOnMethods = "addUser")
	public void updateUser()
	{
		user.setFirstName("Sarvopari Swaminarayan");
		Response response = userEndPoints.updateUser(user.getUsername(), user);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 4, dependsOnMethods = "updateUser")
	public void deleteUser()
	{
		Response response = userEndPoints.deleteUser(user.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
