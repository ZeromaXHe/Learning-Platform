package com.zerox.gobang.controller;

import com.zerox.gobang.constant.GoBangAiStrategyEnum;
import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.entity.GoBangBoard;
import com.zerox.gobang.entity.vo.GoBangRegretResultVO;
import com.zerox.gobang.entity.vo.GoBangStepResultVO;

/**
 * @Author: zhuxi
 * @Time: 2021/3/5 11:37
 * @Description:
 * @Modified By: zhuxi
 */
public class MainController {
    private GoBangBoard goBangBoard;

    public MainController() {
        goBangBoard = new GoBangBoard();
    }

    public GoBangStepResultVO buttonStep(int x, int y) {
        GoBangStepResultVO vo = new GoBangStepResultVO();
        vo.setButtonSide(goBangBoard.getNowTurn());
        goBangBoard.step(x, y);
        vo.setDominateSide(goBangBoard.getDominateSide());
        return vo;
    }

    public GoBangRegretResultVO regret() {
        GoBangRegretResultVO vo = new GoBangRegretResultVO();
        try {
            vo.setRegretStep(goBangBoard.regret());
        } catch (Exception e) {
            e.printStackTrace();
            vo.setMoreRegret(false);
            return vo;
        }
        vo.setMoreRegret(goBangBoard.getStepCount() != 0);
        return vo;
    }

    public void restart() {
        goBangBoard = new GoBangBoard();
    }

    public int[] getLastStep() {
        return goBangBoard.getStepStackTop();
    }

    public int getBoardAiProcess() {
        return goBangBoard.getAiProcess();
    }

    public int[] getBoardAiNextStep() {
        return goBangBoard.nextAiStep();
    }

    public GoBangEnum getBoardDominateSide() {
        return goBangBoard.getDominateSide();
    }

    public void setBoardAiStrategy(GoBangAiStrategyEnum strategy) {
        goBangBoard.setAiStrategy(strategy);
    }
}
