package org.example.commercial.digibank.application.digibank;

import org.example.commercial.digibank.application.papernet.CommercialPaper;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.GatewayException;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author fabric
 * @apiNote
 * @implNote
 * @since 2022/9/29 15:51
 */
public class Buy {

    private static final String ENVKEY = "CONTRACT_NAME";

    public static void main(String[] args) {
        Gateway.Builder builder = Gateway.createBuilder();

        String contractName = "papercontract";
        // get the name of the contract, in case it is overridden
        Map<String, String> envvar = System.getenv();
        if (envvar.containsKey(ENVKEY)) {
            contractName = envvar.get(ENVKEY);
        }

        try {
            // A wallet stores a collection of identities
            Path walletPath = Paths.get(".", "wallet");
            Wallet wallet = Wallets.newFileSystemWallet(walletPath);
            System.out.println("Read wallet info from: " + walletPath);

            String userName = "User1@org1.example.com";

            Path connectionProfile = Paths.get("..", "gateway", "connection-org1.yaml");

            // Set connection options on the gateway builder
            builder.identity(wallet, userName).networkConfig(connectionProfile).discovery(false);

            // Connect to gateway using application specified parameters
            try (Gateway gateway = builder.connect()) {

                // Access PaperNet network
                System.out.println("Use network channel: mychannel.");
                Network network = gateway.getNetwork("mychannel");

                // Get addressability to commercial paper contract
                System.out.println("Use org.papernet.commercialpaper smart contract.");
                Contract contract = network.getContract(contractName, "org.papernet.commercialpaper");

                // Buy commercial paper
                System.out.println("Submit commercial paper buy transaction.");
                byte[] response = contract.submitTransaction("buy", "MagnetoCorp", "00001", "MagnetoCorp", "DigiBank", "4900000", "2020-05-31");

                // Process response
                System.out.println("Process buy transaction response.");
                CommercialPaper paper = CommercialPaper.deserialize(response);
                System.out.println(paper);
            }
        } catch (GatewayException | IOException | TimeoutException | InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
