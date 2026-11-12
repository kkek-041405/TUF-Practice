# Pattern 5: Inverted Right-Angled Triangle Star Pattern

- **Topic:** Logic Building & Patterns
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Pattern 5](https://takeuforward.org/practice/dsa/pattern-5)
- **Target Language:** Java

---

## 1. Problem Description

Given an integer `n`, your task is to print an inverted right-angled triangle pattern made of asterisks (`*`) of height and initial base $N$.

For any positive integer $N$:
- The pattern consists of exactly $N$ rows.
- The first row contains $N$ stars.
- Each subsequent row has one less star than the preceding row, until the final ($N$-th) row contains exactly $1$ star.
- In general, the $i$-th row (where row indexing starts at $1$ and goes up to $N$) contains $(N - i + 1)$ stars.
- Each row must be terminated with a newline.

---

## 2. Visual Representation & Grid Mechanics

For $N = 5$:

```text
Row 1 (i = 1):  * * * * *                  -> 5 stars
Row 2 (i = 2):  * * * *                    -> 4 stars
Row 3 (i = 3):  * * *                      -> 3 stars
Row 4 (i = 4):  * *                        -> 2 stars
Row 5 (i = 5):  *                          -> 1 star
```

### Grid Matrix Breakdown

| Row Index ($i$) | Star Count ($N - i + 1$) | Column Loop Range ($j$) | Output |
| :---: | :---: | :---: | :--- |
| **1** | $5$ | $1 \le j \le 5$ | `* * * * *` |
| **2** | $4$ | $1 \le j \le 4$ | `* * * *` |
| **3** | $3$ | $1 \le j \le 3$ | `* * *` |
| **4** | $2$ | $1 \le j \le 2$ | `* *` |
| **5** | $1$ | $1 \le j \le 1$ | `*` |

Total stars printed: $\sum_{k=1}^{N} k = \frac{N(N + 1)}{2}$.

---

## 3. Examples & Walkthroughs

### Example 1
- **Input:** `n = 4`
- **Output:**
  ```text
  * * * * 
  * * * 
  * * 
  * 
  ```
- **Explanation:**
  - Row 1: prints 4 stars
  - Row 2: prints 3 stars
  - Row 3: prints 2 stars
  - Row 4: prints 1 star

### Example 2
- **Input:** `n = 2`
- **Output:**
  ```text
  * * 
  * 
  ```

### Example 3 (Minimum Boundary)
- **Input:** `n = 1`
- **Output:**
  ```text
  * 
  ```
- **Explanation:** For $N = 1$, the first and only row has 1 star.

---

## 4. Constraints & Input Specifications

- **Constraint:** $1 \le n \le 100$
- **Expected Time Complexity:** $\mathcal{O}(N^2)$ — You must print $\frac{N(N+1)}{2}$ characters. For $N=100$, this is 5,050 operations, taking $< 2$ milliseconds in Java.
- **Expected Auxiliary Space:** $\mathcal{O}(1)$ — Constant extra space.

---

## 5. Algorithmic Approach & Logic

There are two equally clean ways to write the loops:

### Approach A: Outer loop counts down ($N \to 1$)
```java
for (int i = n; i >= 1; i--) {
    for (int j = 1; j <= i; j++) {
        System.out.print("* ");
    }
    System.out.println();
}
```

### Approach B: Outer loop counts up ($1 \to N$) with decreasing inner boundary
```java
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n - i + 1; j++) {
        System.out.print("* ");
    }
    System.out.println();
}
```

Both approaches are optimal, executing in $\mathcal{O}(N^2)$ time and $\mathcal{O}(1)$ auxiliary space.

---

## 6. Common Pitfalls & Corner Cases

> [!WARNING]
> Keep the following in mind:

1. **Inner loop termination:**
   - If using Approach B, ensure the condition is `j <= n - i + 1`. An off-by-one (e.g. `j <= n - i`) will print $N-1$ stars on row 1 and miss the final star on row $N$.
2. **Spacing Flexibility:**
   - On Take U Forward, both space-separated (`* * * *`) and contiguous (`****`) patterns are accepted by judges. The test suite here normalizes both so either style passes cleanly.
3. **Boundary Condition ($N = 1$):**
   - Ensure the loop runs exactly once and terminates cleanly.

---

## 7. Implementation & Testing

- Starter file: [`Solution.java`](file:///i:/Projects/TUF-Pratice/problems/00-patterns/pattern-5/Solution.java)
- Method signature:
  ```java
  class Solution {
      public void pattern5(int n) {
          // Write code here
      }
  }
  ```
- Run verification tests:
  ```powershell
  .\test.ps1 pattern-5
  ```
