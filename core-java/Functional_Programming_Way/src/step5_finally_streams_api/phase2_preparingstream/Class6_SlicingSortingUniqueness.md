# Lesson 6: Slicing, Sorting, and Uniqueness

Ee operations stream ni organize and "tidy up" cheyadaniki use avutayi. Ivanni **intermediate operations**.

### 1. `distinct()` - No Duplicates, Please!

* **Job:** Stream lo unna duplicate elements ni remove chestundi.
* **How:** Behind the scenes, idi elements ni compare cheyadaniki `Object.equals()` method ni vaadutundi.
* **Use Case:** Unique items matrame kavali anukunappudu.

### 2. `sorted()` - Put Them in Order!

* **Job:** Elements ni sort chestundi.
* **How:** By default, idi "natural order" lo sort chestundi (numbers ni ascending order lo, strings ni alphabetical order lo).
* **Note:** Manam custom sorting logic kuda ivvochu, but for now, let's stick to the default.

### 3. `limit(n)` - Give Me Only the First Few!

* **Job:** Stream lo unna முதல் (modhati) `n` elements ni matrame teeskuntundi.
* **How:** Idi chala efficient. Once `n` elements dorikayaka, stream processing aapestundi. Deenine "short-circuiting operation" antaru.
* **Use Case:** Top 5 scorers, first 10 search results lanti scenarios ki perfect.

### 4. `skip(n)` - Ignore the First Few!

* **Job:** Stream lo unna முதல் (modhati) `n` elements ni vadhilesi (discard chesi), migatha vatini istundi.
* **Use Case:** Pagination lo (e.g., show results from page 3, so skip the first 20 results).

## The Power of Chaining! ✨

Ee operations yokka real power vaatini kalipi vadinappude telustundi. **Order is very important!**

Mana code lo final example chudandi:
`numbers.stream().distinct().sorted().limit(3)`

Ee pipeline ela pani chestundo chudandi:
1.  **Source:** `[10, 4, 8, 4, 1, 15, 8, 10, 3]`
2.  **After `.distinct()`:** `[10, 4, 8, 1, 15, 3]` (duplicates are gone)
3.  **After `.sorted()`:** `[1, 3, 4, 8, 10, 15]` (the unique elements are sorted)
4.  **After `.limit(3)`:** `[1, 3, 4]` (only the first 3 are taken from the sorted stream)
5.  **Finally, `collect()`** aa final result ni oka list lo pedutundi.

Ee chinna operations ni chain chesi, manam complex logic ni chala clean ga rayochu.