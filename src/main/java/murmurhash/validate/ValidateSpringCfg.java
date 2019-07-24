package murmurhash.validate;

import murmurhash.enums.KeysSplitConfig;
import murmurhash.exceptions.NoSuchSplitKeyException;

import java.util.stream.Stream;

import static murmurhash.config.SplitCfg.getSplitCfgKey;

public class ValidateSpringCfg {

    /**
     * Does it exist a splitCfgKey in the enum {@link KeysSplitConfig}
     *
     * @param splitCfgKey - key for search in {@link KeysSplitConfig}
     * @throws NoSuchSplitKeyException - if there isn`t such key in {@link KeysSplitConfig}
     */
    public static void validateSplitCfgKey(String splitCfgKey) {
        if (Stream.of(KeysSplitConfig.values()).noneMatch(v -> v.getValueOfKey().equals(splitCfgKey)))
            throw new NoSuchSplitKeyException("ERROR: incorrect splitCfgKey. Try some are the follow: " + getSplitCfgKey());
    }
}
