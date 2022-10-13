package com.zerox.application.controller;

import com.zerox.application.entity.business.AssetBO;
import com.zerox.application.entity.domain.AssetBonusHistoryDO;
import com.zerox.application.entity.domain.AssetTransHistoryDO;
import com.zerox.application.service.AssetService;
import com.zerox.utils.JsonUtils;
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
    public String queryAsset(@RequestParam("id") String id) {
        AssetBO bo = assetService.queryAssert(id);
        return JsonUtils.objectToJson(bo);
    }

    @RequestMapping("/create")
    public String createAsset(@RequestParam("id") String id,
                              @RequestParam("owners") String owners) {
        AssetBO bo = assetService.createAssert(id, owners);
        return JsonUtils.objectToJson(bo);
    }

    @RequestMapping("/changeOwner")
    public String changeAssetOwner(@RequestParam("id") String id,
                                   @RequestParam("from") String from,
                                   @RequestParam("to") String to,
                                   @RequestParam("share") String share,
                                   @RequestParam("cost") String cost) {
        AssetBO bo = assetService.changeAssetOwner(id, from, to, share, cost);
        return JsonUtils.objectToJson(bo);
    }

    @RequestMapping("/bonus")
    public String bonus(@RequestParam("id") String id,
                        @RequestParam("bonus") String bonus) {
        AssetBO bo = assetService.bonus(id, bonus);
        return JsonUtils.objectToJson(bo);
    }

    @RequestMapping("/queryTransHistory")
    public String queryAssetTransHistory(@RequestParam("id") String id) {
        List<AssetTransHistoryDO> list = assetService.queryAssetTransHistory(id);
        return JsonUtils.objectToJson(list);
    }

    @RequestMapping("/queryBonusHistory")
    public String queryAssetBonusHistory(@RequestParam("id") String id) {
        List<AssetBonusHistoryDO> list = assetService.queryAssetBonusHistory(id);
        return JsonUtils.objectToJson(list);
    }
}
