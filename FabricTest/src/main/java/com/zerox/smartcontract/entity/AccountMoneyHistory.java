package com.zerox.smartcontract.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.StringJoiner;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 17:13
 */
@DataType
public class AccountMoneyHistory {
    @Property
    private final String accountId;
    @Property
    private final Long moneyChange;
    @Property
    private final Long moneyAfter;
    @Property
    private final String reason;
    @Property
    private final Long timestamp;

    public AccountMoneyHistory(@JsonProperty("accountId") final String accountId,
                               @JsonProperty("moneyChange") final Long moneyChange,
                               @JsonProperty("moneyAfter") final Long moneyAfter,
                               @JsonProperty("reason") final String reason,
                               @JsonProperty("timestamp") final Long timestamp) {
        this.accountId = accountId;
        this.moneyChange = moneyChange;
        this.moneyAfter = moneyAfter;
        this.reason = reason;
        this.timestamp = timestamp;
    }

    public String getAccountId() {
        return accountId;
    }

    public Long getMoneyChange() {
        return moneyChange;
    }

    public Long getMoneyAfter() {
        return moneyAfter;
    }

    public String getReason() {
        return reason;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountMoneyHistory that = (AccountMoneyHistory) o;

        if (accountId != null ? !accountId.equals(that.accountId) : that.accountId != null) return false;
        if (moneyChange != null ? !moneyChange.equals(that.moneyChange) : that.moneyChange != null) return false;
        if (moneyAfter != null ? !moneyAfter.equals(that.moneyAfter) : that.moneyAfter != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = accountId != null ? accountId.hashCode() : 0;
        result = 31 * result + (moneyChange != null ? moneyChange.hashCode() : 0);
        result = 31 * result + (moneyAfter != null ? moneyAfter.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccountMoneyHistory.class.getSimpleName() + "[", "]")
                .add("accountId='" + accountId + "'")
                .add("moneyChange=" + moneyChange)
                .add("moneyAfter=" + moneyAfter)
                .add("reason='" + reason + "'")
                .add("timestamp=" + timestamp)
                .toString();
    }
}
