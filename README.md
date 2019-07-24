# Description
The MurMur hash for hive with splitting

## API
```java
java.math.BigInteger murmurhash.MurMurHash.getSplit(java.lang.String, murmurhash.enums.KeysSplitConfig)
```
or  
```java
java.math.BigInteger murmurhash.MurMurHash.getSplit(java.lang.String, java.lang.String)
```


### BUILD WITH TESTS
```bash
mvn clean; mvn package
```

### RUN TESTS
```bash
mvn test
```

### RUN JMH TEST
```bash
java -cp target/murmurhash-1.0-SNAPSHOT.jar murmurhash.benchmarks.BenchmarkRunner
```

### RUN
```bash
java -jar target/murmurhash-1.0-SNAPSHOT.jar userId 8A

```

### Use as a jar 
```bash
java  -cp "/Users/y.glushenkov/IdeaProjects/myProject/:original-murmurhash-1.0-SNAPSHOT.jar" your.Class

# Example
cd /Users/y.glushenkov/IdeaProjects/a/; \
javac -cp ../murmurhash/target/original-murmurhash-1.0-SNAPSHOT.jar -d ./ src/main/java/a/Main.java; \
java  -cp "/Users/y.glushenkov/IdeaProjects/a/:../murmurhash/target/original-murmurhash-1.0-SNAPSHOT.jar" a.Main
```

### Benchmarks
MacBook pro 2017: 2,9 GHz Intel Core i7; 16 GB 2133 MHz LPDDR3
```
Result "murmurhash.benchmarks.AppBenchmark.benchMurmurThroughput":
  1.476 ±(99.9%) 0.008 ops/ms [Average]
  (min, avg, max) = (1.468, 1.476, 1.484), stdev = 0.005
  CI (99.9%): [1.468, 1.484] (assumes normal distribution)
  
Result "murmurhash.benchmarks.AppBenchmark.benchMurmurAverage":
  0.677 ±(99.9%) 0.009 ms/op [Average]
  (min, avg, max) = (0.669, 0.677, 0.686), stdev = 0.006
  CI (99.9%): [0.669, 0.686] (assumes normal distribution)


Run complete. Total time: 00:06:49

Benchmark                           (iterations)   Mode  Cnt   Score    Error   Units
AppBenchmark.benchMurmurThroughput            50  thrpt   10  73.663 ±  5.802  ops/ms
AppBenchmark.benchMurmurThroughput           200  thrpt   10  17.333 ±  0.230  ops/ms
AppBenchmark.benchMurmurThroughput           500  thrpt   10   6.574 ±  0.072  ops/ms
AppBenchmark.benchMurmurThroughput          1000  thrpt   10   3.258 ±  0.036  ops/ms
AppBenchmark.benchMurmurThroughput          2000  thrpt   10   1.476 ±  0.008  ops/ms
AppBenchmark.benchMurmurAverage               50   avgt   10   0.013 ±  0.001   ms/op
AppBenchmark.benchMurmurAverage              200   avgt   10   0.057 ±  0.001   ms/op
AppBenchmark.benchMurmurAverage              500   avgt   10   0.153 ±  0.002   ms/op
AppBenchmark.benchMurmurAverage             1000   avgt   10   0.306 ±  0.003   ms/op
AppBenchmark.benchMurmurAverage             2000   avgt   10   0.677 ±  0.009   ms/op
```  