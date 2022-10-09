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
    private Map<String, Integer> owners;

    public AssetBO toAssetBO() {
        return new AssetBO(id, owners);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Integer> getOwners() {
        return owners;
    }

    public void setOwners(Map<String, Integer> owners) {
        this.owners = owners;
    }
}
