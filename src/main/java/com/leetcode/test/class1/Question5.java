package com.leetcode.test.class1;

/**
 * @Description 算法第五题：
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 *
 *
 * @Author chenziyi
 * @Date 2022/5/11 14:55
 **/
public class Question5 {
    /**
     * 基本思路：
     * 是否可将原字符串进行倒序后再和原字符串进行比较，重合部分就是所求结果
     *
     *  测试结果：
     *  短字符串可以通过测试，字符串过长会超时
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {


        String newStr = new StringBuffer(s).reverse().toString(); // 倒置字符串
        String subStr = "";
        String result = "";
        int regex = 0;
        while (regex + 1 < s.length()){
            for (int i = regex + 2; i <= s.length() ;i ++){
                // 获取不同的子字符串，查看在倒序字符串中是否包含
                subStr = s.substring(regex,i);
                if (newStr.contains(subStr)
                        && (subStr.length() > result.length())
                        && subStr.equals(new StringBuffer(s).reverse().toString())){
                    result = subStr;
                }
            }
            regex ++;
        }
        // 如果最后返回结果还是为“”，则返回第一个字符
        if ("".equals(result)){
            result = String.valueOf(s.toCharArray()[0]);
        }
        return result;
    }


    public static void main(String[] args) {
        String result = new Question5().longestPalindrome("aacabdkacaa");
        System.out.println(result);
    }


    //********************************答案******************************************

    /**
     * 解法1：官方
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 解法2：
     * @param s
     * @return
     */
    public String longestPalindrome3(String s) {
        if (s.equals(""))
            return "";
        String origin = s;
        String reverse = new StringBuffer(s).reverse().toString(); //字符串倒置
        int length = s.length();
        int[][] arr = new int[length][length];
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++) {
                if (origin.charAt(i) == reverse.charAt(j)) {
                    if (i == 0 || j == 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1; // 记录公共子串长度，加了多少个1就说明有多少个字符是连续相同的
                    }
                }
                if (arr[i][j] > maxLen) {
                    // 连续相同字符的最大长度
                    maxLen = arr[i][j];
                    maxEnd = i; //以 i 位置结尾的字符
                }

            }
        }

	    return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
    }

    /**
     * 解法3：（较优）
     * 遍历每一个下标，以这个下标为中心，利用「回文串」中心对称的特点，往两边扩散，看最多能扩散多远。
     * @param s
     * @return
     */
    public String longestPalindrome5(String s) {
        int len = s.length();
        if(len < 2) return s;

        int maxLen = 0;
        // 数组第一位记录起始位置，第二位记录长度
        int[] res = new int[2];
        for (int i = 0; i < s.length() - 1; i++) {
            int[] odd = centerSpread(s, i, i); //奇数回文字符串
            int[] even = centerSpread(s, i, i + 1); // 偶数回文字符串
            int[] max = odd[1] > even[1] ? odd : even; // 取得更长的那个
            if (max[1] > maxLen) {
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }

    private int[] centerSpread(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return new int[]{left + 1, right - left - 1};
    }




    //********************************自我巩固******************************************
    public String longestPalindrome4(String s) {
        if (s.equals(""))
            return "";
        int length = s.length();
        int[][] arr = new int[length][length];
        String origin = s; // 源字符串
        String reverse = new StringBuffer(s).reverse().toString(); // 倒序字符串
        int maxLen = 0;
        int maxEnd = 0;
        for (int i = 0; i < length; i ++){
            for (int j = 0; j < length; j ++){
                if (origin.charAt(i) == reverse.charAt(j)){
                    if (i == 0 || j == 0){
                        arr[i][j] = 1;
                    }else {
                        arr[i][j] = arr[i-1][j-1] + 1;
                    }
                }
                if (arr[i][j] > maxLen){
                    maxLen = arr[i][j];
                    maxEnd = i;
                }

            }
        }
        // 最后返回的字符串还要确保是回文字段
        String result = s.substring(maxEnd - maxLen + 1,maxEnd + 1);
        return s.substring(maxEnd - maxLen + 1,maxEnd + 1);
    }

    }
