package com.zerox.repository.fake;

import com.zerox.entity.Result;
import com.zerox.entity.ResultCode;
import com.zerox.entity.Word;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 14:30
 * @Description: 词汇库
 * @Modified By: zhuxiaohe
 */
public class WordRepository {
    private Map<String, Word> wordStorageMap = new HashMap<>();

    public void addWord(Word word) {
        wordStorageMap.put(word.getWord(), word);
    }

    public Result<Word> queryWord(String word) {
        if (wordStorageMap.containsKey(word)) {
            return new Result<>(wordStorageMap.get(word));
        } else {
            return new Result<>(ResultCode.NONE, null);
        }
    }
}
