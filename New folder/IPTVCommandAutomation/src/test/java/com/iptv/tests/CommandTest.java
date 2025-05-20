package com.iptv.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommandTest {

    @Test
    public void testSendScrollTextCommand() {

        String payload = """
        {
            "subscriberCodes": ["NAG43145"],
            "command": "SCROLL_TEXT",
            "dataMap": {
                "duration": "220",
                "channel": "6674",
                "displayMacId": "true",
                "message": "Scroll Text testing with PARTNER command type"
            },
            "expiryDate": "21-05-2025 12:10:23",
            "module": "DRM",
            "isScheduled": false,
            "startDate": null,
            "endDate": null,
            "intervalInMinutes": 0
        }
        """;

        given()
            .baseUri("http://localhost:8080")
            .basePath("/newdrm/rest/iptv/tpCmdManager/newCommand")
            .header("username", "admin")
            .header("apikey", "0276d666-3593-40ae-b2fb-18deb8c54255")
            .contentType(ContentType.JSON)
            .body(payload)
            .log().all() // logs request
        .when()
            .post()
        .then()
            .log().all() // logs response
            .statusCode(200)
            .body("status", equalTo(true)); // ✅ CORRECT ASSERTION
    }
}
