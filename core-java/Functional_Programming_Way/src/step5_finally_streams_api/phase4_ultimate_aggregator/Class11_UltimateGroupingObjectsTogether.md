# Lesson 11: Advanced `collect()` with `groupingBy`

This is one of the most useful collectors. Its purpose is to take a stream and organize its elements into groups, returning the result as a `Map`.

### The Core Idea: The "Classifier"

`groupingBy` needs one main piece of information:
* **A "Classifier" Function:** This is a function that looks at an element and decides which group it belongs to. The result of this function becomes the **key** in the final `Map`.

In our code, we used `Employee::getDepartment` as the classifier.
* When the collector sees Alice, it calls `Alice.getDepartment()`, which returns `"HR"`. So, Alice goes into the `"HR"` group.
* When it sees Bob, it calls `Bob.getDepartment()`, which returns `"IT"`. So, Bob goes into the `"IT"` group.

### The Result

By default, `groupingBy` returns a `Map<K, List<T>>`.
* `K` is the type of the key (the result of the classifier function, e.g., `String` for the department).
* `List<T>` is a `List` containing all the elements from the stream that belong to that key.

### Connecting to Our Previous Lesson

Think back to the "machine and engine" analogy.
* `.collect()` is still the empty machine.
* `Collectors.groupingBy(...)` is just a different, more complex "engine" we put inside it.

The `.collect()` machine doesn't care what the engine does. It just follows the instructions given by the `Collector` object. In this case, the `groupingBy` engine's recipe tells the machine to create a `Map` and sort elements into it, instead of just adding them to a `List`.