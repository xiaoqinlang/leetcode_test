package com.leetcode.test.class1;

/**
 * @Description 算法第11题
 *
 * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 *
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 *
 * @Author chenziyi
 * @Date 2022/5/20 14:03
 **/
public class Question11 {

    /**
     * 基本思路：
     *  趋向正方形的情况面积最大
     *
     * @param height
     * @return
     */
    // 只适合数据量小的情况
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 1; i < height.length; i ++){
            for (int j = 0;j < i;j ++){
                int length = i - j;
                int heigth = Math.min(height[i],height[j]);
                // 计算长和宽的差值
                int area = length * heigth;
                if (area > max){
                    max = area;
                }

            }
        }
        return max;
    }

    /**
     *
     * 通过测试
     * @param height
     * @return
     */
    public int maxArea2(int[] height){
        int regex1 = 0;
        int regex2 = height.length - 1;
        int area = 0;
        while (regex1 < regex2){
            int length = regex2 - regex1;
            int heigth = Math.min(height[regex1],height[regex2]);
            if (length * heigth > area){
                area = length * heigth;
            }
            if (height[regex1] < height[regex2]){
                regex1 ++;
            }else {
                regex2 --;
            }
        }

        return area;
    }

    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        int area = new Question11().maxArea2(arr);
        System.out.println(area);
    }
}
