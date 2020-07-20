import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.eclipse.kapua.qa.markers.junit.JUnitTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(JUnitTests.class)
public class TagsTests extends Assert {

    @Test
    public void tagsTests () {

            RestAssured.given().when().get("http://www.google.com").then().statusCode(200);

            Response response = (Response) RestAssured.given().
                    auth().
                    preemptive().
                    basic("kapua-sys", "kapua-password").
                    contentType(ContentType.JSON).
                    get("http://localhost:8081/v1/authentication/user").
                    then().
                    assertThat().
                    statusCode(200);



//        RestAssured.given().contentType("application/json").auth()
//                .basic("kapua-sys", "kapua-password")
//                .when()
//                .post("http://localhost:8081/v1/authentication/user")
//                .then()
//                .assertThat()
//                .statusCode(200);

//        assertEquals("status code should be 200", 200, RestAssured.given().auth().basic("kapua-sys", "kapua-password").when().
//                get("http://localhost:8081/v1/authentication/user").then().assertThat().statusCode(200));

//        JSONObject body = new JSONObject();
//        body.put("firstname", "dmitry");
//        body.put("lastname","shyshkyn");
//        body.put("total price", 150);
//        body.put("deposit paid", false);
//
//        JSONObject bookingDates = new JSONObject();
//        bookingDates.put("checkin", "2020-03-25");
//        bookingDates.put("checkout", "2020-03-27");
//        body.put("bookindDates", bookingDates);
//        body.put("additionalNeeds", "baby crib");

//        // tutorial
//        Response response1 = RestAssured.given().contentType(ContentType.JSON).
//                body(body.toString()).post("https://the-booker.herokuapp.com/apidoc/index.html");
//        response1.print();
//        assertEquals("Response should be 200 but it is not", 200, response1.getStatusCode());
//        int bookingId = response1.jsonPath().getInt("bookingId");

//        RestAssured.given().when().get("http://localhost:8081/v1/_/tags?limit=50&offset=0").then().assertThat().statusCode(200);
//
//        Response response = RestAssured.get("http://localhost:8081...");
//        response.print();
//
//        assertEquals("Status should be 200 but it's not", response.getStatusCode(), 200);
//        List<Integer> bookingIds = response.jsonPath().getList("bookingId");
//        assertFalse("List of bookings empty but it should not be", bookingIds.isEmpty());

    }
}
