package com.leetcode.test.class1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 算法十二题
 * 罗马数字包含以下七种字符：I，V，X，L，C，D和M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 *
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 *
 *
 * 1 <= num <= 3999
 * @Author chenziyi
 * @Date 2022/5/23 10:09
 **/
public class Question12 {

    /**
     * 测试通过！！！
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"I");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(40,"XL");
        map.put(50,"L");
        map.put(90,"XC");
        map.put(100,"C");
        map.put(400,"CD");
        map.put(500,"D");
        map.put(900,"CM");
        map.put(1000,"M");
        int[] ex = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int regex = 0;
        int[] newEx = new int[ex.length];
        while (regex < ex.length){
            newEx[regex] = num / ex[regex];
            num = num % ex[regex];
            regex ++;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < newEx.length; i ++){
            for (int j = 0; j < newEx[i]; j ++){
                sb.append(map.get(ex[i]));
            }
        }


        return sb.toString();
    }

    public static void main(String[] args) {
        String result = new Question12().intToRoman(1994); // 正确答案 "MCMXCIV"
        System.out.println(result);
    }


    // *************************************官方答案********************************************


    public String intToRoman2(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }


}
