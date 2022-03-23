package test.request.booking;

import dto.BookingDto;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import request.booking.PostBookingRequest;

import static org.assertj.core.api.Assertions.*;

public class CreateBookingTest {
    @Test
    void createBooking() {

        final JSONObject booking = BookingDto.getDefaultBooking();
        Response response = PostBookingRequest.createBooking(booking);

        JsonPath json = response.jsonPath();
        assertThat(json.getString("booking.firstname")).isEqualTo("Jim");
        assertThat(json.getString("booking.lastname")).isEqualTo("Brown");

    }
}
