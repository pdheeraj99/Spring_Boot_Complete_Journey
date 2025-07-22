# Lesson 10: Introduction to `collect()` - The Ultimate Collector

`collect()` is a **terminal operation** that is used to perform a "mutable reduction." Instead of just combining values, it's used to put the results from a stream into a container like a `List`, `Set`, or `Map`.

It's the most common way to get a collection back after you've processed a stream.

### The `Collectors` Factory Class

The real power of `collect` comes from the `Collectors` helper class. It provides many static factory methods for creating the "instruction manuals" (`Collector` objects) that `collect` needs.

### 1. `Collectors.toList()`

* **Job:** "Put all the stream elements into a `List`."
* **Result:** A `java.util.List`.
* **Key Features:** It preserves the order of the stream and allows duplicate elements.

### 2. `Collectors.toSet()`

* **Job:** "Put all the stream elements into a `Set`."
* **Result:** A `java.util.Set`.
* **Key Features:** It **automatically removes duplicate elements**. The order of elements is generally not guaranteed.

### 3. `Collectors.toMap(keyMapper, valueMapper)`

* **Job:** "Create a `Map` from the stream elements."
* **It needs two functions (two recipes):**
    1.  `keyMapper`: A function that takes an element and produces a **key** for the map.
    2.  `valueMapper`: A function that takes an element and produces a **value** for the map.
* **Example:** `Collectors.toMap(name -> name, name -> name.length())` means "use the name as the key and the name's length as the value."

### Important Trap: Duplicate Keys in `toMap()`

* A `Map` cannot have duplicate keys.
* If you try to use `toMap()` on a stream that would produce the same key more than once (like our original list with two "Suresh"s), your program will crash with an **`IllegalStateException`**!
* This is a safety feature to prevent you from accidentally losing data.
* **How to solve it?**
    * **Solution 1 (Easy):** Use `.distinct()` before `.collect()` if you are sure you want to discard duplicates, like we did in the code.
    * **Solution 2 (Advanced):** There is another version of `toMap()` that takes a third argument—a "merge function"—that tells the collector what to do when it finds a duplicate key. We can cover this later if needed.