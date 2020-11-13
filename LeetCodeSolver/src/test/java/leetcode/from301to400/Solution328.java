package leetcode.from301to400;

/**
 * @Author: zhuxi
 * @Time: 2020/11/13 19:30
 * @Description: 328.奇偶链表 | 难度：中等 | 标签：链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * <p>
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * <p>
 * 说明:
 * 1. 应当保持奇数节点和偶数节点的相对顺序。
 * 2. 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution328 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 执行用时： 0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38 MB, 在所有 Java 提交中击败了 92.14% 的用户
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if(head==null) return null;
        ListNode ptr = head;
        ListNode dummyHead = new ListNode();
        boolean isSingularNode = true;
        ListNode midHead = new ListNode();
        ListNode sigularTail = dummyHead;
        ListNode evenTail = midHead;
        // 将原链表拆成奇数节点链表和偶数节点链表
        while(ptr!=null){
            if(isSingularNode){
                sigularTail.next = ptr;
                sigularTail = ptr;
                ptr = ptr.next;
                // 下一个节点是偶数节点
                isSingularNode = false;
                sigularTail.next = null;
            }else{
                evenTail.next = ptr;
                evenTail = ptr;
                ptr = ptr.next;
                // 下一个节点是奇数节点
                isSingularNode = true;
                evenTail.next = null;
            }
        }
        // 组装奇数节点链表和偶数节点链表为一个链表
        sigularTail.next = midHead.next;

        return dummyHead.next;
    }

//    错误理解题意了：
//    public ListNode oddEvenList(ListNode head) {
//        if (head == null) return null;
//        ListNode dummyHead = new ListNode();
//        dummyHead.next = head;
//        ListNode singularPtr = head;
//        ListNode beforeSingularPtr = dummyHead;
//        ListNode evenPtr = head.next;
//        ListNode beforeEvenPtr = head;
//        while(singularPtr!=null && evenPtr!=null) {
//            // 找到错位的奇数节点
//            while (singularPtr.val % 2 == 1) {
//                if (singularPtr.next != null && singularPtr.next.next != null) {
//                    beforeSingularPtr = singularPtr.next;
//                    singularPtr = beforeSingularPtr.next;
//                } else {
//                    return dummyHead.next;
//                }
//            }
//            // 找到错位的偶数节点
//            while (evenPtr.val % 2 == 0) {
//                if (evenPtr.next != null && evenPtr.next.next != null) {
//                    beforeEvenPtr = evenPtr.next;
//                    evenPtr = beforeEvenPtr.next;
//                } else {
//                    return dummyHead.next;
//                }
//            }
//
//            // 交换
//            beforeSingularPtr.next = evenPtr;
//            beforeEvenPtr.next = singularPtr;
//            ListNode tmp = evenPtr.next;
//            evenPtr.next = singularPtr.next;
//            singularPtr.next = tmp;
//            tmp = evenPtr;
//            evenPtr = singularPtr;
//            singularPtr = tmp;
//        }
//        return dummyHead.next;
//    }

    public static void main(String[] args) {
        Solution328 solution328 = new Solution328();
        // case 1
        ListNode list1_1 = new ListNode(1);
        ListNode list1_2 = new ListNode(2);
        ListNode list1_3 = new ListNode(3);
        ListNode list1_4 = new ListNode(4);
        ListNode list1_5 = new ListNode(5);
        list1_1.next = list1_2;
        list1_2.next = list1_3;
        list1_3.next = list1_4;
        list1_4.next = list1_5;
        System.out.println("case 1:");
        // 1->2->3->4->5
        System.out.println(printList(list1_1));
        // 1->3->5->2->4
        System.out.println(printList(solution328.oddEvenList(list1_1)));
        // case 2
        ListNode list2_1 = new ListNode(2);
        ListNode list2_2 = new ListNode(1);
        ListNode list2_3 = new ListNode(3);
        ListNode list2_4 = new ListNode(5);
        ListNode list2_5 = new ListNode(6);
        ListNode list2_6 = new ListNode(4);
        ListNode list2_7 = new ListNode(7);
        list2_1.next = list2_2;
        list2_2.next = list2_3;
        list2_3.next = list2_4;
        list2_4.next = list2_5;
        list2_5.next = list2_6;
        list2_6.next = list2_7;
        System.out.println("case 2:");
        // 2->1->3->5->6->4->7
        System.out.println(printList(list2_1));
        // 2->3->6->7->1->5->4
        System.out.println(printList(solution328.oddEvenList(list2_1)));
        // case 3
        ListNode list3_1 = new ListNode(100);
        System.out.println("case 3:");
        // 100
        System.out.println(printList(list3_1));
        // 100
        System.out.println(printList(solution328.oddEvenList(list3_1)));
    }

    private static String printList(ListNode head){
        StringBuilder sb = new StringBuilder();
        if(head==null){
            return "head is null";
        }else{
            sb.append(head.val);
        }
        while(head.next!=null){
            sb.append("->");
            head = head.next;
            sb.append(head.val);
        }
        return sb.toString();
    }
}
