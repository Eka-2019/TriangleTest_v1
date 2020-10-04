import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class PostSidesTests extends TestBase {


    // Positive tests
    @Test
    public void normalTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(3, 4, 5));
        response
                .statusCode(200)
                .body("firstSide", equalTo(3.0f))
                .body("secondSide", equalTo(4.0f))
                .body("thirdSide", equalTo(5.0f));
    }

    @Test
    public void normalFloatTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(0.2f, 0.2f, 0.2f));
        response
                .statusCode(200)
                .body("firstSide", equalTo(0.2f))
                .body("secondSide", equalTo(0.2f))
                .body("thirdSide", equalTo(0.2f));
    }

    @Test
    public void normalStringTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle("3", "4", "5"));
        response
                .statusCode(200)
                .body("firstSide", equalTo(3.0f))
                .body("secondSide", equalTo(4.0f))
                .body("thirdSide", equalTo(5.0f));
    }

    @Test
    public void normalCombineTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(5, 2.5f, "4"));
        response
                .statusCode(200)
                .body("firstSide", equalTo(5.0f))
                .body("secondSide", equalTo(2.5f))
                .body("thirdSide", equalTo(4.0f));
    }


    // Negative tests
    @Test
    public void negativeSideTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(-3, 4, 5));
        response
                .statusCode(422)
                .body("error", equalTo("Unprocessable Entity"))
                .body("message", equalTo("Cannot process input"));

    }

    @Test
    public void zeroSideTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(0, 4, 5));
        response
                .statusCode(422)
                .body("error", equalTo("Unprocessable Entity"))
                .body("message", equalTo("Cannot process input"));
    }

    @Test
    public void letterSideTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(5, 2.5f, "A"));
        response
                .statusCode(422)
                .body("error", equalTo("Unprocessable Entity"))
                .body("message", equalTo("Cannot process input"));
    }

    @Test
    public void symbolSideTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(5, 2.5f, "#"));
        response
                .statusCode(422)
                .body("error", equalTo("Unprocessable Entity"))
                .body("message", equalTo("Cannot process input"));
    }

    @Test
    public void lineTriangleTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 9));
        response
                .statusCode(422)
                .body("error", equalTo("Unprocessable Entity"))
                .body("message", equalTo("Cannot process input"));
    }

    @Test
    public void impossibleTriangeTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(3, 4, 7.1f));
        response
                .statusCode(422)
                .body("error", equalTo("Unprocessable Entity"))
                .body("message", equalTo("Cannot process input"));
    }


}
