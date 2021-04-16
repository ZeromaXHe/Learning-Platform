package com.zerox.gobang.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.ToIntBiFunction;

/**
 * @Author: zhuxi
 * @Time: 2021/3/31 11:54
 * @Description: 算分的结果黑方优势为正，白方优势为负
 * @Modified By: zhuxi
 */
public class ScoreMapUtils {
    private final static int SCORE_1 = 10;
    private final static int SCORE_2 = 100;
    private final static int SCORE_3 = 1000;
    private final static int SCORE_4 = 10000;
    private final static int SCORE_5 = 100000;

    public static HashMap<Integer, Integer> initScoreMap() {
        HashMap<Integer, Integer> scoreMap = new HashMap<>();
        backtracePossibleAndAddToScoreMap(scoreMap, 1, 0);
        return scoreMap;
    }

    private static void backtracePossibleAndAddToScoreMap(HashMap<Integer, Integer> scoreMap, int multiplier, int possible) {
        while (possible % 10 <= 2) {
            if (multiplier < 10000) {
                backtracePossibleAndAddToScoreMap(scoreMap, multiplier * 10, possible * 10);
            }
            scoreMap.put(possible, calcPossibleScore(possible));
            possible += 1;
        }
    }

    private static int calcPossibleScore(int possible) {
        LinkedList<Integer> oneIndexes = new LinkedList<>();
        LinkedList<Integer> twoIndexes = new LinkedList<>();
        int index = 0;
        while (possible > 0) {
            if (possible % 10 == 1) {
                oneIndexes.add(index);
            } else if (possible % 10 == 2) {
                twoIndexes.add(index);
            }
            index++;
            possible /= 10;
        }
        int sign = 1;
        if (oneIndexes.size() > twoIndexes.size()) {
            LinkedList<Integer> temp = oneIndexes;
            oneIndexes = twoIndexes;
            twoIndexes = temp;
            sign = -1;
        }
        Optional<ToIntBiFunction<List<Integer>, List<Integer>>> optional = getCalcPossibleFunction(oneIndexes, twoIndexes);
        if (optional.isPresent()) {
            ToIntBiFunction<List<Integer>, List<Integer>> toIntBiFunction = optional.get();
            return sign * toIntBiFunction.applyAsInt(oneIndexes, twoIndexes);
        } else {
            // TODO: 需要日志系统
            System.out.println("fail to get toIntBiFunction!");
            return 0;
        }

    }

    private static Optional<ToIntBiFunction<List<Integer>, List<Integer>>> getCalcPossibleFunction(LinkedList<Integer> oneIndexes, LinkedList<Integer> twoIndexes) {
        ToIntBiFunction<List<Integer>, List<Integer>> toIntBiFunction = null;
        if (oneIndexes.size() == 0) {
            switch (twoIndexes.size()) {
                case 0:
                    toIntBiFunction = ScoreMapUtils::calcPossible0_0;
                    break;
                case 1:
                    toIntBiFunction = ScoreMapUtils::calcPossible0_1;
                    break;
                case 2:
                    toIntBiFunction = ScoreMapUtils::calcPossible0_2;
                    break;
                case 3:
                    toIntBiFunction = ScoreMapUtils::calcPossible0_3;
                    break;
                case 4:
                    toIntBiFunction = ScoreMapUtils::calcPossible0_4;
                    break;
                case 5:
                    toIntBiFunction = ScoreMapUtils::calcPossible0_5;
                    break;
                default:
                    throw new IllegalStateException("calcPossibleScore twoIndexes.size():" + twoIndexes.size());
            }
        } else if (oneIndexes.size() == 1) {
            switch (twoIndexes.size()) {
                case 1:
                    toIntBiFunction = ScoreMapUtils::calcPossible1_1;
                    break;
                case 2:
                    toIntBiFunction = ScoreMapUtils::calcPossible1_2;
                    break;
                case 3:
                    toIntBiFunction = ScoreMapUtils::calcPossible1_3;
                    break;
                case 4:
                    toIntBiFunction = ScoreMapUtils::calcPossible1_4;
                    break;
                default:
                    throw new IllegalStateException("calcPossibleScore twoIndexes.size():" + twoIndexes.size());
            }
        } else if (oneIndexes.size() == 2) {
            switch (twoIndexes.size()) {
                case 2:
                    toIntBiFunction = ScoreMapUtils::calcPossible2_2;
                    break;
                case 3:
                    toIntBiFunction = ScoreMapUtils::calcPossible2_3;
                    break;
                default:
                    throw new IllegalStateException("calcPossibleScore twoIndexes.size():" + twoIndexes.size());
            }
        }
        return toIntBiFunction == null ? Optional.empty() : Optional.of(toIntBiFunction);
    }

