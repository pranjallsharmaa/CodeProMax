class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = sumOfSquares(n);

        if(fast == 1 || slow == fast) {
            return true;
        }

        while(fast != slow) {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        }

        return slow == 1;
    }

    public int sumOfSquares(int n) {
        int sum = 0;
        while(n > 0) {
            int rem = n % 10;
            sum += (rem * rem);
            n = n / 10;
        }
        return sum;
    }
}