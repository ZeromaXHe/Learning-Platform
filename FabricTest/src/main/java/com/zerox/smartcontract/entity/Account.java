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
 * @since 2022/9/29 17:08
 */
@DataType
public class Account {
    @Property
    private final String id;
    @Property
    private final Map<String, Integer> assets;

    public Account(@JsonProperty("id") final String id,
                 @JsonProperty("assets") final Map<String, Integer> assets) {
        this.id = id;
        this.assets = assets;
    }

    public String getId() {
        return id;
    }

    public Map<String, Integer> getAssets() {
        return assets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        return assets != null ? assets.equals(account.assets) : account.assets == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assets != null ? assets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("assets=" + assets)
                .toString();
    }
}
