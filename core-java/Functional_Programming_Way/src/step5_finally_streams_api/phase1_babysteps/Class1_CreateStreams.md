# Lesson 1: Creating Streams

## Key Concepts
Ee lesson lo manam 3 important ways lo streams create cheyadam nerukunnam:
1.  **From a `List`**: `names.stream()`
2.  **From an `Array`**: `Arrays.stream(fruits)`
3.  **Directly**: `Stream.of(1, 2, 3)`

## Important Point
A stream pipeline doesn't start until a **terminal operation** (like `forEach`) is called. Idi *lazy evaluation* anukuntam.

## Code Snippet
```java
List<String> names = List.of("Alice", "Bob", "Charlie");
Stream<String> namesStream = names.stream(); // Creation
namesStream.forEach(System.out::println); // Terminal Operation