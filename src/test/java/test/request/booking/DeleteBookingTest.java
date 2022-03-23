package test.request.booking;

import dto.AuthDto;
import dto.BookingDto;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import request.auth.PostAuthRequest;
import request.booking.DeleteBookingRequest;
import request.booking.PostBookingRequest;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteBookingTest {
    private static String token;

    @BeforeAll
    static void setup() {
        JSONObject defaultAuth = AuthDto.getDefaultAuth();
        token = PostAuthRequest.createToken(defaultAuth);
        System.out.println("TOKEN: " + token);
    }

    @Test
    void DeleteBookingTest() {
        //1.Tworzę nowy booking
        JSONObject defaultBooking = BookingDto.getDefaultBooking();
        Response createResponse = PostBookingRequest.createBooking(defaultBooking);
        String bookingId = createResponse.jsonPath().getString("bookingid");

        //2.Wysyłam delete na booking
        Response deleteResponse = DeleteBookingRequest.deleteBooking(bookingId, token);

        assertThat(deleteResponse.getStatusCode()).isEqualTo(201);

    }
}
