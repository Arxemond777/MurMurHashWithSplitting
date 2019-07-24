package murmurhash;

import java.util.ArrayList;

import static murmurhash.config.SplitCfg.getSplitCfgKey;

/**
 * Implementation of the murmur hash for hadoop with splitting
 *
 * @author y.glushenkov
 */
public class MurMurHashMain {

    public static void main(String[] args) {

        final ArrayList<String> list = getSplitCfgKey();

        if (args.length != 2) {
            String exampleKey = "";

            if (!list.isEmpty())
                exampleKey = list.get(0);
            else {
                System.err.println("ERROR: splitCfg is empty");
                System.exit(1);
            }

            System.err.println("ERROR: Args length myst contains 2 params. For example: java -jar target/murmurhash-1.0-SNAPSHOT.jar userId " + exampleKey);
            System.exit(1);
        }

        final String userId = args[0];
        final String splitCfgKey = args[1];

        new MurMurHash().getSplit(userId, splitCfgKey);
    }
}
