# 基础：万丈高楼平地起——Redis基础数据结构

## Redis基础数据结构

Redis 有 5 种基础数据结构，分别为：string (字符串)、list (列表)、set (集合)、hash (哈希) 和 zset (有序集合)。熟练掌握这 5 种基本数据结构的使用是 Redis 知识最基础也最重要的部分，它也是在 Redis 面试题中问到最多的内容。

### String（字符串）

字符串 string 是 Redis 最简单的数据结构。Redis 所有的数据结构都是以唯一的 key 字符串作为名称，然后通过这个唯一 key 值来获取相应的 value 数据。不同类型的数据结构的差异就在于 value 的结构不一样。

字符串结构使用非常广泛，一个常见的用途就是缓存用户信息。我们将用户信息结构体使用 JSON 序列化成字符串，然后将序列化后的字符串塞进 Redis 来缓存。同样，取用户信息会经过一次反序列化的过程。

Redis 的字符串是动态字符串，是可以修改的字符串，内部结构实现上类似于 Java 的ArrayList，采用预分配冗余空间的方式来减少内存的频繁分配，如图中所示，内部为当前字符串实际分配的空间 capacity 一般要高于实际字符串长度 len。当字符串长度小于 1M 时，扩容都是加倍现有的空间，如果超过 1M，扩容时一次只会多扩 1M 的空间。需要注意的是字符串最大长度为 512M。

#### 键值对

~~~shell
> set name codehole
OK
> get name
"codehole"
> exists name
(integer) 1
> del name
(integer) 1
> get name
(nil)
~~~

#### 批量键值对

可以批量对多个字符串进行读写，节省网络耗时开销。

~~~shell
> set name1 codehole
OK
> set name2 holycoder
OK
> mget name1 name2 name3 # 返回一个列表
1) "codehole"
2) "holycoder"
3) (nil)
> mset name1 boy name2 girl name3 unknown
> mget name1 name2 name3
1) "boy"
2) "girl"
3) "unknown"
~~~

#### 过期和set命令扩展

可以对key设置过期时间，到点自动删除，这个功能常用来控制缓存的失效时间。不过这个「自动删除」的机制是比较复杂的，如果你感兴趣，可以继续深入阅读此书第 26 节《朝生暮死——过期策略》

~~~shell
> set name codehole 
> get name 
"codehole" 
> expire name 5 # 5s 后过期
... # wait for 5s 
> get name 
(nil) 
> setex name 5 codehole # 5s 后过期，等价于 set+expire
> get name 
"codehole" 
... # wait for 5s 
> get name 
(nil) 
> setnx name codehole # 如果 name 不存在就执行 set 创建
(integer) 1 
> get name 
"codehole" 
> setnx name holycoder 
(integer) 0 # 因为 name 已经存在，所以 set 创建不成功
> get name 
"codehole"
# 没有改变
~~~

#### 计数

如果 value 值是一个整数，还可以对它进行自增操作。自增是有范围的，它的范围是 signed long 的最大最小值，超过了这个值，Redis 会报错。

~~~shell
> set age 30 
OK 
> incr age 
(integer) 31 
> incrby age 5 
(integer) 36 
> incrby age -5 
(integer) 31 
> set codehole 9223372036854775807 
# Long.Max 
OK 
> incr codehole
(error) ERR increment or decrement would overflow
~~~

字符串是由多个字节组成，每个字节又是由 8 个 bit 组成，如此便可以将一个字符串看成很多 bit 的组合，这便是 bitmap「位图」数据结构，位图的具体使用会放到后面的章节来讲。

关于字符串的内部结构实现，请阅读此书第 32 节《极度深寒——探索「字符串」内部》

### list（列表）

Redis 的列表相当于 Java 语言里面的 LinkedList，注意它是链表而不是数组。这意味着 list 的插入和删除操作非常快，时间复杂度为 O(1)，但是索引定位很慢，时间复杂度为 O(n)，这点让人非常意外。

当列表弹出了最后一个元素之后，该数据结构自动被删除，内存被回收。

Redis 的列表结构常用来做异步队列使用。将需要延后处理的任务结构体序列化成字符串塞进 Redis 的列表，另一个线程从这个列表中轮询数据进行处理。

#### 右边进左边出：队列 

~~~shell
> rpush books python java golang 
(integer) 3 
> llen books 
(integer) 3 
> lpop books 
"python" 
> lpop books 
"java" 
> lpop books 
"golang" 
> lpop books 
(nil)
~~~

#### 右边进右边出：栈 

~~~shell
> rpush books python java golang 
(integer) 3 
> rpop books 
"golang" 
> rpop books 
"java" 
> rpop books 
"python" 
> rpop books
(nil)
~~~

#### 慢操作

lindex 相当于 Java 链表的 `get(int index)`方法，它需要对链表进行遍历，性能随着参数 `index` 增大而变差。 ltrim 和字面上的含义不太一样，个人觉得它叫 lretain(保留) 更合适一些，因为 ltrim 跟的两个参数 `start_index` 和 `end_index` 定义了一个区间，在这个区间内的值，ltrim 要保留，区间之外统统砍掉。我们可以通过 ltrim 来实现一个定长的链表，这一点非常有用。index 可以为负数，`index=-1` 表示倒数第一个元素，同样 `index=-2` 表示倒数第二个元素

~~~shell
> rpush books python java golang 
(integer) 3 
> lindex books 1 # O(n) 慎用
"java" 
> lrange books 0 -1 # 获取所有元素，O(n) 慎用
1) "python" 
2) "java" 
3) "golang" 
> ltrim books 1 -1 # O(n) 慎用
OK 
> lrange books 0 -1 
1) "java" 
2) "golang" 
> ltrim books 1 0 # 这其实是清空了整个列表，因为区间范围长度为负
OK 
> llen books 
(integer) 0
~~~

#### 快速列表

如果再深入一点，你会发现 Redis 底层存储的还不是一个简单的 `linkedlist`，而是称之为快速链表 `quicklist` 的一个结构。

首先在列表元素较少的情况下会使用一块连续的内存存储，这个结构是 `ziplist`，也即是压缩列表。它将所有的元素紧挨着一起存储，分配的是一块连续的内存。当数据量比较多的时候才会改成 `quicklist`。因为普通的链表需要的附加指针空间太大，会比较浪费空间，而且会加重内存的碎片化。比如这个列表里存的只是 `int` 类型的数据，结构上还需要两个额外的指针 `prev` 和 `next` 。所以 Redis 将链表和 `ziplist` 结合起来组成了 `quicklist`。也就是将多个 `ziplist` 使用双向指针串起来使用。这样既满足了快速的插入删除性能，又不会出现太大的空间冗余。

关于列表的内部结构实现，请阅读此书第 34 节《极度深寒 —— 探索「压缩列表」内部》和第 35 节《极度深寒 —— 探索「快速列表」内部》

### hash（字典）

Redis 的字典相当于 Java 语言里面的 HashMap，它是无序字典。内部实现结构上同Java 的 HashMap 也是一致的，同样的数组 + 链表二维结构。第一维 hash 的数组位置碰撞时，就会将碰撞的元素使用链表串接起来。

不同的是，Redis 的字典的值只能是字符串，另外它们 rehash 的方式不一样，因为Java 的 HashMap 在字典很大时，rehash 是个耗时的操作，需要一次性全部 rehash。Redis 为了高性能，不能堵塞服务，所以采用了渐进式 rehash 策略。

渐进式 rehash 会在 rehash 的同时，保留新旧两个 hash 结构，查询时会同时查询两个hash 结构，然后在后续的定时任务中以及 hash 的子指令中，循序渐进地将旧 hash 的内容一点点迁移到新的 hash 结构中。

当 hash 移除了最后一个元素之后，该数据结构自动被删除，内存被回收。

hash 结构也可以用来存储用户信息，不同于字符串一次性需要全部序列化整个对象，hash 可以对用户结构中的每个字段单独存储。这样当我们需要获取用户信息时可以进行部分获取。而以整个字符串的形式去保存用户信息的话就只能一次性全部读取，这样就会比较浪费网络流量。

hash 也有缺点，hash 结构的存储消耗要高于单个字符串，到底该使用 hash 还是字符串，需要根据实际情况再三权衡。

~~~shell
> hset books java "think in java" # 命令行的字符串如果包含空格，要用引号括起来
(integer) 1 
> hset books golang "concurrency in go" 
(integer) 1 
> hset books python "python cookbook" 
(integer) 1 
> hgetall books # entries()，key 和 value 间隔出现
1) "java" 
2) "think in java" 
3) "golang" 
4) "concurrency in go" 
5) "python" 
6) "python cookbook" 
> hlen books 
(integer) 3 
> hget books java 
"think in java" 
> hset books golang "learning go programming" # 因为是更新操作，所以返回 0
(integer) 0 
> hget books golang 
"learning go programming" 
> hmset books java "effective java" python "learning python" golang "modern golang programming" # 批量 set 
OK
~~~

同字符串一样，hash 结构中的单个子 key 也可以进行计数，它对应的指令是 hincrby， 和 incr 使用基本一样。

~~~shell
# 老钱又老了一岁
> hincrby user-laoqian age 1 
(integer) 30
~~~

关于字典的内部结构实现，请阅读此书第 33 节《极度深寒——探索「字典」内部》。

### set（集合）

Redis 的集合相当于 Java 语言里面的 HashSet，它内部的键值对是无序的唯一的。它的内部实现相当于一个特殊的字典，字典中所有的 value 都是一个值 NULL。

当集合中最后一个元素移除之后，数据结构自动删除，内存被回收。 set 结构可以用来存储活动中奖的用户 ID，因为有去重功能，可以保证同一个用户不会中奖两次。

~~~shell
> sadd books python 
(integer) 1 
> sadd books python # 重复
(integer) 0 
> sadd books java golang 
(integer) 2 
> smembers books # 注意顺序，和插入的并不一致，因为 set 是无序的
1) "java" 
2) "python" 
3) "golang" 
> sismember books java # 查询某个 value 是否存在，相当于 contains(o)
(integer) 1 
> sismember books rust 
(integer) 0 
> scard books # 获取长度相当于 count()
(integer) 3 
> spop books # 弹出一个
"java"
~~~

### zset（有序列表）

