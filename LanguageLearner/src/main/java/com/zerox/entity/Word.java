package com.zerox.entity;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 13:49
 * @Description: 词语
 * @Modified By: zhuxiaohe
 */
public class Word {
    /**
     * 单词
     */
    private String word;

    /**
     * 单词语言
     */
    private Language wordLanguage;

    /**
     * 单词包含的音节
     */
    private List<Syllable> syllables;

    /**
     * 发音
     */
    private String pronunciation;

    /**
     * 意思(可以对应多个语言)
     */
    private Map<Language, String> meaning;

    /**
     * 例句
     */
    private List<String> exampleSentences;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Language getWordLanguage() {
        return wordLanguage;
    }

    public void setWordLanguage(Language wordLanguage) {
        this.wordLanguage = wordLanguage;
    }

    public List<Syllable> getSyllables() {
        return syllables;
    }

    public void setSyllables(List<Syllable> syllables) {
        this.syllables = syllables;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public Map<Language, String> getMeaning() {
        return meaning;
    }

    public void setMeaning(Map<Language, String> meaning) {
        this.meaning = meaning;
    }

    public List<String> getExampleSentences() {
        return exampleSentences;
    }

    public void setExampleSentences(List<String> exampleSentences) {
        this.exampleSentences = exampleSentences;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", wordLanguage=" + wordLanguage +
                ", syllables=" + syllables +
                ", pronunciation='" + pronunciation + '\'' +
                ", meaning=" + meaning +
                ", exampleSentences=" + exampleSentences +
                '}';
    }
}
