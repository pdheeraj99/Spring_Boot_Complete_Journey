# Lesson 19: Real-World Industry Use Cases

This lesson shows how to combine all the stream operations we've learned to solve practical business problems. The key is to break down a complex problem into a pipeline of simple steps.

### Scenario 1: E-commerce Revenue by Category

* **Problem:** Calculate the total sales for each product category.
* **The Plan:** This is a classic "group and sum" problem.
    1.  **Group:** We need to group all products by their category. `Collectors.groupingBy(Product::category, ...)` is the perfect tool.
    2.  **Sum:** For each group, we don't want a `List` of products; we want the sum of their prices. This is a job for a downstream collector: `Collectors.summingDouble(Product::price)`.
* **Result:** A `Map<String, Double>` where the key is the category and the value is the total revenue.

### Scenario 2: Log Analysis: Finding Unique Error IPs

* **Problem:** Find all unique IP addresses that are associated with an "ERROR" log entry.
* **The Plan:** This is a "filter, transform, and collect unique" pipeline.
    1.  **Filter:** First, we only care about lines containing "ERROR". We use `.filter(log -> log.contains("ERROR"))`.
    2.  **Transform:** We don't want the whole log line, just the IP address. We use `.map()` to extract the first part of the string.
    3.  **Collect Unique:** We need unique IP addresses, and a `Set` automatically handles uniqueness. So, we use `collect(Collectors.toSet())`.
* **Result:** A `Set<String>` containing only the unique IP addresses from error logs.

### Scenario 3: Financial Data: Finding Max Transaction per Currency

* **Problem:** For each currency (USD, EUR, etc.), find the single transaction with the highest amount.
* **The Plan:** This is a "group and find max" problem.
    1.  **Group:** We group all transactions by their currency: `Collectors.groupingBy(Transaction::currency, ...)`.
    2.  **Find Max:** For each group, we need to find the transaction with the maximum amount. The downstream collector `Collectors.maxBy()` is designed for this. It needs a `Comparator` to know how to compare two transactions, so we provide `Comparator.comparing(Transaction::amount)`.
* **Result:** A `Map<String, Optional<Transaction>>`. The value is an `Optional` because a group could theoretically be empty.