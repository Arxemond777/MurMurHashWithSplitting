package murmurhash;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Description(
        name = "murMurHashUDFVarargs",
        value = "_FUNC_(str, str...) - Converts a userIdArr&splitCfgKey by the murmur hash function algorithm",
        extended = "Example:\n" +
                "  > SELECT murMurHashUDFVarargs(splitCfgKey, userId1, userId2) FROM users u;"
)
public class MurMurHashUDFVarargs extends UDF {

    private final MurMurHash murMurHashInst;

    public MurMurHashUDFVarargs() {
        this.murMurHashInst = new MurMurHash();
    }

    public List<String> evaluate(final String splitCfgKey, final String... userIdArr) {
        return Stream.of(userIdArr)
                .map(userId -> String.valueOf(murMurHashInst.getSplit(userId, splitCfgKey)))
                .collect(Collectors.toList());
    }
}
