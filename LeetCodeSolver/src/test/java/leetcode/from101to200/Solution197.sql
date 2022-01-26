# 197. 上升的温度 | 难度：简单 | 标签：数据库
# 表 Weather
#
# +---------------+---------+
# | Column Name   | Type    |
# +---------------+---------+
# | id            | int     |
# | recordDate    | date    |
# | temperature   | int     |
# +---------------+---------+
# id 是这个表的主键
# 该表包含特定日期的温度信息
#  
#
# 编写一个 SQL 查询，来查找与之前（昨天的）日期相比温度更高的所有日期的 id 。
#
# 返回结果 不要求顺序 。
#
# 查询结果格式如下例：
#
# Weather
# +----+------------+-------------+
# | id | recordDate | Temperature |
# +----+------------+-------------+
# | 1  | 2015-01-01 | 10          |
# | 2  | 2015-01-02 | 25          |
# | 3  | 2015-01-03 | 20          |
# | 4  | 2015-01-04 | 30          |
# +----+------------+-------------+
#
# Result table:
# +----+
# | id |
# +----+
# | 2  |
# | 4  |
# +----+
# 2015-01-02 的温度比前一天高（10 -> 25）
# 2015-01-04 的温度比前一天高（20 -> 30）
#
# 来源：力扣（LeetCode）
# 链接：https://leetcode-cn.com/problems/rising-temperature
# 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

# SQL架构
Create table If Not Exists Weather
(
    id          int,
    recordDate  date,
    temperature int
);
Truncate table Weather;
insert into Weather (id, recordDate, temperature)
values ('1', '2015-01-01', '10');
insert into Weather (id, recordDate, temperature)
values ('2', '2015-01-02', '25');
insert into Weather (id, recordDate, temperature)
values ('3', '2015-01-03', '20');
insert into Weather (id, recordDate, temperature)
values ('4', '2015-01-04', '30');

# Write your MySQL query statement below
# 执行用时： 493 ms , 在所有 MySQL 提交中击败了 42.67% 的用户
# 内存消耗： 0 B , 在所有 MySQL 提交中击败了 100.00% 的用户
# 通过测试用例： 14 / 14
#
# 参考官方题解，主要问题就是不知道这个 DATEDIFF 函数
SELECT w1.id AS 'Id'
FROM weather w1
         JOIN
     weather w2 ON DATEDIFF(w1.recordDate, w2.recordDate) = 1
         AND w1.Temperature > w2.Temperature