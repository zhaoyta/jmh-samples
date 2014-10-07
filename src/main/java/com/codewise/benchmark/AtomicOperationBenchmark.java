package com.codewise.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(100)
@State(Scope.Benchmark)
public class AtomicOperationBenchmark {

	final AtomicInteger atomicInt = new AtomicInteger();
	final AtomicLong atomicLong = new AtomicLong();
	final LongAdder longAdder = new LongAdder();

	int nonThreadSafeInt = 0;
	volatile int volatileInt = 0;

	final Object lock = new Object();
	long synchronizedLong = 0;

	final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	long readWriteLockLong = 0;

	final StampedLock stampedLock = new StampedLock();
	long stampedLockLong = 0;

	@Benchmark
	public void testAtomicInteger() {
		atomicInt.incrementAndGet();
	}

	@Benchmark
	public void testAtomicLong() {
		atomicLong.incrementAndGet();
	}

	@Benchmark
	public void testLongAdder() {
		longAdder.increment();
	}

	@Benchmark
	public void testVolatileInteger() {
		volatileInt++; //not valid!
	}

	@Benchmark
	public void testNonThreadSafeInteger() {
		nonThreadSafeInt++; //not valid!
	}


	@Benchmark
	public void testSynchronizedLong() {
		synchronized (lock) {
			synchronizedLong++;
		}
	}

	@Benchmark
	public void testReadWriteLockLong() {
		Lock lck = readWriteLock.writeLock();
		lck.lock();
		readWriteLockLong++;
		lck.unlock();
	}

	@Benchmark
	public void testStampedLockLong() {
		long stamp = stampedLock.writeLock();
		stampedLockLong++;
		stampedLock.unlockWrite(stamp);
	}

}
