/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
       
       int left = 1;
       int right = n;

       while(left < right) {
        int mid = left + (right - left) / 2; // Calculating mid like this to avoid "overflow" in some cases. Can also potentially use (left + right) / 2

        if(isBadVersion(mid)) {
            right = mid; // We need to check if it's first bad or not, so need to check more on mid's left
        } else {
            left = mid + 1;
        }
       }
       return left; // Here, left will be equal to right, just return anything
    }
}