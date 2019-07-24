package murmmurhash;

import murmurhash.MurMurHash;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static murmurhash.enums.KeysSplitConfig.*;

/**
 * @author y.glushenkov
 */
public class MurMurHashTest {
    public static final String USER_ID = "abcdefghijklmnopqrstuvwxyz";
    @Test
    public void basicTestCorrectSpitKey1() {
        assertEquals(8, new MurMurHash().getSplit(USER_ID, KEY_8_M).intValueExact());
    }

    @Test
    public void basicTestCorrectSpitKey2() {
        assertEquals(1, new MurMurHash().getSplit("a", KEY_8_C).intValueExact());
    }

    @Test
    public void basicTestCorrectSpitKey3() {
        assertEquals(3, new MurMurHash().getSplit("adfdsvdsvfdvf3489fhd7r3gcy834dybewdBYBDR(YVR$DVBGUCVBCUGDBEOBCD", KEY_8_M).intValueExact());
    }
}
