package murmurhash;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(
        name = "murMurHashUDF",
        value = "_FUNC_(str, str) - Converts a userId&splitCfgKey by the murmur hash function algorithm",
        extended = "Example:\n" +
                "  > SELECT murMurHashUDF(userId, splitCfgKey) FROM users u;"
)
public class MurMurHashUDF extends UDF {

    private final MurMurHash murMurHashInst;

    public MurMurHashUDF() {
        this.murMurHashInst = new MurMurHash();
    }

    public String evaluate(final String userId, final String splitCfgKey) {

        return String.valueOf(murMurHashInst.getSplit(userId, splitCfgKey));
    }
}
