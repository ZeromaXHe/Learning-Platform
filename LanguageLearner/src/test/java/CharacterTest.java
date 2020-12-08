import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 14:16
 * @Description:
 * @Modified By: zhuxiaohe
 */
public class CharacterTest {
    @Test
    public void japaneseCharTest(){
        char c1 = '働';
        char c2 = 'き';
        System.out.println(c1);
        System.out.println((int)c1);
        System.out.println(c2);
        System.out.println((int)c2);
    }

    @Test
    public void chineseCharTest(){
        char c = '訸';
        System.out.println(c);
        System.out.println((int)c);
    }
}
