package gobang;

import com.zerox.gobang.utils.ScoreMapUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2021/3/31 15:09
 * @Description:
 * @Modified By: zhuxi
 */
public class ScoreMapUtilsTest {
    @Test
    public void initScoreMapTest(){
        HashMap<Integer, Integer> scoreMap = ScoreMapUtils.initScoreMap();
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(scoreMap.entrySet());
        list.sort(Comparator.comparingInt(Map.Entry::getValue));
        for(Map.Entry e :list) {
            System.out.println(e);
        }
    }
}
