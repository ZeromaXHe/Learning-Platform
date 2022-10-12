package com.zerox.application.entity.domain;

import com.zerox.application.entity.business.AssetBonusHistoryBO;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Map;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 16:36
 */
public class AssetBonusHistoryDO {
    private String assetId;
    private Long bonusSum;
    private Map<String, Long> ownerBonus;
    private Long timestamp;

    public AssetBonusHistoryBO toAssetBonusHistoryBO() {
        return new AssetBonusHistoryBO(assetId, bonusSum, ownerBonus, timestamp);
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Long getBonusSum() {
        return bonusSum;
    }

    public void setBonusSum(Long bonusSum) {
        this.bonusSum = bonusSum;
    }

    public Map<String, Long> getOwnerBonus() {
        return ownerBonus;
    }

    public void setOwnerBonus(Map<String, Long> ownerBonus) {
        this.ownerBonus = ownerBonus;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
