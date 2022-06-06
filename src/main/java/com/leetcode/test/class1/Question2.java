package com.leetcode.test.class1;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.math.BigInteger;
import java.util.*;

/**
 * @Description 算法第二题
 * @Author century
 * @Date 2022/5/7 14:26
 **/
public class Question2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Integer int1 = transferNode2Int(l1);
        Integer int2 = transferNode2Int(l2);
        Integer res = int1 + int2;
        return transferInt2Node(res);
    }

    /**
     * 将单向链表转成反序整数
     * @param node
     * @return
     */
    public static Integer transferNode2Int(ListNode node){
        // 1、将node数据存入后进先出队列

        Stack<Integer> stack = new Stack<>();
        while (node.next != null){
            stack.add(node.val);
            node = node.next;
        }
        stack.add(node.val);

        // 2、取出队列中数据并转成整数
        StringBuffer sb = new StringBuffer();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        String str = sb.toString();

        // TODO 缺陷：string过长会有转换错误 （舍弃）
        return Integer.parseInt(str);
    }

    /**
     * 整数转回反序的单向链表
     * @param i
     * @return
     */
    public static ListNode transferInt2Node(Integer i){
        ListNode firstNode = new ListNode();
        ListNode currentNode = new ListNode();
        List<Integer> list = new ArrayList<>();
        while (i >= 10){
            int i1 = i % 10;
            list.add(i1);
            i /= 10;
        }
        list.add(i);

        Iterator<Integer> iterator = list.iterator();
        if (iterator.hasNext()){
            // 第一个节点
            currentNode.val = iterator.next();
            firstNode = currentNode;
            while (iterator.hasNext()){
                currentNode.next = new ListNode(iterator.next());
                currentNode = currentNode.next;
            }
        }
        return firstNode;
    }

    public static void main(String[] args) {
        Long l = Long.valueOf("19999999999");
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.valueOf("12313"));
    }

//********************************答案******************************************
    /**
     * 官方解答
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0; // 进位值
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0; // 节点值为空默认填0
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }


    /**
     * 自己复习编写
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        // 第一个节点
        ListNode head = null;
        // 当前节点
        ListNode tail = null;
        // 进值
        int carry = 0;
        while (l1 != null || l2 != null){
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null){
                // 当前是头节点
                head = tail = new ListNode(sum % 10);
            } else {
                // 当前不是头节点，创建下一个节点并进行偏移
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }

            carry = sum / 10;
            // 参数节点各自进行偏移
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }
        // 最后一轮循环结束，如果还有进值，需要多创建一个节点
        if (carry > 0){
            tail.next = new ListNode(carry);
        }
        return head;
    }
}
