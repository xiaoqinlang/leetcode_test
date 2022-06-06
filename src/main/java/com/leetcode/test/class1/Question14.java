package com.leetcode.test.class1;

/**
 * @Description 算法第14题
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 *
 * @Author chenziyi
 * @Date 2022/5/27 15:06
 **/
public class Question14 {
    /**
     * 通过测试
     * 但是循环次数有点多
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {

        // 第一个字符串

        String firstStr = strs[0];
        String sameStr = firstStr;
        boolean isEnd = false;
        for (int i = 1; i <= firstStr.length(); i ++){
            if (!isEnd){
                String subStr = firstStr.substring(0,i);
                for (String str : strs){
                    if (!str.startsWith(subStr)){
                        sameStr = firstStr.substring(0,i-1);
                        isEnd = true;
                        break;
                    }
                }
            }else
                return sameStr;
        }

        return sameStr;
    }

    /**
     * 只循环一次
     * 当前字符串和后一个比较，计算相同子字符串，跟sameStr比较，再去取相同字符串
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {

        int regex = 0;
        String sameStr = strs[0] ;
        while (regex + 1 < strs.length){
            String currentStr = strs[regex];
            String nextStr = strs[regex + 1];
            char[] chars1 = currentStr.toCharArray();
            char[] chars2 = nextStr.toCharArray();
            int minLength = Math.min(chars1.length,chars2.length);
            int k = 0;
            String subStr = "";
            while (k < minLength){
                if (chars1[k] != chars2[k]){
                    break;
                };
                k++;
                subStr = strs[regex].substring(0,k);

            }
            // 比较sameStr和subStr
            if (subStr.length() < sameStr.length()){
                sameStr = subStr;
            }
            regex ++;
        }

        return sameStr;
    }

    public static void main(String[] args) {
        String[] arr = {"flower","flow","flight"};
        System.out.println(new Question14().longestCommonPrefix2(arr));
        System.out.println("ABCDEFG".compareTo("ABKDFG"));
    }
}
