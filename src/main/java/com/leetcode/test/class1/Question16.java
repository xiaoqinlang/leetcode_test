package com.leetcode.test.class1;

import java.util.Arrays;

/**
 * @Description 算法第16题
 * 给你一个长度为 n 的整数数组nums和 一个目标值target。请你从 nums 中选出三个整数，使它们的和与target最接近。
 *
 * 返回这三个数的和。
 *
 * 假定每组输入只存在恰好一个解。
 *
 *
 * 示例
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 *
 * 范围限制：
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 *
 *
 * @Author chenziyi
 * @Date 2022/5/31 13:22
 **/
public class Question16 {
    /**
     * 基本思路：任选3个数，计算与目标值的差值的绝对值，取最小值
     * 测试通过
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        // 为了计算最接近的值，从目标值开始向两边循环，这样可以尽量减少循环次数
        Arrays.sort(nums);
        int length = nums.length;
        if (length <= 3){
            int sum = 0;
            for (int num : nums){
                sum +=num;
            }
            return sum;
        }
        if (nums[length - 1] == nums[0]){
            return nums[0] * 3;
        }
        int currentSum = target;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i ++){
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int L = i + 1;
            int R = nums.length - 1;

            while (L < R){
                // 三个数之和
                int sum = nums[i] + nums[L] + nums[R];
                // 跟目标数的距离
                int distance = sum - target;
                if (distance == 0)
                    return target;
                else if (distance < 0){
                    if (-distance < minDistance){
                        minDistance = -distance;
                        currentSum = sum;
                    }
                    L ++;
                } else {
                    if (distance < minDistance){
                        minDistance = distance;
                        currentSum = sum;
                    }
                    R --;
                }
            }
        }
        return currentSum;
    }


    public static void main(String[] args) {

        int[] nums = {1,1,1,0};
        int target = -100;

        System.out.println(new Question16().threeSumClosest(nums,target));
    }



    // *************************************官方答案********************************************
    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int best = 10000000;

        // 枚举 a
        for (int i = 0; i < n; ++i) {
            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 使用双指针枚举 b 和 c
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                // 如果和为 target 直接返回答案
                if (sum == target) {
                    return target;
                }
                // 根据差值的绝对值来更新答案
                if (Math.abs(sum - target) < Math.abs(best - target)) {
                    best = sum;
                }
                if (sum > target) {
                    // 如果和大于 target，移动 c 对应的指针
                    int k0 = k - 1;
                    // 移动到下一个不相等的元素
                    while (j < k0 && nums[k0] == nums[k]) {
                        --k0;
                    }
                    k = k0;
                } else {
                    // 如果和小于 target，移动 b 对应的指针
                    int j0 = j + 1;
                    // 移动到下一个不相等的元素
                    while (j0 < k && nums[j0] == nums[j]) {
                        ++j0;
                    }
                    j = j0;
                }
            }
        }
        return best;
    }




}
