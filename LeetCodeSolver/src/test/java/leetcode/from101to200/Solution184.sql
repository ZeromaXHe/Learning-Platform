# 184. 部门工资最高的员工 | 难度：中等 | 标签：数据库
# Employee 表包含所有员工信息，每个员工有其对应的 Id, salary 和 department Id。
#
# +----+-------+--------+--------------+
# | Id | Name  | Salary | DepartmentId |
# +----+-------+--------+--------------+
# | 1  | Joe   | 70000  | 1            |
# | 2  | Jim   | 90000  | 1            |
# | 3  | Henry | 80000  | 2            |
# | 4  | Sam   | 60000  | 2            |
# | 5  | Max   | 90000  | 1            |
# +----+-------+--------+--------------+
# Department 表包含公司所有部门的信息。
#
# +----+----------+
# | Id | Name     |
# +----+----------+
# | 1  | IT       |
# | 2  | Sales    |
# +----+----------+
# 编写一个 SQL 查询，找出每个部门工资最高的员工。对于上述表，您的 SQL 查询应返回以下行（行的顺序无关紧要）。
#
# +------------+----------+--------+
# | Department | Employee | Salary |
# +------------+----------+--------+
# | IT         | Max      | 90000  |
# | IT         | Jim      | 90000  |
# | Sales      | Henry    | 80000  |
# +------------+----------+--------+
# 解释：
#
# Max 和 Jim 在 IT 部门的工资都是最高的，Henry 在销售部的工资最高。
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/department-highest-salary
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL架构
Create table If Not Exists Employee
(
    id           int,
    name         varchar(255),
    salary       int,
    departmentId int
);
Create table If Not Exists Department
(
    id   int,
    name varchar(255)
);
Truncate table Employee;
insert into Employee (id, name, salary, departmentId)
values ('1', 'Joe', '70000', '1');
insert into Employee (id, name, salary, departmentId)
values ('2', 'Jim', '90000', '1');
insert into Employee (id, name, salary, departmentId)
values ('3', 'Henry', '80000', '2');
insert into Employee (id, name, salary, departmentId)
values ('4', 'Sam', '60000', '2');
insert into Employee (id, name, salary, departmentId)
values ('5', 'Max', '90000', '1');
Truncate table Department;
insert into Department (id, name)
values ('1', 'IT');
insert into Department (id, name)
values ('2', 'Sales');

# Write your MySQL query statement below
# 执行用时： 815 ms , 在所有 MySQL 提交中击败了 12.04% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 14 / 14
SELECT sub.name AS 'department', e1.name AS 'employee', e1.salary AS 'salary'
FROM Employee AS e1,
     (SELECT d.id AS 'id', d.name AS 'name', max(e2.salary) AS 'max_salary'
      FROM Employee AS e2,
           Department AS d
      WHERE e2.departmentId = d.id
      GROUP BY d.id
     ) AS sub
WHERE e1.departmentId = sub.id
  AND e1.salary = sub.max_salary