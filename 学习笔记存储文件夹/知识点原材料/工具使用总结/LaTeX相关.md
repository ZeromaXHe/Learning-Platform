# Typora行内LaTeX公式无效

## Question:

在Typora中输入$...$后，无法显示为公式。

## Solution:

> 找到Typora -> Preference -> Mardown -> Syntax Support -> 勾选Inline Math(e.g: $\LaTeX$)。
> 然后重启Typora编辑器，即可！

对于我目前使用的0.9.96(beta)版本，路径如下：

文件 -> 偏好设置 -> Markdown -> Markdown扩展语法 -> 内联公式



# LaTeX语法大全

https://blog.csdn.net/young951023/article/details/79601664

## 声调

| 语法           | 效果           | 语法           | 效果           | 语法             | 效果             |
| -------------- | -------------- | -------------- | -------------- | ---------------- | ---------------- |
| `\bar{x}`      | $\bar{x}$      | `\acute{\eta}` | $\acute{\eta}$ | `\check{\alpha}` | $\check{\alpha}$ |
| `\grave{\eta}` | $\grave{\eta}$ | `\breve{a}`    | $\breve{a}$    | `\ddot{y}`       | $\ddot{y}$       |
| `\dot{x}`      | $\dot{x}$      | `\hat{\alpha}` | $\hat{\alpha}$ | `\tilde{\iota}`  | $\tilde{\iota}$  |

## 运算符

| 名称 | 语法                 | 效果                 |
| ---- | -------------------- | -------------------- |
| 求和 | `\sum_1^n`           | $\sum_1^n$           |
| 积分 | `\int_1^n`           | $\int_1^n$           |
| 极限 | `lim_{x \to \infty}` | $lim_{x \to \infty}$ |
| 分数 | `\frac{3}{8}`        | $\frac{3}{8}$        |
| 根号 | `\sqrt[n]{3}`        | $\sqrt[n]{3}$        |

## 集合

| 语法        | 效果        | 语法          | 效果          | 语法        | 效果        | 语法        | 效果        | 语法          | 效果          |
| ----------- | ----------- | ------------- | ------------- | ----------- | ----------- | ----------- | ----------- | ------------- | ------------- |
| `\forall`   | $\forall$   | `\exists`     | $\exists$     | `\empty`    | $\empty$    | `\emptyset` | $\emptyset$ | `\varnothing` | $\varnothing$ |
| `\in`       | $\in$       | `\ni`         | $\ni$         | `\not\in`   | $\not\in$   | `\notin`    | $\notin$    | `\subset`     | $\subset$     |
| `\subseteq` | $\subseteq$ | `\supset`     | $\supset$     | `\supseteq` | $\supseteq$ | `\cap`      | $\cap$      | `\bigcap`     | $\bigcap$     |
| `\cup`      | $\cup$      | `\bigcup`     | $\bigcup$     | `\biguplus` | $\biguplus$ | `\sqsubset` | $\sqsubset$ | `\sqsubseteq` | $\sqsubseteq$ |
| `\sqsupset` | $\sqsupset$ | `\sqsupseteq` | $\sqsupseteq$ | `\sqcap`    | $\sqcap$    | `\sqcup`    | $\sqcup$    | `\bigsqcup`   | $\bigsqcup$   |

