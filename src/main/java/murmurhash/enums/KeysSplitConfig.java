package murmurhash.enums;

import murmurhash.config.SplitCfg;

/**
 * All available keys for splitting. Add here required key and after
 * it init them values in the {@link SplitCfg}` static block
 *
 * @author y.glushenkov
 */
public enum  KeysSplitConfig {
    KEY_8_A("8A"),
    KEY_8_C("8C"),
    KEY_8_M("8M");

    private String key;

    KeysSplitConfig(String key) {
        this.key = key;
    }

    public String getValueOfKey() {
        return key;
    }
}
