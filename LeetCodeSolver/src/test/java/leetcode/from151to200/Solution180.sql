# 180. 连续出现的数字 | 难度：中等 | 标签：数据库
# 表：Logs
#
# +-------------+---------+
# | Column Name | Type    |
# +-------------+---------+
# | id          | int     |
# | num         | varchar |
# +-------------+---------+
# id 是这个表的主键。
#
# 编写一个 SQL 查询，查找所有至少连续出现三次的数字。
#
# 返回的结果表中的数据可以按 任意顺序 排列。
#
# 查询结果格式如下面的例子所示：
#
# Logs 表：
# +----+-----+
# | Id | Num |
# +----+-----+
# | 1  | 1   |
# | 2  | 1   |
# | 3  | 1   |
# | 4  | 2   |
# | 5  | 1   |
# | 6  | 2   |
# | 7  | 2   |
# +----+-----+
#
# Result 表：
# +-----------------+
# | ConsecutiveNums |
# +-----------------+
# | 1               |
# +-----------------+
# 1 是唯一连续出现至少三次的数字。
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/consecutive-numbers
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL架构
Create table If Not Exists Logs
(
    id  int,
    num int
);
Truncate table Logs;
insert into Logs (id, num)
values ('1', '1');
insert into Logs (id, num)
values ('2', '1');
insert into Logs (id, num)
values ('3', '1');
insert into Logs (id, num)
values ('4', '2');
insert into Logs (id, num)
values ('5', '1');
insert into Logs (id, num)
values ('6', '2');
insert into Logs (id, num)
values ('7', '2');

# Write your MySQL query statement below
# 执行用时： 557 ms , 在所有 MySQL 提交中击败了 37.47% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 21 / 21
#
# 官方题解
SELECT DISTINCT l1.Num AS ConsecutiveNums
FROM Logs l1,
     Logs l2,
     Logs l3
WHERE l1.Id = l2.Id - 1
  AND l2.Id = l3.Id - 1
  AND l1.Num = l2.Num
  AND l2.Num = l3.Num;
