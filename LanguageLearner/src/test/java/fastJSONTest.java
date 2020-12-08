import com.alibaba.fastjson.JSON;
import com.zerox.entity.Language;
import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 15:50
 * @Description: fastJSON测试
 * @Modified By: zhuxiaohe
 */
public class fastJSONTest {

    /**
     * 通常，fastjson在序列化及反序列化枚举时，一般以下几种策略：
     * 1）.根据枚举的name值序列化及反序列化（默认）
     * 2）.根据枚举的ordinal序列化及反序列化
     * 3）.根据枚举的toString方法序列化，但是反序列仍采取默认的策略
     * <p>
     * 这里测试的是第一种策略
     */
    @Test
    public void enumTest() {
        EnumClass test = new EnumClass();
        test.setLanguage(Language.JAPANESE);
        String str = JSON.toJSONString(test);
        System.out.println(str);
        EnumClass enumClass = JSON.parseObject("{\"language\": \"JAPANESE\"}", EnumClass.class);
        System.out.println(enumClass);
    }
}

class EnumClass {
    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "EnumClass{" +
                "language=" + language +
                '}';
    }
}
