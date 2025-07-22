# Lesson 17: Stream Best Practices & Common Pitfalls

### 1. Use Streams for Processing, Not Just for Iteration
Streams are designed for transforming and processing data. If all you need to do is a simple loop over a collection, a traditional `for-each` loop is often more readable and just as efficient.

* **Good Use Case:** `people.stream().filter(...).map(...).collect(...)`
* **A `for` loop is better:** `for (Person p : people) { System.out.println(p.getName()); }` (instead of `people.stream().forEach(...)`).

### 2. Avoid Side Effects in Intermediate Operations (The Golden Rule)
The functions you pass to intermediate operations like `filter()` and `map()` should be **stateless**. This means their result should only depend on their input, and they should **not** modify any external state (like adding to another list or changing a variable).

* **BAD (Don't do this!):**
    ```
    List<String> results = new ArrayList<>();
    people.stream()
          .filter(p -> p.getAge() > 18)
          .forEach(p -> results.add(p.getName())); // Side effect!
    ```

* **GOOD (Functional Style):**
    ```java
    List<String> results = people.stream()
                                 .filter(p -> p.getAge() > 18)
                                 .map(Person::getName)
                                 .toList(); // No side effects!
    ```
  Violating this rule, especially in parallel streams, can lead to unpredictable results and race conditions.

### 3. Be Careful with Parallel Streams
Parallel streams are a powerful tool but not a magic bullet.
* **Don't** use them for small datasets; the overhead will make them slower.
* **Don't** use them if the order of elements matters (e.g., with `findFirst()`).
* **Absolutely DON'T** use them with stateful lambda expressions (see rule #2).

### 4. Prefer Method References Over Lambdas for Readability
If a lambda expression is just calling a single, existing method, a method reference is almost always cleaner and more readable.

* **Lambda:** `people.stream().map(person -> person.getName())`
* **Method Reference:** `people.stream().map(Person::getName)`

### 5. Know When a Simple Loop is Better
Streams are great, but they are not always the answer. If your logic is very complex, with multiple `if/else` branches, `break` statements, or complex state changes, a traditional `for` loop can be much easier to write, read, and debug. Don't force everything into a stream pipeline if it makes the code harder to understand.