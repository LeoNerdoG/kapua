package org.eclipse.kapua.app.api.resources.v1.resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AuthenticationTest extends JSONObject {

//    @Test
//    public void loginWithCorrectCredentials() {
//        UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentialsImpl("kapua-sys", "kapua-password");
//
//        Authentication authentication = new Authentication();
//        try {
//            authentication.loginUsernamePassword(usernamePasswordCredentials);
//        } catch (KapuaException e) {
//            e.printStackTrace();
//        }

    @Test
    public void test1() {
        // Rest API's URL
        String restAPIUrl = "http://localhost:8081/v1/authentication/user";

        // API Body
        String requestBody = "{\r\n" + "  \"username\": \"kapua-sys\",\r\n" + "  \"password\": \"kapua-password\"\r\n"
                + "}";

        // Building request by using requestSpecBuilder
        RequestSpecBuilder builder = new RequestSpecBuilder();

        // Set API's Body
        builder.setBody(requestBody);

        // Setting content type as application/json
        builder.setContentType("application/json; charset=UTF-8");

        RequestSpecification requestSpec = builder.build();

        // Making post request with authentication or leave blank if you don't have
        // credentials like: basic("","")
        Response response = given().auth().preemptive().basic("kapua-sys", "kapua-password").spec(requestSpec).when()
                .post(restAPIUrl);

        JSONObject JSONResponseBody = new JSONObject(response.body().asString());

        // Get the desired value of a parameter
        String result = JSONResponseBody.getString("scopeId");

        // Check the Result and status code
        Assert.assertEquals(result, "AQ");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void loginWithIncorrectCredentials() throws JSONException, InterruptedException {

        // Rest API's URL
        String restAPIUrl = "http://localhost:8081/v1/authentication/user";

        // API Body
        String requestBody = "{\r\n" + "  \"message\": \"string\",\r\n" + "  \"kapuaErrorCode\": \"string\"\r\n" + "}";

        // Building request by using requestSpecBuilder
        RequestSpecBuilder builder = new RequestSpecBuilder();

        // Set API's Body
        builder.setBody(requestBody);

        // Setting content type as application/json
        builder.setContentType("application/json; charset=UTF-8");

        RequestSpecification requestSpec = builder.build();

        // Making post request with authentication or leave blank if you don't have
        // credentials like: basic("","")
        Response response = given().auth().preemptive().basic("kapua-sys", "kapua-password").spec(requestSpec).when()
                .post(restAPIUrl);

        JSONObject JSONResponseBody = new JSONObject(response.body().asString());

        // Get the desired value of a parameter
        //String result = JSONResponseBody.getString("scopeId");

        // Check the Result and status code
        //Assert.assertEquals(result, "AQ");
        Assert.assertEquals(500, response.getStatusCode());
    }
}