package leetcode.from2251to2300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuxi
 * @apiNote 2300. 咒语和药水的成功对数 | 难度：中等 | 标签：数组、双指针、二分查找、排序
 * 给你两个正整数数组 spells 和 potions ，长度分别为 n 和 m ，其中 spells[i] 表示第 i 个咒语的能量强度，potions[j] 表示第 j 瓶药水的能量强度。
 * <p>
 * 同时给你一个整数 success 。一个咒语和药水的能量强度 相乘 如果 大于等于 success ，那么它们视为一对 成功 的组合。
 * <p>
 * 请你返回一个长度为 n 的整数数组 pairs，其中 pairs[i] 是能跟第 i 个咒语成功组合的 药水 数目。
 * <p>
 * 示例 1：
 * 输入：spells = [5,1,3], potions = [1,2,3,4,5], success = 7
 * 输出：[4,0,3]
 * 解释：
 * - 第 0 个咒语：5 * [1,2,3,4,5] = [5,10,15,20,25] 。总共 4 个成功组合。
 * - 第 1 个咒语：1 * [1,2,3,4,5] = [1,2,3,4,5] 。总共 0 个成功组合。
 * - 第 2 个咒语：3 * [1,2,3,4,5] = [3,6,9,12,15] 。总共 3 个成功组合。
 * 所以返回 [4,0,3] 。
 * <p>
 * 示例 2：
 * 输入：spells = [3,1,2], potions = [8,5,8], success = 16
 * 输出：[2,0,2]
 * 解释：
 * - 第 0 个咒语：3 * [8,5,8] = [24,15,24] 。总共 2 个成功组合。
 * - 第 1 个咒语：1 * [8,5,8] = [8,5,8] 。总共 0 个成功组合。
 * - 第 2 个咒语：2 * [8,5,8] = [16,10,16] 。总共 2 个成功组合。
 * 所以返回 [2,0,2] 。
 * <p>
 * <p>
 * 提示：
 * n == spells.length
 * m == potions.length
 * 1 <= n, m <= 10^5
 * 1 <= spells[i], potions[i] <= 10^5
 * 1 <= success <= 10^10
 * @implNote
 * @since 2023/11/10 10:02
 */
public class Solution2300 {
    /**
     * 时间 99 ms
     * 击败 5.12% 使用 Java 的用户
     * 内存 71.74 MB
     * 击败 5.02% 使用 Java 的用户
     *
     * @param spells
     * @param potions
     * @param success
     * @return
     */
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        HashMap<Integer, List<Integer>> spellToIdxMap = new HashMap<>();
        for (int i = 0; i < spells.length; i++) {
            spellToIdxMap.putIfAbsent(spells[i], new ArrayList<>());
            spellToIdxMap.get(spells[i]).add(i);
        }
        Arrays.sort(spells);
        int to = potions.length - 1;
        HashMap<Integer, Integer> spellToCountMap = new HashMap<>();
        for (int spell : spells) {
            if (spellToCountMap.containsKey(spell)) {
                continue;
            }
            int searchIdx = binarySearch(potions, to, success / spell + (success % spell == 0 ? 0 : 1));
            spellToCountMap.put(spell, potions.length - searchIdx);
            to = searchIdx == potions.length ? searchIdx - 1 : searchIdx;
        }
        int[] result = new int[spells.length];
        for (Map.Entry<Integer, List<Integer>> e : spellToIdxMap.entrySet()) {
            int count = spellToCountMap.get(e.getKey());
            for (Integer idx : e.getValue()) {
                result[idx] = count;
            }
        }
        return result;
    }

    private int binarySearch(int[] arr, int to, long val) {
        int l = 0;
        int r = to;
        int result = arr.length;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] >= val) {
                result = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }
}
