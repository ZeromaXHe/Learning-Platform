package com.zerox.application.entity.domain;

import org.hyperledger.fabric.contract.annotation.Property;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 17:19
 */
public class AccountMoneyHistoryDO {
    private String accountId;
    private Long moneyChange;
    private Long moneyAfter;
    private String reason;
    private Long timestamp;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Long getMoneyChange() {
        return moneyChange;
    }

    public void setMoneyChange(Long moneyChange) {
        this.moneyChange = moneyChange;
    }

    public Long getMoneyAfter() {
        return moneyAfter;
    }

    public void setMoneyAfter(Long moneyAfter) {
        this.moneyAfter = moneyAfter;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
