package test.request.booking;

import dto.AuthDto;
import dto.BookingDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import request.auth.PostAuthRequest;
import request.booking.PostBookingRequest;
import request.booking.PutBookingRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class PutBookingTest {

    private static String token;

    @BeforeAll
    static void setup() {
        JSONObject defaultAuth = AuthDto.getDefaultAuth();
        token = PostAuthRequest.createToken(defaultAuth);
        System.out.println("TOKEN: " + token);
    }

    @Test
    void PutUpdateBookingTest() {
        //1.Tworzę nowy booking
        JSONObject defaultBooking = BookingDto.getDefaultBooking();
        Response createResponse = PostBookingRequest.createBooking(defaultBooking);
        String bookingId = createResponse.jsonPath().getString("bookingid");

        //2.Obiekt z nowymi danymi

        JSONObject putbookingdates = new JSONObject();
        putbookingdates.put("checkin", "2022-01-01");
        putbookingdates.put("checkout", "2022-01-02");

        JSONObject putbooking = new JSONObject();
        putbooking.put("firstname", "Janek");
        putbooking.put("lastname", "Fioletowy");
        putbooking.put("totalprice", 10);
        putbooking.put("depositpaid", false);
        putbooking.put("additionalneeds", "Dinner");
        putbooking.put("bookingdates", putbookingdates);

        //3.Wysyłam put na booking
        Response putResponse = PutBookingRequest.putBooking(bookingId, putbooking, token);
        JsonPath jsonPath = putResponse.jsonPath();

        assertThat(jsonPath.getString("firstname")).isEqualTo("Janek");
        assertThat(jsonPath.getString("lastname")).isEqualTo("Fioletowy");

        System.out.println(putResponse.asString());

    }
}
