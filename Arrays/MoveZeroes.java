class Solution {
    public void moveZeroes(int[] nums) {
        int n=nums.length;
        int insertPos=0;
        for(int j=0;j<n;j++){
            if(nums[j]!=0){
                nums[insertPos]=nums[j];
                insertPos++;
            }
        }
        while(insertPos<n){
            nums[insertPos]=0;
            insertPos++;
        }
    }
}
