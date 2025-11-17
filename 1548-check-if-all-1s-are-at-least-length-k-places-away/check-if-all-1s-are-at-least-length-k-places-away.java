class Solution {

    public boolean kLengthApart(int[] nums, int k) {
        int last = -1, curr = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (curr == -1) {
                    curr = i;
                } else {
                    last = curr;
                    curr = i;

                    // System.out.println(curr + " *** " + last);

                    if ((curr - last) - 1 < k) return false;
                }
            }
        }

        return true;
    }
}
