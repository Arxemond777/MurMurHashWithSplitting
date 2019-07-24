package murmurhash.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static murmurhash.enums.KeysSplitConfig.*;
import static murmurhash.enums.SplitValuesName.*;

/**
 * Create and init spitCfg
 *
 * @author y.glushenkov
 */
public class SplitCfg {
    private static final Map<String, Map<String, ?>> splitCfg = new HashMap<>();

    /**
     * Init splitCfg
     */
    static {
        Map<String, Object> map8a = new HashMap<>();
        map8a.put(SPLITS.getValueName(), 8);
        map8a.put(SALT.getValueName(), "a8");
        map8a.put(SEED.getValueName(), "e17a1465"); // 0xe17a1465
        splitCfg.put(KEY_8_A.getValueOfKey(), map8a);

        Map<String, Object> map8c = new HashMap<>(map8a);
        map8c.put(SALT.getValueName(), "22");
        splitCfg.put(KEY_8_C.getValueOfKey(), map8c);

        Map<String, Object> map8m = new HashMap<>(map8a);
        map8m.put(SALT.getValueName(), "122");
        splitCfg.put(KEY_8_M.getValueOfKey(), map8m);
    }

    public static ArrayList<String> getSplitCfgKey() {
        return new ArrayList<>(getSplitCfg().keySet());
    }

    public static Map<String, Map<String, ?>> getSplitCfg() {
        return new HashMap<>(splitCfg);
    }
}
