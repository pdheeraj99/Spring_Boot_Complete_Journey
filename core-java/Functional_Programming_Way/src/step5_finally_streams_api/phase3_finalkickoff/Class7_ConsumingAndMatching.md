# Lesson 7: Terminal Operations - Consuming, Counting, and Matching

## What is a Terminal Operation?

A terminal operation is the final step in a stream pipeline.
* **It triggers the execution** of all the intermediate operations (Lazy Evaluation).
* **It consumes the stream.** After a terminal operation is called, you cannot reuse that same stream.
* **It produces a final result,** which is **NOT** another stream. It could be a primitive (`long`, `boolean`), an object, or nothing (`void`).

### 1. `forEach(Consumer<T>)` - The Doer

* **Job:** "Do this action for every single element."
* **Result:** `void` (nothing).
* **Purpose:** Its main purpose is to cause **side-effects**. For example: printing to the console, saving an item to a database, calling another method, etc.

### 2. `count()` - The Counter

* **Job:** "How many elements are in the final stream?"
* **Result:** A `long` value.
* **Purpose:** To count the elements after filtering or other intermediate operations.

### 3. Matching Operations - The Checkers

Ee operations anni oka `Predicate` teeskuni, oka `boolean` (`true`/`false`) ni return chestayi. Ivanni **short-circuiting**, ante full stream ni chudakundane result ivvochu.

* #### `anyMatch(Predicate<T>)`
    * **Question:** "Stream lo kanisam okka element aina ee condition ki match avutunda?" (Is there at least one?)
    * **Stops:** As soon as it finds the **first matching element**, it returns `true` and stops.

* #### `allMatch(Predicate<T>)`
    * **Question:** "Stream lo unna anni elements ee condition ni match chestunnaya?" (Do they all match?)
    * **Stops:** As soon as it finds the **first non-matching element**, it returns `false` and stops.

* #### `noneMatch(Predicate<T>)`
    * **Question:** "Stream lo okka element kuda ee condition ni match cheyatleda?" (Does nothing match?)
    * **Stops:** As soon as it finds the **first matching element**, it returns `false` and stops.