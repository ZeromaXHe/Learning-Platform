package com.zerox.gobang.controller;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.entity.vo.GoBangRegretResultVO;
import com.zerox.gobang.entity.vo.GoBangStepResultVO;
import com.zerox.gobang.service.GoBangAiService;
import com.zerox.gobang.service.GoBangBasicFuncService;

/**
 * @Author: zhuxi
 * @Time: 2021/3/5 11:37
 * @Description:
 * @Modified By: zhuxi
 */
public class MainController {

    private GoBangBasicFuncService basicFuncService;
    private GoBangAiService aiService;

    public MainController() {
        basicFuncService = new GoBangBasicFuncService();
        aiService = new GoBangAiService();
    }

    public GoBangStepResultVO buttonStep(int x, int y) {
        return basicFuncService.buttonStep(x, y);
    }

    public GoBangRegretResultVO regret() {
        return basicFuncService.regret();
    }

    public void restart() {
        basicFuncService.restart();
    }

    public int[] getLastStep() {
        return basicFuncService.getLastStep();
    }

    public GoBangEnum getBoardDominateSide() {
        return basicFuncService.getBoardDominateSide();
    }

    /**
     * 获取Ai思考的进度
     *
     * @return
     */
    public int getBoardAiProcess() {
        return aiService.getThinkProcess();
    }

    public int[] getBoardAiNextStep() {
        return aiService.nextStep();
    }

    /**
     * 设置ai策略
     *
     * @param strategy
     */
    public void setBoardAiStrategy(GoBangAiStrategyEnum strategy) {
        aiService.setAiStrategy(strategy);
    }
}
