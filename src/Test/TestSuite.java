package Test;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ProductStockTest.class
})
@IncludeTags("sanity")
public class TestSuite {
}
