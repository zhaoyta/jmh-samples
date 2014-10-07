package com.codewise.benchmark;


import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@State(Scope.Benchmark)
public class MapPutBenchmark {

	@Param({"1000", "10000", "100000"})
	public int elementCount;

	GoodMapKey[] goodMapKeys;
	BadMapKey[] badMapKeys;

	Map hashMap;

	@Setup(Level.Trial)
	public void setupGlobal() {
		goodMapKeys = new GoodMapKey[elementCount];
		badMapKeys = new BadMapKey[elementCount];
		for (int i = 0; i < elementCount; i++) {
			String key = "" + i;
			goodMapKeys[i] = new GoodMapKey(key);
			badMapKeys[i] = new BadMapKey(key);
		}
	}

	@Setup(Level.Iteration)
	public void setupIteration() {
		hashMap = new HashMap(elementCount, 1f);
	}

	@Benchmark
	public void testHashMapPutWithGoodMapKeys() {
		for (int i = 0; i < elementCount; i++) {
			GoodMapKey key = goodMapKeys[i];
			hashMap.put(key, key);
		}
	}

	@Benchmark
	public void testHashMapPutWithBadMapKeys() {
		for (int i = 0; i < elementCount; i++) {
			BadMapKey key = badMapKeys[i];
			hashMap.put(key, key);
		}
	}


	class GoodMapKey {
		final String val;

		GoodMapKey(String val) {
			this.val = val;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			GoodMapKey that = (GoodMapKey) o;

			return val.equals(that.val);
		}

		@Override
		public int hashCode() {
			return val.hashCode();
		}
	}

	class BadMapKey {
		final String val;

		BadMapKey(String val) {
			this.val = val;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			BadMapKey that = (BadMapKey) o;

			return val.equals(that.val);
		}

		@Override
		public int hashCode() {
			return val.hashCode() % 1000;
		}
	}
}
