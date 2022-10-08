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
 * @since 2022/9/29 10:37
 */
@DataType
public class Asset {
    @Property
    private final String id;
    @Property
    private final Map<String, Integer> owners;

    public Asset(@JsonProperty("id") final String id,
                 @JsonProperty("owners") final Map<String, Integer> owners) {
        this.id = id;
        this.owners = owners;
    }

    public String getId() {
        return id;
    }

    public Map<String, Integer> getOwners() {
        return owners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asset asset = (Asset) o;

        if (id != null ? !id.equals(asset.id) : asset.id != null) return false;
        return owners != null ? owners.equals(asset.owners) : asset.owners == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (owners != null ? owners.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Asset.class.getSimpleName() + "[", "]")
                .add("name='" + id + "'")
                .add("owners=" + owners)
                .toString();
    }
}
