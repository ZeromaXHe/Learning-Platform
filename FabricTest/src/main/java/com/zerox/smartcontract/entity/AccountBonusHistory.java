package com.zerox.smartcontract.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/10/8 10:00
 */
@DataType
public class AccountBonusHistory {
    @Property
    private final String accountId;
    @Property
    private final String assetId;
    @Property
    private final Integer shares;
    @Property
    private final Long bonus;
    @Property
    private final Long timestamp;

    public AccountBonusHistory(@JsonProperty("accountId") final String accountId,
                               @JsonProperty("assetId") final String assetId,
                               @JsonProperty("shares") final Integer shares,
                               @JsonProperty("bonus") final Long bonus,
                               @JsonProperty("timestamp") final Long timestamp) {
        this.accountId = accountId;
        this.assetId = assetId;
        this.shares = shares;
        this.bonus = bonus;
        this.timestamp = timestamp;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAssetId() {
        return assetId;
    }

    public Integer getShares() {
        return shares;
    }

    public Long getBonus() {
        return bonus;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountBonusHistory that = (AccountBonusHistory) o;

        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (assetId != null ? !assetId.equals(that.assetId) : that.assetId != null) return false;
        if (shares != null ? !shares.equals(that.shares) : that.shares != null) return false;
        if (bonus != null ? !bonus.equals(that.bonus) : that.bonus != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (assetId != null ? assetId.hashCode() : 0);
        result = 31 * result + (shares != null ? shares.hashCode() : 0);
        result = 31 * result + (bonus != null ? bonus.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountBonusHistory{" +
                "accountId='" + accountId + '\'' +
                ", assetId='" + assetId + '\'' +
                ", shares=" + shares +
                ", bonus=" + bonus +
                ", timestamp=" + timestamp +
                '}';
    }
}
