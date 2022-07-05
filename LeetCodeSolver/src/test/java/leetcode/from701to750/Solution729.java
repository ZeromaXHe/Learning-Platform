package leetcode.from701to750;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author zhuxi
 * @apiNote 729. 我的日程安排表 I | 难度：中等 | 标签：设计、线段树、二分查找、有序集合
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * <p>
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * <p>
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。
 * <p>
 * 实现 MyCalendar 类：
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 * <p>
 * 示例：
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 * <p>
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。
 * <p>
 * 提示：
 * 0 <= start < end <= 109
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/my-calendar-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2022/7/5 11:37
 */
public class Solution729 {
    /**
     * 执行用时：25 ms, 在所有 Java 提交中击败了 59.21% 的用户
     * 内存消耗：41.9 MB, 在所有 Java 提交中击败了 82.18% 的用户
     * 通过测试用例：107 / 107
     */
    class MyCalendar {
        private TreeSet<int[]> booked;

        public MyCalendar() {
            booked = new TreeSet<>(Comparator.comparingInt(arr -> arr[0]));
        }

        public boolean book(int start, int end) {
            if (booked.isEmpty()) {
                booked.add(new int[]{start, end});
                return true;
            }
            int[] bigger = booked.ceiling(new int[]{end, end});
            int[] test = bigger == null ? booked.last() : booked.lower(bigger);
            if (test == null || test[1] <= start) {
                booked.add(new int[]{start, end});
                return true;
            }
            return false;
        }
    }

    /**
     * 执行用时：22 ms, 在所有 Java 提交中击败了 84.54% 的用户
     * 内存消耗：42.5 MB, 在所有 Java 提交中击败了 18.52% 的用户
     * 通过测试用例：107 / 107
     */
    class MyCalendar_TreeMap {
        private TreeMap<Integer, Integer> booked;

        public MyCalendar_TreeMap() {
            booked = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            if (booked.isEmpty()) {
                booked.put(start, end);
                return true;
            }
            Integer bigger = booked.ceilingKey(end);
            Integer test = bigger == null ? booked.lastKey() : booked.lowerKey(bigger);
            if (test == null || booked.get(test) <= start) {
                booked.put(start, end);
                return true;
            }
            return false;
        }
    }

    /**
     * 执行用时：39 ms, 在所有 Java 提交中击败了 51.97% 的用户
     * 内存消耗：49 MB, 在所有 Java 提交中击败了 8.56% 的用户
     * 通过测试用例：107 / 107
     */
    class MyCalendar_SegmentTree {
        private final SegmentTree root = new SegmentTree();
        private final int N = (int) 1e9;

        public MyCalendar_SegmentTree() {

        }

        public boolean book(int start, int end) {
            if (root.query(0, N, start, end - 1) != 0) {
                return false;
            }
            root.update(0, N, start, end - 1, 1);
            return true;
        }
    }

    /**
     * 力扣提交时不允许 MyCalendar 并列的类加 static，可以放到 MyCalendar 下加 statitc
     */
    class SegmentTree {
        // 左子节点
        SegmentTree left;
        // 右子节点
        SegmentTree right;
        // 当前节点值
        int val;
        // 懒惰标记值
        int lazy;

        public void update(int start, int end, int l, int r, int val) {
            if (l <= start && end <= r) {
                this.val += val;
                this.lazy += val;
                return;
            }
            int mid = (start + end) >> 1;
            pushDown();
            if (l <= mid) {
                this.left.update(start, mid, l, r, val);
            }
            if (r > mid) {
                this.right.update(mid + 1, end, l, r, val);
            }
            pushUp();
        }

        public int query(int start, int end, int l, int r) {
            if (l <= start && end <= r) {
                return this.val;
            }
            int mid = (start + end) >> 1;
            int ans = 0;
            pushDown();
            if (l <= mid) ans = this.left.query(start, mid, l, r);
            if (r > mid) ans = Math.max(ans, this.right.query(mid + 1, end, l, r));
            return ans;
        }

        private void pushUp() {
            // 每个节点存的是当前区间的最大值
            this.val = Math.max(this.left.val, this.right.val);
        }

        private void pushDown() {
            // 没有子节点的话，建立新的子节点
            if (this.left == null) {
                this.left = new SegmentTree();
            }
            if (this.right == null) {
                this.right = new SegmentTree();
            }
            if (this.lazy == 0) {
                return;
            }
            // 将更新值 lazy 向下推
            this.left.val += this.lazy;
            this.right.val += this.lazy;
            this.left.lazy += this.lazy;
            this.right.lazy += this.lazy;
            this.lazy = 0;
        }
    }

    /**
     * 执行用时：13 ms, 在所有 Java 提交中击败了 97.69% 的用户
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了 32.14% 的用户
     * 通过测试用例：107 / 107
     */
    class MyCalendar_SegTree {
        private SegTree root;

        public MyCalendar_SegTree() {
            this.root = new SegTree(-2, -1);
        }

        public boolean book(int start, int end) {
            return root.book(start, end);
        }
    }

    class SegTree {
        int min;
        int max;
        SegTree left;
        SegTree right;

        public SegTree() {

        }

        public SegTree(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public boolean book(int start, int end) {
            if (this.min >= end) {
                if (this.left == null) {
                    this.left = new SegTree(start, end);
                    return true;
                }
                return this.left.book(start, end);
            } else if (this.max <= start) {
                if (this.right == null) {
                    this.right = new SegTree(start, end);
                    return true;
                }
                return this.right.book(start, end);
            } else {
                return false;
            }
        }
    }
/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
}
