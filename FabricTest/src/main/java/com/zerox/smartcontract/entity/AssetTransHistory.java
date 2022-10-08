package com.zerox.smartcontract.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.StringJoiner;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 17:16
 */
@DataType
public class AssetTransHistory {
    @Property
    private final String assetId;
    @Property
    private final String originOwnerId;
    @Property
    private final String currentOwnerId;
    @Property
    private final Integer shares;
    @Property
    private final Long cost;

    public AssetTransHistory(@JsonProperty("assetId") final String assetId,
                             @JsonProperty("originOwnerId") final String originOwnerId,
                             @JsonProperty("currentOwnerId") final String currentOwnerId,
                             @JsonProperty("shares") final Integer shares,
                             @JsonProperty("cost") final Long cost) {
        this.assetId = assetId;
        this.originOwnerId = originOwnerId;
        this.currentOwnerId = currentOwnerId;
        this.shares = shares;
        this.cost = cost;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getOriginOwnerId() {
        return originOwnerId;
    }

    public String getCurrentOwnerId() {
        return currentOwnerId;
    }

    public Integer getShares() {
        return shares;
    }

    public Long getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetTransHistory that = (AssetTransHistory) o;

        if (assetId != null ? !assetId.equals(that.assetId) : that.assetId != null) return false;
        if (originOwnerId != null ? !originOwnerId.equals(that.originOwnerId) : that.originOwnerId != null)
            return false;
        if (currentOwnerId != null ? !currentOwnerId.equals(that.currentOwnerId) : that.currentOwnerId != null)
            return false;
        if (shares != null ? !shares.equals(that.shares) : that.shares != null) return false;
        return cost != null ? cost.equals(that.cost) : that.cost == null;
    }

    @Override
    public int hashCode() {
        int result = assetId != null ? assetId.hashCode() : 0;
        result = 31 * result + (originOwnerId != null ? originOwnerId.hashCode() : 0);
        result = 31 * result + (currentOwnerId != null ? currentOwnerId.hashCode() : 0);
        result = 31 * result + (shares != null ? shares.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AssetTransHistory.class.getSimpleName() + "[", "]")
                .add("assetId='" + assetId + "'")
                .add("originOwnerId='" + originOwnerId + "'")
                .add("currentOwnerId='" + currentOwnerId + "'")
                .add("shares=" + shares)
                .add("cost=" + cost)
                .toString();
    }
}
