package com.zerox.application.service.impl;

import com.zerox.application.dao.AssetDao;
import com.zerox.application.entity.business.AssetBO;
import com.zerox.application.entity.domain.AssetDO;
import com.zerox.application.service.AssetService;
import org.springframework.stereotype.Service;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 14:22
 */
@Service
public class AssetServiceImpl implements AssetService {
    private final AssetDao assetDao;

    public AssetServiceImpl(AssetDao assetDao) {
        this.assetDao = assetDao;
    }

    @Override
    public AssetBO queryAssert(String id) {
        AssetDO domain = assetDao.queryAsset(id);
        if (domain == null) return null;
        return domain.toAssetBO();
    }

    @Override
    public void createAssert(AssetBO asset) {

    }
}
