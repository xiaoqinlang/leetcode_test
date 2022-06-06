package com.leetcode.test.class1;

/**
 * @Description 算法21题
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 *
 * @Author chenziyi
 * @Date 2022/6/2 14:35
 **/
public class Question21 {
    /**
     * 基本思路：
     * 轮询两个链表，取出小的那个节点，存入新链表
     *
     * 测试通过
     * 但是占用内存较多
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newNode = new ListNode(0);
        ListNode firstNode = newNode;

        // 将一个链表插入到另一个链表当中
//        while (list2.next != null){
//            while (list1.next != null){
//                if (list1.val <= list2.val){
//                    list1 = list1.next;
//                }else {
//                    list1.next = list2;
//                }
//            }
//            list2 = list2.next;
//        }

        // 用一个新的单向链表来接受
        while (list1 != null || list2 != null){
            if (list1 == null){
                newNode.next = list2;
                break;
            }
            if (list2 == null){
                newNode.next = list1;
                break;
            }
            if (list1.val <= list2.val){
                newNode.next = list1;
                newNode = newNode.next;
                list1 = list1.next;
            }
            else {
                newNode.next = list2;
                newNode = newNode.next;
                list2 = list2.next;
            }


        }
        return firstNode.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;

        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;

        new Question21().mergeTwoLists(node1,node21);

    }
}


