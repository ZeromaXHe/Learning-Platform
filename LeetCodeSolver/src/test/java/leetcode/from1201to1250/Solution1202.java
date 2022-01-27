package leetcode.from1201to1250;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhuxiaohe
 * @Time: 2021/1/11 10:11
 * @Description: 1202. 交换字符串中的元素 | 难度：中等 | 标签：并查集、数组
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * <p>
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * <p>
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 * <p>
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * <p>
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * <p>
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 *  
 * <p>
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxiaohe
 */
public class Solution1202 {
    /**
     * 执行用时： 43 ms , 在所有 Java 提交中击败了 66.15% 的用户
     * 内存消耗： 87.2 MB , 在所有 Java 提交中击败了 33.15% 的用户
     *
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        List<List<Integer>> unionList = transformPairsToUnionList(pairs, s.length());
        char[] chars = s.toCharArray();
        for (List<Integer> union : unionList) {
            dictionarySortUnion(union, chars);
        }
        return new String(chars);
    }

    private void dictionarySortUnion(List<Integer> union, char[] chars) {
        char[] unionChars = new char[union.size()];
        int i = 0;
        for (int unionI : union) {
            unionChars[i++] = chars[unionI];
        }
        Arrays.sort(unionChars);
        i = 0;
        for (int unionI : union) {
            chars[unionI] = unionChars[i++];
        }
    }

    private List<List<Integer>> transformPairsToUnionList(List<List<Integer>> pairs, int len) {
        int[][] unionSet = initUnionSet(pairs, len);
        return tranformUnionSetToList(unionSet);
    }

    private int[][] initUnionSet(List<List<Integer>> pairs, int len) {
        int[][] unionSet = new int[len][2];
        // 这里不能用Arrays.fill，不然所有的unionSet[i]指向的都是同一个数组
        for (int i = 0; i < unionSet.length; i++) {
            unionSet[i] = new int[]{-1, 1};
        }
        for (List<Integer> pair : pairs) {
            int pair0 = pair.get(0);
            int pair1 = pair.get(1);
            while (unionSet[pair0][0] != -1 && unionSet[pair0][0] != pair0) {
                pair0 = unionSet[pair0][0];
            }
            while (unionSet[pair1][0] != -1 && unionSet[pair1][0] != pair1) {
                pair1 = unionSet[pair1][0];
            }
            if (pair0 != pair1) {
                if (unionSet[pair0][1] < unionSet[pair1][1]) {
                    unionSet[pair0][0] = pair1;
                    unionSet[pair1][0] = pair1;
                    unionSet[pair1][1] = Math.max(unionSet[pair1][1], unionSet[pair0][1] + 1);
                } else {
                    unionSet[pair0][0] = pair0;
                    unionSet[pair1][0] = pair0;
                    unionSet[pair0][1] = Math.max(unionSet[pair0][1], unionSet[pair1][1] + 1);
                }
            }
        }
        return unionSet;
    }

    private List<List<Integer>> tranformUnionSetToList(int[][] unionSet) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < unionSet.length; i++) {
            if (unionSet[i][0] != -1) {
                int root = unionSet[i][0];
                while (unionSet[root][0] != root) {
                    root = unionSet[root][0];
                }
                if (map.containsKey(root)) {
                    map.get(root).add(i);
                } else {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(i);
                    map.put(root, list);
                }
            }
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    @Test
    public void smallestStringWithSwapsTest() {
        List<List<Integer>> listList1 = new ArrayList<>(3);
        List<Integer> list1_1 = new ArrayList<>(2);
        list1_1.add(0);
        list1_1.add(3);
        List<Integer> list1_2 = new ArrayList<>(2);
        list1_2.add(1);
        list1_2.add(2);
        List<Integer> list1_3 = new ArrayList<>(2);
        list1_3.add(0);
        list1_3.add(2);

        listList1.add(list1_1);
        listList1.add(list1_2);
        listList1.add(list1_3);

        // "dcab"
        // [[0,3],[1,2],[0,2]]
        Assert.assertEquals(smallestStringWithSwaps("dcab", listList1), "abcd");

        List<List<Integer>> listList2 = new ArrayList<>(3);
        List<Integer> list2_1 = new ArrayList<>(2);
        list2_1.add(0);
        list2_1.add(3);
        List<Integer> list2_2 = new ArrayList<>(2);
        list2_2.add(1);
        list2_2.add(2);

        listList2.add(list2_1);
        listList2.add(list2_2);
        // "dcab"
        // [[0,3],[1,2]]
        Assert.assertEquals(smallestStringWithSwaps("dcab", listList2), "bacd");
    }
}
