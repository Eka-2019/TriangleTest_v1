import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.BeforeClass;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class TestBase {
    JSONObject bodyJson = new JSONObject();


    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://qa-quiz.natera.com/triangle";
        cleanUp();
    }

    public static RequestSpecification baseRequest() {
        RequestSpecification request = given().header("X-User", "027723fb-b0dd-48b1-a096-3c813217f4ed");
        return request;
    }


    public String baseTriangle(int a, int b, int c, String separator) {
        String input = a + separator + b + separator + c;
        bodyJson.put("separator", separator);
        bodyJson.put("input", input);
        return bodyJson.toString();
    }

    public String baseTriangle(int a, int b, int c) {
        String s = ";";
        String input = a + s + b + s + c;
        bodyJson.put("input", input);
        return bodyJson.toString();
    }


    public String baseTriangle(float a, float b, float c) {
        String s = ";";
        String input = a + s + b + s + c;
        bodyJson.put("input", input);
        return bodyJson.toString();
    }


    public String baseTriangle(String a, String b, String c) {
        String s = ";";
        String input = a + s + b + s + c;
        bodyJson.put("input", input);
        return bodyJson.toString();
    }

    public String baseTriangle(int a, float b, String c) {
        String s = ";";
        String input = a + s + b + s + c;
        bodyJson.put("input", input);
        return bodyJson.toString();
    }

    public ValidatableResponse baseBodyRequest(String baseInput) {
        RequestSpecification request = baseRequest()
                .header("Content-Type", "application/json")
                .body(baseInput);
        ValidatableResponse response = request.post().then();
        return response;
    }


    public String getTriangleId(int a, int b, int c) {
        return new JSONObject(baseBodyRequest(baseTriangle(a, b, c)).extract().asString()).getString("id");
    }


    public static ValidatableResponse getTriangleAll() {
        return baseRequest().get("/all").then();
    }

    public static ValidatableResponse getTriangleByID(String id) {
        return baseRequest().get("/" + id).then();
    }

    public static ValidatableResponse deleteTriangleByID(String id) {
        return baseRequest().delete("/" + id).then();

    }

    public static ValidatableResponse getTrianglePerimeter(String id) {
        return baseRequest().get("/" + id + "/perimeter").then();
    }

    public static ValidatableResponse getTriangleArea(String id) {
        return baseRequest().get("/" + id + "/area").then();
    }


    public class Triangle {
        String id;
        float firstSide, secondSide, thirdSide;
    }


    public static ValidatableResponse deleteTriangle(String id) {
        return baseRequest().delete("/" + id).then();
    }


    public static void cleanUp() {
        ValidatableResponse response = getTriangleAll();

        ArrayList<TestBase.Triangle> allTriangles = new Gson().fromJson(response.extract().asString(), new TypeToken<List<Triangle>>(){}.getType());

        for (TestBase.Triangle triangle : allTriangles) {
            deleteTriangle(triangle.id);
        }
    }

}
