package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;

import api.payload.User;

public class UserEndPoints {
	
	public Response createUser(User user)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(user)
		.when()
		.post(Routes.POST_URL);
	}

	public Response getUser(String username)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
		.when()
		.get(Routes.GET_URL);
	}
	
	public Response updateUser(String username,User user)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
			.body(user)
		.when()
		.put(Routes.PUT_URL);
	}
	
	public Response deleteUser(String username)
	{
		return given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", username)
		.when()
		.delete(Routes.DELETE_URL);
	}
}
