package com.kornel.practice.api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class FirstTest extends TestBase {

    @Test
    void queryForEdSheeranShouldReturnEdSheeranAsTheFirstItem() {
        given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1/")
                .auth()
                .oauth2(accessToken)
                .accept(ContentType.JSON)
                .queryParam("q", "Ed Sheeran")
                .queryParam("type", "artist")
                .when()
                .get("search")
                .then()
                .log()
                .body()
                .statusCode(200)
                .body("artists.items.name[0]", equalTo("Ed Sheeran"))
                .extract()
                .response();
    }

    @Test
    void artistShouldBeEdSheeranForId(){
        given()
                .baseUri("https://api.spotify.com")
                .basePath("/v1/")
                .auth()
                .oauth2(accessToken)
                .accept(ContentType.JSON)
                .pathParam("id", "6eUKZXaKkcviH0Ku9w2n3V")
                .when()
                .get("artists/{id}")
                .then()
                .log()
                .body()
                .statusCode(200)
                .body("name", equalTo("Ed Sheeran"))
                .extract()
                .response();
    }
}
