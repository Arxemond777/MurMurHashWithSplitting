package murmurhash;

import murmurhash.config.SplitCfg;
import murmurhash.enums.KeysSplitConfig;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static murmurhash.config.SplitCfg.getSplitCfg;
import static murmurhash.enums.SplitValuesName.*;
import static murmurhash.validate.ValidateSpringCfg.validateSplitCfgKey;

/**
 * A MurMur hash with splitting
 *
 * @author y.glushenkov
 */
public class MurMurHash {

    private static final BigInteger M = new BigInteger("c6a4a7935bd1e995", 16); // tht decimal representation of the hex-decimal 0xc6a4a7935bd1e995
    private static final BigInteger INT_MAX = new BigInteger("18446744073709551615");
    private static final int R = 47;
    private static final int EIGHT = 8;

    /**
     * A MurMur hash with splitting
     *
     * @param strForMurmurHash - str for the murmur hash
     * @param sp               - a concrete split chosen from {@link SplitCfg#splitCfg}
     * @return - murmurhash value
     */
    private BigInteger murmurSplit(final String strForMurmurHash, Map<String, ?> sp) {
        final String strForMurmurHashSalt = strForMurmurHash + sp.get(SALT.getValueName());
        final int length = strForMurmurHashSalt.length();

        final long seed = Long.parseLong(sp.get(SEED.getValueName()).toString(), 16);
        BigInteger h = BigInteger.valueOf(seed) // seed ^ (length * m) & intmax
                .xor(M.multiply(new BigInteger(Integer.toString(length))))
                .and(INT_MAX);

        for (int i = 0; i < length / EIGHT; i++) {
            final int chunkFrom = i * EIGHT;
            final int chunkTo = (i + 1) * EIGHT;
            final String murMurUpperSaltSubstring = new String(strForMurmurHashSalt.substring(chunkFrom, chunkTo).getBytes(UTF_8));

            BigInteger k = BigInteger.valueOf(ByteBuffer.wrap(murMurUpperSaltSubstring.getBytes()).order(ByteOrder.LITTLE_ENDIAN).getLong());// is equal to struct.unpack('q', str), see verbose my answer https://stackoverflow.com/a/57168294/5185128
            k = k.multiply(M).and(INT_MAX); // k = (k * m) & intmax
            k = k.shiftRight(R).xor(k); // k ^= (k >> r)
            k = k.multiply(M).and(INT_MAX); // k = (k * m) & intmax

            h = h.xor(k); // h ^= k
            h = h.multiply(M).and(INT_MAX); // (h * m) & intmax
        }

        final int mod = length % EIGHT;
        if (mod > 0) {
            int shift = EIGHT * (mod - 1);
            final String strForProcessing = new StringBuilder(strForMurmurHashSalt).reverse().substring(0, mod); // data[-mod:][::-1]

            for (Character c : strForProcessing.toCharArray()) {
                /*
                 * In the expression below and its comment the order is IMPORTANT
                 * read the comment (h ^ ord(c) << shift) & intmax as (h ^ (ord(c) << shift)) & intmax
                 */
                h = (BigInteger.valueOf((int) c).shiftLeft(shift).xor(h)).and(INT_MAX); // (h ^ ord(c) << shift) & intmax
                shift -= EIGHT;
            }

            h = h.multiply(M).and(INT_MAX); // (h * m) & intmax
        }

        h = ((h.shiftRight(R)).xor(h)).and(INT_MAX); // h = (h ^ (h >> r)) & intmax
        h = h.multiply(M).and(INT_MAX); // (h * m) & intmax
        h = ((h.shiftRight(R)).xor(h)).and(INT_MAX); // h = (h ^ (h >> r)) & intmax

        final BigInteger extractSplit = BigInteger.valueOf(Long.valueOf(sp.get(SPLITS.getValueName()).toString()));

        return ((h.and(BigInteger.valueOf(Long.MAX_VALUE))).mod(extractSplit)).add(BigInteger.valueOf(1L)); //(h & 0x7FFFFFFFFFFFFFFFL) % splits + 1 0x7FFFFFFFFFFFFFFF -Long> 0x7FFFFFFFFFFFFFFFL
    }

    public BigInteger getSplit(final String userId, final KeysSplitConfig keysSplitConfig) {
        return this.getSplit(userId, keysSplitConfig.getValueOfKey());
    }

    public BigInteger getSplit(final String userId, String splitCfgKey) {
        splitCfgKey = splitCfgKey.toUpperCase();

        validateSplitCfgKey(splitCfgKey);

        final Map<String, ?> splitVariation = getSplitCfg().get(splitCfgKey);
        return murmurSplit(userId, splitVariation);
    }
}
