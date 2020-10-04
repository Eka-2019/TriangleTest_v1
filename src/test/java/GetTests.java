
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetTests extends TestBase {

    // Positive tests
    @Test
    public void getTriangleTest() {
        String id = getTriangleId(4, 5, 7);
        getTriangleByID(id)
                .statusCode(200)
                .body("firstSide", equalTo(4.0f))
                .body("secondSide", equalTo(5.0f))
                .body("thirdSide", equalTo(7.0f));
        getTriangleArea(id)
                .statusCode(200)
                .body("result", equalTo(9.797958971132712f));
        getTrianglePerimeter(id)
                .statusCode(200)
                .body("result", equalTo(16.0f));
    }


    @Test
    public void getAllTriangleTest() {
        String id = getTriangleId(4, 5, 7);
        String id1 = getTriangleId(5, 6, 8);
        Assert.assertTrue(getTriangleAll().extract().asString().contains(id));
        Assert.assertTrue(getTriangleAll().extract().asString().contains(id1));

    }

}
