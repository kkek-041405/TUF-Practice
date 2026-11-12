# Pattern 6: Inverted Number Pyramid

- **Topic:** Logic Building & Patterns
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Pattern 6](https://takeuforward.org/practice/dsa/pattern-6)
- **Target Language:** Java

---

## 1. Problem Description

Given an integer `n`, your task is to print an inverted right-angled number pyramid of height $N$.

For any positive integer $N$:
- The pattern consists of exactly $N$ rows.
- The first row starts with numbers from `1` up to `N`.
- Each subsequent row contains numbers starting from `1` up to one less than the row above it, ending with just the single number `1` on the $N$-th row.
- In general, the $i$-th row (where row index $i$ goes from $1$ to $N$) prints numbers from `1` to $(N - i + 1)$.
- Each number on the same row must be separated by a single space.
- Each row must be terminated with a newline.

---

## 2. Visual Representation & Grid Mechanics

For $N = 5$:

```text
Row 1 (i = 1):  1 2 3 4 5                  -> 5 numbers (1 to 5)
Row 2 (i = 2):  1 2 3 4                    -> 4 numbers (1 to 4)
Row 3 (i = 3):  1 2 3                      -> 3 numbers (1 to 3)
Row 4 (i = 4):  1 2                        -> 2 numbers (1 to 2)
Row 5 (i = 5):  1                          -> 1 number  (1)
```

### Grid Matrix Breakdown

| Row Index ($i$) | Numbers in Row | Column Loop Range ($j$) | Characters Printed |
| :---: | :---: | :---: | :--- |
| **1** | $5$ | $1 \le j \le 5$ | `1 2 3 4 5` |
| **2** | $4$ | $1 \le j \le 4$ | `1 2 3 4` |
| **3** | $3$ | $1 \le j \le 3$ | `1 2 3` |
| **4** | $2$ | $1 \le j \le 2$ | `1 2` |
| **5** | $1$ | $1 \le j \le 1$ | `1` |

Total numbers printed: $\sum_{i=1}^{N} i = \frac{N(N + 1)}{2}$.

---

## 3. Examples & Walkthroughs

### Example 1
- **Input:** `n = 4`
- **Output:**
  ```text
  1 2 3 4
  1 2 3
  1 2
  1
  ```
- **Explanation:**
  - Row 1: `1 2 3 4`
  - Row 2: `1 2 3`
  - Row 3: `1 2`
  - Row 4: `1`

### Example 2
- **Input:** `n = 2`
- **Output:**
  ```text
  1 2
  1
  ```

### Example 3 (Minimum Boundary)
- **Input:** `n = 1`
- **Output:**
  ```text
  1
  ```
- **Explanation:** $N = 1$ contains only 1 row with the number `1`.

---

## 4. Constraints & Input Specifications

- **Constraint:** $1 \le n \le 100$
- **Expected Time Complexity:** $\mathcal{O}(N^2)$ — You must print $\frac{N(N+1)}{2}$ numbers. For $N=100$, this is roughly 5,050 operations, taking $< 2$ milliseconds in Java.
- **Expected Auxiliary Space:** $\mathcal{O}(1)$ — Constant extra space.

---

## 5. Algorithmic Approach & Logic

1. **Outer Loop (Row Count / Upper Limit):**
   - The outer loop determines how many numbers are printed in each row:
     ```java
     for (int i = n; i >= 1; i--)
     ```
2. **Inner Loop (Printing Sequence from 1 up to `i`):**
   - The inner loop prints the numbers from $1$ up to $i$:
     ```java
     for (int j = 1; j <= i; j++) {
         System.out.print(j + " ");
     }
     ```
3. **Newline Break:**
   - Move to the next row after printing the sequence:
     ```java
     System.out.println();
     ```

---

## 6. Common Pitfalls & Corner Cases

> [!WARNING]
> Keep the following in mind:

1. **Printing `i` vs `j`:**
   - Remember to print `j` (which counts `1, 2, 3...`) and not `i` (which is the row limit).
2. **Reverse Sequence vs Decreasing Length:**
   - The row length decreases, but each row always starts from `1` and counts *upwards* (`1 2 3...`). Do not print `5 4 3 2 1`.
3. **Multi-Digit Numbers ($N \ge 10$):**
   - When $N \ge 10$, the first row will include `1 2 3 4 5 6 7 8 9 10`. Using `j + " "` naturally formats single- and multi-digit numbers.

---

## 7. Implementation & Testing

- Starter file: [`Solution.java`](file:///i:/Projects/TUF-Pratice/problems/00-patterns/pattern-6/Solution.java)
- Method signature:
  ```java
  class Solution {
      public void pattern6(int n) {
          // Write code here
      }
  }
  ```
- Run verification tests:
  ```powershell
  .\test.ps1 pattern-6
  ```
