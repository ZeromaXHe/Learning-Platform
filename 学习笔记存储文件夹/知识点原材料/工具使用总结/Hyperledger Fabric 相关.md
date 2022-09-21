# Ubuntu 安装 Git

1. 打开终端，输入 `sudo apt update` 命令。
2. 输入密码，确认授权。
3. 输入 `sudo apt install git` 命令。
4. 输入 `y`，确认命令执行。
5. 输入 `git --version` 命令，查看安装版本。
6. Git当前版本为【2.34.1】，就此安装完成。

# Ubuntu 安装 curl

1. `sudo apt-get update`
2. `sudo apt install curl`
3. `curl` 验证是否安装成功。成功时显示为：“curl: try 'curl --help' or 'curl --manual' for more information”

> Ubuntu 使用 sudo apt install安装时，提示：`正在等待缓存锁：无法获得锁 /var/lib/dpkg/lock-frontend。锁正由进程 4860（unattended-upgr）持有`
>
> 1. 终端输入 `ps aux` ，列出进程。找到含有 apt-get 的进程，直接 `sudo kill PID`。
> 2. 强制解锁,命令
>    `sudo rm /var/cache/apt/archives/lock`
>    `sudo rm /var/lib/dpkg/lock`
>
> 上面都没有用，最后还是 `sudo rm /var/lib/dpkg/lock-frontend` 解决了。

# Ubuntu 安装 Docker

1. `sudo apt-get install -y docker.io`
2. `docker --version` 确认安装成功，显示版本
3. 确保 docker daemon 是在运行着的: `sudo systemctl start docker`
4. 安装 docker-compose: `sudo apt-get -y install docker-compose`
5. 检查 docker-compose 是否安装: `docker-compose --version`

# 安装 fabric-samples 示例

`sudo curl -sSL https://bit.ly/2ysbOFE | bash -s`

> 短链对应 https://raw.githubusercontent.com/hyperledger/fabric/master/scripts/bootstrap.sh ，国内可能需要替换成对应镜像
>
> 前面需要加 sudo，不然后面安装 docker 镜像时会报：Got permission denied while trying to connect to the Docker daemon socket
>
> 但是 bootstrap.sh 脚本里面其实还是用的 github.com 去 clone git 和获取 releases，可以把脚本下下来，用镜像站改掉里面的地址，这样下载会快些。

# 启动测试网络

参考 https://hyperledger-fabric.readthedocs.io/zh_CN/latest/test_network.html

1. `cd fabric-samples/test-network`
2. 在`test-network`目录中，运行以下命令删除先前运行的所有容器或工程: `sudo ./network.sh down`
3. 然后，您可以通过执行以下命令来启动网络: `sudo ./network.sh up`

# 创建一个通道

您也可以使用channel标志创建具有自定义名称的通道。 作为一个例子，以下命令将创建一个名为 `channel1` 的通道（不加 -c 与其后的内容，则为默认名称 `mychannel`）：

`sudo ./network.sh createChannel -c channel1`

# 在通道启动一个链码

使用 `network.sh` 创建频道后，您可以使用以下命令在通道上启动链码：

`./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-go -ccl go`

`deployCC` 子命令将在 `peer0.org1.example.com` 和 `peer0.org2.example.com` 上安装 **asset-transfer (basic)** 链码。 然后在使用通道标志（或 `mychannel` 如果未指定通道）的通道上部署指定的通道的链码。 如果您第一次部署一套链码，脚本将安装链码的依赖项。默认情况下，脚本安装 Go 版本的 asset-transfer (basic) 链码。 但是您可以使用语言便签 `-l`，用于安装 Java 或 javascript 版本的链码。 您可以在 `fabric-samples` 目录的 `asset-transfer-basic` 文件夹中找到 asset-transfer (basic) 链码。 此目录包含作为案例和用来突显 Fabric 特征的样本链码。

