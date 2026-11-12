// TUF Problem: Pattern 1
// Write your code inside pattern1(int n).
// Do not modify the class name or method signature.

class Solution {
    public void pattern1(int n) {
        for(int i = 0;i<n;i++){
            for(int j =0;j<n;j++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
