package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviderUtility;
import io.restassured.response.Response;

public class DataDrivenTests {

	private UserEndPoints userEndPoints;
	
	@BeforeMethod
	public void setup()
	{
		userEndPoints = new UserEndPoints();
	}
	@Test(priority = 1, dataProvider = "data", 
			dataProviderClass = DataProviderUtility.class)
	public void testPostUsers(String userId, String username, 
			String firstName, String lastName, 
			String email, String password, String phone)
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		userPayload.setUsername(username);
		
		Response response = userEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2, 
			dataProvider = "usernames", 
			dataProviderClass = DataProviderUtility.class)
	public void deleteUsers(String userName)
	{
		Response response = userEndPoints.deleteUser(userName.trim());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
