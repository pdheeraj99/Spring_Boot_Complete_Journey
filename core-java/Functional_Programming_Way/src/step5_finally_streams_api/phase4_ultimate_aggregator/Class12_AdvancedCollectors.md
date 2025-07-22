# Lesson 12: More Advanced Collectors

### `partitioningBy(Predicate)` - The Two-Way Split

This is a special and often more efficient version of `groupingBy`.
* **Job:** It splits a stream into exactly two groups based on a `Predicate`: one for `true` and one for `false`.
* **Result:** It *always* returns a `Map<Boolean, List<T>>`.
* **Analogy:** Sorting your laundry into just two baskets: "Whites" (true) and "Everything Else" (false).

### `joining(delimiter)` - The String Stitcher

This collector only works on streams of `CharSequence` (like `String`).
* **Job:** It joins all the strings in the stream together into a single string.
* **Argument:** You can provide a "delimiter" to put between each element (e.g., `, `).
* There are also versions to add a prefix and suffix, like `joining(", ", "[", "]")`.

### The Ultimate Power-Up: Downstream Collectors

This is where `groupingBy` becomes incredibly powerful. You can provide a **second argument** to `groupingBy`, which is another `Collector`. This is called a **downstream collector**.

* **Job:** The downstream collector tells `groupingBy` what to do with all the values that land in each group. Instead of just putting them in a `List`, you can perform another aggregation on them immediately.

**Analogy:** After you've sorted your laundry into color baskets (`groupingBy`), you perform a second action on **each basket**:
1.  You count how many items are in the "White" basket (`Collectors.counting()`).
2.  You count how many items are in the "Dark" basket.

In our code:
* `Collectors.groupingBy(Employee::getDepartment, Collectors.counting())`
    * **First:** Group employees by department.
    * **Then, for each department group:** Don't collect the employees into a `List`. Instead, just count them and give me the final number (`Long`).
    * This results in a `Map<String, Long>`.

* `Collectors.groupingBy(Employee::getDepartment, Collectors.summingInt(Employee::salary))`
    * **First:** Group employees by department.
    * **Then, for each department group:** Go through all the employees, get their salaries, and sum them up.
    * This results in a `Map<String, Integer>`.