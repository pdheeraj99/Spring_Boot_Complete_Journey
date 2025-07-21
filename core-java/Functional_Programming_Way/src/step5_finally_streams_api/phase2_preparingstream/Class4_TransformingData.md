# Lesson 4: Transforming Data with `map()`

## Core Concept

`map()` anedi oka **intermediate operation**. Stream lo unde prathi element ki oka function ni apply chesi, aa function return chesina result tho oka kotha stream ni tayaru chestundi.

Mukhya vishayam enti ante, `map` tho manam stream yokka type ni kuda marchavachu. (e.g., `Stream<String>` ni `Stream<Integer>` ga).

### `filter()` vs `map()`

* **`filter()`**: Oka jalleda laantidi (a sieve). Konni elements ni aapi, konnitini pampistundi. **Number of elements marochu, kani elements type maradu.**
* **`map()`**: Oka conversion machine laantidi. Anni elements ni teeskuni, vaatini marustundi. **Number of elements maradu, kani elements type marochu.**

## The `Function<T, R>` Functional Interface

`map()` method oka `Function` ni argument ga teeskuntundi.

* **Function ante emiti?** Idi oka functional interface. Daaniki oka input (type `T`) isthe, adi oka output (type `R`) istundi.
* **Lambda Expression:** Manam `map` lo rase lambda `name -> name.length()` anedi oka `Function`.
    * `name`: Input (type `String`)
    * `name.length()`: The transformation logic that returns an output (type `Integer`).
    * `T` anedi `String` and `R` anedi `Integer`.

## Code Explanation

* **Example 1:** `name -> name.length()` ane function, prathi `String` name ni teeskuni, daani length (oka `Integer`) ni return chestundi. So, `Stream<String>` anedi `Stream<Integer>` ga marindi.
* **Example 2:** `name -> new User(name)` ane function, prathi `String` name ni teeskuni, daanitho oka kotha `User` object ni create chesi return chestundi. So, `Stream<String>` anedi `Stream<User>` ga marindi.

## `collect(Collectors.toList())`

Ee code lo manam `forEach` badulu `collect(Collectors.toList())` ane kotha terminal operation vadam. Don't worry, deeni gurinchi manam **Phase 4** lo chala detail ga nerchukuntam. Ippatiki, idi stream lo unna elements anni teeskuni oka kotha `List` ga istundi ani gurtupettukondi.