zset 可能是 Redis 提供的最为特色的数据结构，它也是在面试中面试官最爱问的数据结构。它类似于 Java 的 SortedSet 和 HashMap 的结合体，一方面它是一个 set，保证了内部value 的唯一性，另一方面它可以给每个 value 赋予一个 score，代表这个 value 的排序权重。它的内部实现用的是一种叫着「跳跃列表」的数据结构。

zset 中最后一个 value 被移除后，数据结构自动删除，内存被回收。 zset 可以用来存粉丝列表，value 值是粉丝的用户 ID，score 是关注时间。我们可以对粉丝列表按关注时间进行排序。

zset 还可以用来存储学生的成绩，value 值是学生的 ID，score 是他的考试成绩。我们可以对成绩按分数进行排序就可以得到他的名次。

~~~shell
> zadd books 9.0 "think in java" 
(integer) 1 
> zadd books 8.9 "java concurrency" 
(integer) 1 
> zadd books 8.6 "java cookbook" 
(integer) 1 
> zrange books 0 -1 # 按 score 排序列出，参数区间为排名范围
1) "java cookbook" 
2) "java concurrency" 
3) "think in java" 
> zrevrange books 0 -1 # 按 score 逆序列出，参数区间为排名范围
1) "think in java" 
2) "java concurrency" 
3) "java cookbook" 
> zcard books # 相当于 count()
(integer) 3 
> zscore books "java concurrency" # 获取指定 value 的 score
"8.9000000000000004" # 内部 score 使用 double 类型进行存储，所以存在小数点精度问题
> zrank books "java concurrency" # 排名
(integer) 1 
> zrangebyscore books 0 8.91 # 根据分值区间遍历 zset
1) "java cookbook" 
2) "java concurrency" 
> zrangebyscore books -inf 8.91 withscores # 根据分值区间 (-∞, 8.91] 遍历 zset，同时返
回分值。inf 代表 infinite，无穷大的意思。
1) "java cookbook" 
2) "8.5999999999999996" 
3) "java concurrency" 
4) "8.9000000000000004" 
> zrem books "java concurrency" # 删除 value
(integer) 1 
> zrange books 0 -1 
1) "java cookbook" 
2) "think in java"
~~~

#### 跳跃列表

zset 内部的排序功能是通过「跳跃列表」数据结构来实现的，它的结构非常特殊，也比较复杂。

因为 zset 要支持随机的插入和删除，所以它不好使用数组来表示。我们先看一个普通的链表结构。

我们需要这个链表按照 score 值进行排序。这意味着当有新元素需要插入时，要定位到特定位置的插入点，这样才可以继续保证链表是有序的。通常我们会通过二分查找来找到插入点，但是二分查找的对象必须是数组，只有数组才可以支持快速位置定位，链表做不到，那该怎么办？

想想一个创业公司，刚开始只有几个人，团队成员之间人人平等，都是联合创始人。随着公司的成长，人数渐渐变多，团队沟通成本随之增加。这时候就会引入组长制，对团队进行划分。每个团队会有一个组长。开会的时候分团队进行，多个组长之间还会有自己的会议安排。公司规模进一步扩展，需要再增加一个层级 —— 部门，每个部门会从组长列表中推选出一个代表来作为部长。部长们之间还会有自己的高层会议安排。

跳跃列表就是类似于这种层级制，最下面一层所有的元素都会串起来。然后每隔几个元素挑选出一个代表来，再将这几个代表使用另外一级指针串起来。然后在这些代表里再挑出二级代表，再串起来。最终就形成了金字塔结构。 想想你老家在世界地图中的位置：亚洲- ->中国->安徽省->安庆市->枞阳县->汤沟镇->田间村->xxxx 号，也是这样一个类似的结构。

「跳跃列表」之所以「跳跃」，是因为内部的元素可能「身兼数职」，比如上图中间的这个元素，同时处于 L0、L1 和 L2 层，可以快速在不同层次之间进行「跳跃」。

定位插入点时，先在顶层进行定位，然后下潜到下一级定位，一直下潜到最底层找到合适的位置，将新元素插进去。你也许会问，那新插入的元素如何才有机会「身兼数职」呢？

跳跃列表采取一个随机策略来决定新元素可以兼职到第几层。

首先 L0 层肯定是 100% 了，L1 层只有 50% 的概率，L2 层只有 25% 的概率，L3 层只有 12.5% 的概率，一直随机到最顶层 L31 层。绝大多数元素都过不了几层，只有极少数元素可以深入到顶层。列表中的元素越多，能够深入的层次就越深，能进入到顶层的概率就会越大。

这还挺公平的，能不能进入中央不是靠拼爹，而是看运气。 关于跳跃列表的内部结构实现，请阅读此书第 36 节《极度深寒 —— 探索「跳跃列表」内部结构》

## 容器型数据结构的通用规则

list/set/hash/zset 这四种数据结构是容器型数据结构，它们共享下面两条通用规则：

**1、create if not exists** 

如果容器不存在，那就创建一个，再进行操作。比如 rpush 操作刚开始是没有列表的，Redis 就会自动创建一个，然后再 rpush 进去新元素。

**2、drop if no elements** 

如果容器里元素没有了，那么立即删除元素，释放内存。这意味着 lpop 操作到最后一个元素，列表就消失了。

### 过期时间 

Redis 所有的数据结构都可以设置过期时间，时间到了，Redis 会自动删除相应的对象。需要注意的是过期是以对象为单位，比如一个 hash 结构的过期是整个 hash 对象的过期，而不是其中的某个子 key。

还有一个需要特别注意的地方是如果一个字符串已经设置了过期时间，然后你调用了set 方法修改了它，它的过期时间会消失。

~~~shell
127.0.0.1:6379> set codehole yoyo 
OK 
127.0.0.1:6379> expire codehole 600 
(integer) 1 
127.0.0.1:6379> ttl codehole 
(integer) 597 
127.0.0.1:6379> set codehole yoyo 
OK 
127.0.0.1:6379> ttl codehole 
(integer) -1
~~~

# 应用1：千帆竞发——分布式锁

分布式应用进行逻辑处理时经常会遇到并发问题。

比如一个操作要修改用户的状态，修改状态需要先读出用户的状态，在内存里进行修改，改完了再存回去。如果这样的操作同时进行了，就会出现并发问题，因为读取和保存状态这两个操作不是原子的。（Wiki 解释：所谓**原子操作**是指不会被线程调度机制打断的操作；这种操作一旦开始，就一直运行到结束，中间不会有任何 context switch 线程切换。）

这个时候就要使用到分布式锁来限制程序的并发执行。Redis 分布式锁使用非常广泛，它是面试的重要考点之一，很多同学都知道这个知识，也大致知道分布式锁的原理，但是具体到细节的使用上往往并不完全正确。

## 分布式锁

分布式锁本质上要实现的目标就是在 Redis 里面占一个“茅坑”，当别的进程也要来占时，发现已经有人蹲在那里了，就只好放弃或者稍后再试。

占坑一般是使用 setnx(set if not exists) 指令，只允许被一个客户端占坑。先来先占， 用完了，再调用 del 指令释放茅坑。

~~~shell
# 这里的冒号:就是一个普通的字符，没特别含义，它可以是任意其它字符，不要误解
> setnx lock:codehole true
OK
... do something critical ...
> del lock:codehole
(integer) 1
~~~

但是有个问题，如果逻辑执行到中间出现异常了，可能会导致 del 指令没有被调用，这样就会陷入死锁，锁永远得不到释放。

于是我们在拿到锁之后，再给锁加上一个过期时间，比如 5s，这样即使中间出现异常也可以保证 5 秒之后锁会自动释放。

~~~shell
> setnx lock:codehole true
OK
> expire lock:codehole 5
... do something critical ...
> del lock:codehole
(integer) 1
~~~

但是以上逻辑还有问题。如果在 setnx 和 expire 之间服务器进程突然挂掉了，可能是因为机器掉电或者是被人为杀掉的，就会导致 expire 得不到执行，也会造成死锁。

这种问题的根源就在于 setnx 和 expire 是两条指令而不是原子指令。如果这两条指令可以一起执行就不会出现问题。也许你会想到用 Redis 事务来解决。但是这里不行，因为 expire 是依赖于 setnx 的执行结果的，如果 setnx 没抢到锁，expire 是不应该执行的。事务里没有 if-else 分支逻辑，事务的特点是一口气执行，要么全部执行要么一个都不执行。

为了解决这个疑难，Redis 开源社区涌现了一堆分布式锁的 library，专门用来解决这个问题。实现方法极为复杂，小白用户一般要费很大的精力才可以搞懂。如果你需要使用分布式锁，意味着你不能仅仅使用 Jedis 或者 redis-py 就行了，还得引入分布式锁的 library。

为了治理这个乱象，Redis 2.8 版本中作者加入了 set 指令的扩展参数，使得 setnx 和 expire 指令可以一起执行，彻底解决了分布式锁的乱象。从此以后所有的第三方分布式锁 library 可以休息了。 

~~~shell
> set lock:codehole true ex 5 nx 
OK 
... do something critical ... 
> del lock:codehole
~~~

上面这个指令就是 setnx 和 expire 组合在一起的原子指令，它就是分布式锁的奥义所在。

## 超时问题

Redis 的分布式锁不能解决超时问题，如果在加锁和释放锁之间的逻辑执行的太长，以至于超出了锁的超时限制，就会出现问题。因为这时候锁过期了，第二个线程重新持有了这把锁，但是紧接着第一个线程执行完了业务逻辑，就把锁给释放了，第三个线程就会在第二个线程逻辑执行完之间拿到了锁。

为了避免这个问题，Redis 分布式锁不要用于较长时间的任务。如果真的偶尔出现了，数据出现的小波错乱可能需要人工介入解决。

~~~python
tag = random.nextint() # 随机数
if redis.set(key, tag, nx=True, ex=5):
    do_something()
    redis.delifequals(key, tag) # 假象的 delifequals 指令
~~~

有一个更加安全的方案是为 set 指令的 value 参数设置为一个随机数，释放锁时先匹配随机数是否一致，然后再删除 key。但是匹配 value 和删除 key 不是一个原子操作，Redis 也没有提供类似于 delifequals 这样的指令，这就需要使用 Lua 脚本来处理了，因为 Lua 脚本可以保证连续多个指令的原子性执行。

