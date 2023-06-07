package leetcode.from451to500;

/**
 * @author zhuxi
 * @apiNote 458. 可怜的小猪 | 难度：困难 | 标签：数学、动态规划、组合数学
 * 有 buckets 桶液体，其中 正好有一桶 含有毒药，其余装的都是水。它们从外观看起来都一样。
 * 为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
 * <p>
 * 喂猪的规则如下：
 * <p>
 * 选择若干活猪进行喂养
 * 可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
 * 小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
 * 过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
 * 重复这一过程，直到时间用完。
 * 给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回 在规定时间内判断哪个桶有毒所需的 最小 猪数 。
 * <p>
 * 示例 1：
 * 输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
 * 输出：5
 * <p>
 * 示例 2：
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 15
 * 输出：2
 * <p>
 * 示例 3：
 * 输入：buckets = 4, minutesToDie = 15, minutesToTest = 30
 * 输出：2
 * <p>
 * 提示：
 * 1 <= buckets <= 1000
 * 1 <= minutesToDie <= minutesToTest <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/poor-pigs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/7 11:07
 */
public class Solution458 {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了 97.01% 的用户
     * 通过测试用例：18 / 18
     *
     * @param buckets
     * @param minutesToDie
     * @param minutesToTest
     * @return
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int test = (minutesToTest / minutesToDie) + 1;
        // 不减 1e-5 的话，无法通过 poorPigs(125, 1, 4) 的测试用例
        return (int) Math.ceil(Math.log(buckets) / Math.log(test) - 1e-5);
    }
}
