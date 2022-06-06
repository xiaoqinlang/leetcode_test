package com.leetcode.test.class1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 算法第四题
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * 示例：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 *
 * @Author chenziyi
 * @Date 2022/5/10 9:51
 **/
public class Question4 {
    /**
     * 基本思路：首先和并数组后进行排序，然后根据数组长度是奇数还是偶数来取中位值
     * 本质上还是排序题
     * (测试通过！！！！！！！！！！！)
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 数组长度
        int length1 = nums1.length;
        int length2 = nums2.length;
        int regex1 = -1; // 数组1的指针
        int regex2 = -1; // 数组2的指针
        int num1 = -1;
        int num2 = -1;
        List<Integer> list = new ArrayList<>();
        int sumLength = length1 + length2;
        // 合并数组
        for (int i = 0; i < length1+length2; i ++){
            if (regex1 + 1 < length1){
                num1 = nums1[regex1 + 1]; // 取出当前索引值
            }else {
                num1 = 10*10*10*10*10*10; // 如果已经遍历完了直接改成最大值
            }

            if (regex2 + 1 < length2){
                num2 = nums2[regex2 + 1]; // 取出当前索引值
            }else {
                num2 = 10*10*10*10*10*10;
            }

            if (num1 < num2){
                list.add(num1);
                regex1 ++;
            }else if (num1 > num2){
                list.add(num2);
                regex2 ++;
            } else {
                list.add(num1);
                list.add(num2);
                regex1 ++;
                regex2 ++;
            }


        }
        // 计算中值
        if (sumLength % 2 == 1){
            // 奇数 取中间值即可
            return list.get(sumLength / 2);
        } else {
            // 偶数 取中间两值的平均值
            double mid = (double)list.get(sumLength / 2 - 1) + list.get(sumLength / 2);
            return mid / 2.0;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3,8};
        int[] nums2 = {2,4,6};
        double result = findMedianSortedArrays(nums1,nums2);
        System.out.println(result);
    }

    //********************************官方答案******************************************

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }








}
