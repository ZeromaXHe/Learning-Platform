package tester;

import com.zerox.entity.Tester;

/**
 * @Author: zhuxi
 * @Time: 2020/11/10 14:55
 * @Description: 测试Leetcode测试工具的类
 * @Modified By: zhuxi
 */
public class TesterTest {
    public String testFunction(String s) {
//        if("babad".equals(s)){
//            return "aba";
//        }
//        if("cbbd".equals(s)){
//            return "bb";
//        }
        return "";
    }

    public static void main(String[] args) {
        TesterTest test = new TesterTest();
        Tester<String, String> tester = new Tester<>();
        tester.addCase("babad", "bab","aba");
        tester.addCase("cbbd", "bb");
        tester.setFunction(test::testFunction);
        System.out.println(tester.testAll());
    }
}
