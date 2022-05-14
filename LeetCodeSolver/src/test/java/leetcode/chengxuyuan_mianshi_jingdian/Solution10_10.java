package leetcode.chengxuyuan_mianshi_jingdian;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/5/14 15:14
 * @Description: 面试题 10.10. 数字流的秩 | 难度：中等 | 标签：设计、树状数组、二分查找、数据流
 * 假设你正在读取一串整数。每隔一段时间，你希望能找出数字 x 的秩(小于或等于 x 的值的个数)。请实现数据结构和算法来支持这些操作，也就是说：
 * <p>
 * 实现 track(int x) 方法，每读入一个数字都会调用该方法；
 * <p>
 * 实现 getRankOfNumber(int x) 方法，返回小于或等于 x 的值的个数。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例:
 * 输入:
 * ["StreamRank", "getRankOfNumber", "track", "getRankOfNumber"]
 * [[], [1], [0], [0]]
 * 输出:
 * [null,0,null,1]
 * 提示：
 * x <= 50000
 * track 和 getRankOfNumber 方法的调用次数均不超过 2000 次
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rank-from-stream-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution10_10 {
    @Test
    public void lowBitTest() {
        int[] tree = new int[100];
        add(2, 1, tree);
        for (int i = 0; i < 100; i++) {
            if (tree[i] != 0) {
                System.out.print(i + "(" + Integer.toBinaryString(i) + "): " + tree[i] + ", ");
            }
        }
        System.out.println(query(90, tree));

        // i += lowbit(i) 相当于给最后的一个 1 加一
        System.out.println(Integer.toBinaryString(0b110 + (0b110 & -0b110)));
        System.out.println(Integer.toBinaryString(0b100110 + (0b100110 & -0b100110)));
        // i -= lowbit(i) 相当于将最后一个 1 置 0
        System.out.println(Integer.toBinaryString(0b10111 - (0b10111 & -0b10111)));
        System.out.println(Integer.toBinaryString(0b10100 - (0b10100 & -0b10100)));
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private int query(int x, int[] tree) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            System.out.println("visited tree[" + i + "]");
            ans += tree[i];
        }
        return ans;
    }

    private void add(int x, int u, int[] tree) {
        for (int i = x; i < tree.length; i += lowbit(i)) {
            tree[i] += u;
        }
    }

    /**
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 99.78% 的用户
     * 内存消耗： 41.9 MB , 在所有 Java 提交中击败了 70.54% 的用户
     * 通过测试用例： 29 / 29
     * <p>
     * 参考题解做的，之后感觉可以好好研究一下这个树状数组，之前没看过……
     * 结果里面最快的居然是直接 for 循环数组去加一 O(n) 的算法，惊了……
     */
    class StreamRank {
        int[] tree;
        int n = 50001;

        public StreamRank() {
            tree = new int[n + 1];
        }

        public void track(int x) {
            // 利用树状数组向（包括）x + 1 之后的数字都加 1
            // 树状数组的根节点是 tree 数组中首位为 1 其他为 0 的最大值索引处，循环将在这里结束
            for (int i = x + 1; i <= n; i += i & -i) {
                tree[i]++;
            }
        }

        public int getRankOfNumber(int x) {
            int ans = 0;
            for (int i = x + 1; i > 0; i -= i & -i) {
                ans += tree[i];
            }
            return ans;
        }
    }

    /**
     * 执行用时： 15 ms , 在所有 Java 提交中击败了 43.30% 的用户
     * 内存消耗： 41.9 MB , 在所有 Java 提交中击败了 64.73% 的用户
     * 通过测试用例： 29 / 29
     */
    class StreamRank_slow {
        int[] tree;
        int n = 50001;

        public StreamRank_slow() {
            tree = new int[n + 1];
        }

        public void track(int x) {
            // 利用树状数组向（包括）x + 1 之后的数字都加 1
            add(x + 1, 1);
        }

        public int getRankOfNumber(int x) {
            return query(x + 1);
        }

        private int lowbit(int x) {
            return x & -x;
        }

        private int query(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= lowbit(i)) {
                ans += tree[i];
            }
            return ans;
        }

        private void add(int x, int u) {
            // 树状数组的根节点是 tree 数组中首位为 1 其他为 0 的最大值索引处，循环将在这里结束
            for (int i = x; i <= n; i += lowbit(i)) {
                tree[i] += u;
            }
        }
    }

/**
 * Your StreamRank object will be instantiated and called as such:
 * StreamRank obj = new StreamRank();
 * obj.track(x);
 * int param_2 = obj.getRankOfNumber(x);
 */
}
