package dto; //data transfer object

import org.json.JSONObject;

public class BookingDto {

    public static JSONObject getDefaultBooking() {
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");

        JSONObject booking = new JSONObject();
        booking.put("firstname", "Jim");
        booking.put("lastname", "Brown");
        booking.put("totalprice", 1000);
        booking.put("depositpaid", true);
        booking.put("additionalneeds", "Breakfast");
        booking.put("bookingdates", bookingdates);
        return booking;
    }
}