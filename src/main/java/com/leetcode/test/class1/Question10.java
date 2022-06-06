package com.leetcode.test.class1;

import java.util.ArrayList;

/**
 * @Description 算法第十题
 *
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 *
 * 示例1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例2：
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例3：
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 *
 * @Author chenziyi
 * @Date 2022/5/13 17:07
 **/
public class Question10 {
    /**
     * 基本思路：
     * 首先字符串本身s的长度一定是大于等于p的
     *
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int regax1 = 0;
        int reges2 = 0;
        boolean isMatch = false;

        // 不包含符号的情况
        if (!p.contains(".") && !p.contains("*")){
            if (s.equals(p)){
                return true;
            } else {
                return false;
            }
        }
        // 如果p以* 开头，则直接返回false
        if (p.startsWith("*")){
            return isMatch;
        }

        for (int i = 0 ; i < p.length(); i ++){
            if (p.charAt(i) == s.charAt(0)){
                p = p.substring(i);
                break;
            }
        }
        int replaceNum = s.length() - p.length() + 1;

        while (regax1 + 1< s.length() && reges2 + 1 < p.length()){
            boolean nextIs = false;
            // 判断p下一个字符是否是*
            if (p.charAt(reges2 + 1) == '*'){
                nextIs = true;
            }

            // 当前字符相同
            if (s.charAt(regax1) == p.charAt(reges2)){
                // 说明s中可能会有>=1个的字符，具体会有多少个得看*后面的字符是什么
                if (nextIs){
                    int replace = 0;
                    // 此时s后面如果有相同字符则直接跳过
                    reges2 ++;
                    while (regax1 + 1< s.length() && s.charAt(regax1 + 1) == s.charAt(regax1) && replace++ < replaceNum){
                        regax1 ++;
                    }

                } else {
                    // 后面字符不相同且p不是* ，则指针继续后移
                    regax1 ++;
                    reges2 ++;
                    continue;
                }
                // TODO


                if (p.charAt(reges2 + 1) == '*' ){

                }
                if (regax1 + 1 < s.length() && reges2 + 1 < p.length()){
                    regax1 ++;
                    reges2 ++;
                }else {
                    break;
                }

            }else {
                if (p.charAt(reges2) == '.'){
                    regax1 ++;
                    reges2 ++;
                }else {
                    return false;

                }
            }



        }

//        while (reges2 < p.length() && regax1 < s.length()){
//
//
//            // 有 “.” 的情况
//            if (s.charAt(regax1) == p.charAt(reges2) || p.charAt(reges2) == '.'){
//                if (regax1 + 1 < s.length() && reges2 + 1 < p.length()){
//                    regax1 ++;
//                    reges2 ++;
//                } else {
//                    break;
//                }
//            }else {
//                // 不相等的情况分为几种：
//                // 1、不匹配，直接返回false
//                // 2、p中部分匹配，返回true
//                // 3、p和s完全匹配，返回true
//                if (p.charAt(reges2) != '*') {
////                    reges2 ++;
////                    continue;
//                    return false;
//                } else {
//                    // 需要计算两个字符串的长度差，看看*具体替换了几个字符
//                    for (int i = 0; i < s.length()-p.length() + 1;i ++){
//
//                    }
//                    if (p.charAt(reges2 - 1) == '.' || p.charAt(reges2 - 1) == s.charAt(regax1)) {
//                        if (regax1 + 1 == s.length())
//                            break;
//                        regax1 ++;
//                    }else {
//                        if (reges2 + 1 == p.length())
//                            break;
//                        reges2 ++;
//                    }
//                }
//
//            }
//        }

        if (regax1 == s.length() - 1 && reges2 == p.length() - 1){
            isMatch = true;
        }
        return isMatch;
    }


    /**
     * "aaa", "ab*ac*a"
     */
    public boolean isMatch2(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        int regax1 = 0;
        int reges2 = 0;
//        for(int i = 0; i < pLength; i ++){
//            if (p.charAt(i) == '*'){
//                if (i == 0){
//                    return false;
//                }
//                char replaceChar = p.charAt(i - 1);
//                if (!s.contains(String.valueOf(replaceChar))){
//
//                }
//            };
//
//        }
        while (regax1 + 1 < s.length() && reges2 + 1 < p.length()){
            if (s.charAt(regax1) == p.charAt(reges2)){

            }
        }




            return false;
    }


    /**
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * '*'跟它前面的那个元素组合之后可以看作0个或多个相同字符，
     * 比如输入"aaa", "ab*ac*a"，结果为true
     * 因为b*和c*都可以看作0个字符，去除之后就是aaa，结果为true
     * @param args
     */

    public static void main(String[] args) {
//        boolean result = new Question10().isMatch("aaa", "ab*ac*a");
//        System.out.println(result);

        System.out.println("ab*ac*a".replace("b*",""));
        System.out.println("aaa".contains("b"));
    }



}
