import com.alibaba.fastjson.JSON;
import com.zerox.controller.MainController;
import com.zerox.entity.Syllable;
import com.zerox.entity.Word;
import org.junit.Test;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 15:15
 * @Description:
 * @Modified By: zhuxiaohe
 */
public class MainControllerTester {
    /**
     * {
     *     "word": "私",
     *     "language": "JAPANESE",
     *     "syllables": [
     *         {
     *             "character": "私",
     *             "syllable": "わたし"
     *         }
     *     ],
     *     "pronunciation": "わたし",
     *     "meaning": {
     *         "CHINESE": "我"
     *     },
     *     "exampleSentences": [
     *         "私は中国人です"
     *     ]
     * }
     */
    String wordTemplateJSON = "{" +
            "\"word\": \"私\"," +
            "\"wordLanguage\": \"JAPANESE\"," +
            "\"syllables\": [" +
            "{\"character\":\"私\",\"syllable\":\"わたし\"}" +
            "]," +
            "\"pronunciation\": \"わたし\"," +
            "\"meaning\": {" +
            "\"CHINESE\": \"我\"" +
            "}," +
            "\"exampleSentences\": [" +
            "\"私は中国人です\"" +
            "]" +
            "}";

    @Test
    public void mainControllerTest() {
        MainController mainController = new MainController();
        mainController.addWord(JSON.parseObject(wordTemplateJSON, Word.class));
        System.out.println(mainController.queryWord("私").getData());
        System.out.println(mainController.queryWordBySyllable(new Syllable("私","わたし")).getData());
    }
}
