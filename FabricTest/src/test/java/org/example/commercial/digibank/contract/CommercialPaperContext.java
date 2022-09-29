package org.example.commercial.digibank.contract;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeStub;

/**
 * @author fabric
 * @apiNote
 * @implNote
 * @since 2022/9/29 15:20
 */
public class CommercialPaperContext extends Context {
    public CommercialPaperContext(ChaincodeStub stub) {
        super(stub);
        this.paperList = new PaperList(this);
    }

    public PaperList paperList;
}
