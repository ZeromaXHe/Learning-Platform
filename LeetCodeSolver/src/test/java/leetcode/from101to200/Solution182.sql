# 182. 查找重复的电子邮箱 | 难度：简单 | 标签：数据库
# 编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
#
# 示例：
#
# +----+---------+
# | Id | Email   |
# +----+---------+
# | 1  | a@b.com |
# | 2  | c@d.com |
# | 3  | a@b.com |
# +----+---------+
# 根据以上输入，你的查询应返回以下结果：
#
# +---------+
# | Email   |
# +---------+
# | a@b.com |
# +---------+
# 说明：所有电子邮箱都是小写字母。
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/duplicate-emails
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL架构
Create table If Not Exists Person
(
    id    int,
    email varchar(255)
);
Truncate table Person;
insert into Person (id, email)
values ('1', 'a@b.com');
insert into Person (id, email)
values ('2', 'c@d.com');
insert into Person (id, email)
values ('3', 'a@b.com');

# Write your MySQL query statement below
# 执行用时： 397 ms , 在所有 MySQL 提交中击败了 54.22% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 15 / 15
#
# 用 HAVING 会更简单一些，直接:
# select Email
# from Person
# group by Email
# having count(Email) > 1;
SELECT email
FROM (SELECT email, count(*) AS c FROM Person GROUP BY email) AS a
WHERE c > 1;