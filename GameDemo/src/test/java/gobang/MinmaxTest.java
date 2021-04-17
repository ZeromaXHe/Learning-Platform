package gobang;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.dao.GoBangBoard;
import com.zerox.gobang.service.GoBangAiService;
import com.zerox.gobang.utils.BoardUtils;
import com.zerox.gobang.utils.ValueUtils;
import org.junit.Test;

import java.util.Scanner;

/**
 * @Author: zhuxi
 * @Time: 2021/4/16 10:19
 * @Description:
 * @Modified By: zhuxi
 */
public class MinmaxTest {
    private final static boolean MANUAL_STEP = true;

    public static void main(String[] args) {
        new MinmaxTest().minmaxTest();
    }

    @Test
    public void minmaxTest() {
        GoBangBoard goBangBoard = GoBangBoard.getInstance();
        GoBangAiService aiService = new GoBangAiService();
        aiService.setAiStrategy(GoBangAiStrategyEnum.MINMAX);
        Scanner scanner = new Scanner(System.in);
        while (goBangBoard.getDominateSide() == GoBangEnum.EMPTY) {
            // ai行棋
            aiControlledMinmaxStep(goBangBoard, aiService);
            // 不用人工的话，直接下一轮ai
            if(!MANUAL_STEP){
                continue;
            }
            if(goBangBoard.getDominateSide() != GoBangEnum.EMPTY) {
                break;
            }
            // 人工行棋
            manualControlledMinmaxStep(goBangBoard, aiService, scanner);
        }
    }

    private void aiControlledMinmaxStep(GoBangBoard goBangBoard, GoBangAiService aiService) {
        int[] result = aiService.nextStep();
        goBangBoard.step(result[0], result[1]);
        int[][] board = goBangBoard.getBoardCopy();
        System.out.println(BoardUtils.transBoardToString(board));
        System.out.println("value: " + ValueUtils.value(board));
        System.out.println("=======================================================");
    }

    private void manualControlledMinmaxStep(GoBangBoard goBangBoard, GoBangAiService aiService, Scanner scanner) {
        int[][] board;
        int x = 0;
        int y = 0;
        boolean success = false;
        while (!success) {
            try {
                System.out.println("input your step:(format \"x y\", \"ai\" for step ai choice)");
                // JUnit 可能无法正常读取，需要加-Deditable.java.test.console=true
                // 没加的话，请使用上面的main函数
                String nextLine = scanner.nextLine();
                if ("ai".equals(nextLine)) {
                    int[] result2 = aiService.nextStep();
                    x = result2[0];
                    y = result2[1];
                } else {
                    String[] coordinate = nextLine.split(" ");
                    x = Integer.parseInt(coordinate[0]);
                    y = Integer.parseInt(coordinate[1]);
                }
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        goBangBoard.step(x, y);
        board = goBangBoard.getBoardCopy();
        System.out.println(BoardUtils.transBoardToString(board));
        System.out.println("value: " + ValueUtils.value(board));
        System.out.println("=======================================================");
    }
}
