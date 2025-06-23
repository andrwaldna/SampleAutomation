package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestUtils {

    private static RequestSpecification requestSpec;

    // Set Base URI
    public static void setBaseURI(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    // Build request with headers and payload
    private static RequestSpecification buildRequest(Map<String, String> headers, Object payload) {
        requestSpec = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        if (headers != null) {
            requestSpec.headers(headers);
        }

        if (payload != null) {
            requestSpec.body(payload);
        }

        return requestSpec;
    }

    // GET request
    public static Response get(String endpoint, Map<String, String> headers) {
        return buildRequest(headers, null)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    // POST request
    public static Response doPostRequest(String endPoint, RequestSpecBuilder requestBuilt, ResponseSpecification responseSpecification) {
        RequestSpecification requestSpec = requestBuilt.build();
        createRequestLogs(requestSpec);
        Response response;

        response = RestAssured.given(requestSpec)
                .post()
                .peek();

        return response;
    }

    // PUT request
    public static Response put(String endpoint, Map<String, String> headers, Object payload) {
        return buildRequest(headers, payload)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    // DELETE request
    public static Response delete(String endpoint, Map<String, String> headers) {
        return buildRequest(headers, null)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    public static ResponseSpecification defaultResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    public static void createRequestLogs(RequestSpecification spec) {
        QueryableRequestSpecification querySpec = new SpecificationQuerier().query(spec);
        String baseURI = "baseURI : " + querySpec.getBaseUri();
        String basePath = "endpoint : " + querySpec.getBasePath();
        String contentType = "content type : " + querySpec.getContentType();
        String contentBody = "";
        if (querySpec.getContentType().contains("multipart")) {
            for (int i = 0; i < querySpec.getMultiPartParams().size(); i++) {
                String controlName = querySpec.getMultiPartParams().get(i).toString().split(",")[0];
                String content = querySpec.getMultiPartParams().get(i).toString().split(",")[4];
                contentBody = contentBody + controlName + "---------" + content + "\n";
            }
        } else
            contentBody = querySpec.getBody();
        String requestLogs = baseURI + "\n" + basePath + "\n" + contentType + "\n" + contentBody;
        System.out.println(requestLogs);
        System.out.println(querySpec.getMultiPartParams().size());
    }
}


