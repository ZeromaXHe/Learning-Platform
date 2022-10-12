package com.zerox.utils;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import org.hyperledger.fabric.shim.ledger.KeyValue;
import org.hyperledger.fabric.shim.ledger.QueryResultsIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 10:38
 */
public class ContractUtils {
    public static void throwChaincodeException(Logger logger, String msg, String payload) {
        logger.severe(msg);
        throw new ChaincodeException(msg, payload);
    }

    public static void validateNonExistence(Logger logger, ChaincodeStub stub, String key, String type) {
        String assetJson = stub.getStringState(key);
        // 对应 key 已经存在值
        if (assetJson != null && !assetJson.isEmpty()) {
            throwChaincodeException(logger,
                    String.format("%s key %s is already used", type, key),
                    type.toUpperCase() + "_KEY_USED");
        }
    }

    public static String validateExistenceAndGetValueJson(Logger logger, ChaincodeStub stub, String key, String valueType) {
        String json = stub.getStringState(key);
        // 没找到对应值
        if (json == null || json.isEmpty()) {
            throwChaincodeException(logger,
                    String.format("%s %s does not exist", valueType, key),
                    valueType.toUpperCase() + "_NOT_FOUND");
        }
        return json;
    }

    public static <T> String history(Class<T> tClass, final Context ctx, final String keyPrefix, final String... params) {
        ChaincodeStub stub = ctx.getStub();
        QueryResultsIterator<KeyValue> result = stub.getStateByPartialCompositeKey(keyPrefix, params);
        List<T> list = new ArrayList<>();
        for (KeyValue kv : result) {
            list.add(ChaincodeJsonUtils.jsonToObject(kv.getStringValue(), tClass));
        }
        return ChaincodeJsonUtils.objectToJson(list);
    }
}
