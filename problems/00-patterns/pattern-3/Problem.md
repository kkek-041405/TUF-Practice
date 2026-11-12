# Pattern 3: Right-Angled Number Pyramid

- **Topic:** Logic Building & Patterns
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Pattern 3](https://takeuforward.org/practice/dsa/pattern-3)
- **Target Language:** Java

---

## 1. Problem Description

Given an integer `n`, your task is to print a right-angled number pyramid pattern of height $N$.

For any positive integer $N$:
- The pattern consists of exactly $N$ rows.
- The $i$-th row (where row indexing starts at $1$ and goes up to $N$) must contain numbers starting from `1` incrementally up to `i`.
- Each number on the same row must be separated by a single space.
- Each row must be followed by a newline.

---

## 2. Visual Representation & Grid Mechanics

For $N = 5$:

```text
Row 1 (i = 1):  1                          -> 1 number
Row 2 (i = 2):  1 2                        -> 2 numbers
Row 3 (i = 3):  1 2 3                      -> 3 numbers
Row 4 (i = 4):  1 2 3 4                    -> 4 numbers
Row 5 (i = 5):  1 2 3 4 5                  -> 5 numbers
```

### Grid Matrix Breakdown

| Row Index ($i$) | Loop Range ($j$) | Characters Printed | Number Count |
| :---: | :---: | :--- | :---: |
| **1** | $1 \le j \le 1$ | `1` | 1 |
| **2** | $1 \le j \le 2$ | `1 2` | 2 |
| **3** | $1 \le j \le 3$ | `1 2 3` | 3 |
| **4** | $1 \le j \le 4$ | `1 2 3 4` | 4 |
| **5** | $1 \le j \le 5$ | `1 2 3 4 5` | 5 |

Total numbers printed: $\sum_{i=1}^{N} i = \frac{N(N + 1)}{2}$.

---

## 3. Examples & Walkthroughs

### Example 1
- **Input:** `n = 4`
- **Output:**
  ```text
  1
  1 2
  1 2 3
  1 2 3 4
  ```
- **Explanation:**
  - Row 1: prints `1`
  - Row 2: prints `1` then `2`
  - Row 3: prints `1`, `2`, `3`
  - Row 4: prints `1`, `2`, `3`, `4`

### Example 2
- **Input:** `n = 2`
- **Output:**
  ```text
  1
  1 2
  ```

### Example 3 (Minimum Boundary)
- **Input:** `n = 1`
- **Output:**
  ```text
  1
  ```
- **Explanation:** The pyramid consists of a single number `1` on a single line.

---

## 4. Constraints & Input Specifications

- **Constraint:** $1 \le n \le 100$
- **Expected Time Complexity:** $\mathcal{O}(N^2)$ — You must print $\frac{N(N+1)}{2}$ numbers. For $N=100$, this is roughly 5,050 operations, well within the standard 1-second execution budget ($\approx 10^8$ operations).
- **Expected Auxiliary Space:** $\mathcal{O}(1)$ — No extra data structures (lists, arrays) are needed; only loop counter variables.

---

## 5. Algorithmic Approach & Logic

To solve any pattern problem, break it down into 3 fundamental questions:

1. **How many rows are there?**
   - There are $N$ rows. This controls the **outer loop**:
     ```java
     for (int i = 1; i <= n; i++)
     ```
2. **What needs to be printed in row $i$?**
   - In row $i$, we need to print numbers starting from $1$ up to $i$. This controls the **inner loop**:
     ```java
     for (int j = 1; j <= i; j++)
     ```
3. **What needs to happen after each row?**
   - Once all numbers for row $i$ are printed, move the cursor to the next line:
     ```java
     System.out.println();
     ```

---

## 6. Common Pitfalls & Corner Cases

> [!WARNING]
> Pay close attention to these common mistakes when implementing:

1. **Printing `i` instead of `j`:**
   - If you print `i + " "`, you will get rows like `2 2`, `3 3 3` (Pattern 4).
   - This problem requires numbers from $1$ to $i$, so print the column index `j + " "`.
2. **0-based vs 1-based indexing:**
   - If you start `j = 0`, you will print `0 1 2 ...`, which is incorrect. Start from `1`.
3. **Two-Digit Numbers ($N \ge 10$):**
   - When $N \ge 10$, numbers like `10`, `11`, etc. have two digits. Printing with `j + " "` naturally handles this without any special formatting.
4. **Spacing:**
   - Make sure each number has a space (e.g. `System.out.print(j + " ")`).
   - Do not omit the space between numbers (printing `1234` instead of `1 2 3 4`).

---

## 7. Implementation & Testing

- Starter file: [`Solution.java`](file:///i:/Projects/TUF-Pratice/problems/00-patterns/pattern-3/Solution.java)
- Method signature:
  ```java
  class Solution {
      public void pattern3(int n) {
          // Write code here
      }
  }
  ```
- Run verification tests:
  ```powershell
  .\test.ps1 pattern-3
  ```