    private static int calcPossible0_0(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        return 0;
    }

    private static int calcPossible0_1(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        return -SCORE_1;
    }

    private static int calcPossible0_2(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        // distance in [1,4]
        int distance = twoIndexes.get(1) - twoIndexes.get(0);
        return -(SCORE_2 - (distance - 1) * SCORE_2 / 4);
    }

    private static int calcPossible0_3(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        // each distance in [1,3]
        // distance2sum in [1,4]
        int distance2sum = twoIndexes.get(2) - twoIndexes.get(0);
        return -(SCORE_3 - (distance2sum - 2) * SCORE_3 / 3);
    }

    private static int calcPossible0_4(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        // each distance in [1,2]
        // distance3sum in [1,4]
        int distance3sum = twoIndexes.get(3) - twoIndexes.get(0);
        return -(SCORE_4 - (distance3sum - 3) * SCORE_4 / 2);
    }

    private static int calcPossible0_5(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        return -SCORE_5;
    }

    private static int calcPossible1_1(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        return 0;
    }

    private static int calcPossible1_2(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        int oneIndex = oneIndexes.get(0);
        int twoIndex1 = twoIndexes.get(0);
        int twoIndex2 = twoIndexes.get(1);
        if (oneIndex > twoIndex1 && oneIndex < twoIndex2) {
            return 0;
        }
        // twoIndex2 - twoIndex1 in [1, 3]
        return SCORE_1 - (SCORE_2 - (twoIndex2 - twoIndex1 - 1) * SCORE_2 / 3) / 2;
    }

    private static int calcPossible1_3(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        int oneIndex = oneIndexes.get(0);
        int twoIndex1 = twoIndexes.get(0);
        int twoIndex2 = twoIndexes.get(2);
        if (oneIndex > twoIndex1 && oneIndex < twoIndex2) {
            return 0;
        }
        // twoIndex2 - twoIndex1 in [1, 3]
        return SCORE_2 - (SCORE_3 - (twoIndex2 - twoIndex1 - 2) * SCORE_3 / 2) / 2;
    }

    private static int calcPossible1_4(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        int oneIndex = oneIndexes.get(0);
        int twoIndex1 = twoIndexes.get(0);
        int twoIndex2 = twoIndexes.get(3);
        if (oneIndex > twoIndex1 && oneIndex < twoIndex2) {
            return 0;
        }
        return SCORE_3 - SCORE_4 / 2;
    }

    private static int calcPossible2_2(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        int oneIndex1 = oneIndexes.get(0);
        int oneIndex2 = oneIndexes.get(1);
        int twoIndex1 = twoIndexes.get(0);
        int twoIndex2 = twoIndexes.get(1);
        if (twoIndex1 > oneIndex2 || oneIndex1 > twoIndex2) {
            // xxxIndex2 - xxxIndex1 in [1, 2]
            return (SCORE_2 - (oneIndex2 - oneIndex1 - 1) * SCORE_2 / 2) - (SCORE_2 - (twoIndex2 - twoIndex1 - 1) * SCORE_2 / 2);
        }
        return 0;
    }

    private static int calcPossible2_3(List<Integer> oneIndexes, List<Integer> twoIndexes) {
        int oneIndex1 = oneIndexes.get(0);
        int oneIndex2 = oneIndexes.get(1);
        int twoIndex1 = twoIndexes.get(0);
        int twoIndex2 = twoIndexes.get(2);
        if (twoIndex1 > oneIndex2 || oneIndex1 > twoIndex2) {
            return SCORE_2 - SCORE_3 / 2;
        }
        return 0;
    }
}
