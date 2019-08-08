package murmurhash;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Description(
        name = "murMurHashUDFStringArr",
        value = "_FUNC_(str, str) - Converts a userId&splitCfgKey by the murmur hash function algorithm",
        extended = "Example:\n" +
                "  > SELECT murMurHashUDFStringArr(userId, splitCfgKey) FROM users u;"
)
public class MurMurHashUDFStringArr extends UDF {

    private final MurMurHash murMurHashInst;

    public MurMurHashUDFStringArr() {
        this.murMurHashInst = new MurMurHash();
    }

    public String[] evaluate(final String[] userIdArr, final String splitCfgKey) {
        return Stream.of(userIdArr)
                .map(userId -> String.valueOf(murMurHashInst.getSplit(userId, splitCfgKey)))
                .toArray(String[]::new);
    }
}
