package ru.myStudy;

import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.config.JsonPathConfig;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ru.myStudy.pojo.Manufacturer;
import ru.myStudy.pojo.Models;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Test3 {

    @Test
    public void getTest() {
        get(EndPoints.MANUFACTURES).then().log().all().body("manufacturers.size()", is(4));
    }

    @Test
    public void getQueryParamTest() {
        given().queryParam("id", 1)
                .when()
                .get(EndPoints.MANUFACTURES)
                .then().log().all().body("title", equalTo("Mazda"));
    }

    @Test
    public void getPathParamTest() {
        given().pathParam("id", 1)
                .when()
                .get(EndPoints.MANUFACTURE).then().log().all().body("title", equalTo("Mazda"));
    }

    @Test
    public void getPathParamTest1() {
//        given().pathParam("id", 1)
        when().get(EndPoints.MANUFACTURE, "1")
                .then().log().all().body("title", equalTo("Mazda"));
    }

    @Test
    public void postTest() {
        String body = "{\n" +
                "  \"country\": \"Russia\",\n" +
                "  \"models\": [\n" +
                "    {\n" +
                "      \"averagePrice\": 600.000,\n" +
                "      \"title\": \"Lada Vesta\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"title\": \"VAZ\"\n" +
                "}";
        given().contentType(ContentType.JSON).body(body)
                .when().post(EndPoints.MANUFACTURES)
                .then().log().all().statusCode(201);
    }

    @Test
    public void putTest() {
        String body = "{\n" +
                "  \"id\": \"30\",\n" +
                "  \"country\": \"Russia\",\n" +
                "  \"models\": [\n" +
                "    {\n" +
                "      \"averagePrice\": 600.000,\n" +
                "      \"title\": \"Lada Vesta\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"averagePrice\": 400.000,\n" +
                "      \"title\": \"Lada Kalina\"\n" +
                "    },\n" +
                " {\n" +
                "      \"averagePrice\": 500.000,\n" +
                "      \"title\": \"Niva 4x4\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"title\": \"VAZ\"\n" +
                "}";
        given().contentType(ContentType.JSON).body(body)
                .when().put(EndPoints.MANUFACTURES)
                .then().log().all().body(equalTo("{success}")).statusCode(200);
    }

    @Test
    public void deleteTest() {
        given().when().delete(EndPoints.MANUFACTURE, "30")
                .then().log().all().body(equalTo("{success}")).statusCode(200);
    }

    @Test
    public void getBodyAsString() {
        String body = given().get(EndPoints.MANUFACTURES)
                .getBody().asString();
        System.out.println(body);
    }

    @Test
    public void getBody() {
        String body = given().get(EndPoints.MANUFACTURES)
                .then().body("manufacturers.size()", is(3)).contentType(ContentType.JSON)
                .and()
                .extract().body().asString();
        System.out.println(body);
    }

    @Test
    public void getBodyJsonPath() {
        String title = given().get(EndPoints.MANUFACTURES)
                .getBody().jsonPath().get("manufacturers[0].country");
        System.out.println(title);
    }

    @Test
    public void getSecured() {
        RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
        String sessionId1 = given().auth().basic("user", "password")
                .get(EndPoints.SECURED)
                .then().statusCode(200)
                .and()
                .extract().sessionId();
        System.out.println(sessionId1);
    }

    @Test
    public void getPojo() {
        Manufacturer manufacturer = given()
                .get(EndPoints.MANUFACTURES).jsonPath().getObject("manufacturers[-1]", Manufacturer.class);
        System.out.println(manufacturer);
    }

    @Test
    public void postPOJO() {
        Models[] models = new Models[2];
        models[0] = new Models(null, new BigDecimal(600.000), "Lada Vesta");
        models[1] = new Models(null, new BigDecimal(500.000), "Lada 4x4");
        Manufacturer manufacturer = new Manufacturer(models, null, "VAZ", "Russia");
        given().contentType(ContentType.JSON).log().all().body(manufacturer).post(EndPoints.MANUFACTURES)
                .then().statusCode(201).log().all();

    }

    @Test
    public void responceTime() {
        responseSpecification = expect().time(lessThan(3000L));
        given().get(EndPoints.MANUFACTURES);
    }

    @Test
    public void gpathFind() {
        Object toyota = get(EndPoints.MANUFACTURES).then().log().all().extract().path("manufacturers.find { manufacturer -> manufacturer.title =~ 'Toyota'}");
        System.out.println(toyota);
    }

    @Test
    public void gpathFindAll() {
        Object toyota = get(EndPoints.MANUFACTURES).then().log().all().extract()
                .path("manufacturers.find { manufacturer -> manufacturer.title =~ 'Toyota'}.models.findAll { it.averagePrice > 2_000_000 }.title.collect {it.toUpperCase()}");
        System.out.println(toyota);
    }
    @Test
    public void gpathMinMax() {
        Object max = get(EndPoints.MANUFACTURES).then().log().all().extract()
                .path("manufacturers.find { manufacturer -> manufacturer.title =~ 'Toyota'}.models.max {it.averagePrice}.title");
        System.out.println(max);
        System.out.println("--------------------");
        Object min = get(EndPoints.MANUFACTURES).then().log().all().extract()
                .path("manufacturers.find { manufacturer -> manufacturer.title =~ 'Toyota'}.models.min {it.averagePrice}.title");
        System.out.println(min);
    }

    @Test
    public void gsonPathBigDecimal() {
        Object sum = given()
        .config(config().
                jsonConfig(JsonConfig.jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)))
        .get(EndPoints.MANUFACTURES).then().log().all().extract()
                .path("manufacturers.find { manufacturer -> manufacturer.title =~ 'Toyota'}.models.collect { it.averagePrice }.sum()");
        System.out.println(sum instanceof BigDecimal);
        System.out.println(sum);
    }
}
