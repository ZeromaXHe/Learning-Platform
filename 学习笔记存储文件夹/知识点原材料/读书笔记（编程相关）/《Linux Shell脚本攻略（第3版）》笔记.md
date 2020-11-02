# 第1章 小试牛刀

## 1.1 简介

当贝尔实验室为全新的Unix操作系统创建了交互式用户界面之后，计算机便拥有了一项独有的特性。它可以从文本文件（称为shell脚本）中读取并执行命令，就好像这些命令是在终端中输入的一样。

这种能力是生产力上的一次巨大飞跃。程序员们再也不用输入一堆命令来执行一系列操作，只需要把这些命令保存在文件中，随后轻敲几次按键运行这个文件就可以了。 shell脚本不仅节省了时间，而且清楚明白地表明了所执行的操作。

Unix刚开始只支持一种交互式shell，它是由Stephen Bourne所编写的Bourne Shell（ sh）。

1989年， GNU项目的Brian Fox吸收了大量其他用户界面的特性，编写出了一种全新的shell：Bourne Again Shell（ bash）。 bash shell与Bourne Shell完全兼容，同时又增添了一些来自csh、 ksh等的功能。

随着Linux成为最流行的类Unix操作系统实现， bash shell也变成了Unix和Linux中既成事实的标准shell。

本书关注的是Linux和bash。即便如此，书中的大部分脚本都可以运行在使用了bash、 sh、 ash、dash、 ksh或其他sh风格shell的Linux和Unix系统中。

本章将带领读者熟悉shell环境并演示一些基本的shell特性。

## 1.2 在终端中显示输出

用户是通过终端会话同shell环境打交道的。如果你使用的是基于图形用户界面的系统，这指的就是终端窗口。如果没有图形用户界面（生产服务器或SSH会话），那么登录后你看到的就是shell提示符。

在终端中显示文本是大多数脚本和实用工具经常需要执行的任务。 shell可以使用多种方法和格式显示文本。

### 1.2.1 预备知识

命令都是在终端会话中输入并执行的。打开终端时会出现一个提示符。有很多方法可以配置提示符，不过其形式通常如下：

`username@hostname$`

或者也可以配置成root@hostname #，或者简单地显示为$或#。

$表示普通用户， #表示管理员用户root。 root是Linux系统中权限最高的用户。

> 以root用户（管理员）的身份直接使用shell来执行任务可不是个好主意。因为如果shell具备较高的权限，命令中出现的输入错误有可能造成更严重的破坏， 所以推荐使用普通用户（ shell会在提示符中以$来表明这种身份）登录系统，然后借助sudo这类工具来运行特权命令。使用sudo <command> <arguments>执行命令的效果和root一样。

shell脚本通常以shebang①起始：

```shell script
#!/bin/bash
```

> shebang这个词其实是两个字符名称（ sharp-bang）的简写。在Unix的行话里，用sharp或hash（有时候是mesh）来称呼字符“#”，用bang来称呼惊叹号“!”，因而shebang合起来就代表了这两个字符。详情请参考：http://en.wikipedia.org/wiki/Shebang_(Unix)。（注：书中脚注均为译者注。）

shebang是一个文本行， 其中#!位于解释器路径之前。 /bin/bash是Bash的解释器命令路径。 bash将以#符号开头的行视为注释。脚本中只有第一行可以使用shebang来定义解释该脚本所使用的解释器。

脚本的执行方式有两种。

(1) 将脚本名作为命令行参数：
`bash myScript.sh`

(2) 授予脚本执行权限，将其变为可执行文件：
`chmod 755 myScript.sh`
`./myScript.sh.`

如果将脚本作为bash的命令行参数来运行，那么就用不着使用shebang了。可以利用shebang来实现脚本的独立运行。可执行脚本使用shebang之后的解释器路径来解释脚本。

使用chmod命令赋予脚本可执行权限：
`$ chmod a+x sample.sh`

该命令使得所有用户可以按照下列方式执行该脚本：
`$ ./sample.sh #./表示当前目录`
或者
`$ /home/path/sample.sh #使用脚本的完整路径`

内核会读取脚本的首行并注意到shebang为#!/bin/bash。它会识别出/bin/bash并执行该脚本：
`$ /bin/bash sample.sh`

当启动一个交互式shell时，它会执行一组命令来初始化提示文本、颜色等设置。这组命令来自用户主目录中的脚本文件~/.bashrc（对于登录shell则是~/.bash_profile）。 Bash shell还维护了一个历史记录文件~/.bash_history，用于保存用户运行过的命令。

> ~表示主目录，它通常是/home/user，其中user是用户名，如果是root用户， 则为/root。登录shell是登录主机后创建的那个shell。但登录图形化环境（比如GNOME、 KDE等）后所创建的终端会话并不是登录shell。使用GNOME或KDE这类显示管理器登录后并不会读取.profile或.bash_profile（绝大部分情况下不会），而使用ssh登录远程系统时则会读取.profile。 shell使用分号或换行符来分隔单个命令或命令序列。比如：
  $ cmd1 ; cmd2
  这等同于：
>
> $ cmd1
>
> $ cmd2

注释部分以#为起始，一直延续到行尾。注释行通常用于描述代码或是在调试期间禁止执行某行代码（ shell不执行脚本中的任何注释部分。）：
~~~shell script
# sample.sh - echoes "hello world"
echo "hello world"
~~~
现在让我们继续讨论基本特性。

### 1.2.2 实战演练

echo是用于终端打印的最基本命令。

默认情况下， echo在每次调用后会添加一个换行符：
~~~shell script
$ echo "Welcome to Bash"
Welcome to Bash
~~~
只需要将文本放入双引号中， echo命令就可以将其中的文本在终端中打印出来。类似地，不使用双引号也可以得到同样的输出结果：
~~~shell script
$ echo Welcome to Bash
Welcome to Bash
~~~
实现相同效果的另一种方式是使用单引号：
~~~shell script
$ echo 'text in quotes'
~~~
这些方法看起来相似，但各有特定的用途及副作用。双引号允许shell解释字符串中出现的特殊字符。单引号不会对其做任何解释。