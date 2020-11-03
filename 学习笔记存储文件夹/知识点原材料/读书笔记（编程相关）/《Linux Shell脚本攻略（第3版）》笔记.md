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

思考下面这行命令：

~~~shell script
$ echo "cannot include exclamation - ! within double quotes"
~~~

命令输出如下：
~~~
bash: !: event not found error
~~~
 
如果需要打印像!这样的特殊字符，那就不要将其放入双引号中，而是使用单引号，或是在特殊字符之前加上一个反斜线（\）：
~~~shell script
$ echo Hello world !
~~~ 
或者
~~~shell script
$ echo 'Hello world !'
~~~ 
或者
~~~shell script
$ echo "Hello world \!" #将转义字符放在前面
~~~
如果不使用引号，我们无法在echo中使用分号，因为分号在Bash shell中用作命令间的分隔符：
~~~shell script
echo hello; hello
~~~ 
对于上面的命令，Bash将echo hello作为一个命令，将hello作为另外一个命令。

在下一条攻略中将讨论到的变量替换不会在单引号中执行。

另一个可用于终端打印的命令是printf。该命令使用的参数和C语言中的printf函数一样。例如：
~~~shell script
$ printf "Hello world"
~~~
 
printf命令接受引用文本或由空格分隔的参数。我们可以在printf中使用格式化字符串来指定字符串的宽度、左右对齐方式等。默认情况下，printf并不会自动添加换行符，我们必须在需要的时候手动指定，比如在下面的脚本中：
~~~shell script
#!/bin/bash 
#文件名: printf.sh 
printf "%-5s %-10s %-4s\n" No Name Mark 
printf "%-5s %-10s %-4.2f\n" 1 Sarath 80.3456 
printf "%-5s %-10s %-4.2f\n" 2 James 90.9989 
printf "%-5s %-10s %-4.2f\n" 3 Jeff 77.564
~~~ 
可以得到如下格式化的输出：
~~~
No   Name      Mark 
1    Sarath    80.35 
2    James     91.00 
3    Jeff      77.56
~~~

### 1.2.3 工作原理

%s、%c、%d和%f都是格式替换符（format substitution character），它们定义了该如何打印后续参数。%-5s指明了一个格式为左对齐且宽度为5的字符串替换（-表示左对齐）。如果不指明-，字符串就采用右对齐形式。宽度指定了保留给某个字符串的字符数量。对Name而言，其保留宽度是10。因此，任何Name字段的内容都会被显示在10字符宽的保留区域内，如果内容不足10个
字符，余下的则以空格填充。

对于浮点数，可以使用其他参数对小数部分进行舍入（round off）。

对于Mark字段，我们将其格式化为%-4.2f，其中.2指定保留两位小数。注意，在每行的格式字符串后都有一个换行符（`\n`）

### 1.2.4 补充内容

使用echo和printf的命令选项时，要确保选项出现在命令中的所有字符串之前，否则Bash会将其视为另外一个字符串。

#### 1. 在echo中转义换行符

默认情况下，echo会在输出文本的尾部追加一个换行符。可以使用选项-n来禁止这种行为。echo同样接受双包含转义序列的双引号字符串作为参数。在使用转义序列时，需要使用echo -e "包含转义序列的字符串"这种形式。例如：
~~~shell script
echo -e "1\t2\t3"
~~~
~~~ 
1 2 3
~~~

#### 2. 打印彩色输出

脚本可以使用转义序列在终端中生成彩色文本。

文本颜色是由对应的色彩码来描述的。其中包括：重置=0，黑色=30，红色=31，绿色=32，黄色=33，蓝色=34，洋红=35，青色=36，白色=37。

