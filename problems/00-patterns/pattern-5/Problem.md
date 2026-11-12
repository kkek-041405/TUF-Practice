# Pattern 5: Inverted Right-Angled Triangle Star Pattern

- **Topic:** Logic Building & Patterns
- **Difficulty:** Easy
- **Platform Source:** [Take U Forward - Pattern 5](https://takeuforward.org/practice/dsa/pattern-5)
- **Target Language:** Java

---

## 1. Problem Description

Given an integer `n`, your task is to print an inverted right-angled triangle pattern made of asterisks (`*`) of height $N$.

For any positive integer $N$:
- The pattern consists of exactly $N$ rows.
- The first row contains $N$ stars.
- Each subsequent row contains one fewer star than the row above it, ending with 1 star on the $N$-th row.
- In general, the $i$-th row (where row index $i$ goes from $1$ to $N$) contains $(N - i + 1)$ stars.
- Each star on the same row must be separated by a single space.
- Each row must be terminated with a newline.

---

## 2. Visual Representation & Grid Mechanics

For $N = 5$:

```text
Row 1 (i = 1):  * * * * *                  -> 5 stars (N - 1 + 1)
Row 2 (i = 2):  * * * *                    -> 4 stars (N - 2 + 1)
Row 3 (i = 3):  * * *                      -> 3 stars (N - 3 + 1)
Row 4 (i = 4):  * *                        -> 2 stars (N - 4 + 1)
Row 5 (i = 5):  *                          -> 1 star  (N - 5 + 1)
```

### Grid Matrix Breakdown

| Row Index ($i$) | Stars in Row | Column Loop Range ($j$) | Characters Printed |
| :---: | :---: | :---: | :--- |
| **1** | $5$ | $1 \le j \le 5$ | `* * * * *` |
| **2** | $4$ | $1 \le j \le 4$ | `* * * *` |
| **3** | $3$ | $1 \le j \le 3$ | `* * *` |
| **4** | $2$ | $1 \le j \le 2$ | `* *` |
| **5** | $1$ | $1 \le j \le 1$ | `*` |

Total stars printed: $\sum_{i=1}^{N} i = \frac{N(N + 1)}{2}$.

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
  - Row 1: 4 stars
  - Row 2: 3 stars
  - Row 3: 2 stars
  - Row 4: 1 star

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
- **Explanation:** $N = 1$ contains only 1 row with 1 star.

---

## 4. Constraints & Input Specifications

- **Constraint:** $1 \le n \le 100$
- **Expected Time Complexity:** $\mathcal{O}(N^2)$ — You must print $\frac{N(N+1)}{2}$ stars. For $N=100$, this is 5,050 iterations, taking $< 2$ milliseconds in Java.
- **Expected Auxiliary Space:** $\mathcal{O}(1)$ — Constant extra space.

---

## 5. Algorithmic Approach & Logic

There are two equally clean ways to structure the nested loops:

### Approach A: Decrementing Outer Loop
Run the outer loop backwards from $N$ down to $1$:
```java
for (int i = n; i >= 1; i--) {
    for (int j = 1; j <= i; j++) {
        System.out.print("* ");
    }
    System.out.println();
}
```

### Approach B: Forward Outer Loop with Formula
Run the outer loop from $0$ to $N - 1$, and print $(N - i)$ stars:
```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n - i; j++) {
        System.out.print("* ");
    }
    System.out.println();
}
```

---

## 6. Common Pitfalls & Corner Cases

> [!WARNING]
> Keep the following in mind:

1. **Loop Termination Condition:**
   - When decrementing (`i--`), ensure the loop condition is `i >= 1` (or `i > 0`), not `i > 1` (which would miss the final 1-star row).
2. **Spacing:**
   - Use `System.out.print("* ")` to ensure spaces between stars as shown in TUF diagrams.
3. **Boundary Condition ($N = 1$):**
   - Ensure $N = 1$ prints exactly one star and doesn't run into zero or negative iterations.

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
