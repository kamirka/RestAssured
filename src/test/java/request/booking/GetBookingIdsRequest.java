package request.booking;

import io.restassured.response.Response;
import url.BookingUrl;

import static io.restassured.RestAssured.given;

public class GetBookingIdsRequest {
    public static Response getAllBookingIds() {
        return given()
                .when()
                .get(BookingUrl.BASE_URL + BookingUrl.BOOKING)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
}