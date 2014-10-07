package com.codewise.benchmark;

import com.eaio.uuid.UUIDGen;
import org.openjdk.jmh.annotations.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(25)
public class UUIDBenchmark {

	@Benchmark
	public UUID testJavaUUIDClass() {
		return UUID.randomUUID();
	}

	@Benchmark
	public UUID testEaioUUIDClass() {
		return new UUID(UUIDGen.newTime(), UUIDGen.getClockSeqAndNode());
	}

}
