package utils;

import org.junit.Assert;
import org.junit.Test;

public class MyMathTest {

    @Test
    public void testModPow() throws Exception {
        Assert.assertEquals(3, MyMath.modPow(24, 1, 7));
        Assert.assertEquals(3, MyMath.modPow(24, 1123, 7));
        Assert.assertEquals(1398, MyMath.modPow(24, 2341, 2434));
        Assert.assertEquals(1048, MyMath.modPow(524, 2341, 2434));
    }
}