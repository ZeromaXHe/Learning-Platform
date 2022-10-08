package com.zerox.application.dao.impl;

import com.zerox.application.dao.AssetDao;
import com.zerox.application.entity.domain.AssetDO;
import com.zerox.utils.JsonUtils;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/10/8 11:00
 */
@Repository
public class AssetDaoImpl implements AssetDao {
    private static final Logger logger = LoggerFactory.getLogger(AssetDaoImpl.class);

    private volatile Gateway.Builder builder;
    private final static String CONTRACT_NAME = "asset_contract";

    private Gateway.Builder initBuilder() {
        Gateway.Builder res;
        try {
            res = Gateway.createBuilder();
            // A wallet stores a collection of identities
            Path walletPath = Paths.get(".", "wallet");
            Wallet wallet = Wallets.newFileSystemWallet(walletPath);

            String userName = "User1@org1.example.com";
            Path connectionProfile = Paths.get("..", "gateway", "connection-org1.yaml");

            // Set connection options on the gateway builder
            res.identity(wallet, userName).networkConfig(connectionProfile).discovery(false);
        } catch (IOException e) {
            logger.error("init builder error", e);
            res = null;
        }
        return res;
    }

    private void doubleCheckBuilderSingletonInit() {
        if(builder == null) {
            synchronized (this) {
                if(builder == null) {
                    builder = initBuilder();
                }
            }
        }
    }

    @Override
    public AssetDO queryAsset(String id) {
        doubleCheckBuilderSingletonInit();
        // Connect to gateway using application specified parameters
        try (Gateway gateway = builder.connect()) {

            // Access PaperNet network
            Network network = gateway.getNetwork("mychannel");

            // Get addressability to commercial paper contract
            Contract contract = network.getContract(CONTRACT_NAME, "org.papernet.commercialpaper");

            // Buy commercial paper
            byte[] response = contract.submitTransaction("queryAsset", id);

            // Process response
            String json = new String(response);
            logger.info("queryAsset resp json: {}", json);
            return JsonUtils.jsonToObject(json, AssetDO.class);
        } catch (ContractException | InterruptedException | TimeoutException e) {
            logger.error("queryAsset error", e);
            return null;
        }
    }
}
