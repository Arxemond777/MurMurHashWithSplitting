package murmurhash.enums;

/**
 * @author y.glushenkov
 */
public enum  SplitValuesName {
    SPLITS("splits"),
    SALT("salt"),
    SEED("seed");

    private final String valueName;

    SplitValuesName(final String valueName) {
        this.valueName = valueName;
    }

    public String getValueName() {
        return valueName;
    }
}
