package org.ska.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.ska.tests.classes.StatusEnum;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.ska.tests.actions.pets.PetsActions.PET_ENDPOINT;


public class PetClassWithRequestSpec {
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(Constants.BASE_URL)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL).build();

    @Test
    public void getPetsByStatus() {
        given(requestSpecification)
                .queryParam("status", StatusEnum.available.toString())
                .get(PET_ENDPOINT + "/findByStatus")
                .then().extract().body().jsonPath().prettyPrint();
    }

    @Test
    public void getPetById() {
        given(requestSpecification)
                .pathParam("petId", "499278344")
                .get(PET_ENDPOINT + "/{petId}")
                .then().extract().body().jsonPath().prettyPrint();
    }
}
