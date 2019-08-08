package murmmurhash;

import murmurhash.MurMurHashUDF;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static murmmurhash.MurMurHashTest.USER_ID;
import static murmurhash.enums.KeysSplitConfig.*;

/**
 * @author y.glushenkov
 */
public class MurMurHashUDFTest {
    @Test
    public void basicTestCorrectSpitKey1() {
        assertEquals(String.valueOf(8), new MurMurHashUDF().evaluate(USER_ID, KEY_8_M.getValueOfKey()));
    }
}
