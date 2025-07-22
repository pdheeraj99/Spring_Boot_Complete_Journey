# Lesson 14: Parallel Streams

### What are Parallel Streams?

A parallel stream is a stream that can process its elements concurrently, using multiple threads from a common **ForkJoinPool**. This can lead to significant performance improvements on multi-core processors, especially for large datasets and computationally intensive operations.

### How to Create a Parallel Stream

There are two simple ways:
1.  **From a collection:** Use the `.parallelStream()` method instead of `.stream()`.
    ```
    java
    List<String> list = ...
    Stream<String> pStream = list.parallelStream();
    ```
2.  **From an existing stream:** Use the intermediate operation `.parallel()`. This will convert a sequential stream into a parallel one.
    ```java
    long sum = LongStream.rangeClosed(1, 1_000_000)
                         .parallel() // Convert to parallel
                         .sum();
    ```

### When to Use Parallel Streams

Parallel streams are not always faster. They have an overhead cost for splitting the task and combining the results. Use them when:
* You have a **large dataset**.
* The operations on each element are **computationally expensive** (e.g., complex math, not just simple additions).
* The order of processing **does not matter**.
* The stream operations are **stateless** and can be safely run in parallel.

### **IMPORTANT: When NOT to Use Parallel Streams**

Using parallel streams incorrectly can make your code **slower** and introduce bugs. Avoid them when:
* **The dataset is small.** The overhead of managing threads will be greater than the benefit.
* **The operations are simple and fast.** For example, just adding a few numbers.
* **The terminal operation is order-dependent**, like `findFirst()`. Using `findAny()` is better for parallel streams.
* **Your stream operations are stateful.** This is the most dangerous pitfall. A stateful lambda is one whose result depends on any state that might change during the execution of the stream pipeline (e.g., modifying an external `List` or variable). This can lead to unpredictable results and race conditions.