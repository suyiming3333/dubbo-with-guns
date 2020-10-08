##  dubbo with guns 基于dubbo+guns实现分布式微服务功能开发后台

## 主要模块

1. 网关服务(多节点部署)

   - AuthFilter实现网关统一资源拦截
   - Jwt实现用户token校验
   - ThreadLocal存储用户信息方便调用
   - 微服务api聚合

2. 用户服务

   - 用户信息增删改查

3. 订单服务

   - 下单购票

4. 支付服务

   - 调用红包+资金组合支付

5. 资金服务

   - tcc-transcation引入处理分布式事务

6. 红包服务

   - tcc-transcation引入处理分布式事务

7. 影片服务

   - 获取影片列表信息

8. 影院服务

   - 获取院线售座信息

    

## 技术实现

#### 整体技术

1. dubbo rpc实现分布式微服务开发,dubbo admin服务治理
2. springboot + mybtiaplus +mysql
3. TCC-TRANSACTION分布式事务
4. zipkin链路追踪

#### 用户服务

- 启动检查
- 负载均衡策略
- 多协议支持

#### 影片服务

- dubbo异步服务调用

#### 影院服务

- dubbo结果缓存，cache = "lru"
- 并发与连接控制

#### 订单服务

- 分组与聚合 group & merge
- 版本管理(蓝绿发布+版本控制)
- 下单接口通过令牌桶算法限流
- 引入hystrix熔断器，快速失败

#### 支付服务

- 本地存根stub(逻辑在客户端上处理，如参数校验通过了，才去调用服务；服务降级、伪造返回数据)
- 本地伪装mock(本地存根的子集，只能处理RPC Exception)降级结果返回
- 隐式参数传递(RpcContext)

#### 红包+资金组合支付

- tcc-transaction分布式事务