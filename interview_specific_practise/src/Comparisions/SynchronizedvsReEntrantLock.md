### `ReentrantLock` vs. `synchronized` Summary

| Feature           | `synchronized`                  | `ReentrantLock`                       |
|:------------------|:--------------------------------|:--------------------------------------|
| **Locking** | Implicit (automatic lock/unlock) | **Explicit** (`lock()` and `unlock()`) |
| **Re-entrant?** | Yes                             | Yes                                   |
| **`tryLock`** | No                              | **Yes** (timed & polling)             |
| **Interruptible?**| No                              | **Yes** |
| **Fairness** | No (Unfair)                     | **Yes** (configurable)                |
| **Conditions** | One per object                  | **Multiple** per lock                 |
