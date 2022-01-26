# 181. 超过经理收入的员工 | 难度：简单 | 标签：数据库
# Employee 表包含所有员工，他们的经理也属于员工。每个员工都有一个 Id，此外还有一列对应员工的经理的 Id。
#
# +----+-------+--------+-----------+
# | Id | Name  | Salary | ManagerId |
# +----+-------+--------+-----------+
# | 1  | Joe   | 70000  | 3         |
# | 2  | Henry | 80000  | 4         |
# | 3  | Sam   | 60000  | NULL      |
# | 4  | Max   | 90000  | NULL      |
# +----+-------+--------+-----------+
# 给定 Employee 表，编写一个 SQL 查询，该查询可以获取收入超过他们经理的员工的姓名。在上面的表格中，Joe 是唯一一个收入超过他的经理的员工。
#
# +----------+
# | Employee |
# +----------+
# | Joe      |
# +----------+
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/employees-earning-more-than-their-managers
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL架构
Create table If Not Exists Employee
(
    id        int,
    name      varchar(255),
    salary    int,
    managerId int
);
Truncate table Employee;
insert into Employee (id, name, salary, managerId)
values ('1', 'Joe', '70000', '3');
insert into Employee (id, name, salary, managerId)
values ('2', 'Henry', '80000', '4');
insert into Employee (id, name, salary, managerId)
values ('3', 'Sam', '60000', 'None');
insert into Employee (id, name, salary, managerId)
values ('4', 'Max', '90000', 'None');

# Write your MySQL query statement below
# 执行用时： 415 ms , 在所有 MySQL 提交中击败了 44.31% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 14 / 14
SELECT e1.name AS 'Employee'
FROM Employee AS e1,
     Employee AS e2
WHERE e1.managerId = e2.id
  AND e1.salary > e2.salary;