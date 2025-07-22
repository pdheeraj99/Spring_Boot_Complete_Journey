# Lesson 13: Primitive Streams

### The Problem: Boxing Overhead

When you use a generic stream for numbers, like `Stream<Integer>`, each number is treated as an `Object`. The process of converting a primitive `int` into an `Integer` object is called **boxing**.

For a few numbers, this is fine. But for a large number of elements, the memory and performance cost of these "boxed" objects can be significant.

### The Solution: `IntStream`, `LongStream`, `DoubleStream`

Java provides specialized stream types to work directly with primitives, avoiding the boxing overhead.

* `IntStream` works with `int` values.
* `LongStream` works with `long` values.
* `DoubleStream` works with `double` values.

### Key Advantages

1.  **Performance:** They are much faster for numerical computations because there is no boxing/unboxing overhead.
2.  **Specialized Methods:** They come with extra, highly useful terminal operations that are not available on `Stream<T>`, such as:
    * `sum()`: Directly calculates the sum of all numbers.
    * `average()`: Returns an `OptionalDouble` with the average.
    * `max()`: Returns an `OptionalInt` with the maximum value.
    * `min()`: Returns an `OptionalInt` with the minimum value.

### Useful Creation Methods

* `IntStream.of(...)`: Creates a stream from a sequence of numbers.
* `IntStream.range(int startInclusive, int endExclusive)`: Creates a stream of numbers from a starting point up to (but not including) an end point. `range(1, 5)` produces `1, 2, 3, 4`.
* `IntStream.rangeClosed(int startInclusive, int endInclusive)`: Similar to `range`, but it **includes** the end point. `rangeClosed(1, 5)` produces `1, 2, 3, 4, 5`.

### `IntSummaryStatistics` - The Power Tool

This is an amazing helper class that calculates the count, min, max, sum, and average of a stream **all in one single pass**. It's incredibly efficient. Instead of running the stream 5 times to get 5 different values, you run it once and get an object that contains all the results.