package com.leetcode.test.class1;

/**
 * @Description 算法第六题：
 *
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 *
 *
 *
 * @Author chenziyi
 * @Date 2022/5/12 14:49
 **/
public class Question6 {

    /**
     * 基本思路：
     * 每一个循环都有 numRows + (numRows - 2)个字母
     * 以3行为例，所以第一行就是第1、5、9...个字母
     * 最后一行就是第3、7、11...个字母
     * 除去第一行和最后一行的字母剩下的都是中间的，主要解决中间几行的排序
     *(测试通过！！！)
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        // 间隔数
        int sub = numRows + numRows - 2;
        int length = s.length();
        if (sub == 0){
            return s;
        }
        StringBuffer sb = new StringBuffer();
        int currentIndex = 0;
        // 第一层循环层数
        for (int j = 0; j < numRows; j ++){
            // 第二层循环具体字符
            for (int i = 0; i < length; i ++){
                int num = i % sub;
                if (num == 0 && num == j){
                    // 当前字符在第一行
                    sb.append(s.charAt(i));
                }
                if (num > 0 && num < numRows && num == j){
                    // 当前余数是几就是第几行
                    sb.append(s.charAt(i));
                }
                if (num >= numRows && (sub - num) == j){
                    sb.append(s.charAt(i));
                }
            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String result = new Question6().convert("PAYPALISHIRING",4);
        System.out.println(result);
    }


    //********************************答案******************************************

    public String convert2(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }


}
