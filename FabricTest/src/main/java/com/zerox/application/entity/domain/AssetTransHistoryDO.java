package com.zerox.application.entity.domain;

import com.zerox.application.entity.business.AssetTransHistoryBO;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 16:13
 */
public class AssetTransHistoryDO {
    private String assetId;
    private String originOwnerId;
    private String currentOwnerId;
    private Integer shares;
    private Long cost;
    private Long timestamp;

    public AssetTransHistoryBO toAssetTransHistoryBO() {
        return new AssetTransHistoryBO(assetId, originOwnerId, currentOwnerId, shares, cost, timestamp);
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getOriginOwnerId() {
        return originOwnerId;
    }

    public void setOriginOwnerId(String originOwnerId) {
        this.originOwnerId = originOwnerId;
    }

    public String getCurrentOwnerId() {
        return currentOwnerId;
    }

    public void setCurrentOwnerId(String currentOwnerId) {
        this.currentOwnerId = currentOwnerId;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
