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
}
