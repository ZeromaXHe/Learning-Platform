package leetcode.from251to300;

/**
 * @author zhuxi
 * @apiNote 275. H 指数 II | 难度：中等 | 标签：数组、二分查找
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照 升序排列 。计算并返回该研究者的 h 指数。
 * <p>
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * <p>
 * 请你设计并实现对数时间复杂度的算法解决此问题。
 * <p>
 * 示例 1：
 * 输入：citations = [0,1,3,5,6]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3 。
 * <p>
 * 示例 2：
 * 输入：citations = [1,2,100]
 * 输出：2
 * <p>
 * 提示：
 * n == citations.length
 * 1 <= n <= 105
 * 0 <= citations[i] <= 1000
 * citations 按 升序排列
 * @implNote
 * @since 2023/10/30 9:47
 */
public class Solution275 {
    /**
     * 时间 0 ms
     * 击败 100.00% 使用 Java 的用户
     * 内存 48.38 MB
     * 击败 19.51% 使用 Java 的用户
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        int l = 0;
        int r = citations.length - 1;
        int result = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (citations[mid] >= citations.length - mid) {
                result = citations.length - mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }
}