~~~lua
# delifequals
if redis.call("get",KEYS[1]) == ARGV[1] then
    return redis.call("del",KEYS[1])
else
    return 0
end
~~~

## 可重入性

可重入性是指线程在持有锁的情况下再次请求加锁，如果一个锁支持同一个线程的多次加锁，那么这个锁就是可重入的。比如 Java 语言里有个 ReentrantLock 就是可重入锁。Redis 分布式锁如果要支持可重入，需要对客户端的 set 方法进行包装，使用线程的 Threadlocal 变量存储当前持有锁的计数。

~~~python
# -*- coding: utf-8 
import redis 
import threading 
locks = threading.local() 
locks.redis = {} 
def key_for(user_id): 
    return "account_{}".format(user_id) 

def _lock(client, key): 
    return bool(client.set(key, True, nx=True, ex=5)) 

def _unlock(client, key): 
    client.delete(key) 

def lock(client, user_id): 
    key = key_for(user_id) 
    if key in locks.redis:
        locks.redis[key] += 1 
        return True 
    ok = _lock(client, key) 
    if not ok: 
        return False 
    locks.redis[key] = 1 
    return True 

def unlock(client, user_id): 
    key = key_for(user_id) 
    if key in locks.redis: 
        locks.redis[key] -= 1
        if locks.redis[key] <= 0: 
            del locks.redis[key] 
        return True 
    return False 
        
client = redis.StrictRedis() 
print "lock", lock(client, "codehole") 
print "lock", lock(client, "codehole") 
print "unlock", unlock(client, "codehole") 
print "unlock", unlock(client, "codehole")
~~~

以上还不是可重入锁的全部，精确一点还需要考虑内存锁计数的过期时间，代码复杂度将会继续升高。老钱不推荐使用可重入锁，它加重了客户端的复杂性，在编写业务方法时注意在逻辑结构上进行调整完全可以不使用可重入锁。下面是 Java 版本的可重入锁。

~~~java
public class RedisWithReentrantLock { 
    private ThreadLocal<Map> lockers = new ThreadLocal<>(); 
    private Jedis jedis; 
    public RedisWithReentrantLock(Jedis jedis) { 
        this.jedis = jedis; 
    } 

    private boolean _lock(String key) { 
        return jedis.set(key, "", "nx", "ex", 5L) != null; 
    } 

    private void _unlock(String key) { 
        jedis.del(key); 
    } 

    private Map <String, Integer> currentLockers() { 
        Map <String, Integer> refs = lockers.get(); 
        if (refs != null) { 
            return refs; 
        } 
        lockers.set(new HashMap<>()); 
        return lockers.get(); 
    } 

    public boolean lock(String key) { 
        Map refs = currentLockers(); 
        Integer refCnt = refs.get(key); 
        if (refCnt != null) { 
            refs.put(key, refCnt + 1);
            return true; 
        } 

        boolean ok = this._lock(key); 
        if (!ok) { 
            return false; 
        } 
        refs.put(key, 1); 
        return true; 
    } 

    public boolean unlock(String key) { 
        Map refs = currentLockers();
        Integer refCnt = refs.get(key); 
        if (refCnt == null) { 
            return false; 
        } 
        refCnt -= 1; 
        if (refCnt > 0) { 
            refs.put(key, refCnt); 
        } else { 
            refs.remove(key);
            this ._unlock(key); 
        } 
        return true; 
    } 

    public static void main(String[] args) { 
        Jedis jedis = new Jedis(); 
        RedisWithReentrantLock redis = new RedisWithReentrantLock(jedis); 
        System.out.println(redis.lock("codehole")); 
        System.out.println(redis.lock("codehole")); 
        System.out.println(redis.unlock("codehole")); 
        System.out.println(redis.unlock("codehole")); 
    } 
}
~~~

跟 Python 版本区别不大，也是基于 ThreadLocal 和引用计数。

以上还不是分布式锁的全部，在小册的拓展篇《拾遗漏补 —— 再谈分布式锁》，我们还会继续对分布式锁做进一步的深入理解。

# 应用2：缓兵之计——延时队列

我们平时习惯于使用 Rabbitmq 和 Kafka 作为消息队列中间件，来给应用程序之间增加异步消息传递功能。这两个中间件都是专业的消息队列中间件，特性之多超出了大多数人的理解能力。

使用过 Rabbitmq 的同学知道它使用起来有多复杂，发消息之前要创建 Exchange，再创建 Queue，还要将 Queue 和 Exchange 通过某种规则绑定起来，发消息的时候要指定 routing-key，还要控制头部信息。消费者在消费消息之前也要进行上面一系列的繁琐过程。但是绝大多数情况下，虽然我们的消息队列只有一组消费者，但还是需要经历上面这些繁琐的过程。

有了 Redis，它就可以让我们解脱出来，对于那些只有一组消费者的消息队列，使用 Redis 就可以非常轻松的搞定。Redis 的消息队列不是专业的消息队列，它没有非常多的高级特性，没有 ack 保证，如果对消息的可靠性有着极致的追求，那么它就不适合使用。

## 异步消息队列

Redis 的 list(列表) 数据结构常用来作为异步消息队列使用，使用rpush/lpush操作入队列，使用 lpop 和 rpop 来出队列。

~~~shell
> rpush notify-queue apple banana pear 
(integer) 3 
> llen notify-queue 
(integer) 3 
> lpop notify-queue 
"apple"
> llen notify-queue 
(integer) 2 
> lpop notify-queue 
"banana"
> llen notify-queue 
(integer) 1 
> lpop notify-queue 
"pear"
> llen notify-queue 
(integer) 0 
> lpop notify-queue 
(nil)
~~~

上面是 rpush 和 lpop 结合使用的例子。还可以使用 lpush 和 rpop 结合使用，效果是一样的。这里不再赘述。

## 队列空了怎么办？

客户端是通过队列的 pop 操作来获取消息，然后进行处理。处理完了再接着获取消息，再进行处理。如此循环往复，这便是作为队列消费者的客户端的生命周期。

可是如果队列空了，客户端就会陷入 pop 的死循环，不停地 pop，没有数据，接着再 pop，又没有数据。这就是浪费生命的空轮询。空轮询不但拉高了客户端的 CPU，redis 的 QPS 也会被拉高，如果这样空轮询的客户端有几十来个，Redis 的慢查询可能会显著增多。

通常我们使用 sleep 来解决这个问题，让线程睡一会，睡个 1s 钟就可以了。不但客户端的 CPU 能降下来，Redis 的 QPS 也降下来了。

~~~python
time.sleep(1) # python 睡 1s
~~~

~~~java
Thread.sleep(1000) // java 睡 1s
~~~

## 队列延迟

用上面睡眠的办法可以解决问题。但是有个小问题，那就是睡眠会导致消息的延迟增大。如果只有 1 个消费者，那么这个延迟就是 1s。如果有多个消费者，这个延迟会有所下降，因为每个消费者的睡觉时间是岔开来的。

有没有什么办法能显著降低延迟呢？你当然可以很快想到：那就把睡觉的时间缩短点。这种方式当然可以，不过有没有更好的解决方案呢？当然也有，那就是 blpop/brpop。

这两个指令的前缀字符 b 代表的是 blocking，也就是阻塞读。

阻塞读在队列没有数据的时候，会立即进入休眠状态，一旦数据到来，则立刻醒过来。消息的延迟几乎为零。用 blpop/brpop 替代前面的 lpop/rpop，就完美解决了上面的问题。...

## 空闲连接自动断开

你以为上面的方案真的很完美么？先别急着开心，其实他还有个问题需要解决。

什么问题？—— **空闲连接**的问题。

如果线程一直阻塞在哪里，Redis 的客户端连接就成了闲置连接，闲置过久，服务器一般会主动断开连接，减少闲置资源占用。这个时候 blpop/brpop 会抛出异常来。

所以编写客户端消费者的时候要小心，注意捕获异常，还要重试。...

## 锁冲突处理

上节课我们讲了分布式锁的问题，但是没有提到客户端在处理请求时加锁没加成功怎么办。一般有 3 种策略来处理加锁失败：

1. 直接抛出异常，通知用户稍后重试； 
2. sleep 一会再重试； 
3. 将请求转移至延时队列，过一会再试；

### 直接抛出特定类型的异常

这种方式比较适合由用户直接发起的请求，用户看到错误对话框后，会先阅读对话框的内容，再点击重试，这样就可以起到人工延时的效果。如果考虑到用户体验，可以由前端的代码替代用户自己来进行延时重试控制。它本质上是对当前请求的放弃，由用户决定是否重新发起新的请求。

### sleep

sleep 会阻塞当前的消息处理线程，会导致队列的后续消息处理出现延迟。如果碰撞的比较频繁或者队列里消息比较多，sleep 可能并不合适。如果因为个别死锁的 key 导致加锁不成功，线程会彻底堵死，导致后续消息永远得不到及时处理。

### 延时队列

这种方式比较适合异步消息处理，将当前冲突的请求扔到另一个队列延后处理以避开冲突。

## 延时队列的实现

延时队列可以通过 Redis 的 zset(有序列表) 来实现。我们将消息序列化成一个字符串作为 zset 的 value，这个消息的到期处理时间作为 score，然后用多个线程轮询 zset 获取到期的任务进行处理，多个线程是为了保障可用性，万一挂了一个线程还有其它线程可以继续处理。因为有多个线程，所以需要考虑并发争抢任务，确保任务不能被多次执行。

~~~python
def delay(msg): 
    msg.id = str(uuid.uuid4()) # 保证 value 值唯一
    value = json.dumps(msg) 
    retry_ts = time.time() + 5 # 5 秒后重试
    redis.zadd("delay-queue", retry_ts, value) 

def loop(): 
    while True: 
        # 最多取 1 条
        values = redis.zrangebyscore("delay-queue", 0, time.time(), start=0, num=1) 
        if not values:
            time.sleep(1) # 延时队列空的，休息 1s
			continue 
		value = values[0] # 拿第一条，也只有一条
		success = redis.zrem("delay-queue", value) # 从消息队列中移除该消息
		if success: # 因为有多进程并发的可能，最终只会有一个进程可以抢到消息
			msg = json.loads(value) 
			handle_msg(msg)
~~~

