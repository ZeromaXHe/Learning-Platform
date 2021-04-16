package gobang;

import com.zerox.gobang.dao.GoBangBoard;
import org.junit.Test;

/**
 * @Author: zhuxi
 * @Time: 2021/3/5 14:16
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangBoardTest {
    @Test
    public void test() {
        GoBangBoard goBangBoard = GoBangBoard.getInstance();
        System.out.println(goBangBoard.getNowTurn());
        goBangBoard.step(7, 7);
        goBangBoard.step(7, 8);
        System.out.println(goBangBoard.getBoardString());
    }
}
