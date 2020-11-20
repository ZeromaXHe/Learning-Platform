package leetcode.from101to200;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/11/20 10:26
 * @Description: 147. 对链表进行插入排序 | 难度：中等 | 标签：排序、链表
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * <p>
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * <p>
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution147 {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     *
     * 执行用时: 28 ms, 在所有 Java 提交中击败了 24.27% 的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了 80.75% 的用户
     * 
     * 看评论区，很多人偷鸡，直接用O(n*log n)的算法sort了。
     * （年轻人不讲码德啊，很快啊。我大意了，执行用时就拉跨了。）
     * 不过话说回来，自己的执行用时估计还是可以略微优化，用的指针有点多。
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        ListNode dummyTail = new ListNode(Integer.MAX_VALUE);
        ListNode end = head;
        dummyHead.next = head;
        ListNode insert = head.next;
        head.next = dummyTail;
        ListNode postInsert;
        ListNode index;
        ListNode preIndex;
        while (insert != null) {
            index = dummyHead.next;
            preIndex = dummyHead;
            postInsert = insert.next;
            while (index.val < insert.val) {
                preIndex = index;
                index = index.next;
            }
            if(index == dummyTail){
                end = insert;
            }
            preIndex.next = insert;
            insert.next = index;
            insert = postInsert;
        }
        end.next = null;
        return dummyHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

