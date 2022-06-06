package com.leetcode.test.class1;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Description 算法第二十题
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 示例：
 * 输入：s = "()"
 * 输出：true
 *
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 输入：s = "(]"
 * 输出：false
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 *
 *
 * @Author chenziyi
 * @Date 2022/6/2 13:34
 **/
public class Question20 {

    /**
     * 基本思路：
     * 放入栈中，类似于消消乐，如果压入栈中的字符和上一个是一对，则消除，最后栈中剩下字符为0则说明为true
     *
     * 测试通过
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('(',-1);
        map.put(')',1);
        map.put('[',-2);
        map.put(']',2);
        map.put('{',-3);
        map.put('}',3);
        Deque<Integer> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i ++){
            Integer currentInt = map.get(chars[i]);
            if (stack.size() == 0){
                stack.push(currentInt);
            }else {
                Integer lastInt = stack.getFirst();
                if (lastInt < currentInt && currentInt + lastInt == 0){
                    stack.pop();
                }else {
                    stack.push(currentInt);
                }
            }
        }
        if (stack.size() == 0)
            return true;
        else
            return false;

    }

    public static void main(String[] args) {
        String str = "{[]}";
        System.out.println(new Question20().isValid(str));
    }


    // *************************************官方答案********************************************
    private static final Map<Character,Character> map2 = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid2(String s) {
        if(s.length() > 0 && !map2.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map2.containsKey(c)) stack.addLast(c);
            else if(map2.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }

}
