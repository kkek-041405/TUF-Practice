// TUF Problem: Pattern 3
// Write your code inside pattern3(int n).
// Do not modify the class name or method signature.

class Solution {
    public void pattern3(int n) {
        for(int i = 1;i<=n;i++){
            for(int j =1;j<=i;j++){
                System.out.print(j+" ");
            }
            System.out.println("");
        }
    }
}
