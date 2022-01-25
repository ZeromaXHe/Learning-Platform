# 176. 第二高的薪水 | 难度：中等 | 标签：数据库
# Employee 表：
# +-------------+------+
# | Column Name | Type |
# +-------------+------+
# | id          | int  |
# | salary      | int  |
# +-------------+------+
# id 是这个表的主键。
# 表的每一行包含员工的工资信息。
#  
#
# 编写一个 SQL 查询，获取并返回 Employee 表中第二高的薪水 。如果不存在第二高的薪水，查询应该返回 null 。
#
# 查询结果如下例所示。
#
#  
#
# 示例 1：
#
# 输入：
# Employee 表：
# +----+--------+
# | id | salary |
# +----+--------+
# | 1  | 100    |
# | 2  | 200    |
# | 3  | 300    |
# +----+--------+
# 输出：
# +---------------------+
# | SecondHighestSalary |
# +---------------------+
# | 200                 |
# +---------------------+
# 示例 2：
#
# 输入：
# Employee 表：
# +----+--------+
# | id | salary |
# +----+--------+
# | 1  | 100    |
# +----+--------+
# 输出：
# +---------------------+
# | SecondHighestSalary |
# +---------------------+
# | null                |
# +---------------------+
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/second-highest-salary
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL架构
Create table If Not Exists Employee
(
    id     int,
    salary int
);
Truncate table Employee;
insert into Employee (id, salary)
values ('1', '100');
insert into Employee (id, salary)
values ('2', '200');
insert into Employee (id, salary)
values ('3', '300');

# 测试用例：
# 通过测试用例：2 / 7
# {"headers":{"Employee":["id","salary"]},"rows":{"Employee":[[1,100]]}}
# 通过测试用例：6 / 7
# {"headers":{"Employee":["id","salary"]},"rows":{"Employee":[[1,100],[2,100]]}}

# 执行用时： 249 ms , 在所有 MySQL 提交中击败了 17.17% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 7 / 7
# Write your MySQL query statement below
SELECT (SELECT DISTINCT salary AS SecondHighestSalary
        FROM Employee
        ORDER BY salary DESC
        LIMIT 1,1) AS SecondHighestSalary;