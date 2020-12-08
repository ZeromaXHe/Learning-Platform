package com.zerox.controller;

import com.zerox.entity.Result;
import com.zerox.entity.Syllable;
import com.zerox.entity.Word;
import com.zerox.repository.fake.SyllableRepository;
import com.zerox.repository.fake.WordRepository;

import java.util.List;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 14:36
 * @Description: 主控制器
 * @Modified By: zhuxiaohe
 */
public class MainController {
    private WordRepository wordRepository = new WordRepository();
    private SyllableRepository syllableRepository = new SyllableRepository();

    public void addWord(Word word){
        wordRepository.addWord(word);
        syllableRepository.addWord(word);
    }

    public Result<Word> queryWord(String word){
        return wordRepository.queryWord(word);
    }

    public Result<List<Word>> queryWordBySyllable(Syllable syllable){
        return syllableRepository.queryWordBySyllable(syllable);
    }
}
