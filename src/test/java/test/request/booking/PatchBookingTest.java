package test.request.booking;

import dto.AuthDto;
import dto.BookingDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import request.auth.PostAuthRequest;
import request.booking.PatchBookingRequest;
import request.booking.PostBookingRequest;

import static org.assertj.core.api.Assertions.*;

public class PatchBookingTest {

    private static String token;

    @BeforeAll
    static void setup() {
        JSONObject defaultAuth = AuthDto.getDefaultAuth();
        token = PostAuthRequest.createToken(defaultAuth);
        System.out.println("TOKEN: " + token);
    }

    @Test
    void PartialUpdateBookingTest() {
        //1.Tworzę nowy booking
        JSONObject defaultBooking = BookingDto.getDefaultBooking();
        Response createResponse = PostBookingRequest.createBooking(defaultBooking);
        String bookingId = createResponse.jsonPath().getString("bookingid");

        //2.Obiekt z nowymi danymi
        JSONObject patchBooking = new JSONObject();
        patchBooking.put("firstname", "Tomek");
        patchBooking.put("lastname", "Czarny");

        //3.Wysyłam patch na booking
        Response patchResponse = PatchBookingRequest.patchBooking(bookingId, patchBooking, token);
        JsonPath jsonPath = patchResponse.jsonPath();

        assertThat(jsonPath.getString("firstname")).isEqualTo("Tomek");
        assertThat(jsonPath.getString("lastname")).isEqualTo("Czarny");

        System.out.println(patchResponse.asString());

    }
}
