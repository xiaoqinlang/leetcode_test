package com.leetcode.test.class1;

import java.util.*;

/**
 * @Description 算法第十七题
 *
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * 97～122号为26个小写英文字母
 *
 * @Author chenziyi
 * @Date 2022/5/31 15:19
 **/
public class Question17 {
    /**
     * 基本思路：有n个数字就有3^n种组合
     * 这种情况单层的嵌套循环无法完成，可以使用递归来实现
     *
     * 测试通过！！！
     * 但是感觉比较浪费内存，创建的list对象过多
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        char[] nums = digits.toCharArray();
        int regex = 0;
        List<String> list = new ArrayList<>();

        return appendStr(nums,regex,list);
    }

    public List<String> appendStr(char[] nums,int regex,List<String> list){
        if (regex == nums.length){
            return list;
        }
        List<String> newList = new ArrayList<>();
        char currentChar = nums[regex];
        int currentInt = Integer.parseInt(String.valueOf(currentChar));
        String oldStr = "";
        int start = (currentInt-2) * 3 + 97;
        int max = (currentInt-1) * 3 + 97;

        if (currentInt == 7){
            max ++;
        }
        if (currentInt == 8){
            start ++;
            max ++;
        }
        if (currentInt == 9){
            start++;
            max+=2;
        }
        for (int i = start; i < max; i ++){
            char c = (char) i; // 2(a b c)  3(d e f)
            if (regex == 0){
                newList.add(String.valueOf(c));
            }else {
                for (int j = 0;j < list.size();j ++){
                    //之前数组的每一个都有3种拼接可能
                    oldStr = list.get(j);
                    newList.add(oldStr + c);
                }
            }

        }
        list = null;
        return appendStr(nums,++regex,newList);

    }

    public static void main(String[] args) {

        System.out.println(new Question17().letterCombinations("7").toString());
//        System.out.println(Integer.parseInt(String.valueOf('3')));
    }


    // *************************************官方答案********************************************
    public List<String> letterCombinations2(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }




}
