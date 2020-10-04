import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PostSeparatorTests extends TestBase {

    //Positive tests
    @Test //duplicat from side tests, but if we are going to run sides and separator tests separately then it can make sense.
    public void defaultSeparatorTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(3, 4, 5));
        response
                .statusCode(200)
                .body("firstSide", equalTo(3.0f))
                .body("secondSide", equalTo(4.0f))
                .body("thirdSide", equalTo(5.0f));
    }


    @Test
    public void emptySeparatorTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 7, " "));
        response
                .statusCode(200)
                .body("firstSide", equalTo(4.0f))
                .body("secondSide", equalTo(5.0f))
                .body("thirdSide", equalTo(7.0f));
    }

    @Test
    public void letterSeparatorTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 7, "P"));
        response
                .statusCode(200)
                .body("firstSide", equalTo(4.0f))
                .body("secondSide", equalTo(5.0f))
                .body("thirdSide", equalTo(7.0f));
    }


    @Test
    public void anotherSeparatorTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 7, "\"+")); //can be .(dot), ,(comma), #, -, =, &, @, !, :
        response
                .statusCode(200)
                .body("firstSide", equalTo(4.0f))
                .body("secondSide", equalTo(5.0f))
                .body("thirdSide", equalTo(7.0f));
    }

    //Negative tests
    @Test
    public void noSeparatorTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 7, ""));
        response
                .statusCode(422)
                .body("error", equalTo("Unprocessable Entity"))
                .body("message", equalTo("Cannot process input"));
    }

    @Test
    public void forbidenSymbolSeparatorTest() {
        ValidatableResponse response = baseBodyRequest(baseTriangle(4, 5, 7, "*"));  //??
        System.out.println(response. extract().asString());
        response
                .statusCode(500)
                .body("error", equalTo("Internal Server Error"))
                .body("message", containsString("Dangling meta character"));
    }

}
