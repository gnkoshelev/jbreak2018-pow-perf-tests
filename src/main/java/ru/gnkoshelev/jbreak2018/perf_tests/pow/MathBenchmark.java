package ru.gnkoshelev.jbreak2018.perf_tests.pow;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * Created by kgn on 22.03.2018.
 */
@Fork(value = 3, warmups = 0)
@Warmup(iterations = 5, time = 1_000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 1_000, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(value = TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class MathBenchmark {
    public double a;

    @Setup
    public void setup() {
        a = 1234567.890;
    }

    @Benchmark
    public void mathOctaPowBenchmark(Blackhole bh) {
        bh.consume(mathOctaPow(a));
    }

    @Benchmark
    public void plainOctaPowBenchmark(Blackhole bh) {
        bh.consume(plainOctaPow(a));
    }

    @Benchmark
    public void trickyMathOctaPowBenchmark(Blackhole bh) {
        bh.consume(trickyMathOctaPow(a));
    }

    @Benchmark
    public void trickyPlainOctaPowBenchmark(Blackhole bh) {
        bh.consume(trickyPlainOctaPow(a));
    }

    public double mathOctaPow(double a) {
        return Math.pow(a, 8);
    }

    public double plainOctaPow(double a) {
        return a * a * a * a * a * a * a * a;
    }

    public double trickyMathOctaPow(double a) {
        return Math.pow(Math.pow(Math.pow(a, 2), 2), 2);
    }

    public double trickyPlainOctaPow(double a) {
        a *= a; a *= a; return a * a;
    }
}
