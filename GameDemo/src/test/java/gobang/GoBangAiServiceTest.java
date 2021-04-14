package gobang;

import com.zerox.gobang.service.GoBangAiService;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: zhuxi
 * @Time: 2021/3/31 16:14
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangAiServiceTest {
    @Test
    public void valueTest() {
        int[][] board = new int[15][15];
        board[0][0] = 1;
        Class<GoBangAiService> aiServiceClass = GoBangAiService.class;
        try {
            Method value = aiServiceClass.getDeclaredMethod("value", int[][].class);
            value.setAccessible(true);
            System.out.println(value.invoke(aiServiceClass.newInstance(), (Object) board));
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试value()函数中的valueLine的起点
     */
    @Test
    public void valueLineStartInValueTest() {
        /// 逻辑简单
//        for (int i = 0; i < 15; i++) {
//            for (int j = 0; j < 11; j++) {
//                System.out.println("x: " + i + ", y: " + j);
//                System.out.println("x: " + j + ", y: " + i);
//            }
//        }

        // 打印出来才发现，没必要非要按斜线本身的顺序来遍历……
        // 直接取左下角和左上角，距离对角两边4格以上的就好了……
        int[][] board = new int[15][15];
        System.out.println("============");
        for (int i = -10; i < 11; i++) {
            for (int j = Math.max(-i, 0); j < 11 - Math.max(i, 0); j++) {
                System.out.println("x: " + (i + j) + ", y: " + j);
                board[i + j][j] = 1;
            }
        }
        System.out.println(print2DArrWithChangeLine(board));

        board = new int[15][15];
        System.out.println("============");
        for (int i = 4; i < 25; i++) {
            for (int j = Math.max(i - 15, 0); j < 11; j++) {
                System.out.println("x: " + Math.min(14, i) + ", y: " + j);
                board[Math.min(14, i)][j] = 1;
            }
        }
        System.out.println(print2DArrWithChangeLine(board));

    }

    private static String print2DArrWithChangeLine(int[][] arr2D) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < arr2D.length; i++) {
            if (i != 0) sb.append(",\n");
            sb.append('[');
            for (int j = 0; j < arr2D[0].length; j++) {
                if (j != 0) sb.append(',');
                sb.append(arr2D[i][j]);
            }
            sb.append(']');
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * 测试valueStep()方法中寻找开始点的办法
     */
    @Test
    public void valueStep_slashTest() {
        Assert.assertEquals("[4,0],", valueStep_slash(3, 1));

        int[][] board = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i + j > 24 || i + j < 4) {
                    board[i][j] = 1;
                }
            }
        }
        System.out.println(print2DArrWithChangeLine(board));
    }

    private String valueStep_slash(int x, int y) {
        if (x + y > 24 || x + y < 4) {
            System.out.println("not enough space");
        }
        StringBuilder sb = new StringBuilder();
        int xStartMin = Math.max(0, x - 4);
        int xStartMax = Math.min(10, x);
        int xSlashStartMax = Math.min(14, x + 5);
        int xSlashStartMin = Math.max(4, x);
        int yStartMin = Math.max(0, y - 4);
        int yStartMax = Math.min(10, y);
        for (int i = Math.max(xSlashStartMin - x, y - yStartMax); i <= Math.min(xSlashStartMax - x, y - yStartMin); i++) {
            System.out.println("i:" + i);
            System.out.println("[" + (x + i) + "," + (y - i) + "]");
            if (x + i > 14 || x + i - 4 < 0 || y - i < 0 || y - i + 4 > 14) {
                System.out.println("out of range");
            }
            sb.append('[')
                    .append(x + i)
                    .append(',')
                    .append(y - i)
                    .append("],");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    @Test
    public void valueStep_backSlashTest() {
        Assert.assertEquals("", valueStep_backSlash(3, 14));
        Assert.assertEquals("[7,7],[8,6],[9,5],[10,4],[11,3],", valueStep_backSlash(7, 7));

        int[][] board = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i - j > 10 || j - i > 10) {
                    board[i][j] = 1;
                }
            }
        }
        System.out.println(print2DArrWithChangeLine(board));
    }

    private String valueStep_backSlash(int x, int y) {
        if (x - y > 10 || y - x > 10) {
            System.out.println("not enough space");
        }
        StringBuilder sb = new StringBuilder();
        int xStartMin = Math.max(0, x - 4);
        int xStartMax = Math.min(10, x);
        int xSlashStartMax = Math.min(14, x + 5);
        int xSlashStartMin = Math.max(4, x);
        int yStartMin = Math.max(0, y - 4);
        int yStartMax = Math.min(10, y);
        for (int i = Math.max(x - xStartMax, y - yStartMax); i <= Math.min(x - xStartMin, y - yStartMin); i++) {
            System.out.println("i:" + i);
            System.out.println("[" + (x + i) + "," + (y - i) + "]");
            if (x + i > 14 || x + i - 4 < 0 || y - i < 0 || y - i + 4 > 14) {
                System.out.println("out of range");
            }
            sb.append('[')
                    .append(x + i)
                    .append(',')
                    .append(y - i)
                    .append("],");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
