# Pattern 4: Triangle with Repeated Numbers

- **Topic:** Logic Building & Patterns
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Pattern 4](https://takeuforward.org/practice/dsa/pattern-4)
- **Target Language:** Java

---

## 1. Problem Description

Given an integer `n`, your task is to print a right-angled triangle pattern of height $N$ where each row consists of repeated numbers.

For any positive integer $N$:
- The pattern consists of exactly $N$ rows.
- The $i$-th row (where row indexing starts at $1$ and goes up to $N$) must contain the number $i$ repeated exactly $i$ times.
- Each number on the same row must be separated by a single space.
- Each row must be terminated with a newline.

---

## 2. Visual Representation & Grid Mechanics

For $N = 5$:

```text
Row 1 (i = 1):  1                          -> number 1, 1 time
Row 2 (i = 2):  2 2                        -> number 2, 2 times
Row 3 (i = 3):  3 3 3                      -> number 3, 3 times
Row 4 (i = 4):  4 4 4 4                    -> number 4, 4 times
Row 5 (i = 5):  5 5 5 5 5                  -> number 5, 5 times
```

### Grid Matrix Breakdown

| Row Index ($i$) | Number to Print | Repetitions ($j$) | Characters Printed |
| :---: | :---: | :---: | :--- |
| **1** | `1` | $1 \le j \le 1$ | `1` |
| **2** | `2` | $1 \le j \le 2$ | `2 2` |
| **3** | `3` | $1 \le j \le 3$ | `3 3 3` |
| **4** | `4` | $1 \le j \le 4$ | `4 4 4 4` |
| **5** | `5` | $1 \le j \le 5$ | `5 5 5 5 5` |

Total numbers printed: $\sum_{i=1}^{N} i = \frac{N(N + 1)}{2}$.

---

## 3. Examples & Walkthroughs

### Example 1
- **Input:** `n = 4`
- **Output:**
  ```text
  1
  2 2
  3 3 3
  4 4 4 4
  ```
- **Explanation:**
  - Row 1: prints `1` (1 time)
  - Row 2: prints `2` (2 times, space-separated)
  - Row 3: prints `3` (3 times, space-separated)
  - Row 4: prints `4` (4 times, space-separated)

### Example 2
- **Input:** `n = 2`
- **Output:**
  ```text
  1
  2 2
  ```

### Example 3 (Minimum Boundary)
- **Input:** `n = 1`
- **Output:**
  ```text
  1
  ```
- **Explanation:** A single row with the number `1` repeated once.

---

## 4. Constraints & Input Specifications

- **Constraint:** $1 \le n \le 100$
- **Expected Time Complexity:** $\mathcal{O}(N^2)$ — You must print $\frac{N(N+1)}{2}$ numbers. For $N=100$, this is roughly 5,050 operations, taking $< 2$ milliseconds in Java.
- **Expected Auxiliary Space:** $\mathcal{O}(1)$ — Constant extra space.

---

## 5. Algorithmic Approach & Logic

1. **Outer Loop (Row Count & Number Value):**
   - The outer loop variable `i` determines both the **row index** and the **number to be printed**:
     ```java
     for (int i = 1; i <= n; i++)
     ```
2. **Inner Loop (Repetition Count):**
   - The inner loop controls how many times `i` is printed on that row (exactly $i$ times):
     ```java
     for (int j = 1; j <= i; j++) {
         System.out.print(i + " ");
     }
     ```
3. **Newline Break:**
   - After printing the $i$ repetitions of number $i$:
     ```java
     System.out.println();
     ```

---

## 6. Common Pitfalls & Corner Cases

> [!WARNING]
> Keep the following in mind:

1. **Printing `j` instead of `i`:**
   - In Pattern 3, you printed the column index `j` (`1 2 3...`).
   - In Pattern 4, you must print the **row index `i`** (`3 3 3...`). Mixing them up is the #1 most common bug!
2. **0-based vs 1-based indexing:**
   - If starting at `i = 0`, you might print `0` on the first line. Start the outer loop at `i = 1`.
3. **Multi-Digit Numbers ($N \ge 10$):**
   - For $N = 10$, row 10 will print `10 10 10 ...` (ten times). Printing `i + " "` automatically handles two- and three-digit numbers.

---

## 7. Implementation & Testing

- Starter file: [`Solution.java`](file:///i:/Projects/TUF-Pratice/problems/00-patterns/pattern-4/Solution.java)
- Method signature:
  ```java
  class Solution {
      public void pattern4(int n) {
          // Write code here
      }
  }
  ```
- Run verification tests:
  ```powershell
  .\test.ps1 pattern-4
  ```