要打印彩色文本，可输入如下命令：
~~~shell script
echo -e "\e[1;31m This is red text \e[0m"
~~~ 
其中\e[1;31m是一个转义字符串，可以将颜色设为红色，\e[0m将颜色重新置回。只需要将31替换成想要的色彩码就可以了。

对于彩色背景，经常使用的颜色码是：重置=0，黑色=40，红色=41，绿色=42，黄色=43，蓝色=44，洋红=45，青色=46，白色=47。

要设置彩色背景的话，可输入如下命令：
~~~shell script
echo -e "\e[1;42m Green Background \e[0m"
~~~ 
这些例子中包含了一些转义序列。可以使用man console_codes来查看相关文档。

## 1.3 使用变量与环境变量

所有的编程语言都利用变量来存放数据，以备随后使用或修改。和编译型语言不同，大多数脚本语言不要求在创建变量之前声明其类型。用到什么类型就是什么类型。在变量名前面加上一个美元符号就可以访问到变量的值。shell定义了一些变量，用于保存用到的配置信息，比如可用的打印机、搜索路径等。这些变量叫作环境变量。

### 1.3.1 预备知识

变量名由一系列字母、数字和下划线组成，其中不包含空白字符。常用的惯例是在脚本中使用大写字母命名环境变量，使用驼峰命名法或小写字母命名其他变量。

所有的应用程序和脚本都可以访问环境变量。可以使用env或printenv命令查看当前shell中所定义的全部环境变量：
~~~
$> env 
PWD=/home/clif/ShellCookBook 
HOME=/home/clif 
SHELL=/bin/bash 
# …… 其他行
~~~

要查看其他进程的环境变量，可以使用如下命令：
~~~shell script
cat /proc/$PID/environ
~~~ 
其中，PID是相关进程的进程ID（PID是一个整数）。

假设有一个叫作gedit的应用程序正在运行。我们可以使用pgrep命令获得gedit的进程ID：
~~~shell script
$ pgrep gedit 
~~~
~~~
12501
~~~ 
那么，你就可以执行以下命令来查看与该进程相关的环境变量：
~~~shell script
$ cat /proc/12501/environ
~~~
~~~ 
GDM_KEYBOARD_LAYOUT=usGNOME_KEYRING_PID=1560USER=slynuxHOME=/home/slynux
~~~

> 注意，实际输出的环境变量远不止这些，只是考虑到页面篇幅的限制，这里删除了不少内容。
>
> 特殊文件/proc/PID/environ是一个包含环境变量以及对应变量值的列表。每一个变量以name=value的形式来描述，彼此之间由null字符（\0）分隔。形式上确实不太易读。

要想生成一份易读的报表，可以将cat命令的输出通过管道传给tr，将其中的\0替换成\n：
~~~shell script
$ cat /proc/12501/environ | tr '\0' '\n'
~~~

### 1.3.2 实战演练

可以使用等号操作符为变量赋值：
~~~shell script
varName=value 
~~~
varName是变量名，value是赋给变量的值。如果value不包含任何空白字符（例如空格），那么就不需要将其放入引号中，否则必须使用单引号或双引号。

>注意，var = value不同于var=value。把var=value写成var = value是一个常见的错误。两边没有空格的等号是赋值操作,加上空格的等号表示的是等量关系测试。

在变量名之前加上美元符号（$）就可以访问变量的内容。
~~~shell script
var="value" #将"value"赋给变量var 
echo $var 
~~~
也可以这样写：
~~~shell script
echo ${var} 
~~~
输出如下：
~~~
value
~~~ 
我们可以在printf、echo或其他命令的双引号中引用变量值：
~~~shell script
#!/bin/bash 
#文件名:variables.sh 
fruit=apple 
count=5 
echo "We have $count ${fruit}(s)"
~~~
输出如下：
~~~
We have 5 apple(s)
~~~

因为shell使用空白字符来分隔单词，所以我们需要加上一对花括号来告诉shell这里的变量名是fruit，而不是fruit(s)。

环境变量是从父进程中继承而来的变量。例如环境变量HTTP_PROXY，它定义了Internet连接应该使用哪个代理服务器。

该环境变量通常被设置成：
~~~shell script
HTTP_PROXY=192.168.1.23:3128 
export HTTP_PROXY
~~~ 
export命令声明了将由子进程所继承的一个或多个变量。这些变量被导出后，当前shell脚本所执行的任何应用程序都会获得这个变量。shell创建并用到了很多标准环境变量，我们也可以导出自己的环境变量。

例如，PATH变量列出了一系列可供shell搜索特定应用程序的目录。一个典型的PATH变量包含如下内容:

~~~
$ echo $PATH 
/home/slynux/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games
~~~

各目录路径之间以:分隔。$PATH通常定义在/etc/environment、/etc/profile或~/.bashrc中。

如果需要在PATH中添加一条新路径，可以使用如下命令：
~~~shell script
export PATH="$PATH:/home/user/bin"
~~~ 
也可以使用
~~~
$ PATH="$PATH:/home/user/bin" 
$ export PATH 
$ echo $PATH 
/home/slynux/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/home/user/bin
~~~
这样，我们就将/home/user/bin添加到了PATH中。

另外还有一些众所周知的环境变量：HOME、PWD、USER、UID、SHELL等。

> 使用单引号时，变量不会被扩展（expand），仍依照原样显示。这意味着$ echo '$var'会显示$var。
>
> 但如果变量$var已经定义过，那么$ echo "$var"会显示出该变量的值；如果没有定义过，则什么都不显示。

### 1.3.3 补充内容

shell还有很多内建特性。下面就是其中一些。

#### 1.获得字符串的长度

可以用下面的方法获得变量值的长度：
~~~shell script
length=${#var} 
~~~
考虑这个例子：
~~~
$ var=12345678901234567890 
$ echo ${#var} 
20 
~~~
length就是字符串所包含的字符数。

#### 2.识别当前所使用的shell

可以通过环境变量SHELL获知当前使用的是哪种shell：
~~~shell script
echo $SHELL 
~~~
也可以用
~~~shell script
echo $0 
~~~
例如：
~~~
$ echo $SHELL 
/bin/bash
~~~ 
执行echo $0命令也可以得到同样的输出：
~~~
$ echo $0 
/bin/bash
~~~

#### 3.检查是否为超级用户

环境变量UID中保存的是用户ID。它可以用于检查当前脚本是以root用户还是以普通用户的身份运行的。例如：

~~~shell script
If [ $UID -ne 0 ]; then 
 echo Non root user. Please run as root. 
else 
 echo Root user 
fi 
~~~

注意，[实际上是一个命令，必须将其与剩余的字符串用空格隔开。上面的脚本也可以写成：
~~~shell script
If test $UID -ne 0:1 
 then 
  echo Non root user. Please run as root. 
 else
  echo Root user 
fi 
~~~
root用户的UID是0。

#### 4.修改Bash的提示字符串（username@hostname:~$）

当我们打开终端或是运行shell时，会看到类似于user@hostname:/home/$的提示字符串。不同的GNU/Linux发布版中的提示字符串及颜色各不相同。我们可以利用PS1环境变量来定义主提示字符串。默认的提示字符串是在文件~/.bashrc中的某一行设置的。

 查看设置变量PS1的那一行：
~~~
$ cat ~/.bashrc | grep PS1 
PS1='${debian_chroot:+($debian_chroot)}\u@\h:\w\$ '
~~~ 
 如果要修改提示字符串，可以输入：
~~~
slynux@localhost: ~$ PS1="PROMPT>" #提示字符串已经改变
PROMPT> Type commands here.
~~~ 
 我们可以利用类似于\e[1;31的特定转义序列来设置彩色的提示字符串（参考1.2节的内
容）。

还有一些特殊的字符可以扩展成系统参数。例如：\u可以扩展为用户名，\h可以扩展为主
机名，而\w可以扩展为当前工作目录。

## 1.4 使用函数添加环境变量

环境变量通常保存了可用于搜索可执行文件、库文件等的路径列表。例如$PATH和$LD_LIBRARY_PATH，它们通常看起来像这样：
~~~
PATH=/usr/bin; /bin 
LD_LIBRARY_PATH=/usr/lib; /lib
~~~ 
这意味着只要shell执行应用程序（二进制文件或脚本）时，它就会首先查找/usr/bin，然后查找/bin。

当你使用源代码构建并安装程序时，通常需要为新的可执行文件和库文件添加特定的路径。假设我们要将myapp安装到/opt/myapp，它的二进制文件在/opt/myapp/bin目录中，库文件在/opt/ myapp /lib目录中。

### 1.4.1 实战演练

