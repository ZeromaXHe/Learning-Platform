package com.zerox.smartcontract.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 12:01
 */
@DataType
public class AccountMoneyTransHistory {
    @Property
    private final String from;
    @Property
    private final String to;
    @Property
    private final Long money;
    @Property
    private final Long timestamp;

    public AccountMoneyTransHistory(@JsonProperty("from") String from,
                                    @JsonProperty("to") String to,
                                    @JsonProperty("money") Long money,
                                    @JsonProperty("timestamp") Long timestamp) {
        this.from = from;
        this.to = to;
        this.money = money;
        this.timestamp = timestamp;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Long getMoney() {
        return money;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountMoneyTransHistory that = (AccountMoneyTransHistory) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (to != null ? !to.equals(that.to) : that.to != null) return false;
        if (money != null ? !money.equals(that.money) : that.money != null) return false;
        return timestamp != null ? timestamp.equals(that.timestamp) : that.timestamp == null;
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountMoneyTransHistory{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", money=" + money +
                ", timestamp=" + timestamp +
                '}';
    }
}
