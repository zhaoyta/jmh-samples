package com.codewise;

import com.codewise.benchmark.AtomicOperationBenchmark;
import com.codewise.benchmark.MapPutBenchmark;
import com.codewise.benchmark.UUIDBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class JMHRunner {

	public static void main(String[] args) throws RunnerException {
		new Runner(
				new OptionsBuilder()
						.include(".*" + MapPutBenchmark.class.getSimpleName() + ".*")
						.build()
		)
				.run();
	}
}
