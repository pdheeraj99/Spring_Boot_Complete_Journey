# Lesson 15: New Stream Methods in Java 16+

### `Stream.toList()` (Java 16+) - The Simple Collector

This is a new terminal operation that makes collecting stream elements into a `List` much simpler.

#### Old Way (Before Java 16)
```java
List<String> list = stream.collect(Collectors.toList());
```

#### New Way (Java 16+)
```java
List<String> list = stream.toList();
```
It's more concise and is now the recommended way in most cases.

**Key Feature: Unmodifiability**
The most important difference is that `stream.toList()` returns an **unmodifiable list**. You cannot add or remove elements from it. This is a great feature for writing safer, more predictable code, as it prevents accidental changes to the list later on. `Collectors.toList()` typically returns a standard mutable `ArrayList`.

### `Stream.mapMulti()` (Java 16+) - The Flexible `flatMap`

`mapMulti` is an intermediate operation that can be a more efficient and sometimes simpler alternative to `flatMap`.

* **Job:** It's designed to replace one stream element with *zero, one, or many* elements.
* **How it works:** It takes a `BiConsumer` as an argument. This consumer gets two things:
    1.  The element from the stream (`str`).
    2.  A `downstream` consumer, which you can call to pass new elements onward.

**`flatMap` vs. `mapMulti`**
* With `flatMap`, your lambda function has to create and return a whole new `Stream` for every single element. This can be inefficient if you are only producing a few items.
* With `mapMulti`, you don't create new streams. You just call `downstream.accept(newItem)` for each new element you want to produce. This can be more performant.

In our code example, `mapMulti` is used to both **filter** (by ignoring non-numeric strings) and **map** (by converting the numeric string to an integer and repeating it) in one efficient step.