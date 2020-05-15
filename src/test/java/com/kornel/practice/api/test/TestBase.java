package com.kornel.practice.api.test;

import static com.kornel.practice.api.helper.SpotifyAuthHelper.generateAccessToken;
import static com.kornel.practice.api.helper.SpotifyAuthHelper.getAuthToken;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    protected static String accessToken;

    static {
        try {
            RestAssured.filters(new AllureRestAssured());
            Properties properties = new Properties();
            properties.load(TestBase.class.getClassLoader().getResourceAsStream("spotifyauth.properties"));
            accessToken = generateAccessToken(getAuthToken(properties.getProperty("client.id"), properties.getProperty("client.secret")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
