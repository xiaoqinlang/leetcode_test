package com.leetcode.test.class1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Description 算法第十九题
 *  给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 范围：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * @Author chenziyi
 * @Date 2022/6/1 17:10
 **/
public class Question19 {
    /**
     * 首先一定得知道链表长度
     * 是否可以逆转链表方向
     *
     * 测试通过
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode firstNode = head;
        ListNode preNode = head;
        int length = 1;
        // 首先计算链表整体长度
        while (head.next != null){
            head = head.next;
            length ++;
        }
        if (length == 1 && n == 1){
            head = null;
            return head;
        }

        // 正数第几个
        int num = length - n + 1;
        // 获取要删除目标节点的前一个节点和后一个节点
        int i = 1;
        while (preNode.next != null && i < num - 1){
            preNode = preNode.next;
            i++;
        }
        // 如果要删除的节点恰好是最后一个
        if (num == 1 && length > 1){
            firstNode = firstNode.next;
        }
        if (num == length){
            preNode.next = null;
        }else {
            ListNode nextNode = preNode.next.next;
            preNode.next = nextNode;
        }

        return firstNode;
    }

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node.next = node2;

        new Question19().removeNthFromEnd(node,1);

    }


    // *************************************官方答案********************************************

    /**
     * 最简单的一种思路，和通过的一样
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }


    /**
     * 通过栈来实现
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }


}