> 需要安装 go
>
> 1. `go version` 检查是否安装
> 2. `sudo apt install golang-go`
>
> 启动链码时，go 依赖下载会被拒绝：`go: github.com/golang/protobuf@v1.3.2: Get "https://proxy.golang.org/github.com/golang/protobuf/@v/v1.3.2.mod": dial tcp 172.217.160.113:443: connect: connection refused`
>
> 解决方案，打开 GO111MODULE 工具，更换 Go 代理，命令行输入：
>
> `go env -w GOPROXY=https://goproxy.io,direct`
>
> `go env -w GO111MODULE=on`
>
> 整了半天，还是不行…… 根本不熟悉 Go 的生态，各种问题只能上网搜，搜索还解决不了…… 放弃了官方文档里的示例，自己改用了 java 链码



java 链码：

`sudo ./network.sh deployCC -ccn basic -ccp ../asset-transfer-basic/chaincode-java -ccl java -c channel1`

> 安装 java
>
> 1. `sudo apt install openjdk-8-jdk`
> 2. `java -version`
>
> 
>
> scripts/ccutils.sh: 行 9: jq：未找到命令
>
> 需要安装 jq：
>
> `sudo apt-get install jq`
>
> 
>
> 必须加 sudo，否则报错：`KeyMaterial not found in SigningIdentityInfo`
>
> 
>
> `-c channel1` 指定通道，否则默认为 mychannel，没有建立的话会报 `Error: proposal failed with status: 500 - channel 'mychannel' not found`



# 与网络交互

确保您正在从 `test-network` 目录进行操作。您可以在 `fabric-samples` 代码库的 `bin` 文件夹中找到 `peer` 二进制文件。 使用以下命令将这些二进制文件添加到您的 CLI 路径：

`export PATH=${PWD}/../bin:$PATH` 

> 需要使用 `sudo bash` 指令后再 export， 不然后面指令没办法在 sudo 下执行。
>
> 其实本章内容都需要在 `sudo bash` 中完成再 `exit` 退出。不然 peer 直接执行的话，会报 `KeyMaterial not found in SigningIdentityInfo`

您还需要将 `fabric-samples` 代码库中的 `FABRIC_CFG_PATH` 设置为指向其中的 `core.yaml` 文件：

`export FABRIC_CFG_PATH=$PWD/../config/`

现在，您可以设置环境变量，以允许您作为 Org1 操作 `peer` CLI：

```sh
# Environment variables for Org1

export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org1MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
export CORE_PEER_ADDRESS=localhost:7051
```

`CORE_PEER_TLS_ROOTCERT_FILE` 和 `CORE_PEER_MSPCONFIGPATH` 环境变量指向 Org1 的 `organizations` 文件夹中的的加密材料。

如果您使用 `./network.sh deployCC -ccl go` 安装和启动 asset-transfer (basic) 链码，您可以调用链码（Go）的 `InitLedger` 方法来赋予一些账本上的初始资产（如果使用 typescript 或者 javascript，例如 `./network.sh deployCC -l javascript`，你会调用相关链码的 `initLedger` 功能； Java 直接使用下面代码即可）。运行以下命令用一些资产来初始化账本：运行以下命令用一些资产来初始化账本：

```sh
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem -C channel1 -n basic --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt -c '{"function":"InitLedger","Args":[]}'
```

如果命令成功，您将观察到类似以下的输出：

```
-> INFO 001 Chaincode invoke successful. result: status:200
```

现在你可以用你的 CLI 工具来查询账本。运行以下指令来获取添加到通道账本的资产列表：

```sh
peer chaincode query -C channel1 -n basic -c '{"Args":["GetAllAssets"]}'
```

如果成功，您将看到以下输出：

```
[
  {"ID": "asset1", "color": "blue", "size": 5, "owner": "Tomoko", "appraisedValue": 300},
  {"ID": "asset2", "color": "red", "size": 5, "owner": "Brad", "appraisedValue": 400},
  {"ID": "asset3", "color": "green", "size": 10, "owner": "Jin Soo", "appraisedValue": 500},
  {"ID": "asset4", "color": "yellow", "size": 10, "owner": "Max", "appraisedValue": 600},
  {"ID": "asset5", "color": "black", "size": 15, "owner": "Adriana", "appraisedValue": 700},
  {"ID": "asset6", "color": "white", "size": 15, "owner": "Michel", "appraisedValue": 800}
]
```

