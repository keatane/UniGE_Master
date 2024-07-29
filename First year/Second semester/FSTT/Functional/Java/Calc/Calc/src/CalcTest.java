import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class CalcTest {

    private static Calc c;
    
    @BeforeAll
    static void setUp() {
        c = new Calc();
    }

    @Test
    void testSub() {
        System.out.println("testSub");
        //assertEquals(0, c.sub(5, 2)); # gives failure
        // assertEquals(0, c.sub(5/0, 2)); # gives error
        assertEquals(3, c.sub(5, 2));
    }

    @Test
    void testSum() {
        System.out.println("testSum");
        assertEquals(7, c.sum(5, 2));
    }
}
