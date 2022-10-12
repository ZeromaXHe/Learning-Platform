package com.zerox.application.dao.impl;

import com.zerox.application.dao.AssetDao;
import com.zerox.application.dao.FabricManager;
import com.zerox.application.entity.domain.AssetBonusHistoryDO;
import com.zerox.application.entity.domain.AssetDO;
import com.zerox.application.entity.domain.AssetTransHistoryDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/10/8 11:00
 */
@Repository
public class AssetDaoImpl implements AssetDao {
    private static final Logger logger = LoggerFactory.getLogger(AssetDaoImpl.class);

    private final FabricManager fabricManager;

    public AssetDaoImpl(FabricManager fabricManager) {
        this.fabricManager = fabricManager;
    }

    @Override
    public AssetDO queryAsset(String id) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AssetDO.class, "queryAsset", id);
    }

    @Override
    public AssetDO createAssert(String id, String owners) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AssetDO.class, "createAssert", id, owners);
    }

    @Override
    public AssetDO changeAssetOwner(String id, String from, String to, String share, String cost) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AssetDO.class, "changeAssetOwner", id, from, to, share, cost);
    }

    @Override
    public AssetDO bonus(String id, String bonus) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AssetDO.class, "bonus", id, bonus);
    }

    @Override
    public List<AssetTransHistoryDO> queryAssetTransHistory(String id) {
        return fabricManager.contractSubmitTransactionAndGetList(
                AssetTransHistoryDO.class, "queryAssetTransHistory", id);
    }

    @Override
    public List<AssetBonusHistoryDO> queryAssetBonusHistory(String id) {
        return fabricManager.contractSubmitTransactionAndGetList(
                AssetBonusHistoryDO.class, "queryAssetBonusHistory", id);
    }
}
