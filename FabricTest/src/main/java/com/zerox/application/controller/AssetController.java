package com.zerox.application.controller;

import com.zerox.application.entity.business.AssetBO;
import com.zerox.application.entity.domain.AssetBonusHistoryDO;
import com.zerox.application.entity.domain.AssetTransHistoryDO;
import com.zerox.application.service.AssetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 13:45
 */
@RestController
@RequestMapping("/asset")
public class AssetController {
    private static final Logger logger = LoggerFactory.getLogger(AssetController.class);
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @RequestMapping("/query")
    public AssetBO queryAsset(@RequestParam("id") String id) {
        return assetService.queryAssert(id);
    }

    @RequestMapping("/create")
    public AssetBO createAsset(@RequestParam("id") String id,
                               @RequestParam("owners") String owners,
                               @RequestParam("timestamp") String timestamp) {
        return assetService.createAsset(id, owners, timestamp);
    }

    @RequestMapping("/changeOwner")
    public AssetBO changeAssetOwner(@RequestParam("id") String id,
                                    @RequestParam("from") String from,
                                    @RequestParam("to") String to,
                                    @RequestParam("share") String share,
                                    @RequestParam("cost") String cost,
                                    @RequestParam("timestamp") String timestamp) {
        return assetService.changeAssetOwner(id, from, to, share, cost, timestamp);
    }

    @RequestMapping("/bonus")
    public AssetBO bonus(@RequestParam("id") String id,
                         @RequestParam("bonus") String bonus,
                         @RequestParam("timestamp") String timestamp) {
        return assetService.bonus(id, bonus, timestamp);
    }

    @RequestMapping("/queryTransHistory")
    public List<AssetTransHistoryDO> queryAssetTransHistory(@RequestParam("id") String id) {
        return assetService.queryAssetTransHistory(id);
    }

    @RequestMapping("/queryBonusHistory")
    public List<AssetBonusHistoryDO> queryAssetBonusHistory(@RequestParam("id") String id) {
        return assetService.queryAssetBonusHistory(id);
    }
}