Redis 的 zrem 方法是多线程多进程争抢任务的关键，它的返回值决定了当前实例有没有抢到任务，因为 loop 方法可能会被多个线程、多个进程调用，同一个任务可能会被多个进程线程抢到，通过 zrem 来决定唯一的属主。

同时，我们要注意一定要对 handle_msg 进行异常捕获，避免因为个别任务处理问题导致循环异常退出。以下是 Java 版本的延时队列实现，因为要使用到 Json 序列化，所以还需要 fastjson 库的支持。

~~~java
import java.lang.reflect.Type; 
import java.util.Set; 
import java.util.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference; 
import redis.clients.jedis.Jedis; 
public class RedisDelayingQueue<T> { 
    static class TaskItem<T> { 
        public String id; 
        public T msg; 
    } 
    // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference 
    private Type TaskType = new TypeReference<TaskItem<T>>() { }.getType(); 
    private Jedis jedis; 
    private String queueKey; 
    public RedisDelayingQueue(Jedis jedis, String queueKey) { 
        this.jedis = jedis; 
        this.queueKey = queueKey; 
    } 
    
    public void delay(T msg) { 
        TaskItem task = new TaskItem(); 
        task.id = UUID.randomUUID().toString(); // 分配唯一的 uuid 
        task.msg = msg; 
        String s = JSON.toJSONString(task); // fastjson 序列化
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s); // 塞入延时队列 ,5s 后再试
    }
    
    public void loop() { 
        while (!Thread.interrupted()) {
            // 只取一条
            Set values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1); 
            if (values.isEmpty()) { 
                try { 
                    Thread.sleep(500); // 歇会继续
                } 
                catch (InterruptedException e) { 
                    break;
                } 
                continue; 
            } 
            String s = values.iterator().next(); 
            if (jedis.zrem(queueKey, s) > 0) { // 抢到了
                TaskItem task = JSON.parseObject(s, TaskType); // fastjson 反序列化
                this.handleMsg(task.msg); 
            } 
        } 
    } 
    
    public void handleMsg(T msg) { 
        System.out.println(msg); 
    } 
    
    public static void main(String[] args) { 
        Jedis jedis = new Jedis(); 
        RedisDelayingQueue queue = new RedisDelayingQueue<>(jedis, "q-demo"); 
        Thread producer = new Thread() { 
            public void run() { 
                for (int i = 0; i < 10; i++) { 
                    queue.delay("codehole" + i); 
                } 
            } 
        }; 
        Thread consumer = new Thread() { 
            public void run() { 
                queue.loop(); 
            } 
        }; 
        producer.start(); 
        consumer.start(); 
        try { 
            producer.join(); 
            Thread.sleep(6000); 
            consumer.interrupt();
            consumer.join(); 
        } 
        catch (InterruptedException e) { 
        } 
    } 
}
~~~

## 进一步优化

上面的算法中同一个任务可能会被多个进程取到之后再使用 zrem 进行争抢，那些没抢到的进程都是白取了一次任务，这是浪费。可以考虑使用 lua scripting 来优化一下这个逻辑，将 zrangebyscore 和 zrem 一同挪到服务器端进行原子化操作，这样多个进程之间争抢任务时就不会出现这种浪费了。

# 应用3：节衣缩食——位图

在我们平时开发过程中，会有一些 bool 型数据需要存取，比如用户一年的签到记录，签了是 1，没签是 0，要记录 365 天。如果使用普通的 key/value，每个用户要记录 365 个，当用户上亿的时候，需要的存储空间是惊人的。

为了解决这个问题，Redis 提供了位图数据结构，这样每天的签到记录只占据一个位，365 天就是 365 个位，46 个字节 (一个稍长一点的字符串) 就可以完全容纳下，这就大大节约了存储空间。

位图不是特殊的数据结构，它的内容其实就是普通的字符串，也就是 byte 数组。我们可以使用普通的 get/set 直接获取和设置整个位图的内容，也可以使用位图操作 getbit/setbit 等将 byte 数组看成「位数组」来处理。以老钱的经验，在面试中有 Redis 位图使用经验的同学很少，如果你对 Redis 的位图有所了解，它将会是你的面试加分项。

## 基本使用

Redis 的位数组是自动扩展，如果设置了某个偏移位置超出了现有的内容范围，就会自动将位数组进行零扩充。

接下来我们使用位操作将字符串设置为 hello (不是直接使用 set 指令)，首先我们需要得到 hello 的 ASCII 码，用 Python 命令行可以很方便地得到每个字符的 ASCII 码的二进制值。

~~~python
>>> bin(ord('h')) 
'0b1101000' # 高位 -> 低位
>>> bin(ord('e'))
'0b1100101'
>>> bin(ord('l')) 
'0b1101100'
>>> bin(ord('l'))
'0b1101100'
>>> bin(ord('o'))
'0b1101111'
~~~

接下来我们使用 redis-cli 设置第一个字符，也就是位数组的前 8 位，我们只需要设置值为 1 的位，如上图所示，h 字符只有 1/2/4 位需要设置，e 字符只有 9/10/13/15 位需要设置。值得注意的是位数组的顺序和字符的位顺序是相反的。

~~~shell
127.0.0.1:6379> setbit s 1 1 
(integer) 0 
127.0.0.1:6379> setbit s 2 1 
(integer) 0 
127.0.0.1:6379> setbit s 4 1 
(integer) 0 
127.0.0.1:6379> setbit s 9 1 
(integer) 0 
127.0.0.1:6379> setbit s 10 1 
(integer) 0 
127.0.0.1:6379> setbit s 13 1
(integer) 0 
127.0.0.1:6379> setbit s 15 1 
(integer) 0 
127.0.0.1:6379> get s 
"he"
~~~

上面这个例子可以理解为「零存整取」，同样我们还也可以「零存零取」，「整存零取」。「零存」就是使用 setbit 对位值进行逐个设置，「整存」就是使用字符串一次性填充所有位数组，覆盖掉旧值。

### 零存零取

~~~shell
127.0.0.1:6379> setbit w 1 1
(integer) 0
127.0.0.1:6379> setbit w 2 1
(integer) 0
127.0.0.1:6379> setbit w 4 1
(integer) 0
127.0.0.1:6379> getbit w 1 # 获取某个具体位置的值 0/1
(integer) 1
127.0.0.1:6379> getbit w 2
(integer) 1
127.0.0.1:6379> getbit w 4
(integer) 1
127.0.0.1:6379> getbit w 5
(integer) 0
~~~

### 整存零取

~~~shell
127.0.0.1:6379> set w h # 整存
(integer) 0
127.0.0.1:6379> getbit w 1
(integer) 1
127.0.0.1:6379> getbit w 2
(integer) 1
127.0.0.1:6379> getbit w 4
(integer) 1
127.0.0.1:6379> getbit w 5
(integer) 0
~~~

如果对应位的字节是不可打印字符，redis-cli 会显示该字符的 16 进制形式。

~~~shell
127.0.0.1:6379> setbit x 0 1
(integer) 0
127.0.0.1:6379> setbit x 1 1
(integer) 0
127.0.0.1:6379> get x
"\xc0"
~~~

## 统计和查找

Redis 提供了位图统计指令 bitcount 和位图查找指令 bitpos，bitcount 用来统计指定位置范围内 1 的个数，bitpos 用来查找指定范围内出现的第一个 0 或 1。

比如我们可以通过 bitcount 统计用户一共签到了多少天，通过 bitpos 指令查找用户从哪一天开始第一次签到。如果指定了范围参数[start, end]，就可以统计在某个时间范围内用户签到了多少天，用户自某天以后的哪天开始签到。

遗憾的是， start 和 end 参数是字节索引，也就是说指定的位范围必须是 8 的倍数，而不能任意指定。这很奇怪，我表示不是很能理解 Antirez 为什么要这样设计。因为这个设计，我们无法直接计算某个月内用户签到了多少天，而必须要将这个月所覆盖的字节内容全部取出来 (getrange 可以取出字符串的子串) 然后在内存里进行统计，这个非常繁琐。

接下来我们简单试用一下 bitcount 指令和 bitpos 指令:

~~~shell
127.0.0.1:6379> set w hello
OK
127.0.0.1:6379> bitcount w
(integer) 21
127.0.0.1:6379> bitcount w 0 0 # 第一个字符中 1 的位数
(integer) 3
127.0.0.1:6379> bitcount w 0 1 # 前两个字符中 1 的位数
(integer) 7
127.0.0.1:6379> bitpos w 0 # 第一个 0 位
(integer) 0
127.0.0.1:6379> bitpos w 1 # 第一个 1 位
(integer) 1
127.0.0.1:6379> bitpos w 1 1 1 # 从第二个字符算起，第一个 1 位
(integer) 9
127.0.0.1:6379> bitpos w 1 2 2 # 从第三个字符算起，第一个 1 位
(integer) 17
~~~

## 魔术指令 bitfield

前文我们设置 (setbit) 和获取 (getbit) 指定位的值都是单个位的，如果要一次操作多个位，就必须使用管道来处理。 不过 Redis 的 3.2 版本以后新增了一个功能强大的指令，有了这条指令，不用管道也可以一次进行多个位的操作。 bitfield 有三个子指令，分别是 get/set/incrby，它们都可以对指定位片段进行读写，但是最多只能处理 64 个连续的位，如果超过 64 位，就得使用多个子指令，bitfield 可以一次执行多个子指令。

接下来我们对照着上面的图看个简单的例子:

~~~shell
127.0.0.1:6379> set w hello
OK
127.0.0.1:6379> bitfield w get u4 0 # 从第一个位开始取 4 个位，结果是无符号数 (u)
(integer) 6
127.0.0.1:6379> bitfield w get u3 2 # 从第三个位开始取 3 个位，结果是无符号数 (u)
(integer) 5
127.0.0.1:6379> bitfield w get i4 0 # 从第一个位开始取 4 个位，结果是有符号数 (i)
1) (integer) 6
127.0.0.1:6379> bitfield w get i3 2 # 从第三个位开始取 3 个位，结果是有符号数 (i)
1) (integer) -3
~~~

所谓有符号数是指获取的位数组中第一个位是符号位，剩下的才是值。如果第一位是1，那就是负数。无符号数表示非负数，没有符号位，获取的位数组全部都是值。有符号数最多可以获取 64 位，无符号数只能获取 63 位 (因为 Redis 协议中的 integer 是有符号数，最大 64 位，不能传递 64 位无符号值)。如果超出位数限制，Redis 就会告诉你参数错误。

