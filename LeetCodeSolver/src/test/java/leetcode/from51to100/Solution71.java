package leetcode.from51to100;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/1/13 10:06
 * @Description: 71. 简化路径 | 难度：中等 | 标签：栈、字符串
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * <p>
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * <p>
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * <p>
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 * <p>
 * 示例 1：
 * 输入：path = "/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * <p>
 * 示例 2：
 * 输入：path = "/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根目录是你可以到达的最高级。
 * <p>
 * 示例 3：
 * 输入：path = "/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * <p>
 * 示例 4：
 * 输入：path = "/a/./b/../../c/"
 * 输出："/c"
 * <p>
 * 提示：
 * 1 <= path.length <= 3000
 * path 由英文字母，数字，'.'，'/' 或 '_' 组成。
 * path 是一个有效的 Unix 风格绝对路径。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplify-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution71 {
    @Test
    public void simplifyPathTest() {
        Assert.assertEquals("/home", simplifyPath("/home/"));
        Assert.assertEquals("/", simplifyPath("/../"));
        Assert.assertEquals("/home/foo", simplifyPath("/home//foo/"));
        Assert.assertEquals("/c", simplifyPath("/a/./b/../../c/"));
    }

    /**
     * split使用"/+"耗时长很多
     * <p>
     * 执行用时： 12 ms , 在所有 Java 提交中击败了 5.99% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 7.40% 的用户
     * 通过测试用例： 256 / 256
     * <p>
     * 使用“/”就比较正常
     * <p>
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 87.47% 的用户
     * 内存消耗： 38.6 MB , 在所有 Java 提交中击败了 21.45% 的用户
     * 通过测试用例： 256 / 256
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList<>();
        String[] paths = path.split("/");
        for (String s : paths) {
            if (".".equals(s) || "".equals(s)) {
                continue;
            }
            if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else {
                stack.addLast(s);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : stack) {
                sb.append('/').append(s);
            }
            return sb.toString();
        }
    }
}
