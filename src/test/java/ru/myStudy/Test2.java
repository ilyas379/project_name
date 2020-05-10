package ru.myStudy;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

public class Test2 {
    @Test
    public void getTest() {
        get(EndPoints.MANUFACTURES).then().log().all().body("manufacturers.size()", is(3));
    }

    @Test
    public void getQueryParamTest() {
        given().queryParam("id", 1)
                .when()
                .get(EndPoints.MANUFACTURES).then().log().all().body("title", Matchers.equalTo("Mazda"));
    }

    @Test
    public void getPathParamTest() {
//        given().pathParam("id", "1")
                when().get(EndPoints.MANUFACTURE, "1")
                .then().log().all().body("title", Matchers.equalTo("Mazda"));
    }
}
