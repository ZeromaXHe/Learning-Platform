package com.zerox.application.entity.business;

import java.util.Map;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 16:42
 */
public class AssetBonusHistoryBO {
    private String assetId;
    private Long bonusSum;
    private Map<String, Long> ownerBonus;
    private Long timestamp;

    public AssetBonusHistoryBO(String assetId, Long bonusSum, Map<String, Long> ownerBonus, Long timestamp) {
        this.assetId = assetId;
        this.bonusSum = bonusSum;
        this.ownerBonus = ownerBonus;
        this.timestamp = timestamp;
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
