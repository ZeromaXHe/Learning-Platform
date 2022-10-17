# Maven 相关

com.github.everit-org.json-schema:org.everit.json.schema 这个依赖在中央库里好像没有，搜到的一个 mulesoft 库里的也下不下来。于是就只好直接去 github 上把 1.12.1 的 jar 包下载下来本地安装了。

因为包名有划线，需要用双引号包裹起来（参考 https://blog.csdn.net/weixin_30682415/article/details/101948894）

指令如下：

```sh
mvn install:install-file "-Dfile={替换为 everit-json-schema-1.12.1.jar 的路径}" "-DgroupId=com.github.everit-org.json-schema" "-DartifactId=org.everit.json.schema" "-Dversion=1.12.1" "-Dpackaging=jar"
```

然后就可以 Maven packge 指令打包了。

# VMware Workstation 共享文件

在虚拟机设置里的“选项”中打开共享文件夹即可。

共享文件夹在 Ubuntu 虚拟机中的路径是 `/mnt/hgfs`（安装好VMware Tools后，mnt 下会出现 hgfs）, 主机里是自己指定的位置

可以将打好的链码 jar 包通过共享传到虚拟机

# 链码测试相关指令



```shell
# 移动链码 jar 包
cp /mnt/hgfs/Shares/FabricTest-1.0-SNAPSHOT-chaincode.jar /home/zeromax/mychaincode/FabricTest.jar

# 启动网络
cd /home/zeromax/fabric/fabric-samples/test-network
sudo bash
./network.sh up createChannel -ca

# peer 指令相关
export PATH=${PWD}/../bin:$PATH
export FABRIC_CFG_PATH=$PWD/../config/
peer version

# 创建链码包
peer lifecycle chaincode package my_chaincode.tar.gz --path /home/zeromax/mychaincode --lang java --label my_chaincode_1

# 安装链码包
# Org1
export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
export CORE_PEER_ADDRESS=localhost:7051
peer lifecycle chaincode install my_chaincode.tar.gz
# Org2
export CORE_PEER_LOCALMSPID="Org2MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp
export CORE_PEER_ADDRESS=localhost:9051
peer lifecycle chaincode install my_chaincode.tar.gz

# 批准链码定义
peer lifecycle chaincode queryinstalled
export CC_PACKAGE_ID={上面指令中的Package ID,形式如包名+冒号+一堆小写十六进制数字}

peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name my_chaincode --version 1.0 --package-id $CC_PACKAGE_ID --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem

export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
export CORE_PEER_ADDRESS=localhost:7051
peer lifecycle chaincode approveformyorg -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name my_chaincode --version 1.0 --package-id $CC_PACKAGE_ID --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem

# 将链码定义提交到通道
# peer lifecycle chaincode checkcommitreadiness --channelID mychannel --name my_chaincode --version 1.0 --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem --output json
peer lifecycle chaincode commit -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --channelID mychannel --name my_chaincode --version 1.0 --sequence 1 --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
# peer lifecycle chaincode querycommitted --channelID mychannel --name my_chaincode --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem

# 调用链码
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem -C mychannel -n my_chaincode --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt -c '{"function":"AccountContract:createAccount","Args":["zerox","1231000"]}'

peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem -C mychannel -n my_chaincode --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt -c {指令JSON}
# 按顺序执行下面指令 JSON
'{"function":"AccountContract:createAccount","Args":["zerox","1231000"]}'
'{"function":"AccountContract:queryAccount","Args":["zerox"]}'
'{"function":"AccountContract:queryAccount","Args":["zerox2"]}'
'{"function":"AccountContract:changeAccountMoney","Args":["zerox","200","CHARGE","1232000"]}'
'{"function":"AccountContract:queryAccountMoneyHistory","Args":["zerox"]}'
'{"function":"AccountContract:createAccount","Args":["zerox2","1233000"]}'
'{"function":"AccountContract:queryAccount","Args":["zerox2"]}'
'{"function":"AccountContract:transferAccountMoney","Args":["zerox","zerox2","100","1234000"]}'
'{"function":"AccountContract:queryAccountMoneyTransFromHistory","Args":["zerox"]}'
'{"function":"AssetContract:createAsset","Args":["test1","zerox=10000","1235000"]}'
'{"function":"AssetContract:queryAsset","Args":["test1"]}'
'{"function":"AssetContract:changeAssetOwner","Args":["test1","zerox","zerox2","5000","50","1236000"]}'
'{"function":"AssetContract:queryAssetTransHistory","Args":["test1"]}'
'{"function":"AssetContract:bonus","Args":["test1","10000","1237000"]}'
'{"function":"AssetContract:queryAssetBonusHistory","Args":["test1"]}'
'{"function":"AccountContract:queryAccountBonusHistory","Args":["zerox"]}'
'{"function":"AccountContract:queryAccountBonusFromAssetHistory","Args":["zerox2","test1"]}'


# 查看日志
docker ps -a
docker logs -f {节点NAME}

# 退出
./network.sh down
# 退出 sudo bash
exit
```

