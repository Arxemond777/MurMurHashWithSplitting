package murmurhash;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.List;
import java.util.stream.Collectors;

@Description(
        name = "murMurHashUDFList",
        value = "_FUNC_(str, str) - Converts a userId&splitCfgKey by the murmur hash function algorithm",
        extended = "Example:\n" +
                "  > SELECT murMurHashUDFList(userId, splitCfgKey) FROM users u;"
)
public class MurMurHashUDFList extends UDF {

    private final MurMurHash murMurHashInst;

    public MurMurHashUDFList() {
        this.murMurHashInst = new MurMurHash();
    }

    public List<String> evaluate(final List<String> userIdList, final String splitCfgKey) {
        return userIdList.stream()
                .map(userId -> String.valueOf(murMurHashInst.getSplit(userId, splitCfgKey)))
                .collect(Collectors.toList());
    }
}
