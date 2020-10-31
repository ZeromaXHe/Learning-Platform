# GitHub和Gitee共通项目建立过程中有待研究的点

## clone SSH和HTTP的区别
本项目在GitHub和Gitee上均有涉及到。但是引入项目时候，一般都是用的HTTP协议的地址，未使用SSH。所以拉取和提交的时候，remote会要求提供登录用户名和密码。

这里SSH和HTTP的区别有待研究。

SSH貌似在之后合并GitHub和Gitee分支的时候用到了。输入git pull git@github.com:github用户名/repository名.git --allow-unrelated-histories来合并GitHub和Gitee不相关历史的两个分支。这里的`git@github.com:github用户名/repository名.git`其实对应的就是clone里面的SSH地址。

## Git rsa公钥

一开始看的教程提到Git需要在本地用`ssh-keygen –t rsa –C “邮箱地址”`来生成公钥。这次刚好gitee和GitHub用的是同一个邮箱账号，所以就没有重新生成一个，Gitee用的之前弄GitHub时候生成的。

那么假如需要有两个邮箱对应的公钥，生成的文件还是只能在C://用户/用户名文件夹/.ssh下弄一个id_rsa和id_rsa.pub吗？还是需要重命名再生成？这里需要深入研究一下。尤其是结合前面SSH和HTTP的区别，看看这个公钥究竟是干嘛用的。怕不了解出什么安全问题。

## GitHub上main分支和master分支

GitHub上出现了两个分支，这个点也比较奇怪。我手动删除了main分支，后续看看是否有影响。

之前单独在GitHub上更新的项目，基本就只有master分支，没有出现过这个情况。我合并的Gitee上面的内容也是进入了master分支，main分支只是保留GitHub创建项目时候的原状。