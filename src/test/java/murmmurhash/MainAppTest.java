package murmmurhash;

import murmurhash.MurMurHashMain;
import org.junit.Test;

import static murmurhash.enums.KeysSplitConfig.KEY_8_A;
import static murmurhash.validate.ValidateSpringCfg.validateSplitCfgKey;

/**
 * Base tests for the app
 *
 * @author y.glushenkov
 */
public class MainAppTest {

    @Test
    public void basicTestCorrectSpitKey() {
        String[] args = new String[]{"aaa", KEY_8_A.getValueOfKey()};
        MurMurHashMain.main(args);
    }

    @Test(expected = murmurhash.exceptions.NoSuchSplitKeyException.class)
    public void basicTestIncorrectSpitKey() {
            validateSplitCfgKey("abc");
        }
    }
