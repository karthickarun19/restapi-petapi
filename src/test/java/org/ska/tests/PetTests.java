package org.ska.tests;

import io.restassured.http.ContentType;
import org.ska.tests.classes.StatusEnum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.ska.tests.Constants.BASE_URL;
import static org.ska.tests.actions.pets.PetsActions.PET_ENDPOINT;


public class PetTests {

    @Test
    public void getPetsByStatus() {
        given()
                .baseUri(BASE_URL)
                .log().everything()
                .contentType(ContentType.JSON)
                .queryParam("status", StatusEnum.available.toString())
        .when()
                .get(PET_ENDPOINT + "/findByStatus")
        .then()
                .extract()
                .body()
                .jsonPath()
                .prettyPrint();
    }

    @Test
    public void getPetById() {
        given()
                .baseUri(BASE_URL)
                .log().everything()
                .contentType(ContentType.JSON)
                .pathParam("petId", "499278344")
                .get(PET_ENDPOINT + "/{petId}")
                .getBody()
                .prettyPrint();
    }
}
