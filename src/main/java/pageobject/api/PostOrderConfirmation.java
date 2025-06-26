package pageobject.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PostOrderConfirmation {

    private final String baseUri = "https://api.example.com";
    private final String endpoint = "/v1/orders/confirm";

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

    public Response postOrderConfirmationResponse(List<Map<String, String>> list) {
        Map<String, String> map = separateTableDetails(list);
        return RestUtils.doPostRequest(endpoint, this.postOrderConfirmationRequestSpec(map),responseSpec());
    }

    private RequestSpecBuilder postOrderConfirmationRequestSpec(Map<String, String> map) {
        RequestSpecBuilder requestSpec = new RequestSpecBuilder();
        requestSpec.setBaseUri(baseUri);
        requestSpec.setBasePath(endpoint);
        requestSpec.setContentType(ContentType.JSON);
        requestSpec.setAccept(ContentType.JSON);
        requestSpec.setBody(createPostOrderConfirmationBody(map).toString());
        return requestSpec;
    }

    private Map<String, Object> createPostOrderConfirmationBody(Map<String, String> map) {
        String orderId = map.get("orderId");
        String userId = map.get("userId");
        String email = map.get("email");
        String name = map.get("name");
        String orderDate = map.get("orderDate");
        String totalAmount = map.get("totalAmount");
        String currency = map.get("currency");
        String productId = map.get("productId");
        String productName = map.get("productName");
        String quantity = map.get("quantity");
        String price = map.get("price");
        String line1 = map.get("line1");
        String line2 = map.get("line2");
        String city = map.get("city");
        String state = map.get("state");
        String postalCode = map.get("postalCode");
        String country = map.get("country");
        String sendEmail = map.get("sendEmail");


        JSONObject details = new JSONObject();

        //For orderId field
        details.put("orderId", orderId);

        // For user fields
        JSONObject user = new JSONObject();
        user.put("userId", userId);
        user.put("email", email);
        user.put("name", name);
        details.put("user", user);

        // For orderDetails fields
        JSONObject orderDetails = new JSONObject();
        orderDetails.put("orderDate", orderDate);
        orderDetails.put("totalAmount", Double.parseDouble(totalAmount));
        orderDetails.put("currency", currency);

        //For orderDetails-item fields
        // Split comma-separated values for items
        String[] productIds = map.get("productId").split(",");
        String[] productNames = map.get("productName").split(",");
        String[] quantities = map.get("quantity").split(",");
        String[] prices = map.get("price").split(",");

        JSONArray items = new JSONArray();

        // To ensure that all arrays are of the same length
        int itemCount = Math.min(Math.min(productIds.length, productNames.length),
                Math.min(quantities.length, prices.length));

        for (int i = 0; i < itemCount; i++) {
            JSONObject item = new JSONObject();
            item.put("productId", productIds[i].trim());
            item.put("productName", productNames[i].trim());
            item.put("quantity", Integer.parseInt(quantities[i].trim()));
            item.put("price", Double.parseDouble(prices[i].trim()));
            items.add(item);
        }

        orderDetails.put("items", items);
        details.put("orderDetails", orderDetails);

        // For shippingAddress fields
        JSONObject shippingAddress = new JSONObject();
        shippingAddress.put("line1", line1);
        shippingAddress.put("line2", line2);
        shippingAddress.put("city", city);
        shippingAddress.put("state", state);
        shippingAddress.put("postalCode", postalCode);
        shippingAddress.put("country", country);
        details.put("shippingAddress", shippingAddress);

        // For sendEmail fields
        details.put("sendEmail", Boolean.parseBoolean(sendEmail));

        // To wrap as Map<String, Object> to match return type
        Map<String, Object> response = new HashMap<>();
        response.put("body", details);

        return response;
    }

    private ResponseSpecification responseSpec() {
        ResponseSpecBuilder resSpec = new ResponseSpecBuilder();
        resSpec.expectStatusCode(201);
        return resSpec.build();
    }

    public void validatePostOrderConfirmationStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        System.out.println("Expected Status Code: " + expectedStatusCode);
        System.out.println("Actual Status Code: " + actualStatusCode);

        if (actualStatusCode != expectedStatusCode) {
            throw new AssertionError("Expected: " + expectedStatusCode + ", but got: " + actualStatusCode);
        }
    }
}
