import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    
    @Test
    public void testGetClassNumber() {
        int b = 45;
        int a = getClassNumber();
        Assert.assertTrue("a <= 45", a > b);
        System.out.println("Test OK");
    }
}
