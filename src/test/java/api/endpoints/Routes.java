package api.endpoints;


/*
 * End points :-
 * 1) GET User By userName : https://petstore.swagger.io/v2/user/{userName}
 * 2) POST: https://petstore.swagger.io/v2/user
 * 3) PUT : https://petstore.swagger.io/v2/user/{userName}
 * 4) DELETE : https://petstore.swagger.io/v2/user/{userName}
 */
public class Routes {
	
	//BASE URL
	public static String BASE_URL = "https://petstore.swagger.io/v2";
	
	//User Module
	public static String POST_URL = BASE_URL + "/user";
	public static String PUT_URL = BASE_URL + "/user/{username}";
	public static String DELETE_URL = BASE_URL + "/user/{username}";
	public static String GET_URL = BASE_URL + "/user/{username}";
}
