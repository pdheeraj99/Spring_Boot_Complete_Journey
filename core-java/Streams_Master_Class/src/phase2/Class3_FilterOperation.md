# Lesson 3: Filtering Data with `filter()`

## Core Concept

`filter()` anedi oka **intermediate operation**. Stream lo unde prathi element meeda oka condition (test) apply chesi, aa condition ki `true` return chese elements tho oka kotha stream ni istundi.

Simple ga cheppalante, idi oka gatekeeper laantidi. Condition satisfy cheste lopaliki ranistundi, lekapothe block chestundi.

## The `Predicate<T>` Functional Interface

`filter()` method oka `Predicate` ni argument ga teeskuntundi.

* **Predicate ante emiti?** Idi oka simple functional interface. Daani pani (work) okate: oka input teeskuni, `true` or `false` (boolean) ni return cheyadam.
* **Lambda Expression:** Manam `filter` lo rase lambda `n -> n % 2 == 0` anedi oka `Predicate`.
    * `n`: Input (stream lo okko element)
    * `n % 2 == 0`: The condition (the test logic) that returns a boolean.

## How It Works

1.  Stream lo nunchi okko element `filter()` loki vastundi.
2.  Aa element meeda manam ichina predicate lambda expression execute avutundi.
3.  Lambda `true` return cheste, aa element output stream loki veltundi.
4.  Lambda `false` return cheste, aa element akkade discard aipotundi.

## Code Explanation

* **Example 1:** `n -> n % 2 == 0` ane predicate, kevalam even numbers ki matrame `true` istundi. Anduke final output lo only even numbers vachayi.
* **Example 2:** `name -> name.startsWith("S")` ane predicate, 'S' tho start ayye names ki matrame `true` istundi.

## Important Note

`filter()` oka intermediate operation kabatti, daani tarvata `forEach()` or `collect()` lanti terminal operation unte ne adi execute avutundi. Idi `Lazy Evaluation` concept manam mundu chusindi.