import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostSidesTests.class,
        PostSeparatorTests.class,
        GetTests.class,
        DeleteTests.class,
        AuthCodeTests.class
})

public class TestSuite {
}
