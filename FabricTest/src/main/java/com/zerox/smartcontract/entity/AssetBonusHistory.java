package com.zerox.smartcontract.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Map;
import java.util.StringJoiner;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/10/8 10:00
 */
@DataType
public class AssetBonusHistory {
    @Property
    private final Long bonusSum;
    @Property
    private final Long timestamp;
    @Property
    private final Map<String, Long> ownerBonus;

    public AssetBonusHistory(@JsonProperty("bonusSum") final Long bonusSum,
                             @JsonProperty("timestamp") final Long timestamp,
                             @JsonProperty("ownerBonus") final Map<String, Long> ownerBonus) {
        this.bonusSum = bonusSum;
        this.timestamp = timestamp;
        this.ownerBonus = ownerBonus;
    }

    public Long getBonusSum() {
        return bonusSum;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Map<String, Long> getOwnerBonus() {
        return ownerBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetBonusHistory that = (AssetBonusHistory) o;

        if (bonusSum != null ? !bonusSum.equals(that.bonusSum) : that.bonusSum != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return ownerBonus != null ? ownerBonus.equals(that.ownerBonus) : that.ownerBonus == null;
    }

    @Override
    public int hashCode() {
        int result = bonusSum != null ? bonusSum.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (ownerBonus != null ? ownerBonus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AssetBonusHistory.class.getSimpleName() + "[", "]")
                .add("bonusSum=" + bonusSum)
                .add("timestamp=" + timestamp)
                .add("ownerBonus=" + ownerBonus)
                .toString();
    }
}
