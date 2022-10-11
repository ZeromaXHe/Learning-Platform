package com.zerox.application.dao;

import com.zerox.application.entity.domain.AssetDO;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 14:23
 */
public interface AssetDao {
    AssetDO queryAsset(String id);

    AssetDO createAssert(String id, String owners);

    AssetDO changeAssetOwner(String id, String from, String to, String share, String cost);
}
