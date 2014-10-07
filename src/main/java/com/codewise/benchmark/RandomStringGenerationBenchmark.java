package com.codewise.benchmark;


import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(4)
@State(Scope.Benchmark)
public class RandomStringGenerationBenchmark {

	@Param({"1", "5", "10", "25", "100", "1000"})
	public int lenght;

	@Benchmark
	public String generateRandomAlphanumeric() {
		return RandomStringUtils.randomAlphanumeric(lenght);
	}

	@Benchmark
	public String generateRandomAlphabetic() {
		return RandomStringUtils.randomAlphabetic(lenght);
	}

	@Benchmark
	public String generateRandomNumeric() {
		return RandomStringUtils.randomNumeric(lenght);
	}
}
