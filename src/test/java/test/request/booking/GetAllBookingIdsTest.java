package test.request.booking;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import request.booking.GetBookingIdsRequest;

import static org.assertj.core.api.Assertions.*;

public class GetAllBookingIdsTest {
    @Test
    void getBookingIdsTest(){

        Response response = GetBookingIdsRequest.getAllBookingIds();
        JsonPath json = response.jsonPath();
        assertThat(json.getList("booking").size()).isPositive();
    }
}
