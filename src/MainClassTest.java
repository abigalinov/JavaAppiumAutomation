import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    String a = getClassString();

    @Test
    public void testGetClassString() {
        Assert.assertTrue("Doesn't contain a string", a.contains("Hello") || a.contains("hello"));
        System.out.println("Line is contained");
    }

}