## 链码调用报错

`could not assemble transaction: ProposalResponsePayloads do not match (base64)`

对应是链码逻辑内有随机或者其他使得 peer 可能不一致的逻辑（我自己的代码里面是时间戳的逻辑），需要把那些导致不一致的逻辑替换掉。

# 应用程序测试相关指令

```shell
cp /mnt/hgfs/Shares/FabricTest-1.0-SNAPSHOT-app.jar /home/zeromax/myapp/FabricTest-app.jar
# 否则 wallet 文件夹会生成在当前路径（test-network）下
cd /home/zeromax/myapp
# 不删除的话，下次应用访问重新启动的链码网络时，会报错 UNKNOWN: error validating proposal: access denied: channel [mychannel] creator org unknown, creator is malformed
rm -rf wallet
java -jar /home/zeromax/myapp/FabricTest-app.jar
```

对于未访问过链码测试的指令的，可以执行如下 http 访问：

```
http://localhost:8080/account/create?id=zerox&timestamp=1231000
http://localhost:8080/account/query?id=zerox
http://localhost:8080/account/query?id=zerox2
http://localhost:8080/account/changeMoney?id=zerox&money=200&reason=CHARGE&timestamp=1232000
http://localhost:8080/account/queryMoneyHistory?id=zerox
http://localhost:8080/account/create?id=zerox2&timestamp=1233000
http://localhost:8080/account/query?id=zerox2
http://localhost:8080/account/transferMoney?from=zerox&to=zerox2&money=100&timestamp=1234000
http://localhost:8080/account/queryMoneyTransFromHistory?id=zerox
http://localhost:8080/asset/create?id=test1&owners=zerox%3D10000&timestamp=1235000
http://localhost:8080/asset/query?id=test1
http://localhost:8080/asset/changeOwner?id=test1&from=zerox&to=zerox2&share=5000&cost=50&timestamp=1236000
http://localhost:8080/asset/queryTransHistory?id=test1
http://localhost:8080/asset/bonus?id=test1&bonus=10000&timestamp=1237000
http://localhost:8080/account/queryBonusHistory?id=zerox
http://localhost:8080/account/queryBonusFromAssetHistory?accountId=zerox2&assetId=test1
```

## 应用无法启动

启动时显示“/home/zeromax/myapp/FabricTest-app.jar中没有主清单属性”，说明 jar 包内的 MANIFEST.MF 没有 Main-Class 和 Start-Class（可以用解压缩软件打开 jar 包看到）

解决方法 1(无效)：

1. 在 IDEA 中 file -> project structure，弹框中选择 Artifact -> + -> jar -> from module with dependencies
2. 选择 @SpringBootApplication 注解的类（我们这里是 ClientApplication）作为 Main Class，然后指定 META-INF/MANIFEST.MF 的路径为 src 下

解决方法 2：

1. 在 pom.xml 文件中添加插件 spring-boot-maven-plugin

## Maven 应用和链码依赖冲突

运行后，调用接口后 enrollAdmin 过程会报错：`java.lang.ClassNotFoundException: io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk`

该错误是因为 fabric-sdk-java-2.2.18 和 fabric-chaincode-shim-2.4.1 中的 opentelemetry-bom 版本不同导致的依赖冲突（Maven 依赖冲突相关可以参考 https://www.cnblogs.com/qdhxhz/p/16363532.html）

解决方法就是编译应用时在链码的依赖 fabric-chaincode-shim 中排除掉以下依赖（移动顺序没有作用，因为这些依赖在链码里面层次更少所以更优先，应用依赖到这些的层次更多。Maven 的判定顺序：1. 路径最近者优先， 2. 第一声明者优先）
