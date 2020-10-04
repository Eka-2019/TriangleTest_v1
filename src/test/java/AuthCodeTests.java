import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AuthCodeTests extends TestBase {

    @Test
    //duplicat from side tests, but if we are going to run sides and authCode tests separately then it can make sense.
    public void code200Test() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 7));
        System.out.println(response.extract().asString());
        response
                .statusCode(200);
    }

    @Test
    public void code401Test() {
        RequestSpecification request = given()
                .header("Content-Type", "application/json");
        ValidatableResponse response = request.post().then();
        response
                .statusCode(401);
    }

    @Test
    public void code404Test() {
        String id = getTriangleId(4, 5, 7);
        getTriangleByID(id);
        deleteTriangleByID(id);
        getTriangleByID(id)
                .statusCode(404)
                .body("error", equalTo("Not Found"))
                .body("message", equalTo("Not Found"));

    }


    @Test
    public void code422Test() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 7, "\"*"));
        System.out.println(response.extract().asString());
        response
                .statusCode(422);
    }

    @Test
    public void code500Test() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 7, "*"));
        System.out.println(response.extract().asString());
        response
                .statusCode(500);
    }


}
