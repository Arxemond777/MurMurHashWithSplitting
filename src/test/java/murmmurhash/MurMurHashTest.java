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
    public static final String USER_ID_1 = "5a2ce01f27a9a1b602e2d1834";
    @Test
    public void basicTestCorrectSpitKey1() {
        assertEquals(8, new MurMurHash().getSplitBigInteger(USER_ID, KEY_8_M).intValueExact());
    }

    @Test
    public void basicTestCorrectSpitKey2() {
        assertEquals(1, new MurMurHash().getSplitBigInteger("a", KEY_8_C).intValueExact());
    }

    @Test
    public void basicTestCorrectSpitKey3() {
        assertEquals(3, new MurMurHash().getSplitBigInteger("adfdsvdsvfdvf3489fhd7r3gcy834dybewdBYBDR(YVR$DVBGUCVBCUGDBEOBCD", KEY_8_M).intValueExact());
    }

    @Test
    public void basicTestCorrectSpitKey4() {
        assertEquals(2, new MurMurHash().getSplitBigInteger(USER_ID_1, KEY_8_A).intValueExact());
        assertEquals(7, new MurMurHash().getSplitBigInteger(USER_ID_1, KEY_8_C).intValueExact());
        assertEquals(4, new MurMurHash().getSplitBigInteger(USER_ID_1, KEY_8_M).intValueExact());
        assertEquals(7, new MurMurHash().getSplitBigInteger("", KEY_8_C).intValueExact());
    }
}
