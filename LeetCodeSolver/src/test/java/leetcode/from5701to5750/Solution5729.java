package leetcode.from5701to5750;

import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/11 11:22
 * @Description: 5729.求出MK平均值 | 难度：困难 | 标签：
 * 给你两个整数 m 和 k ，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 MK 平均值 。
 * <p>
 * MK 平均值 按照如下步骤计算：
 * <p>
 * 如果数据流中的整数少于 m 个，MK 平均值 为 -1 ，否则将数据流中最后 m 个元素拷贝到一个独立的容器中。
 * 从这个容器中删除最小的 k 个数和最大的 k 个数。
 * 计算剩余元素的平均值，并 向下取整到最近的整数 。
 * 请你实现 MKAverage 类：
 * <p>
 * MKAverage(int m, int k) 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。
 * void addElement(int num) 往数据流中插入一个新的元素 num 。
 * int calculateMKAverage() 对当前的数据流计算并返回 MK 平均数 ，结果需 取整到最近的整数 。
 * <p>
 * 示例 1：
 * 输入：
 * ["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
 * [[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
 * 输出：
 * [null, null, null, -1, null, 3, null, null, null, 5]
 * <p>
 * 解释：
 * |MKAverage obj = new MKAverage(3, 1);
 * |obj.addElement(3);        // 当前元素为 [3]
 * |obj.addElement(1);        // 当前元素为 [3,1]
 * |obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素
 * |obj.addElement(10);       // 当前元素为 [3,1,10]
 * |obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10]
 * |                          // 删除最小以及最大的 1 个元素后，容器为 [3]
 * |                          // [3] 的平均值等于 3/1 = 3 ，故返回 3
 * |obj.addElement(5);        // 当前元素为 [3,1,10,5]
 * |obj.addElement(5);        // 当前元素为 [3,1,10,5,5]
 * |obj.addElement(5);        // 当前元素为 [3,1,10,5,5,5]
 * |obj.calculateMKAverage(); // 最后 3 个元素为 [5,5,5]
 * |                          // 删除最小以及最大的 1 个元素后，容器为 [5]
 * |                          // [5] 的平均值等于 5/1 = 5 ，故返回 5
 * <p>
 * 提示：
 * 3 <= m <= 105
 * 1 <= k*2 < m
 * 1 <= num <= 105
 * addElement 与 calculateMKAverage 总操作次数不超过 105 次。
 * @Modified By: ZeromaXHe
 */
public class Solution5729 {
    /**
     * 超时，未完成
     */
    class MKAverage {
        private int m;
        private int k;
        private LinkedList<Integer> list = new LinkedList<>();

        public MKAverage(int m, int k) {
            this.m = m;
            this.k = k;
        }

        public void addElement(int num) {
            list.add(num);
            if (list.size() > m) {
                list.removeFirst();
            }
        }

        public int calculateMKAverage() {
            if (list.size() < m) {
                return -1;
            }
            return list.stream()
                    .sorted()
                    .skip(k)
                    .limit(m - 2 * k)
                    .collect(Collectors.averagingInt(Integer::intValue))
                    .intValue();
        }
    }
}
