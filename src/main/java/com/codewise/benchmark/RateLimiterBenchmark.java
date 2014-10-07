package com.codewise.benchmark;

import com.google.common.util.concurrent.RateLimiter;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(2)
@State(Scope.Benchmark)
public class RateLimiterBenchmark {

	final RateLimiter rateLimiter = RateLimiter.create(10000);

	@Benchmark
	public boolean testGuavaRateLimiter() {
		return rateLimiter.tryAcquire();
	}

}
