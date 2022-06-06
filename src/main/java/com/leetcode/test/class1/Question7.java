package com.leetcode.test.class1;

/**
 * @Description 算法第七题
 * 使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *
 *
 * 函数myAtoi(string s) 的算法如下：
 *
 * 1、读入字符串并丢弃无用的前导空格
 * 2、检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 3、读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 4、将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 5、如果整数数超过 32 位有符号整数范围 [−2^31, 2^31− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 231− 1 的整数应该被固定为 231− 1 。
 * 6、返回整数作为最终结果。
 *
 * @Author chenziyi
 * @Date 2022/5/12 16:19
 **/
public class Question7 {
    public int myAtoi(String s) {
        int limit = Integer.MAX_VALUE;
        s = s.trim();
        boolean isUp = true;
        if (s.startsWith("-")){
            limit = Integer.MIN_VALUE;
            isUp = false;
            s = s.substring(1);
        }
        if (s.startsWith("+")){
            s = s.substring(1);
        }
        while (s.startsWith("0")){
            s = s.substring(1,s.length());
        }

        int regex = -1;
        int length = s.length();
        StringBuffer sb = new StringBuffer();

        while (regex + 1 < length){
            if (s.charAt(regex + 1) <= 57 // 0和9对应的ASCII值
                    && s.charAt(regex + 1) >= 48){
                sb.append(s.charAt(regex + 1));
                regex ++;
            }else {
                break;
            }

        }

        String result = sb.toString();
        // 如果大小超过临界值，则直接返回临界值
        if ("".equals(result.trim())){
            result = "0";
        }

        if (!isUp){
            result = "-" + result;
            if (Long.parseLong(result) < limit){
                return limit;
            }
        } else {
            if (Long.parseLong(result) > limit){
                return limit;
            }
        }

        return Integer.parseInt(result);
    }

    public static void main(String[] args) {
        int result = new Question7().myAtoi("   -42");
        System.out.println(result);
    }
}
