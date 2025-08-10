package com.example.testinterview;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITest {
	 public static void main(String[] args) {

	        // Base URI for CoinGecko API
	        RestAssured.baseURI = "https://api.coingecko.com/api/v3";

	        // Send GET request to bitcoin endpoint
	        Response response = 
	            given()
	            .when()
	                .get("/coins/bitcoin")
	            .then()
	                .statusCode(200) // Validate HTTP 200
	                .body("market_data.current_price.usd", notNullValue())
	                .body("market_data.current_price.gbp", notNullValue())
	                .body("market_data.current_price.eur", notNullValue())
	                .body("market_data.market_cap", notNullValue())
	                .body("market_data.total_volume", notNullValue())
	                .body("market_data.price_change_percentage_24h", notNullValue())
	                .body("links.homepage[0]", not(isEmptyOrNullString()))
	                .extract().response();

	        // Print full response
	        System.out.println(response.asPrettyString());
	    }
}