> 实际的数据略有差异：
>
> [{"appraisedValue":300,"assetID":"asset1","color":"blue","owner":"Tomoko","size":5},{"appraisedValue":400,"assetID":"asset2","color":"red","owner":"Brad","size":5},{"appraisedValue":500,"assetID":"asset3","color":"green","owner":"Jin Soo","size":10},{"appraisedValue":600,"assetID":"asset4","color":"yellow","owner":"Max","size":10},{"appraisedValue":700,"assetID":"asset5","color":"black","owner":"Adrian","size":15},{"appraisedValue":700,"assetID":"asset6","color":"white","owner":"Michel","size":15}]

当一个网络成员希望在账本上转一些或者改变一些资产，链码会被调用。使用以下的指令来通过调用 asset-transfer (basic) 链码改变账本上的资产所有者：

```sh
peer chaincode invoke -o localhost:7050 --ordererTLSHostnameOverride orderer.example.com --tls --cafile ${PWD}/organizations/ordererOrganizations/example.com/orderers/orderer.example.com/msp/tlscacerts/tlsca.example.com-cert.pem -C channel1 -n basic --peerAddresses localhost:7051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt --peerAddresses localhost:9051 --tlsRootCertFiles ${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt -c '{"function":"TransferAsset","Args":["asset6","Christopher"]}'
```

> 每次 sudo bash 都需要重新 export：
>
> ```sh
> export PATH=${PWD}/../bin:$PATH
> export FABRIC_CFG_PATH=$PWD/../config/
> ```
>
> 当然，还有 Org1 的一些变量
>
> ```sh
> export CORE_PEER_TLS_ENABLED=true
> export CORE_PEER_LOCALMSPID="Org1MSP"
> export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt
> export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org1.example.com/users/Admin@org1.example.com/msp
> export CORE_PEER_ADDRESS=localhost:7051
> ```

如果命令成功，您应该看到类似以下响应：

```
2019-12-04 17:38:21.048 EST [chaincodeCmd] chaincodeInvokeOrQuery -> INFO 001 Chaincode invoke successful. result: status:200
```

> 实际响应：
>
> `2022-09-21 17:44:40.016 CST 0001 INFO [chaincodeCmd] chaincodeInvokeOrQuery -> Chaincode invoke successful. result: status:200 payload:"Michel"`

因为 asset-transfer (basic) 链码的背书策略需要交易同时被 Org1 和 Org2 签名，链码调用指令需要使用 `--peerAddresses` 标签来指向 `peer0.org1.example.com` 和 `peer0.org2.example.com`。因为网络的 TLS 被开启，指令也需要用 `--tlsRootCertFiles` 标签指向每个 peer 节点的 TLS 证书。

调用链码之后，我们可以使用另一个查询来查看调用如何改变了区块链账本的资产。因为我们已经查询了 Org1 的 peer，我们可以把这个查询链码的机会通过 Org2 的 peer 来运行。设置以下的环境变量来操作 Org2：

```sh
# Environment variables for Org2

export CORE_PEER_TLS_ENABLED=true
export CORE_PEER_LOCALMSPID="Org2MSP"
export CORE_PEER_TLS_ROOTCERT_FILE=${PWD}/organizations/peerOrganizations/org2.example.com/peers/peer0.org2.example.com/tls/ca.crt
export CORE_PEER_MSPCONFIGPATH=${PWD}/organizations/peerOrganizations/org2.example.com/users/Admin@org2.example.com/msp
export CORE_PEER_ADDRESS=localhost:9051
```

你可以查询运行在 `peer0.org2.example.com` asset-transfer (basic) 链码

```sh
peer chaincode query -C channel1 -n basic -c '{"Args":["ReadAsset","asset6"]}'
```

结果显示 `"asset6"` 转给了 Christopher:

```
{"ID":"asset6","color":"white","size":15,"owner":"Christopher","appraisedValue":800}
```

> 实际结果：`{"owner":"Christopher","color":"white","size":15,"appraisedValue":700,"assetID":"asset6"}`

# 关停网络

使用完测试网络后，您可以使用以下命令关闭网络：

```sh
sudo ./network.sh down
```

该命令将停止并删除节点和链码容器，删除组织加密材料，并从Docker Registry移除链码镜像。 该命令还删除之前运行的通道项目和docker卷。如果您遇到任何问题，还允许您再次运行`./ network.sh up`。