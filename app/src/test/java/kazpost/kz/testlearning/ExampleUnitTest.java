package kazpost.kz.testlearning;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        int x = 3;
        assertThat("x is not 3", x, is(3));
    }

    @Test
    public void addition_isNotCorrect() throws Exception {
        assertEquals("Numbers aren't equal", 5, 2 + 2);
    }

    @Test(expected = NullPointerException.class)
    public void nullStringTest() {
        String str = null;
        assertTrue(str.isEmpty());
    }

    @Test(timeout = 1000)
    public void requestTest() {
        try {
            Thread.sleep(900);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }



}