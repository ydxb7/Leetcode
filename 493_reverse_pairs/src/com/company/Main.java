package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] nums = {2,4,3,5,1};

        Solution solution = new Solution();
        int ans = solution.reversePairs(nums);
        System.out.println(ans);
    }

}

class Solution {
    int ans;

    public int reversePairs(int[] nums) {
        int N = nums.length;
        if(N == 0) return 0;

        int[] temp = new int[N];
        ans = 0;
        mergeSort(0, N - 1, nums, temp);
        return ans;
    }

    private void mergeSort(int st, int ed, int[] nums, int[] temp){
        if(st >= ed) return;

        int mid = (st + ed) / 2;
        mergeSort(st, mid, nums, temp);
        mergeSort(mid + 1, ed, nums, temp);

        merge(st, mid, ed, nums, temp);
    }

    private void merge(int st, int mid, int ed, int[] nums, int[] temp){
        int l = st;
        int r = mid + 1;
        int i = st;
        while(l <= mid && r <= ed){
            if((long)nums[l] <= (long)nums[r] * 2){
                l++;
            } else {
                ans += mid - l + 1;
                r++;
            }
        }

        // sort
        l = st;
        r = mid + 1;
        i = st;
        while(l <= mid && r <= ed){
            if(nums[l] <= nums[r]){
                temp[i++] = nums[l++];
            } else {
                temp[i++] = nums[r++];
            }
        }

        while(l <= mid){
            temp[i++] = nums[l++];
        }

        while(r <= ed){
            temp[i++] = nums[r++];
        }

        for(i = st; i <= ed; i++){
            nums[i] = temp[i];
        }
    }
}