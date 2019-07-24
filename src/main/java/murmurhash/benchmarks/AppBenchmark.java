package murmurhash.benchmarks;

import murmurhash.MurMurHash;
import murmurhash.enums.KeysSplitConfig;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * /@Fork(value = 1, warmups = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})  - For each iteration 1 warmup and 1 iteration so
 * {@link ExecutionPlan#iterations}.length * 2 (1 a warmup and 1 an iteration)
 *
 * @author y.glushenkov
 */
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 1, jvmArgs = {"-Xms2G", "-Xmx2G"}) // warmups entire fork
@Timeout(time = 10, timeUnit = TimeUnit.MILLISECONDS)
public class AppBenchmark {
    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        @Param({ "50", "200", "500", "1000", "2000"})
        private int iterations;

        private MurMurHash murMurHash;

        private int random;
        private StringBuilder sb;
        private KeysSplitConfig keysSplitConfig;

        @Setup(Level.Invocation)
        public void setUp() {
            murMurHash = new MurMurHash();
            random = new Random().nextInt(100 - 10) + 10;
            sb = new StringBuilder();

            final int randIdx = new Random().nextInt(KeysSplitConfig.values().length);
            keysSplitConfig = KeysSplitConfig.values()[randIdx];
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 3)
    @Measurement(iterations = 5) // count of iteration per benchmark
    public void benchMurmurThroughput(ExecutionPlan plan, Blackhole blackhole) {
        final BigInteger res = this.murmurHash(plan);

        blackhole.consume(res);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3)
    @Measurement(iterations = 5) // count of iteration per benchmark
    public void benchMurmurAverage(ExecutionPlan plan, Blackhole blackhole) {

        final BigInteger res = this.murmurHash(plan);

        blackhole.consume(res);
    }

    private BigInteger murmurHash(ExecutionPlan plan) {
        for (int i = plan.iterations; i > 0; i--) {
            plan.sb.append(i).append(plan.random);
        }

        return plan.murMurHash.getSplit(plan.sb.toString(), plan.keysSplitConfig);
    }
}