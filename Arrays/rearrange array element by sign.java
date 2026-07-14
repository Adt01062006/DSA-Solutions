class Solution {
    public int[] rearrangeArray(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        int posindex=0,negindex=1;
        for(int i=0;i<n;i++){
            if(nums[i]<0){
                ans[negindex]=nums[i];
                negindex+=2;
            }else{
                ans[posindex]=nums[i];
                posindex+=2;
            }
        }
        return ans;
    }
}