接下来我们一次执行多个子指令:

~~~shell
127.0.0.1:6379> bitfield w get u4 0 get u3 2 get i4 0 get i3 2
1) (integer) 6
2) (integer) 5
3) (integer) 6
4) (integer) -3
~~~

wow，很魔法有没有！

然后我们使用 set 子指令将第二个字符 e 改成 a，a 的 ASCII 码是 97。

~~~shell
127.0.0.1:6379> bitfield w set u8 8 97 # 从第 8 个位开始，将接下来的 8 个位用无符号数 97 替换
1) (integer) 101
127.0.0.1:6379> get w
"hallo"
~~~

再看第三个子指令 incrby，它用来对指定范围的位进行自增操作。既然提到自增，就有可能出现溢出。如果增加了正数，会出现上溢，如果增加的是负数，就会出现下溢出。Redis 默认的处理是折返。如果出现了溢出，就将溢出的符号位丢掉。如果是 8 位无符号数 255， 加 1 后就会溢出，会全部变零。如果是 8 位有符号数 127，加 1 后就会溢出变成 -128。

接下来我们实践一下这个子指令 incrby :

~~~shell
127.0.0.1:6379> set w hello
OK
127.0.0.1:6379> bitfield w incrby u4 2 1 # 从第三个位开始，对接下来的 4 位无符号数 +1
1) (integer) 11
127.0.0.1:6379> bitfield w incrby u4 2 1
1) (integer) 12
127.0.0.1:6379> bitfield w incrby u4 2 1
1) (integer) 13
127.0.0.1:6379> bitfield w incrby u4 2 1
1) (integer) 14
127.0.0.1:6379> bitfield w incrby u4 2 1
1) (integer) 15
127.0.0.1:6379> bitfield w incrby u4 2 1 # 溢出折返了
1) (integer) 0
~~~

bitfield 指令提供了溢出策略子指令 overflow，用户可以选择溢出行为，默认是折返(wrap)，还可以选择失败 (fail) 报错不执行，以及饱和截断 (sat)，超过了范围就停留在最大最小值。overflow 指令只影响接下来的第一条指令，这条指令执行完后溢出策略会变成默认值折返 (wrap)。

接下来我们分别试试这两个策略的行为

### 饱和截断 SAT

~~~shell
127.0.0.1:6379> set w hello
OK
127.0.0.1:6379> bitfield w overflow sat incrby u4 2 1
1) (integer) 11
127.0.0.1:6379> bitfield w overflow sat incrby u4 2 1
1) (integer) 12
127.0.0.1:6379> bitfield w overflow sat incrby u4 2 1
1) (integer) 13
127.0.0.1:6379> bitfield w overflow sat incrby u4 2 1
1) (intege) 14 
127.0.0.1:6379> bitfield w overflow sat incrby u4 2 1 
1) (integer) 15 
127.0.0.1:6379> bitfield w overflow sat incrby u4 2 1 # 保持最大值
1) (integer) 15
~~~

### 失败不执行 FAIL

~~~shell
127.0.0.1:6379> set w hello
OK
127.0.0.1:6379> bitfield w overflow fail incrby u4 2 1
1) (integer) 11
127.0.0.1:6379> bitfield w overflow fail incrby u4 2 1
1) (integer) 12
127.0.0.1:6379> bitfield w overflow fail incrby u4 2 1
1) (integer) 13
127.0.0.1:6379> bitfield w overflow fail incrby u4 2 1
1) (integer) 14
127.0.0.1:6379> bitfield w overflow fail incrby u4 2 1
1) (integer) 15
127.0.0.1:6379> bitfield w overflow fail incrby u4 2 1 # 不执行
1) (nil)
~~~

# 应用4：四两拨千斤——HyperLogLog

在开始这一节之前，我们先思考一个常见的业务问题：如果你负责开发维护一个大型的网站，有一天老板找产品经理要网站每个网页每天的 UV 数据，然后让你来开发这个统计模块，你会如何实现？

如果统计 PV 那非常好办，给每个网页一个独立的 Redis 计数器就可以了，这个计数器的 key 后缀加上当天的日期。这样来一个请求，incrby 一次，最终就可以统计出所有的 PV 数据。

但是 UV 不一样，它要去重，同一个用户一天之内的多次访问请求只能计数一次。这就要求每一个网页请求都需要带上用户的 ID，无论是登陆用户还是未登陆用户都需要一个唯一ID 来标识。

你也许已经想到了一个简单的方案，那就是为每一个页面一个独立的 set 集合来存储所有当天访问过此页面的用户 ID。当一个请求过来时，我们使用 sadd 将用户 ID 塞进去就可以了。通过 scard 可以取出这个集合的大小，这个数字就是这个页面的 UV 数据。没错，这是一个非常简单的方案。

但是，如果你的页面访问量非常大，比如一个爆款页面几千万的 UV，你需要一个很大的 set 集合来统计，这就非常浪费空间。如果这样的页面很多，那所需要的存储空间是惊人的。为这样一个去重功能就耗费这样多的存储空间，值得么？其实老板需要的数据又不需要太精确，105w 和 106w 这两个数字对于老板们来说并没有多大区别，So，有没有更好的解决方案呢？

这就是本节要引入的一个解决方案，Redis 提供了 HyperLogLog 数据结构就是用来解决这种统计问题的。HyperLogLog 提供不精确的去重计数方案，虽然不精确但是也不是非常不精确，标准误差是 0.81%，这样的精确度已经可以满足上面的 UV 统计需求了。

HyperLogLog 数据结构是 Redis 的高级数据结构，它非常有用，但是令人感到意外的是，使用过它的人非常少。

## 使用方法

HyperLogLog 提供了两个指令 pfadd 和 pfcount，根据字面意义很好理解，一个是增加计数，一个是获取计数。pfadd 用法和 set 集合的 sadd 是一样的，来一个用户 ID，就将用户 ID 塞进去就是。pfcount 和 scard 用法是一样的，直接获取计数值。

~~~shell
127.0.0.1:6379> pfadd codehole user1
(integer) 1
127.0.0.1:6379> pfcount codehole
(integer) 1
127.0.0.1:6379> pfadd codehole user2
(integer) 1
127.0.0.1:6379> pfcount codehole
(integer) 2
127.0.0.1:6379> pfadd codehole user3
(integer) 1
127.0.0.1:6379> pfcount codehole
(integer) 3
127.0.0.1:6379> pfadd codehole user4
(integer) 1
127.0.0.1:6379> pfcount codehole
(integer) 4
127.0.0.1:6379> pfadd codehole user5
(integer) 1
127.0.0.1:6379> pfcount codehole
(integer) 5
127.0.0.1:6379> pfadd codehole user6
(integer) 1
127.0.0.1:6379> pfcount codehole
(integer) 6
127.0.0.1:6379> pfadd codehole user7 user8 user9 user10
(integer) 1
127.0.0.1:6379> pfcount codehole
(integer) 10
~~~

简单试了一下，发现还蛮精确的，一个没多也一个没少。接下来我们使用脚本，往里面灌更多的数据，看看它是否还可以继续精确下去，如果不能精确，差距有多大。人生苦短，我用 Python！Python 脚本走起来！

~~~python
# coding: utf-8
import redis

client = redis.StrictRedis()
for i in range(1000):
    client.pfadd("codehole", "user%d" % i)
    total = client.pfcount("codehole")
    if total != i+1:
        print total, i+1
        break
~~~

当然 Java 也不错，大同小异，下面是 Java 版本：

~~~java
public class PfTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 0; i < 1000; i++) {
            jedis.pfadd("codehole", "user" + i);
            long total = jedis.pfcount("codehole");
            if (total != i + 1) {
                System.out.printf("%d %d\n", total, i + 1);
                break;
            }
        }
        jedis.close();
    }
}
~~~

我们来看下输出：

~~~python
> python pftest.py
99 100
~~~

当我们加入第 100 个元素时，结果开始出现了不一致。接下来我们将数据增加到 10w 个，看看总量差距有多大。

跑了约半分钟，我们看输出：

~~~python
> python pftest.py
100000 99723
~~~

差了 277 个，按百分比是 0.277%，对于上面的 UV 统计需求来说，误差率也不算高。然后我们把上面的脚本再跑一边，也就相当于将数据重复加入一边，查看输出，可以发现，pfcount 的结果没有任何改变，还是 99723，说明它确实具备去重功能。

## pfadd这个pf是什么意思？

它是 HyperLogLog 这个数据结构的发明人 Philippe Flajolet 的首字母缩写，老师觉得他发型很酷，看起来是个佛系教授。

## pfmerge适合什么场合用？

HyperLogLog 除了上面的 pfadd 和 pfcount 之外，还提供了第三个指令 pfmerge，用于将多个 pf 计数值累加在一起形成一个新的 pf 值。

比如在网站中我们有两个内容差不多的页面，运营说需要这两个页面的数据进行合并。其中页面的 UV 访问量也需要合并，那这个时候 pfmerge 就可以派上用场了。

## 注意事项

HyperLogLog 这个数据结构不是免费的，不是说使用这个数据结构要花钱，它需要占据一定 12k 的存储空间，所以它不适合统计单个用户相关的数据。如果你的用户上亿，可以算算，这个空间成本是非常惊人的。但是相比 set 存储方案，HyperLogLog 所使用的空间那真是可以使用千斤对比四两来形容了。

不过你也不必过于当心，因为 Redis 对 HyperLogLog 的存储进行了优化，在计数比较小时，它的存储空间采用稀疏矩阵存储，空间占用很小，仅仅在计数慢慢变大，稀疏矩阵占用空间渐渐超过了阈值时才会一次性转变成稠密矩阵，才会占用 12k 的空间。

## HyperLogLog实现原理

HyperLogLog 的使用非常简单，但是实现原理比较复杂，如果读者没有特别的兴趣，下面的内容暂时可以跳过不看。

给定一系列的随机整数，我们记录下低位连续零位的最大长度 k，通过这个 k 值可以估算出随机数的数量。 首先不问为什么，我们编写代码做一个实验，观察一下随机整数的数量和 k 值的关系。

