| 快捷键                         | 作用                                                         |
| ------------------------------ | ------------------------------------------------------------ |
| `Ctrl + /`或`Ctrl + shift + /` | 注释（`//`或`/*...*/`）                                      |
| `Ctrl + D`                     | 复制行                                                       |
| `Ctrl + X`                     | 剪切行                                                       |
| `Ctrl + Y`                     | 删除行                                                       |
| `Ctrl + R`                     | 替换文本                                                     |
| `Ctrl + F`                     | 查找文本                                                     |
| `Ctrl + G`                     | 定位某一行（输入对应行号直接跳转，快速移动光标至指定行数列数） |
| `Ctrl + H`                     | Hierarchy界面（查看类的继承层级关系）                        |
| `Ctrl + I`                     | 实现方法                                                     |
| `Ctrl + O`                     | 覆盖/重写方法                                                |
| `Ctrl + Q`                     | 查看方法说明javadoc注释                                      |
| `Ctrl + J`                     | 自动代码，插入代码模板（类似psvm这些）                       |
| `Ctrl + W`                     | 扩大被选择代码范围（可以用来找对应的括号位置）               |
| `Ctrl + Shift + W`             | 缩小被选择代码范围                                           |
| `Ctrl + F12`                   | structure视图（列出当前类中的所有方法纲要，直接打字可以进行方法的搜索） |
| `Ctrl + Home`                  | 文件开始的位置                                               |
| `Ctrl + End`                   | 文件末尾的位置                                               |
| `Alt + Enter`                  | 快速修复（导包等）                                           |
| `Alt + /`                      | 代码提示                                                     |
| `Alt + Insert`                 | 生成代码(如GET,SET方法,构造函数等)                           |
| `Alt + ←/→`                    | 切换代码视图                                                 |
| `Alt + ↑/↓`                    | 在方法间快速移动定位(上一个/下一个方法)                      |
| `Ctrl + Alt + L`               | 格式化代码（==会和网易云音乐全局快捷键冲突。。。==）         |
| `Ctrl + Alt + T`               | Surround With ...（用try/catch、if、for、synchronized等等语句块包住选择的语句） |
| `Ctrl + Alt + M`               | 重构方法                                                     |
| `Ctrl + Alt + I`               | 自动缩进本行或本块代码                                       |
| `Ctrl + Alt + O`               | 格式化import列表，优化导入的类和包（删除多余import）         |
| `Ctrl + Alt + S`               | 设置                                                         |
| `Ctrl + Alt + U`               | 查看类继承关系类图（在图中选中类按`Ctrl + Alt + B`可以列出实现类，然后全选后回车，可以把继承类加入类图） |
| `Ctrl + Alt + ←`               | 返回上一级代码（==会和网易云音乐全局快捷键冲突。。。==）     |
| `Ctrl + Shift + ↑或↓`          | 向上/下移动代码块                                            |
| `Ctrl + Shift + Space`         | 智能代码提示                                                 |
| `Ctrl + Shift + J`             | 合并多行为一行                                               |
| `Alt + Shift + ↑或↓`           | 向上/下移动代码行                                            |
| `Shift + (Fn) + F6`            | 重构-重命名                                                  |
| `F2`                           | 下一处错误                                                   |
| `Shift + F2`                   | 上一处错误                                                   |

常用的有`fori/sout/psvm + Tab`即可生成循环、System.out、main方法等boilerplate样板代码

for(User user : users)只需输入`user.for + Tab`。再比如，要输入Date birthday = user.getBirthday();只需输入`user.getBirthday().var + Tab`即可

# intellij idea如何取消import合并成*

打开intellij idea开发工具

点击工具栏【File】-【Settings】按钮

打开intellij idea设置界面，点击【Editor】-【Code style】-【java】,在右侧切换到【Imports】选项卡

设置"Class count to use import with *"这个数字调整的大些，就不会出现import * 的问题了
