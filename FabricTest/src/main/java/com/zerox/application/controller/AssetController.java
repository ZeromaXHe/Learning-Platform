package com.zerox.application.controller;

import com.zerox.application.entity.business.AssetBO;
import com.zerox.application.service.AssetService;
import com.zerox.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