通过这实验可以发现 K 和 N 的对数之间存在显著的线性相关性：
$$
N \approx 2^K
$$
如果 N 介于 2^K 和 2^(K+1) 之间，用这种方式估计的值都等于 2^K，这明显是不合理的。这里可以采用多个 BitKeeper，然后进行加权估计，就可以得到一个比较准确的值。

~~~python
import math
import random

def low_zeros(value):
 	for i in xrange(1, 32):
 		if value >> i << i != value:
 			break
	return i - 1

class BitKeeper(object):
	def __init__(self):
		self.maxbits = 0

    def random(self, m):
		bits = low_zeros(m)
		if bits > self.maxbits:
			self.maxbits = bits

class Experiment(object):
	def __init__(self, n, k=1024):
		self.n = n
		self.k = k
		self.keepers = [BitKeeper() for i in range(k)]

    def do(self):
        for i in range(self.n):
			m = random.randint(0, 1<<32-1)
			# 确保同一个整数被分配到同一个桶里面，摘取高位后取模
			keeper = self.keepers[((m & 0xfff0000) >> 16) % len(self.keepers)]
			keeper.random(m)

	def estimate(self):
    	sumbits_inverse = 0 # 零位数倒数
		for keeper in self.keepers:
			sumbits_inverse += 1.0/float(keeper.maxbits) 
		avgbits = float(self.k)/sumbits_inverse # 平均零位数
		return 2**avgbits * self.k # 根据桶的数量对估计值进行放大

for i in range(100000, 1000000, 100000): 
	exp = Experiment(i) 
	exp.do() 
	est = exp.estimate() 
	print i, '%.2f' % est, '%.2f' % (abs(est-i) / i)
~~~

下面是 Java 版：

~~~java
public class PfTest {
    static class BitKeeper {
        private int maxbits;
        public void random(long value) {
            int bits = lowZeros(value);
            if (bits > this.maxbits) {
                this.maxbits = bits;
            }
        }
        private int lowZeros(long value) {
            int i = 1;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }
    static class Experiment {
        private int n;
        private int k;
        private BitKeeper[] keepers;
        public Experiment(int n) {
            this(n, 1024);
        }
        public Experiment(int n, int k) {
            this.n = n;
            this.k = k;
            this.keepers = new BitKeeper[k];
            for (int i = 0; i < k; i++) {
                this.keepers[i] = new BitKeeper();
            }
        }
        public void work() {
            for (int i = 0; i < this.n; i++) {
                long m = ThreadLocalRandom.current().nextLong(1L << 32);
                BitKeeper keeper = keepers[(int) (((m & 0xfff0000) >> 16) % keepers.length)];
                keeper.random(m);
            }
        }
        public double estimate() {
            double sumbitsInverse = 0.0;
            for (BitKeeper keeper : keepers) {
                sumbitsInverse += 1.0 / (float) keeper.maxbits;
            }
            double avgBits = (float) keepers.length / sumbitsInverse;
            return Math.pow(2, avgBits) * this.k;
        }
    }
    public static void main(String[] args) {
        for (int i = 100000; i < 1000000; i += 100000) {
            Experiment exp = new Experiment(i);
            exp.work();
            double est = exp.estimate();
            System.out.printf("%d %.2f %.2f\n", i, est, Math.abs(est - i) / i);
        }
    } 
}
~~~

代码中分了 1024 个桶，计算平均数使用了调和平均 (倒数的平均)。普通的平均法可能因为个别离群值对平均结果产生较大的影响，调和平均可以有效平滑离群值的影响。

观察脚本的输出，误差率控制在百分比个位数

真实的 HyperLogLog 要比上面的示例代码更加复杂一些，也更加精确一些。上面的这个算法在随机次数很少的情况下会出现除零错误，因为 maxbits=0 是不可以求倒数的。

## pf的内存占用为什么是12k？

我们在上面的算法中使用了 1024 个桶进行独立计数，不过在 Redis 的 HyperLogLog 实现中用到的是 16384 个桶，也就是 2^14，每个桶的 maxbits 需要 6 个 bits 来存储，最大可以表示 maxbits=63，于是总共占用内存就是 2^14 * 6 / 8 = 12k 字节。

# 应用5：层峦叠嶂——布隆过滤器

上一节我们学会了使用 HyperLogLog 数据结构来进行估数，它非常有价值，可以解决很多精确度不高的统计需求。

但是如果我们想知道某一个值是不是已经在 HyperLogLog 结构里面了，它就无能为力了，它只提供了 pfadd 和 pfcount 方法，没有提供 pfcontains 这种方法。

讲个使用场景，比如我们在使用新闻客户端看新闻时，它会给我们不停地推荐新的内容，它每次推荐时要去重，去掉那些已经看过的内容。问题来了，新闻客户端推荐系统如何实现推送去重的？

你会想到服务器记录了用户看过的所有历史记录，当推荐系统推荐新闻时会从每个用户的历史记录里进行筛选，过滤掉那些已经存在的记录。问题是当用户量很大，每个用户看过的新闻又很多的情况下，这种方式，推荐系统的去重工作在性能上跟的上么？

实际上，如果历史记录存储在关系数据库里，去重就需要频繁地对数据库进行 exists 查询，当系统并发量很高时，数据库是很难扛住压力的。

你可能又想到了缓存，但是如此多的历史记录全部缓存起来，那得浪费多大存储空间啊？而且这个存储空间是随着时间线性增长，你撑得住一个月，你能撑得住几年么？但是不缓存的话，性能又跟不上，这该怎么办？

这时，布隆过滤器 (Bloom Filter) 闪亮登场了，它就是专门用来解决这种去重问题的。它在起到去重的同时，在空间上还能节省 90% 以上，只是稍微有那么点不精确，也就是有一定的误判概率。

## 布隆过滤器是什么？

布隆过滤器可以理解为一个不怎么精确的 set 结构，当你使用它的 contains 方法判断某个对象是否存在时，它可能会误判。但是布隆过滤器也不是特别不精确，只要参数设置的合理，它的精确度可以控制的相对足够精确，只会有小小的误判概率。

当布隆过滤器说某个值存在时，这个值可能不存在；当它说不存在时，那就肯定不存在。打个比方，当它说不认识你时，肯定就不认识；当它说见过你时，可能根本就没见过面，不过因为你的脸跟它认识的人中某脸比较相似 (某些熟脸的系数组合)，所以误判以前见过你。

套在上面的使用场景中，布隆过滤器能准确过滤掉那些已经看过的内容，那些没有看过的新内容，它也会过滤掉极小一部分 (误判)，但是绝大多数新内容它都能准确识别。这样就可以完全保证推荐给用户的内容都是无重复的。

## Redis中的布隆过滤器

Redis 官方提供的布隆过滤器到了 Redis 4.0 提供了插件功能之后才正式登场。布隆过滤器作为一个插件加载到 Redis Server 中，给 Redis 提供了强大的布隆去重功能。

下面我们来体验一下 Redis 4.0 的布隆过滤器，为了省去繁琐安装过程，我们直接用Docker 吧。

~~~shell
> docker pull redislabs/rebloom # 拉取镜像
> docker run -p6379:6379 redislabs/rebloom # 运行容器
> redis-cli # 连接容器中的 redis 服务
~~~

如果上面三条指令执行没有问题，下面就可以体验布隆过滤器了。

## 布隆过滤器基本使用

布隆过滤器有二个基本指令，`bf.add` 添加元素，`bf.exists` 查询元素是否存在，它的用法和 set 集合的 sadd 和 sismember 差不多。注意 `bf.add` 只能一次添加一个元素，如果想要一次添加多个，就需要用到 `bf.madd` 指令。同样如果需要一次查询多个元素是否存在，就需要用到 `bf.mexists` 指令。

~~~shell
127.0.0.1:6379> bf.add codehole user1
(integer) 1
127.0.0.1:6379> bf.add codehole user2
(integer) 1
127.0.0.1:6379> bf.add codehole user3
(integer) 1
127.0.0.1:6379> bf.exists codehole user1
(integer) 1
127.0.0.1:6379> bf.exists codehole user2
(integer) 1
127.0.0.1:6379> bf.exists codehole user3
(integer) 1
127.0.0.1:6379> bf.exists codehole user4
(integer) 0
127.0.0.1:6379> bf.madd codehole user4 user5 user6
1) (integer) 1
2) (integer) 1
3) (integer) 1
127.0.0.1:6379> bf.mexists codehole user4 user5 user6 user7
1) (integer) 1
2) (integer) 1
3) (integer) 1
4) (integer) 0
~~~

似乎很准确啊，一个都没误判。下面我们用 Python 脚本加入很多元素，看看加到第几个元素的时候，布隆过滤器会出现误判。

~~~python
# coding: utf-8
import redis

client = redis.StrictRedis()
client.delete("codehole")
for i in range(100000):
	client.execute_command("bf.add", "codehole", "user%d" % i)
	ret = client.execute_command("bf.exists", "codehole", "user%d" % i)
     if ret == 0:
         print i
         break
~~~

Java 客户端 Jedis-2.x 没有提供指令扩展机制，所以你无法直接使用 Jedis 来访问Redis Module 提供的 bf.xxx 指令。RedisLabs 提供了一个单独的包 JReBloom，但是它是基于 Jedis-3.0，Jedis-3.0 这个包目前还没有进入 release，没有进入 maven 的中央仓库，需要在 Github 上下载。在使用上很不方便，如果怕麻烦，还可以使用 lettuce，它是另一个Redis 的客户端，相比 Jedis 而言，它很早就支持了指令扩展。

~~~java
public class BloomTest {
    public static void main(String[] args) {
        Client client = new Client();
        client.delete("codehole");
        for (int i = 0; i < 100000; i++) {
            client.add("codehole", "user" + i);
            boolean ret = client.exists("codehole", "user" + i);
            if (!ret) {
                System.out.println(i);
                break;
            }
        }
        client.close();
    }
}
~~~

执行上面的代码后，你会张大了嘴巴发现居然没有输出，塞进去了 100000 个元素，还是没有误判，这是怎么回事？如果你不死心的话，可以将数字再加一个 0 试试，你会发现依然没有误判。

原因就在于布隆过滤器对于已经见过的元素肯定不会误判，它只会误判那些没见过的元素。所以我们要稍微改一下上面的脚本，使用 bf.exists 去查找没见过的元素，看看它是不是以为自己见过了。

