package com.zerox.application.entity.business;

import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 14:36
 */
public class AssetBO {
    private String id;
    private Map<String, Integer> ownerShares;

    public AssetBO() {
    }

    public AssetBO(String id, Map<String, Integer> ownerShares) {
        this.id = id;
        this.ownerShares = ownerShares;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Integer> getOwnerShares() {
        return ownerShares;
    }

    public void setOwnerShares(Map<String, Integer> ownerShares) {
        this.ownerShares = ownerShares;
    }
}
