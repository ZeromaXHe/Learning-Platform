# 185. 部门工资前三高的所有员工 | 难度：困难 | 标签：数据库
# Employee 表包含所有员工信息，每个员工有其对应的工号 Id，姓名 Name，工资 Salary 和部门编号 DepartmentId 。
#
# +----+-------+--------+--------------+
# | Id | Name  | Salary | DepartmentId |
# +----+-------+--------+--------------+
# | 1  | Joe   | 85000  | 1            |
# | 2  | Henry | 80000  | 2            |
# | 3  | Sam   | 60000  | 2            |
# | 4  | Max   | 90000  | 1            |
# | 5  | Janet | 69000  | 1            |
# | 6  | Randy | 85000  | 1            |
# | 7  | Will  | 70000  | 1            |
# +----+-------+--------+--------------+
# Department 表包含公司所有部门的信息。
#
# +----+----------+
# | Id | Name     |
# +----+----------+
# | 1  | IT       |
# | 2  | Sales    |
# +----+----------+
# 编写一个 SQL 查询，找出每个部门获得前三高工资的所有员工。例如，根据上述给定的表，查询结果应返回：
#
# +------------+----------+--------+
# | Department | Employee | Salary |
# +------------+----------+--------+
# | IT         | Max      | 90000  |
# | IT         | Randy    | 85000  |
# | IT         | Joe      | 85000  |
# | IT         | Will     | 70000  |
# | Sales      | Henry    | 80000  |
# | Sales      | Sam      | 60000  |
# +------------+----------+--------+
# 解释：
#
# IT 部门中，Max 获得了最高的工资，Randy 和 Joe 都拿到了第二高的工资，Will 的工资排第三。销售部门（Sales）只有两名员工，Henry 的工资最高，Sam 的工资排第二。
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/department-top-three-salaries
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
values ('1', 'Joe', '85000', '1');
insert into Employee (id, name, salary, departmentId)
values ('2', 'Henry', '80000', '2');
insert into Employee (id, name, salary, departmentId)
values ('3', 'Sam', '60000', '2');
insert into Employee (id, name, salary, departmentId)
values ('4', 'Max', '90000', '1');
insert into Employee (id, name, salary, departmentId)
values ('5', 'Janet', '69000', '1');
insert into Employee (id, name, salary, departmentId)
values ('6', 'Randy', '85000', '1');
insert into Employee (id, name, salary, departmentId)
values ('7', 'Will', '70000', '1');
Truncate table Department;
insert into Department (id, name)
values ('1', 'IT');
insert into Department (id, name)
values ('2', 'Sales');

# Write your MySQL query statement below
# 执行用时： 915 ms , 在所有 MySQL 提交中击败了 72.88% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 19 / 19
SELECT sub.name AS 'department', sub.employee, sub.salary
FROM (SELECT d.name AS 'name', e1.name AS 'employee', e1.salary AS 'salary', count(DISTINCT e2.salary) AS 'c'
      FROM Employee AS e1
               LEFT JOIN Employee AS e2 ON e2.departmentId = e1.departmentId AND e2.salary > e1.salary
               JOIN Department AS d ON e1.departmentId = d.id
      GROUP BY e1.id
     ) AS sub
WHERE sub.c < 3