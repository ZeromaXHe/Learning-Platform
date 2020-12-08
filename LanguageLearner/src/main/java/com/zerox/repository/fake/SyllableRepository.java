package com.zerox.repository.fake;

import com.google.common.collect.Sets;
import com.zerox.entity.Result;
import com.zerox.entity.ResultCode;
import com.zerox.entity.Syllable;
import com.zerox.entity.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: zhuxiaohe
 * @Time: 2020/12/8 14:37
 * @Description: 音节库
 * @Modified By: zhuxiaohe
 */
public class SyllableRepository {
    private Map<Syllable, Set<Word>> syllableWordMap = new HashMap<>();

    public void addWord(Word word) {
        for (Syllable syllable : word.getSyllables()) {
            if (syllableWordMap.containsKey(syllable)) {
                if (!syllableWordMap.get(syllable).contains(word)) {
                    syllableWordMap.get(syllable).add(word);
                }
            } else {
                syllableWordMap.put(syllable, Sets.newHashSet(word));
            }
        }
    }

    public Result<List<Word>> queryWordBySyllable(Syllable syllable) {
        if (syllableWordMap.containsKey(syllable)) {
            return new Result<>(new ArrayList<>(syllableWordMap.get(syllable)));
        } else {
            return new Result<>(ResultCode.NONE, null);
        }
    }
}
