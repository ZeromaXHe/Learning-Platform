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

import static com.zerox.constant.ContractConstants.ASSET_CONTRACT_NAME;

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
                AssetDO.class, ASSET_CONTRACT_NAME, "queryAsset", id);
    }

    @Override
    public AssetDO createAsset(String id, String owners, String timestamp) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AssetDO.class, ASSET_CONTRACT_NAME, "createAsset", id, owners, timestamp);
    }

    @Override
    public AssetDO changeAssetOwner(String id, String from, String to, String share, String cost, String timestamp) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AssetDO.class, ASSET_CONTRACT_NAME, "changeAssetOwner", id, from, to, share, cost, timestamp);
    }

    @Override
    public AssetDO bonus(String id, String bonus, String timestamp) {
        return fabricManager.contractSubmitTransactionAndGetObject(
                AssetDO.class, ASSET_CONTRACT_NAME, "bonus", id, bonus, timestamp);
    }

    @Override
    public List<AssetTransHistoryDO> queryAssetTransHistory(String id) {
        return fabricManager.contractSubmitTransactionAndGetList(
                AssetTransHistoryDO.class, ASSET_CONTRACT_NAME, "queryAssetTransHistory", id);
    }

    @Override
    public List<AssetBonusHistoryDO> queryAssetBonusHistory(String id) {
        return fabricManager.contractSubmitTransactionAndGetList(
                AssetBonusHistoryDO.class, ASSET_CONTRACT_NAME, "queryAssetBonusHistory", id);
    }
}
