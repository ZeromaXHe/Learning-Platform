package com.zerox.gobang.service;

import com.zerox.gobang.constant.GoBangEnum;
import com.zerox.gobang.dao.GoBangBoard;
import com.zerox.gobang.entity.vo.GoBangRegretResultVO;
import com.zerox.gobang.entity.vo.GoBangStepResultVO;

/**
 * @Author: zhuxi
 * @Time: 2021/3/26 17:26
 * @Description:
 * @Modified By: zhuxi
 */
public class GoBangBasicFuncService {

    public GoBangStepResultVO buttonStep(int x, int y) {
        GoBangBoard goBangBoard = GoBangBoard.getInstance();
        GoBangStepResultVO vo = new GoBangStepResultVO();

        vo.setButtonSide(goBangBoard.getNowTurn());
        goBangBoard.step(x, y);
        vo.setDominateSide(goBangBoard.getDominateSide());
        return vo;
    }

    public GoBangRegretResultVO regret() {
        GoBangBoard goBangBoard = GoBangBoard.getInstance();
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
        GoBangBoard.resetInstance();
    }

    public int[] getLastStep() {
        return GoBangBoard.getInstance().getStepStackTop();
    }

    public GoBangEnum getBoardDominateSide() {
        return GoBangBoard.getInstance().getDominateSide();
    }
}
