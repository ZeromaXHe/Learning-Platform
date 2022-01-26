# 196. 删除重复的电子邮箱 | 难度：简单 | 标签：数据库
# 编写一个 SQL 查询，来删除 Person 表中所有重复的电子邮箱，重复的邮箱里只保留 Id 最小 的那个。
#
# +----+------------------+
# | Id | Email            |
# +----+------------------+
# | 1  | john@example.com |
# | 2  | bob@example.com  |
# | 3  | john@example.com |
# +----+------------------+
# Id 是这个表的主键。
# 例如，在运行你的查询语句之后，上面的 Person 表应返回以下几行:
#
# +----+------------------+
# | Id | Email            |
# +----+------------------+
# | 1  | john@example.com |
# | 2  | bob@example.com  |
# +----+------------------+
#  
#
# 提示：
#
# 执行 SQL 之后，输出是整个 Person 表。
# 使用 delete 语句。
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/delete-duplicate-emails
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL架构
Create table If Not Exists Person
(
    Id    int,
    Email varchar(255)
);
Truncate table Person;
insert into Person (id, email)
values ('1', 'john@example.com');
insert into Person (id, email)
values ('2', 'bob@example.com');
insert into Person (id, email)
values ('3', 'john@example.com');

# Write your MySQL query statement below
# 执行用时： 643 ms , 在所有 MySQL 提交中击败了 79.46% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 22 / 22
#
# 之所以子查询SELECT两次，是为了避免”You can't specify target table 'Person' for update in FROM clause“错误
DELETE
FROM Person
WHERE id IN (SELECT DISTINCT sub.id
             FROM (SELECT p1.id
                   FROM Person AS p1,
                        Person AS p2
                   WHERE p1.Email = p2.Email
                     AND p1.id > p2.Id) AS sub)