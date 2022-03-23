package request.booking;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import url.BookingUrl;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    public static Response putBooking(String bookingId, JSONObject json, String token){
      return  given()
                .contentType(ContentType.JSON)
                .body(json.toString())
                .header("Cookie","token="+token)
                .when()
                .patch(BookingUrl.BASE_URL + BookingUrl.BOOKING + "/" + bookingId)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}