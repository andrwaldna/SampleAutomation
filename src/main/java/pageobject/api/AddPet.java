package pageobject.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONArray;
import utils.RestUtils;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class AddPet {

    private Response response;
    private final String baseUri = "https://petstore.swagger.io/v2";
    private final String endpoint = "/pet";


    //testing only if can copy current framework
    // Extracts the first row from the DataTable list
    private Map<String, String> separateTableDetails(List<Map<String, String>> list)
    {
        Map<String, String> map = new HashMap<>();
        for (Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            map = (Map<String, String>) iterator.next();
        }
        return map;
    }

    public Response postAddPetResponse(List<Map<String, String>> list) {
        Map<String, String> map = separateTableDetails(list);
        return RestUtils.doPostRequest(endpoint, this.postAddPetRequestSpec(map),responseSpec());
    }

    private RequestSpecBuilder postAddPetRequestSpec(Map<String, String> map) {
        RequestSpecBuilder requestSpec = new RequestSpecBuilder();
        requestSpec.setBaseUri(baseUri);
        requestSpec.setBasePath(endpoint);
        requestSpec.setContentType(ContentType.JSON);
        requestSpec.setAccept(ContentType.JSON);
        requestSpec.setBody(createPostAddPetBody(map).toString());
        return requestSpec;
    }

    private Map<String, Object> createPostAddPetBody(Map<String, String> map) {
        String name = map.get("name");
        String categoryName = map.get("categoryName");
        String tagsName = map.get("tagsName");
        String status = map.get("status");

        JSONObject details = new JSONObject();

        details.put("id", 0);
        System.out.println("Pet ID: 0");

        JSONObject category = new JSONObject();
        category.put("id", 0);
        category.put("name", categoryName);
        System.out.println("Category: " + category.toJSONString());
        details.put("category", category);

        details.put("name", name);
        System.out.println("Pet Name: " + name);

        JSONArray photoUrls = new JSONArray();
        photoUrls.add("string");
        details.put("photoUrls", photoUrls);
        System.out.println("Photo URLs: " + photoUrls.toJSONString());

        JSONArray tags = new JSONArray();
        JSONObject tag = new JSONObject();
        tag.put("id", 0);
        tag.put("name", tagsName);
        tags.add(tag);
        details.put("tags", tags);
        System.out.println("Tags: " + tags.toJSONString());

        details.put("status", status);
        System.out.println("Status: " + status);

        return details;
    }

    private ResponseSpecification responseSpec() {
        ResponseSpecBuilder resSpec = new ResponseSpecBuilder();
        resSpec.expectStatusCode(201);
        return resSpec.build();
    }

    public void validatePostAddPetStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        System.out.println("Expected Status Code: " + expectedStatusCode);
        System.out.println("Actual Status Code: " + actualStatusCode);

        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("Expected: " + expectedStatusCode + ", but got: " + actualStatusCode);
        }
    }
}
