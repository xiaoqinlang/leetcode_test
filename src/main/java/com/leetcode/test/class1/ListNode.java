package com.leetcode.test.class1;

/**
 * @Description 单向链表
 * @Author chenziyi
 * @Date 2022/6/2 14:40
 **/
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}