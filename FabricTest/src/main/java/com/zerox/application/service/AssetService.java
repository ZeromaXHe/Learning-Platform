package com.zerox.application.service;

import com.zerox.application.entity.business.AssetBO;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 13:47
 */
public interface AssetService {
    AssetBO queryAssert(String id);

    AssetBO createAssert(String id, String owners);

    AssetBO changeAssetOwner(String id, String from, String to, String share, String cost);
}
