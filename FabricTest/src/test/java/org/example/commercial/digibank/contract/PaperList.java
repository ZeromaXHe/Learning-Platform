package org.example.commercial.digibank.contract;

import org.example.commercial.digibank.contract.ledgerapi.StateList;
import org.hyperledger.fabric.contract.Context;

/**
 * @author fabric
 * @apiNote
 * @implNote
 * @since 2022/9/29 15:10
 */
public class PaperList {
    private StateList stateList;

    public PaperList(Context ctx) {
        this.stateList = StateList.getStateList(ctx, PaperList.class.getSimpleName(), CommercialPaper::deserialize);
    }

    public PaperList addPaper(CommercialPaper paper) {
        stateList.addState(paper);
        return this;
    }

    public CommercialPaper getPaper(String paperKey) {
        return (CommercialPaper) this.stateList.getState(paperKey);
    }

    public PaperList updatePaper(CommercialPaper paper) {
        this.stateList.updateState(paper);
        return this;
    }
}
