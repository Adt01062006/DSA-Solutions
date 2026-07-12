class Solution {
    public int singleNumber(int[] nums) {
        int n=nums.length;
        int count=0;
        for(int i=0;i<n;i++){
            count=count^nums[i];
        }
        return count;
    }
}