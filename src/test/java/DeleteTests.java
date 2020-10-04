import org.junit.Assert;
import org.junit.Test;


public class DeleteTests extends TestBase {

    //Positive test
    @Test
    public void deleteTriangleTest1() {
        String id = getTriangleId(3, 4, 5);
        getTriangleByID(id);
        Assert.assertTrue(getTriangleAll().extract().asString().contains(id));
        deleteTriangleByID(id);
        Assert.assertFalse(getTriangleAll().extract().asString().contains(id));
    }

    //Negative test
    @Test
    public void deleteNonExistingTriangleTest() {
        String id = getTriangleId(3, 4, 5);
        getTriangleByID(id);
        Assert.assertTrue(getTriangleAll().extract().asString().contains(id));
        deleteTriangleByID(id+1);
        Assert.assertTrue(getTriangleAll().extract().asString().contains(id));
    }



}