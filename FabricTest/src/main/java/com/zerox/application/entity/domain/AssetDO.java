package com.zerox.application.entity.domain;

import com.zerox.application.entity.business.AssetBO;

import java.util.Map;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/29 14:28
 */
public class AssetDO {
    private String id;
    private Map<String, Integer> ownerShares;

    public AssetBO toAssetBO() {
        return new AssetBO(id, ownerShares);
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
