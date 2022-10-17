package com.zerox.application.dao;

import com.zerox.utils.JsonUtils;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Identity;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.Wallets;
import org.hyperledger.fabric.gateway.X509Identity;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric.sdk.security.CryptoSuiteFactory;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import static com.zerox.constant.ContractConstants.CHAINCODE_NAME;

/**
 * @author ZeromaXHe
 * @apiNote
 * @implNote
 * @since 2022/10/12 16:43
 */
@Repository
public class FabricManager {
    private static final Logger logger = LoggerFactory.getLogger(FabricManager.class);

    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }

    private final static String ORG1_CA_PATH = "/home/zeromax/fabric/fabric-samples/test-network/" +
            "organizations/peerOrganizations/org1.example.com/ca/ca.org1.example.com-cert.pem";

    private volatile Gateway.Builder builder;

    private void doubleCheckBuilderSingletonInit() {
        if (builder == null) {
            synchronized (this) {
                if (builder == null) {
                    builder = initBuilder();
                }
            }
        }
    }

    private void enrollAdmin() {
        try {
            // Create a CA client for interacting with the CA.
            Properties props = new Properties();
            props.put("pemFile", ORG1_CA_PATH);
            props.put("allowAllHostNames", "true");
            HFCAClient caClient = HFCAClient.createNewInstance("https://localhost:7054", props);
            CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
            caClient.setCryptoSuite(cryptoSuite);

            // Create a wallet for managing identities
            Wallet wallet = Wallets.newFileSystemWallet(Paths.get("wallet"));

            // Check to see if we've already enrolled the admin user.
            if (wallet.get("admin") != null) {
                logger.info("An identity for the admin user \"admin\" already exists in the wallet");
                return;
            }

            // Enroll the admin user, and import the new identity into the wallet.
            final EnrollmentRequest enrollmentRequestTLS = new EnrollmentRequest();
            enrollmentRequestTLS.addHost("localhost");
            enrollmentRequestTLS.setProfile("tls");
            Enrollment enrollment = caClient.enroll("admin", "adminpw", enrollmentRequestTLS);
            Identity user = Identities.newX509Identity("Org1MSP", enrollment);
            wallet.put("admin", user);
            logger.info("Successfully enrolled user \"admin\" and imported it into the wallet");
        } catch (Exception e) {
            logger.error("enrollAdmin error: ", e);
        }
    }

    private void registerUser() {
        try {
            // Create a CA client for interacting with the CA.
            Properties props = new Properties();
            props.put("pemFile", ORG1_CA_PATH);
            props.put("allowAllHostNames", "true");
            HFCAClient caClient = HFCAClient.createNewInstance("https://localhost:7054", props);
            CryptoSuite cryptoSuite = CryptoSuiteFactory.getDefault().getCryptoSuite();
            caClient.setCryptoSuite(cryptoSuite);

            // Create a wallet for managing identities
            Wallet wallet = Wallets.newFileSystemWallet(Paths.get("wallet"));

            // Check to see if we've already enrolled the user.
            if (wallet.get("appUser") != null) {
                logger.info("An identity for the user \"appUser\" already exists in the wallet");
                return;
            }

            X509Identity adminIdentity = (X509Identity) wallet.get("admin");
            if (adminIdentity == null) {
                logger.error("\"admin\" needs to be enrolled and added to the wallet first");
                return;
            }
            User admin = new User() {

                @Override
                public String getName() {
                    return "admin";
                }

                @Override
                public Set<String> getRoles() {
                    return null;
                }

                @Override
                public String getAccount() {
                    return null;
                }

                @Override
                public String getAffiliation() {
                    return "org1.department1";
                }

                @Override
                public Enrollment getEnrollment() {
                    return new Enrollment() {

                        @Override
                        public PrivateKey getKey() {
                            return adminIdentity.getPrivateKey();
                        }

                        @Override
                        public String getCert() {
                            return Identities.toPemString(adminIdentity.getCertificate());
                        }
                    };
                }

                @Override
                public String getMspId() {
                    return "Org1MSP";
                }

            };

            // Register the user, enroll the user, and import the new identity into the wallet.
            RegistrationRequest registrationRequest = new RegistrationRequest("appUser");
            registrationRequest.setAffiliation("org1.department1");
            registrationRequest.setEnrollmentID("appUser");
            String enrollmentSecret = caClient.register(registrationRequest, admin);
            Enrollment enrollment = caClient.enroll("appUser", enrollmentSecret);
            Identity user = Identities.newX509Identity("Org1MSP", enrollment);
            wallet.put("appUser", user);
            logger.info("Successfully enrolled user \"appUser\" and imported it into the wallet");
        } catch (Exception e) {
            logger.error("registerUser error: ", e);
        }
    }

    private Gateway.Builder initBuilder() {
        enrollAdmin();
        registerUser();
        Gateway.Builder res;
        try {
            // Load a file system based wallet for managing identities.
            Path walletPath = Paths.get("wallet");
            Wallet wallet = Wallets.newFileSystemWallet(walletPath);
            // load a CCP
            Path networkConfigPath = Paths.get("/home", "zeromax", "fabric", "fabric-samples",
                    "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
            logger.info("network config path: " + networkConfigPath.toAbsolutePath());
            res = Gateway.createBuilder();
            res.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);
        } catch (IOException e) {
            logger.error("init builder error", e);
            res = null;
        }
        return res;
    }

    public <T> List<T> contractSubmitTransactionAndGetList(Class<T> tClass, String contractName, String method, String... params) {
        doubleCheckBuilderSingletonInit();
        String json = contractSubmitTransaction(builder, logger, contractName, method, params);
        if (json != null) return JsonUtils.jsonToList(json, tClass);
        else return null;
    }

    public <T> T contractSubmitTransactionAndGetObject(Class<T> tClass, String contractName, String method, String... params) {
        doubleCheckBuilderSingletonInit();
        String json = contractSubmitTransaction(builder, logger, contractName, method, params);
        if (json != null) return JsonUtils.jsonToObject(json, tClass);
        else return null;
    }

    private static String contractSubmitTransaction(Gateway.Builder builder, Logger logger, String contractName, String method, String... params) {
        // Connect to gateway using application specified parameters
        try (Gateway gateway = builder.connect()) {

            // Access PaperNet network
            Network network = gateway.getNetwork("mychannel");

            // Get addressability to commercial paper contract
            Contract contract = network.getContract(CHAINCODE_NAME, contractName);

            // Buy commercial paper
            byte[] response = contract.submitTransaction(method, params);

            // Process response
            String json = new String(response);
            logger.info(method + " resp json: {}", json);
            return json;
        } catch (ContractException | InterruptedException | TimeoutException e) {
            logger.error(method + " error", e);
            return null;
        }
    }
}