~~~python
# coding: utf-8
import redis
client = redis.StrictRedis()
client.delete("codehole")
for i in range(100000):
    client.execute_command("bf.add", "codehole", "user%d" % i)
    # 注意 i+1，这个是当前布隆过滤器没见过的
    ret = client.execute_command("bf.exists", "codehole", "user%d" % (i+1))
    if ret == 1:
        print i
        break
~~~

Java版：

~~~java
public class BloomTest {
    public static void main(String[] args) {
        Client client = new Client();
        client.delete("codehole");
        for (int i = 0; i < 100000; i++) {
            client.add("codehole", "user" + i);
            boolean ret = client.exists("codehole", "user" + (i + 1));
            if (ret) {
                System.out.println(i);
                break;
            }
        }
        client.close();
    }
}
~~~

运行后，我们看到了输出是 214，也就是到第 214 的时候，它出现了误判。

那如何来测量误判率呢？我们先随机出一堆字符串，然后切分为 2 组，将其中一组塞入布隆过滤器，然后再判断另外一组的字符串存在与否，取误判的个数和字符串总量一半的百分比作为误判率。

可以看到误判率大约 1% 多点。你也许会问这个误判率还是有点高啊，有没有办法降低一点？答案是有的。

我们上面使用的布隆过滤器只是默认参数的布隆过滤器，它在我们第一次 add 的时候自动创建。Redis 其实还提供了自定义参数的布隆过滤器，需要我们在 add 之前使用 bf.reserve 指令显式创建。如果对应的 key 已经存在，bf.reserve 会报错。bf.reserve 有三个参数，分别是 key, error_rate 和 initial_size。错误率越低，需要的空间越大。initial_size 参数表示预计放入的元素数量，当实际数量超出这个数值时，误判率会上升。

所以需要提前设置一个较大的数值避免超出导致误判率升高。如果不使用 bf.reserve，默认的 error_rate 是 0.01，默认的 initial_size 是 100。

我们看到了误判率大约 0.012%，比预计的 0.1% 低很多，不过布隆的概率是有误差的，只要不比预计误判率高太多，都是正常现象。

## 注意事项

布隆过滤器的 initial_size 估计的过大，会浪费存储空间，估计的过小，就会影响准确率，用户在使用之前一定要尽可能地精确估计好元素数量，还需要加上一定的冗余空间以避免实际元素可能会意外高出估计值很多。

布隆过滤器的 error_rate 越小，需要的存储空间就越大，对于不需要过于精确的场合，error_rate 设置稍大一点也无伤大雅。比如在新闻去重上而言，误判率高一点只会让小部分文章不能让合适的人看到，文章的整体阅读量不会因为这点误判率就带来巨大的改变。

## 布隆过滤器的原理

学会了布隆过滤器的使用，下面有必要把原理解释一下，不然读者还会继续蒙在鼓里。

每个布隆过滤器对应到 Redis 的数据结构里面就是一个大型的位数组和几个不一样的无偏 hash 函数。所谓无偏就是能够把元素的 hash 值算得比较均匀。

向布隆过滤器中添加 key 时，会使用多个 hash 函数对 key 进行 hash 算得一个整数索引值然后对位数组长度进行取模运算得到一个位置，每个 hash 函数都会算得一个不同的位置。再把位数组的这几个位置都置为 1 就完成了 add 操作。

向布隆过滤器询问 key 是否存在时，跟 add 一样，也会把 hash 的几个位置都算出来，看看位数组中这几个位置是否都位 1，只要有一个位为 0，那么说明布隆过滤器中这个key 不存在。如果都是 1，这并不能说明这个 key 就一定存在，只是极有可能存在，因为这些位被置为 1 可能是因为其它的 key 存在所致。如果这个位数组比较稀疏，这个概率就会很大，如果这个位数组比较拥挤，这个概率就会降低。具体的概率计算公式比较复杂，感兴趣可以阅读扩展阅读，非常烧脑，不建议读者细看。

使用时不要让实际元素远大于初始化大小，当实际元素开始超出初始化大小时，应该对布隆过滤器进行重建，重新分配一个 size 更大的过滤器，再将所有的历史元素批量 add 进去 (这就要求我们在其它的存储器中记录所有的历史元素)。因为 error_rate 不会因为数量超出就急剧增加，这就给我们重建过滤器提供了较为宽松的时间。

## 空间占用估计

布隆过滤器的空间占用有一个简单的计算公式，但是推导比较繁琐，这里就省去推导过程了，直接引出计算公式，感兴趣的读者可以点击「扩展阅读」深入理解公式的推导过程。

布隆过滤器有两个参数，第一个是预计元素的数量 n，第二个是错误率 f。公式根据这两个输入得到两个输出，第一个输出是位数组的长度 l，也就是需要的存储空间大小 (bit)，第二个输出是 hash 函数的最佳数量 k。hash 函数的数量也会直接影响到错误率，最佳的数量会有最低的错误率。

~~~
k=0.7*(l/n) # 约等于
f=0.6185^(l/n) # ^ 表示次方计算，也就是 math.pow
~~~

从公式中可以看出

1. 位数组相对越长 (l/n)，错误率 f 越低，这个和直观上理解是一致的
2. 位数组相对越长 (l/n)，hash 函数需要的最佳数量也越多，影响计算效率
3. 当一个元素平均需要 1 个字节 (8bit) 的指纹空间时 (l/n=8)，错误率大约为 2%
4. 错误率为 10%，一个元素需要的平均指纹空间为 4.792 个 bit，大约为 5bit
5. 错误率为 1%，一个元素需要的平均指纹空间为 9.585 个 bit，大约为 10bit
6. 错误率为 0.1%，一个元素需要的平均指纹空间为 14.377 个 bit，大约为 15bit

你也许会想，如果一个元素需要占据 15 个 bit，那相对 set 集合的空间优势是不是就没有那么明显了？这里需要明确的是，set 中会存储每个元素的内容，而布隆过滤器仅仅存储元素的指纹。元素的内容大小就是字符串的长度，它一般会有多个字节，甚至是几十个上百个字节，每个元素本身还需要一个指针被 set 集合来引用，这个指针又会占去 4 个字节或 8 个字节，取决于系统是 32bit 还是 64bit。而指纹空间只有接近 2 个字节，所以布隆过滤器的空间优势还是非常明显的。

如果读者觉得公式计算起来太麻烦，也没有关系，有很多现成的网站已经支持计算空间占用的功能了，我们只要把参数输进去，就可以直接看到结果，比如 布隆计算器。

## 实际元素超出时，误判率会怎样变化

当实际元素超出预计元素时，错误率会有多大变化，它会急剧上升么，还是平缓地上升，这就需要另外一个公式，引入参数 t 表示实际元素和预计元素的倍数 t

~~~
f=(1-0.5^t)^k # 极限近似，k 是 hash 函数的最佳数量
~~~

当 t 增大时，错误率，f 也会跟着增大，分别选择错误率为 10%,1%,0.1% 的 k 值，画出它的曲线进行直观观察。

从这个图中可以看出曲线还是比较陡峭的

1. 错误率为 10% 时，倍数比为 2 时，错误率就会升至接近 40%，这个就比较危险了
2. 错误率为 1% 时，倍数比为 2 时，错误率升至 15%，也挺可怕的
3. 错误率为 0.1%，倍数比为 2 时，错误率升至 5%，也比较悬了

## 用不上Redis4.0怎么办？

Redis 4.0 之前也有第三方的布隆过滤器 lib 使用，只不过在实现上使用 redis 的位图来实现的，性能上也要差不少。比如一次 exists 查询会涉及到多次 getbit 操作，网络开销相比而言会高出不少。另外在实现上这些第三方 lib 也不尽完美，比如 pyrebloom 库就不支持重连和重试，在使用时需要对它做一层封装后才能在生产环境中使用。

## 布隆过滤器的其他应用

在爬虫系统中，我们需要对 URL 进行去重，已经爬过的网页就可以不用爬了。但是URL 太多了，几千万几个亿，如果用一个集合装下这些 URL 地址那是非常浪费空间的。这时候就可以考虑使用布隆过滤器。它可以大幅降低去重存储消耗，只不过也会使得爬虫系统错过少量的页面。

布隆过滤器在 NoSQL 数据库领域使用非常广泛，我们平时用到的 HBase、Cassandra 还有 LevelDB、RocksDB 内部都有布隆过滤器结构，布隆过滤器可以显著降低数据库的 IO 请求数量。当用户来查询某个 row 时，可以先通过内存中的布隆过滤器过滤掉大量不存在的row 请求，然后再去磁盘进行查询。

邮箱系统的垃圾邮件过滤功能也普遍用到了布隆过滤器，因为用了这个过滤器，所以平时也会遇到某些正常的邮件被放进了垃圾邮件目录中，这个就是误判所致，概率很低。

# 应用6：断尾求生——简单限流

限流算法在分布式领域是一个经常被提起的话题，当系统的处理能力有限时，如何阻止计划外的请求继续对系统施压，这是一个需要重视的问题。老钱在这里用 “断尾求生” 形容限流背后的思想，当然还有很多成语也表达了类似的意思，如弃卒保车、壮士断腕等等。

除了控制流量，限流还有一个应用目的是用于控制用户行为，避免垃圾请求。比如在UGC 社区，用户的发帖、回复、点赞等行为都要严格受控，一般要严格限定某行为在规定时间内允许的次数，超过了次数那就是非法行为。对非法行为，业务必须规定适当的惩处策略。

## 如何使用Redis来实现简单限流策略？



# 原理5：同舟共济——事务

为了确保连续多个操作的原子性，一个成熟的数据库通常都会有事务支持，Redis 也不例外。Redis 的事务使用非常简单，不同于关系数据库，我们无须理解那么多复杂的事务模型，就可以直接使用。不过也正是因为这种简单性，它的事务模型很不严格，这要求我们不能像使用关系数据库的事务一样来使用 Redis。

## Redis 事务的基本使用

每个事务的操作都有 begin、commit 和 rollback，begin 指示事务的开始，commit 指示事务的提交，rollback 指示事务的回滚。它大致的形式如下。

~~~java
begin();
try {
     command1();
     command2();
     ....
     commit();
} catch(Exception e) {
     rollback();
}
~~~

