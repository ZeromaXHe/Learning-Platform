# 177. 第N高的薪水 | 难度：中等 | 标签：数据库
# 编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
#
# +----+--------+
# | Id | Salary |
# +----+--------+
# | 1  | 100    |
# | 2  | 200    |
# | 3  | 300    |
# +----+--------+
# 例如上述 Employee 表，n = 2 时，应返回第二高的薪水 200。如果不存在第 n 高的薪水，那么查询应返回 null。
#
# +------------------------+
# | getNthHighestSalary(2) |
# +------------------------+
# | 200                    |
# +------------------------+
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/nth-highest-salary
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL架构
Create table If Not Exists Employee
(
    Id     int,
    Salary int
);
Truncate table Employee;
insert into Employee (id, salary)
values ('1', '100');
insert into Employee (id, salary)
values ('2', '200');
insert into Employee (id, salary)
values ('3', '300');

# 执行用时： 412 ms , 在所有 MySQL 提交中击败了 24.96% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 14 / 14
#
# 根本不会写SQL的函数，参考别人题解做的
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N := N - 1;
    RETURN (
        # Write your MySQL query statement below.
        SELECT (SELECT DISTINCT salary AS SecondHighestSalary
                FROM Employee
                ORDER BY salary DESC
                LIMIT N,1) AS SecondHighestSalary
    );
END