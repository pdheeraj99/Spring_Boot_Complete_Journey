Let's analyze your code and the expected output step by step, and clarify how `mapMulti()` works in this context.

---

## 1. **What Does the Code Do?**

You have a stream of strings: `["apple", "2", "cherry", "3"]`.

You want to:
- If the string is a number, convert it to an integer (`num`).
- Then, repeat that number (`num`) times in the output list.
- If it's not a number, ignore it.

**Your code:**
```java
Stream<String> mixedStream = Stream.of("apple", "2", "cherry", "3");

List<Integer> result = mixedStream
    .mapMulti((str, downstream) -> {
        try {
            int num = Integer.parseInt(str);
            for (int i = 0; i < num; i++) {
                downstream.accept(num);
            }
        } catch (NumberFormatException e) {
            // Not a number, do nothing
        }
    })
    .toList();
```
System.out.println("Result from mapMulti: " + result); // Should be [2, 2, 3, 3, 3] => Is this wrong ?
```

---

## 2. **How Does `mapMulti()` Work Here?**

- For each element in the stream, the lambda tries to parse it as an integer.
- If successful, it calls `downstream.accept(num)` exactly `num` times.
- If parsing fails (i.e., the string is not a number), it does nothing (filters it out).

---

## 3. **Step-by-Step Execution**

Let's walk through each element:

1. `"apple"`
    - Not a number.
    - Caught by `NumberFormatException`.
    - **No output.**

2. `"2"`
    - Parsed as `2`.
    - Loop: `i = 0` and `i = 1` (total 2 times).
    - Adds `2` twice to the output.
    - **Output so far: [2, 2]**

3. `"cherry"`
    - Not a number.
    - Caught by `NumberFormatException`.
    - **No output.**

4. `"3"`
    - Parsed as `3`.
    - Loop: `i = 0`, `i = 1`, `i = 2` (total 3 times).
    - Adds `3` three times to the output.
    - **Output so far: [2, 2, 3, 3, 3]**

---

## 4. **Final Output**

The final list is:
```
[2, 2, 3, 3, 3]
```
This matches your expectation.

---

## 5. **Is This Output Wrong?**

**No, this output is correct.**  
Your code is working as intended.
- Non-numeric strings are filtered out.
- Numeric strings are repeated as many times as their value.

---

## 6. **Why Use `mapMulti()` Here?**

- `mapMulti()` is perfect for this use case because it allows you to emit zero, one, or many elements for each input element, and you can do so imperatively (with a loop or condition)  .
- If you used `flatMap()`, you would have to return a stream for each element, which is less efficient and more verbose for this pattern .

---

## 7. **Summary Table**

| Input    | Is Number? | Output Produced      |
|----------|------------|---------------------|
| "apple"  | No         | (none)              |
| "2"      | Yes        | 2, 2                |
| "cherry" | No         | (none)              |
| "3"      | Yes        | 3, 3, 3             |

**Final Output:** `[2, 2, 3, 3, 3]`

---

## 8. **Extra Note: Common Mistakes**

- If you forget to call `downstream.accept()` the correct number of times, or at all, you might get missing or extra elements [[2]].
- Your code handles filtering and mapping in one step, which is a key advantage of `mapMulti()` over `flatMap()` or `filter().map()` chains [[1]].

---

## 9. **Conclusion**

- Your code and expected output are correct.
- `mapMulti()` is being used properly for this scenario.
- The result `[2, 2, 3, 3, 3]` is exactly what should be produced  .

If you have more questions about `mapMulti()` or want to see how this would look with `flatMap()`, let me know!