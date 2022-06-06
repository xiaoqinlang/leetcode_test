package com.leetcode.test.class1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 算法第十八题
 *  给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。
 *  请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d< n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 *  示例：
 *  输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 *  范围：
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 * @Author chenziyi
 * @Date 2022/6/1 14:53
 **/
public class Question18 {
    /**
     * 基本思路：
     * 使用3指针移动
     *
     * 测试通过
     * 但性能较差，占用内存也较高
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
//        if (nums[0] == nums[length - 1] && length >= 4){
//            List<Integer> integerList = new ArrayList<>();
//            for (int i = 0; i < 4;i ++){
//                integerList.add(nums[i]);
//            }
//            result.add(integerList);
//            return result;
//        }
        if (nums.length < 4){
//            List<Integer> integerList = new ArrayList<>();
//            for (int num : nums){
//                integerList.add(num);
//            }
//            result.add(integerList);
            return result;
        }
        int a = 0;


        while (a < nums.length - 3){
            int b = a + 1;
            while (b < nums.length - 2){
                int c = b + 1;
                while (c < nums.length - 1){
                    int d = c + 1;
                    while (d < nums.length){
                        int sum = nums[a] + nums[b] + nums[c] + nums[d];
                        if (sum == target){
                            List<Integer> newList = new ArrayList<>();
                            newList.add(nums[a]);
                            newList.add(nums[b]);
                            newList.add(nums[c]);
                            newList.add(nums[d]);
                            result.add(newList);
                        }
                        while (d<(length-1) && nums[d]==nums[d+1]) d++;
                        d ++;
                    }
                    while (c<(length-2) && nums[c]==nums[c+1]) c++;
                    c++;
                }
                while (b<(length-3) && nums[b]==nums[b+1]) b++;
                b ++;
            }
            while (a<(length-4) && nums[a]==nums[a+1]) a++;
            a ++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,0,0};
        int target = 1;
        System.out.println(new Question18().fourSum(nums,target).toString());
    }

    // *************************************官方答案********************************************

    /**
     * 采用左右双指针
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }


}
