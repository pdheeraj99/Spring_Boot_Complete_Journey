# Lesson 18: Debugging Streams with `peek()`

### The Problem: Streams as a "Black Box"
When you chain many operations together, it can be hard to know what the data looks like at each intermediate step. If you get an unexpected result, how do you find the bug?

### The Solution: `peek()` - The Glass Window
`peek()` is an **intermediate operation** designed for debugging.

* **Job:** It takes a `Consumer` (an action, usually `System.out.println`) and performs that action on each element as it flows past.
* **Key Feature:** It returns the original element unchanged, allowing the stream to continue. It's purely for observing the stream, not transforming it.

### How to Use It
You can insert `.peek()` anywhere in your stream pipeline to see the state of the elements at that point.

```java
List<Integer> result = names.stream()
    .peek(s -> System.out.println("Original: " + s))
    .filter(s -> s.length() > 4)
    .peek(s -> System.out.println("After filter: " + s))
    .map(String::length)
    .peek(len -> System.out.println("After map: " + len))
    .toList();
```
When you run this code, the output will be interleaved, showing you the "story" of how each element travels (or gets filtered out) through the pipeline.

### **Important Best Practice**
`peek()` should **only be used for debugging**. Do not put any business logic inside a `.peek()` call. Because it's an intermediate operation, it only executes when a terminal operation is called. Relying on it for anything other than observing the stream can lead to unpredictable code.