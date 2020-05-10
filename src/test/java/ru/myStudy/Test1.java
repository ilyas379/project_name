//package ru.myStudy;
//
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.config.LogConfig;
//import io.restassured.config.RestAssuredConfig;
//import io.restassured.filter.log.LogDetail;
//import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//import org.hamcrest.Matchers;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.not;
//
//public class Test1 {
//    @Test
//    public void test1() {
//        RestAssured.requestSpecification = new RequestSpecBuilder()
//                .setAccept(ContentType.XML)
//                .build();
//        RestAssured.baseURI = "http://localhost";
//        RestAssured.port = 8080;
//
//        RestAssured.responseSpecification = new ResponseSpecBuilder()
//                .expectStatusCode(201)
//                .build();
//
//        RestAssuredConfig restAssuredConfig = RestAssured
//                .config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL));
//
//        RestAssured.config = restAssuredConfig;
//
//        given()
//                .when().get("/car/manufacturers?id=1")
//                .then();
//    }
//}
