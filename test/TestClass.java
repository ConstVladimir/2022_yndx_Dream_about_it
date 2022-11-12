import org.junit.jupiter.api.*;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestClass {

    private static Point example;
    private static Integer [] change_p;
    private static String result;
    private static List<String> intermediate_result;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        example = new Point(1,10,"k",0);
        change_p = new Integer[]{5, 7, 4, 7, 8, 7};
        result = "7 10 5 2 8 4 9 1 6 3";
        intermediate_result = List.of("10 5 8 4 9 2 1 6 3 7",
                "10 5 8 4 9 2 1 7 6 3",
                "10 5 8 2 4 9 1 7 6 3",
                "7 10 5 8 2 4 9 1 6 3",
                "7 10 5 2 8 4 9 1 6 3",
                "7 10 5 2 8 4 9 1 6 3");

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testMethod() {
        Iterator<String> interm= intermediate_result.iterator();
        for (Integer it: change_p){
            example.change(it);
            assertEquals(interm.next(), example.toString());
        }
        assertEquals(result, example.toString());
    }
}
