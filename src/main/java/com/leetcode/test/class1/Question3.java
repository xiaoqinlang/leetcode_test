package com.leetcode.test.class1;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description 算法第三题：
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例：
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * @Author chenziyi
 * @Date 2022/5/9 14:22
 **/
public class Question3 {
    /**
     * 基本思路：计算两个相同字符的间隔（有缺陷）
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0){
            return 0;
        }
        int repeat = 0;// 重复次数
        int maxCount = 1;
        int count = 1;

        // 计算两个相同字符间的间隔数
        char start = ' ';
        char[] arr = s.toCharArray();
        for (int i = 0 ; i < arr.length; i++){
            // 如果某个字符开始后面几个字符一直不等于起始的字符，那么进行累加
            // 如果轮询到某个字符和起始相同，则记录数字并重新开始计数
            start = arr[i];
            for (int j = i; j < arr.length; j++){
                if (arr[j] != start){
                    count ++;
                    // 如果此时是最后一个字符
                    if ((j == arr.length - 1)){
                        if (count > maxCount){
                            maxCount = count;
                        }
                        count = 1;
                    }
                }else {
                    if (j != i){
                        repeat ++; // 如果有相同的
                    }
                    if (count > maxCount){
                        maxCount = count;
                    }
                    count = 1;
                }

            }

        }
        if (repeat == 0){
            return arr.length;
        }

        return maxCount;
    }



    public static void main(String[] args) {

        int count = lengthOfLongestSubstring2("abcabcdd");
        System.out.println(count);
    }

    //********************************答案******************************************

    /**
     * 官方答案
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


    public static int lengthOfLongestSubstring3(String s) {
        Set<Character> occ = new HashSet<>();
        int length = s.length();
        int rk = -1,ans = 0;
        for (int i = 0; i < length; i ++){
            if (i != 0){
                // 移除前一个字符，类似一个框往后移动
                occ.remove(s.charAt(i - 1));
            }
            // 往右移动指针
            while (rk + 1 < length && !occ.contains(s.charAt(rk + 1))){
                occ.add(s.charAt(rk + 1));
                rk ++;
            }
            ans = Math.max(ans,rk - i + 1);
        }
        return ans;
    }


    }
