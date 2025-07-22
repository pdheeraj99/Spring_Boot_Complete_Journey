# Lesson 16: Sequenced Collections and Streams (Java 21+)

### The "Why": Unifying Ordered Collections
Before Java 21, `List` and `LinkedHashSet` both had a predictable order, but they didn't share a common interface with methods for accessing the first or last element. This made writing generic code for ordered collections difficult.

Java 21 introduced the `SequencedCollection<E>` interface, which `List`, `Deque`, and `LinkedHashSet` now implement. This provides a unified API for all of them.

### New Superpowers for Ordered Collections
All sequenced collections now have these useful methods:
* `getFirst()`: Gets the first element.
* `getLast()`: Gets the last element.
* `addFirst(E)` / `addLast(E)`: Adds an element to the beginning or end.
* `removeFirst()` / `removeLast()`: Removes the first or last element.
* `reversed()`: Returns a reversed-order view of the collection.

### How It Affects Streams: The `reversed()` Method
This is the most important addition for stream processing.

The `.reversed()` method gives you a view of the collection in the opposite order **without creating a new collection or copying all the elements**. It's very efficient.

You can then call `.stream()` on this reversed view to process the elements from last to first.

```
// Get a reversed view, then create a stream from it
sequencedCollection.reversed().stream().forEach(...);
```
This is the new, standard way to process an ordered collection's stream in reverse.