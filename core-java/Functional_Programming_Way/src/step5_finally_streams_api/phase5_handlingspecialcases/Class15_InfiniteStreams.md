# Lesson 15: Infinite Streams

An infinite stream is a stream without a fixed size. It generates elements on demand, potentially forever.

### The Golden Rule: Always Use `limit()`!
You **MUST** use a short-circuiting operation like `limit(n)` to make an infinite stream finite. If you forget, your program will run forever (or until it runs out of memory)! This is the ultimate example of **lazy evaluation**; the stream only generates as many elements as `limit()` asks for.

### 1. `Stream.iterate(seed, UnaryOperator<T>)` - The Sequence Generator

This method is used to create a stream where each element depends on the previous one. It's perfect for creating sequential data.

* **`seed`**: The first element of the stream (the starting point).
* **`UnaryOperator<T>`**: A function that takes the previous element and uses it to generate the next one.
* **Example:** `Stream.iterate(0, n -> n + 2)`
    * Starts with `0`.
    * Next is `0 + 2 = 2`.
    * Next is `2 + 2 = 4`.
    * And so on, forever.

### 2. `Stream.generate(Supplier<T>)` - The Repetitive Producer

This method creates a stream by simply calling a supplier function over and over. Each element is new and independent of the previous ones.

* **`Supplier<T>`**: A function that takes no arguments and just produces a value.
* **Example:** `Stream.generate(Math::random)`
    * The stream will call `Math.random()` to get the first element.
    * Then it will call `Math.random()` *again* to get the second element.
    * It does this forever, producing a stream of different random numbers.