Redis 在形式上看起来也差不多，分别是 multi/exec/discard。multi 指示事务的开始，exec 指示事务的执行，discard 指示事务的丢弃。

~~~shell
> multi
OK
> incr books
QUEUED
> incr books
QUEUED
> exec
(integer) 1
(integer) 2
~~~

上面的指令演示了一个完整的事务过程，所有的指令在 exec 之前不执行，而是缓存在服务器的一个事务队列中，服务器一旦收到 exec 指令，才开执行整个事务队列，执行完毕后一次性返回所有指令的运行结果。因为 Redis 的单线程特性，它不用担心自己在执行队列的时候被其它指令打搅，可以保证他们能得到的「原子性」执行。

上图显示了以上事务过程完整的交互效果。QUEUED 是一个简单字符串，同 OK 是一个形式，它表示指令已经被服务器缓存到队列里了。

## 原子性

事务的原子性是指要么事务全部成功，要么全部失败，那么 Redis 事务执行是原子性的么？

下面我们来看一个特别的例子。

~~~shell
> multi
OK
> set books iamastring
QUEUED
> incr books
QUEUED
> set poorman iamdesperate
QUEUED
> exec
1) OK
2) (error) ERR value is not an integer or out of range
3) OK
> get books
"iamastring"
> get poorman
"iamdesperate
~~~

上面的例子是事务执行到中间遇到失败了，因为我们不能对一个字符串进行数学运算，事务在遇到指令执行失败后，后面的指令还继续执行，所以 poorman 的值能继续得到设置。

到这里，你应该明白 Redis 的事务根本不能算「原子性」，而仅仅是满足了事务的「隔离性」，隔离性中的串行化——当前执行的事务有着不被其它事务打断的权利。

## discard（丢弃）

Redis 为事务提供了一个 discard 指令，用于丢弃事务缓存队列中的所有指令，在 exec 执行之前。

~~~shell
> get books
(nil)
> multi
OK
> incr books
QUEUED
> incr books
QUEUED
> discard
OK
> get books
(nil)
~~~

我们可以看到 discard 之后，队列中的所有指令都没执行，就好像 multi 和 discard 中间的所有指令从未发生过一样。

## 优化

上面的 Redis 事务在发送每个指令到事务缓存队列时都要经过一次网络读写，当一个事务内部的指令较多时，需要的网络 IO 时间也会线性增长。所以通常 Redis 的客户端在执行事务时都会结合 pipeline 一起使用，这样可以将多次 IO 操作压缩为单次 IO 操作。比如我们在使用 Python 的 Redis 客户端时执行事务时是要强制使用 pipeline 的。

~~~python
pipe = redis.pipeline(transaction=true)
pipe.multi()
pipe.incr("books")
pipe.incr("books")
values = pipe.execute()
~~~

## Watch

考虑到一个业务场景，Redis 存储了我们的账户余额数据，它是一个整数。现在有两个并发的客户端要对账户余额进行修改操作，这个修改不是一个简单的 incrby 指令，而是要对余额乘以一个倍数。Redis 可没有提供 multiplyby 这样的指令。我们需要先取出余额然后在内存里乘以倍数，再将结果写回 Redis。

这就会出现并发问题，因为有多个客户端会并发进行操作。我们可以通过 Redis 的分布式锁来避免冲突，这是一个很好的解决方案。**分布式锁是一种悲观锁，那是不是可以使用乐观锁的方式来解决冲突呢？**

Redis 提供了这种 watch 的机制，它就是一种乐观锁。有了 watch 我们又多了一种可以用来解决并发修改的方法。 watch 的使用方式如下：

~~~python
while True:
     do_watch()
     commands()
     multi()
     send_commands()
     try:
         exec()
         break
     except WatchError:
         continue
~~~

watch 会在事务开始之前盯住 1 个或多个关键变量，当事务执行时，也就是服务器收到了 exec 指令要顺序执行缓存的事务队列时，Redis 会检查关键变量自 watch 之后，是否被修改了 (包括当前事务所在的客户端)。如果关键变量被人动过了，exec 指令就会返回 null 回复告知客户端事务执行失败，这个时候客户端一般会选择重试。

~~~shell
> watch books
OK
> incr books # 被修改了
(integer) 1
> multi
OK
> incr books
QUEUED
> exec # 事务执行失败
(nil)
~~~

当服务器给 exec 指令返回一个 null 回复时，客户端知道了事务执行是失败的，通常客户端 (redis-py) 都会抛出一个 WatchError 这种错误，不过也有些语言 (jedis) 不会抛出异常，而是通过在 exec 方法里返回一个 null，这样客户端需要检查一下返回结果是否为 null 来确定事务是否执行失败。

### 注意事项

Redis 禁止在 multi 和 exec 之间执行 watch 指令，而必须在 multi 之前做好盯住关键变量，否则会出错。

接下来我们使用 Python 语言来实现对余额的加倍操作。

~~~python
# -*- coding: utf-8
import redis

def key_for(user_id):
    return "account_{}".format(user_id)

def double_account(client, user_id):
    key = key_for(user_id)
    while True:
        client.watch(key)
        value = int(client.get(key))
        value *= 2 # 加倍
        pipe = client.pipeline(transaction=True)
        pipe.multi()
        pipe.set(key, value)
        try:
            pipe.execute()
            break # 总算成功了
        except redis.WatchError:
            continue # 事务被打断了，重试
    return int(client.get(key)) # 重新获取余额

client = redis.StrictRedis()
user_id = "abc"
client.setnx(key_for(user_id), 5) # setnx 做初始化
print double_account(client, user_id)
~~~

下面我们再使用 Java 语言实现一遍。

~~~java
import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
public class TransactionDemo {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        String userId = "abc";
        String key = keyFor(userId);
        jedis.setnx(key, String.valueOf(5)); // setnx 做初始化
        System.out.println(doubleAccount(jedis, userId));
        jedis.close();
    }

    public static int doubleAccount(Jedis jedis, String userId) {
        String key = keyFor(userId);
        while (true) {
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            value *= 2; // 加倍
            Transaction tx = jedis.multi();
            tx.set(key, String.valueOf(value));
            List<Object> res = tx.exec();
            if (res != null) {
                break; // 成功了
            }
        }
        return Integer.parseInt(jedis.get(key)); // 重新获取余额
    }

    public static String keyFor(String userId) {
        return String.format("account_{}", userId);
    } 
}
~~~

我们常常听说 Python 的代码要比 Java 简短太多，但是从这个例子中我们看到 Java 的代码比 python 的代码也多不了多少，大约只多出 50%。

# 原理6：小道消息——PubSub

# 扩展4：朝生暮死——过期策略

Redis 所有的数据结构都可以设置过期时间，时间一到，就会自动删除。你可以想象 Redis 内部有一个死神，时刻盯着所有设置了过期时间的 key，寿命一到就会立即收割。

你还可以进一步站在死神的角度思考，会不会因为同一时间太多的 key 过期，以至于忙不过来。同时因为 Redis 是单线程的，收割的时间也会占用线程的处理时间，如果收割的太过于繁忙，会不会导致线上读写指令出现卡顿。

这些问题 Antirez 早就想到了，所有在过期这件事上，Redis 非常小心。

## 过期的key集合

redis 会将每个设置了过期时间的 key 放入到一个独立的字典中，以后会定时遍历这个字典来删除到期的 key。除了定时遍历之外，它还会使用惰性策略来删除过期的 key，所谓惰性策略就是在客户端访问这个 key 的时候，redis 对 key 的过期时间进行检查，如果过期了就立即删除。定时删除是集中处理，惰性删除是零散处理。

## 定时扫描策略

Redis 默认会每秒进行十次过期扫描，过期扫描不会遍历过期字典中所有的 key，而是采用了一种简单的贪心策略。

1. 从过期字典中随机 20 个 key；
2. 删除这 20 个 key 中已经过期的 key；
3. 如果过期的 key 比率超过 1/4，那就重复步骤 1；

同时，为了保证过期扫描不会出现循环过度，导致线程卡死现象，算法还增加了扫描时间的上限，默认不会超过 25ms。

设想一个大型的 Redis 实例中所有的 key 在同一时间过期了，会出现怎样的结果？

毫无疑问，Redis 会持续扫描过期字典 (循环多次)，直到过期字典中过期的 key 变得稀疏，才会停止 (循环次数明显下降)。这就会导致线上读写请求出现明显的卡顿现象。导致这种卡顿的另外一种原因是内存管理器需要频繁回收内存页，这也会产生一定的 CPU 消耗。

也许你会争辩说“扫描不是有 25ms 的时间上限了么，怎么会导致卡顿呢”？这里打个比方，假如有 101 个客户端同时将请求发过来了，然后前 100 个请求的执行时间都是25ms，那么第 101 个指令需要等待多久才能执行？2500ms，这个就是客户端的卡顿时间，是由服务器不间断的小卡顿积少成多导致的。

所以业务开发人员一定要注意过期时间，如果有大批量的 key 过期，要给过期时间设置一个随机范围，而不能全部在同一时间过期。

~~~python
# 在目标过期时间上增加一天的随机时间
redis.expire_at(key, random.randint(86400) + expire_ts)
~~~

在一些活动系统中，因为活动是一期一会，下一期活动举办时，前面几期的很多数据都可以丢弃了，所以需要给相关的活动数据设置一个过期时间，以减少不必要的 Redis 内存占用。如果不加注意，你可能会将过期时间设置为活动结束时间再增加一个常量的冗余时间，如果参与活动的人数太多，就会导致大量的 key 同时过期。掌阅服务端在开发过程中就曾出现过多次因为大量 key 同时过期导致的卡顿报警现象，通过将过期时间随机化总是能很好地解决了这个问题，希望读者们今后能少犯这样的错误。

## 从库的过期策略

从库不会进行过期扫描，从库对过期的处理是被动的。主库在 key 到期时，会在 AOF 文件里增加一条 del 指令，同步到所有的从库，从库通过执行这条 del 指令来删除过期的 key。

因为指令同步是异步进行的，所以主库过期的 key 的 del 指令没有及时同步到从库的话，会出现主从数据的不一致，主库没有的数据在从库里还存在，比如上一节的集群环境分布式锁的算法漏洞就是因为这个同步延迟产生的。

# 拓展5：优胜劣汰——LRU

