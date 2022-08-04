# 1407. 排名靠前的旅行者 | 难度：简单 | 标签：数据库
# 表：Users
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | id            | int     |
# | name          | varchar |
# +---------------+---------+
# id 是该表单主键。
# name 是用户名字。
#
# 表：Rides
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | id            | int     |
# | user_id       | int     |
# | distance      | int     |
# +---------------+---------+
# id 是该表单主键。
# user_id 是本次行程的用户的 id, 而该用户此次行程距离为 distance 。
#
# 写一段 SQL , 报告每个用户的旅行距离。
#
# 返回的结果表单，以 travelled_distance 降序排列 ，如果有两个或者更多的用户旅行了相同的距离, 那么再以 name 升序排列 。
#
# 查询结果格式如下例所示。
#
# Users 表：
# +------+-----------+
# | id   | name      |
# +------+-----------+
# | 1    | Alice     |
# | 2    | Bob       |
# | 3    | Alex      |
# | 4    | Donald    |
# | 7    | Lee       |
# | 13   | Jonathan  |
# | 19   | Elvis     |
# +------+-----------+
#
# Rides 表：
# +------+----------+----------+
# | id   | user_id  | distance |
# +------+----------+----------+
# | 1    | 1        | 120      |
# | 2    | 2        | 317      |
# | 3    | 3        | 222      |
# | 4    | 7        | 100      |
# | 5    | 13       | 312      |
# | 6    | 19       | 50       |
# | 7    | 7        | 120      |
# | 8    | 19       | 400      |
# | 9    | 7        | 230      |
# +------+----------+----------+
#
# Result 表：
# +----------+--------------------+
# | name     | travelled_distance |
# +----------+--------------------+
# | Elvis    | 450                |
# | Lee      | 450                |
# | Bob      | 317                |
# | Jonathan | 312                |
# | Alex     | 222                |
# | Alice    | 120                |
# | Donald   | 0                  |
# +----------+--------------------+
# Elvis 和 Lee 旅行了 450 英里，Elvis 是排名靠前的旅行者，因为他的名字在字母表上的排序比 Lee 更小。
# Bob, Jonathan, Alex 和 Alice 只有一次行程，我们只按此次行程的全部距离对他们排序。
# Donald 没有任何行程, 他的旅行距离为 0。
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode.cn/problems/top-travellers
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL 架构
Create Table If Not Exists Users
(
    id   int,
    name varchar(30)
);
Create Table If Not Exists Rides
(
    id       int,
    user_id  int,
    distance int
);
Truncate table Users;
insert into Users (id, name)
values ('1', 'Alice');
insert into Users (id, name)
values ('2', 'Bob');
insert into Users (id, name)
values ('3', 'Alex');
insert into Users (id, name)
values ('4', 'Donald');
insert into Users (id, name)
values ('7', 'Lee');
insert into Users (id, name)
values ('13', 'Jonathan');
insert into Users (id, name)
values ('19', 'Elvis');
Truncate table Rides;
insert into Rides (id, user_id, distance)
values ('1', '1', '120');
insert into Rides (id, user_id, distance)
values ('2', '2', '317');
insert into Rides (id, user_id, distance)
values ('3', '3', '222');
insert into Rides (id, user_id, distance)
values ('4', '7', '100');
insert into Rides (id, user_id, distance)
values ('5', '13', '312');
insert into Rides (id, user_id, distance)
values ('6', '19', '50');
insert into Rides (id, user_id, distance)
values ('7', '7', '120');
insert into Rides (id, user_id, distance)
values ('8', '19', '400');
insert into Rides (id, user_id, distance)
values ('9', '7', '230');

# 执行用时：879 ms, 在所有 MySQL 提交中击败了 48.16% 的用户
# 内存消耗：0 B, 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例：18 / 18
# Write your MySQL query statement below
select u.name as 'name', ifnull(r.distance, 0) as 'travelled_distance'
from Users as u
         left join (select user_id, sum(distance) as 'distance' from Rides group by user_id) as r
                   on u.id = r.user_id
order by r.distance desc, u.name asc
