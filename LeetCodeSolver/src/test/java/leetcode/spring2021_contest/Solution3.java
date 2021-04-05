package leetcode.spring2021_contest;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/4/5 15:54
 * @Description: 3.魔塔游戏 | 难度：中等 | 标签：
 * 小扣当前位于魔塔游戏第一层，共有 N 个房间，编号为 0 ~ N-1。每个房间的补血道具/怪物对于血量影响记于数组 nums，其中正数表示道具补血数值，即血量增加对应数值；负数表示怪物造成伤害值，即血量减少对应数值；0 表示房间对血量无影响。
 * <p>
 * 小扣初始血量为 1，且无上限。假定小扣原计划按房间编号升序访问所有房间补血/打怪，为保证血量始终为正值，小扣需对房间访问顺序进行调整，每次仅能将一个怪物房间（负数的房间）调整至访问顺序末尾。请返回小扣最少需要调整几次，才能顺利访问所有房间。若调整顺序也无法访问完全部房间，请返回 -1。
 * <p>
 * 示例 1：
 * 输入：nums = [100,100,100,-250,-60,-140,-50,-50,100,150]
 * 输出：1
 * 解释：初始血量为 1。至少需要将 nums[3] 调整至访问顺序末尾以满足要求。
 * <p>
 * 示例 2：
 * 输入：nums = [-200,-300,400,0]
 * 输出：-1
 * 解释：调整访问顺序也无法完成全部房间的访问。
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * @Modified By: ZeromaXHe
 */
public class Solution3 {
    /**
     * 已完成
     *
     * @param nums
     * @return
     */
    public int magicTower(int[] nums) {
        long hp = 1;
        for (int num : nums) {
            hp += num;
        }
        if (hp <= 0) {
            return -1;
        }
        hp = 1;
        int count = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        loop:
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(new int[]{i, nums[i]});
            while (hp + nums[i] <= 0) {
                int[] poll = minHeap.poll();
                if (poll[0] != i) {
                    hp += -poll[1];
                    count++;
                } else {
                    count++;
                    continue loop;
                }
            }
            hp += nums[i];
        }
        return count;
    }

    @Test
    public void magicTowerTest() {
        Assert.assertEquals(1, magicTower(new int[]{100, 100, 100, -250, -60, -140, -50, -50, 100, 150}));
        Assert.assertEquals(-1, magicTower(new int[]{-200, -300, 400, 0}));
    }
}
