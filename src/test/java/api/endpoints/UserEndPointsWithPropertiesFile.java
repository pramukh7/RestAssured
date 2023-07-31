package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

import java.util.ResourceBundle;

import org.hamcrest.Matchers.*;

import api.payload.User;
import api.utilities.GetPropertiesFileEndPoints;

public class UserEndPointsWithPropertiesFile {
	
	public Response createUser(User user)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(user)
		.when()
		.post(GetPropertiesFileEndPoints.getPropertiesFileResourceBundle("routes").getString("POST_URL"));
	}

	public Response getUser(String username)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
		.when()
		.get(GetPropertiesFileEndPoints.getPropertiesFileResourceBundle("routes").getString("GET_URL"));
	}
	
	public Response updateUser(String username,User user)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(user)
		.when()
		.put(GetPropertiesFileEndPoints.getPropertiesFileResourceBundle("routes").getString("PUT_URL"));
	}
	
	public Response deleteUser(String username)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
		.when()
		.delete(GetPropertiesFileEndPoints.getPropertiesFileResourceBundle("routes").getString("DELETE_URL"));
	}
}
