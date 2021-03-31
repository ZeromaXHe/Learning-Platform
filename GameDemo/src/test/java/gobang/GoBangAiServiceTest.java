package gobang;

import com.zerox.gobang.service.GoBangAiService;
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
}
