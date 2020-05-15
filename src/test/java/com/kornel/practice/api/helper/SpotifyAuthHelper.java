package com.kornel.practice.api.helper;

import static io.restassured.RestAssured.given;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.codec.binary.Base64;

public class SpotifyAuthHelper {

    public static String getAuthToken(String clientid, String clientSecret) {
        return Stream.of(clientid + ":" + clientSecret)
                .map(String::getBytes)
                .map(Base64::encodeBase64)
                .map(String::new)
                .collect(Collectors.joining());
    }

    public static String generateAccessToken(String authToken) {
        return given()
                .baseUri("https://accounts.spotify.com/api")
                .header("Authorization", "Basic " + authToken)
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .log().all()
                .when()
                .post("token")
                .jsonPath()
                .get("access_token");
    }
}
