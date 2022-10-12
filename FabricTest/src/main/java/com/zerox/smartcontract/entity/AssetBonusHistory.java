package com.zerox.smartcontract.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/10/8 10:00
 */
@DataType
public class AssetBonusHistory {
    @Property
    private final String assetId;
    @Property
    private final Long bonusSum;
    @Property
    private final Map<String, Long> ownerBonus;
    @Property
    private final Long timestamp;

    public AssetBonusHistory(@JsonProperty("assetId") final String assetId,
                             @JsonProperty("bonusSum") final Long bonusSum,
                             @JsonProperty("ownerBonus") final Map<String, Long> ownerBonus,
                             @JsonProperty("timestamp") final Long timestamp) {
        this.assetId = assetId;
        this.bonusSum = bonusSum;
        this.ownerBonus = ownerBonus;
        this.timestamp = timestamp;
    }

    public String getAssetId() {
        return assetId;
    }

    public Long getBonusSum() {
        return bonusSum;
    }

    public Map<String, Long> getOwnerBonus() {
        return ownerBonus;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetBonusHistory that = (AssetBonusHistory) o;

        if (assetId != null ? !assetId.equals(that.assetId) : that.assetId != null) return false;
        if (bonusSum != null ? !bonusSum.equals(that.bonusSum) : that.bonusSum != null) return false;
        if (ownerBonus != null ? !ownerBonus.equals(that.ownerBonus) : that.ownerBonus != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = assetId != null ? assetId.hashCode() : 0;
        result = 31 * result + (bonusSum != null ? bonusSum.hashCode() : 0);
        result = 31 * result + (ownerBonus != null ? ownerBonus.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AssetBonusHistory{" +
                "assetId='" + assetId + '\'' +
                ", bonusSum=" + bonusSum +
                ", ownerBonus=" + ownerBonus +
                ", timestamp=" + timestamp +
                '}';
    }
}
