# BookSaleHub - 在线图书销售系统

## 项目概述

BookSaleHub是一个功能完善、用户体验良好、安全可靠的在线图书销售系统，采用前后端分离架构设计。系统满足游客浏览、用户购买及管理员管理等多方面需求，为用户提供便捷的图书购买体验。

## 系统架构

### 前端技术栈

- **框架**：React.js
- **UI组件库**：Ant Design
- **状态管理**：Redux
- **路由管理**：React Router
- **HTTP客户端**：Axios
- **构建工具**：Webpack

### 后端技术栈

- **框架**：Spring Boot
- **ORM框架**：MyBatis-Plus
- **数据库**：MySQL
- **缓存**：Redis
- **搜索引擎**：Elasticsearch
- **消息队列**：RabbitMQ
- **安全框架**：Spring Security + JWT
- **API文档**：Swagger

### 系统架构图

```
+------------------+    +------------------+    +------------------+
|                  |    |                  |    |                  |
|  前端应用层       |    |  后端服务层       |    |  数据持久层       |
|                  |    |                  |    |                  |
+------------------+    +------------------+    +------------------+
|                  |    |                  |    |                  |
| - 用户界面        |    | - 用户服务        |    | - MySQL数据库     |
| - 管理员界面      |    | - 图书服务        |    | - Redis缓存      |
|                  |    | - 订单服务        |    | - Elasticsearch  |
|                  |    | - 购物车服务      |    |                  |
|                  |    | - 支付服务        |    |                  |
|                  |    | - 搜索服务        |    |                  |
|                  |    |                  |    |                  |
+------------------+    +------------------+    +------------------+
```

## 功能模块

### 1. 游客浏览模块

- **图书分类浏览**：支持按类别、作者、出版社等多维度浏览图书
- **图书搜索**：支持关键词搜索、高级搜索、搜索结果排序
- **图书详情**：展示书名、作者、出版社、ISBN、价格、库存、简介、封面图片等信息

### 2. 用户认证模块

- **用户注册**：支持邮箱或手机号注册，包含验证流程
- **用户登录**：支持多种登录方式（用户名、邮箱、手机号）
- **权限管理**：基于角色的访问控制（RBAC）
- **第三方登录**：支持微信、QQ等第三方账号登录（可选功能）

### 3. 图书购买模块

- **购物车管理**：添加、删除图书，编辑数量
- **收藏夹**：收藏喜欢的图书
- **结算流程**：选择收货地址、支付方式
- **支付集成**：模拟支付流程

### 4. 订单管理模块

- **订单创建**：生成订单号，记录订单信息
- **订单状态**：待付款、已付款、已发货、已完成、已取消等状态管理
- **订单查询**：查看当前订单及历史订单
- **订单详情**：查看订单中的图书信息、收货信息、支付信息等

### 5. 用户信息管理模块

- **个人中心**：展示用户基本信息
- **信息修改**：修改用户名、邮箱、手机号等
- **地址管理**：添加、编辑、删除收货地址
- **密码修改**：修改登录密码

### 6. 管理员后台模块

- **图书管理**：添加、编辑、上下架图书
- **订单处理**：发货、退货、修改订单状态
- **用户管理**：查看用户信息、禁用/启用用户账号
- **统计分析**：销售统计、用户活跃度分析等

## 数据库设计

### 用户表（User）

| 字段名 | 类型 | 描述 |
| --- | --- | --- |
| id | BIGINT | 主键，自增 |
| username | VARCHAR(50) | 用户名 |
| password | VARCHAR(100) | 密码（加密存储） |
| email | VARCHAR(100) | 邮箱 |
| phone | VARCHAR(20) | 手机号 |
| avatar | VARCHAR(255) | 头像URL |
| role | TINYINT | 角色（0-游客，1-注册用户，2-管理员） |
| status | TINYINT | 状态（0-禁用，1-正常） |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 图书表（Book）

| 字段名 | 类型 | 描述 |
| --- | --- | --- |
| id | BIGINT | 主键，自增 |
| title | VARCHAR(100) | 书名 |
| author | VARCHAR(100) | 作者 |
| publisher | VARCHAR(100) | 出版社 |
| publish_date | DATE | 出版日期 |
| isbn | VARCHAR(20) | ISBN |
| category_id | BIGINT | 分类ID（外键） |
| price | DECIMAL(10,2) | 价格 |
| stock | INT | 库存 |
| cover_url | VARCHAR(255) | 封面图片URL |
| description | TEXT | 图书简介 |
| status | TINYINT | 状态（0-下架，1-上架） |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 分类表（Category）

| 字段名 | 类型 | 描述 |
| --- | --- | --- |
| id | BIGINT | 主键，自增 |
| name | VARCHAR(50) | 分类名称 |
| parent_id | BIGINT | 父分类ID |
| level | TINYINT | 分类级别 |
| sort | INT | 排序值 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 购物车表（Cart）

| 字段名 | 类型 | 描述 |
| --- | --- | --- |
| id | BIGINT | 主键，自增 |
| user_id | BIGINT | 用户ID（外键） |
| book_id | BIGINT | 图书ID（外键） |
| quantity | INT | 数量 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 订单表（Order）

| 字段名 | 类型 | 描述 |
| --- | --- | --- |
| id | BIGINT | 主键，自增 |
| order_no | VARCHAR(50) | 订单号 |
| user_id | BIGINT | 用户ID（外键） |
| total_amount | DECIMAL(10,2) | 订单总金额 |
| pay_amount | DECIMAL(10,2) | 实付金额 |
| pay_type | TINYINT | 支付方式（1-支付宝，2-微信，3-银行卡） |
| status | TINYINT | 订单状态（0-待付款，1-已付款，2-已发货，3-已完成，4-已取消） |
| shipping_id | BIGINT | 收货地址ID（外键） |
| payment_time | DATETIME | 支付时间 |
| shipping_time | DATETIME | 发货时间 |
| receive_time | DATETIME | 收货时间 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 订单项表（OrderItem）

| 字段名 | 类型 | 描述 |
| --- | --- | --- |
| id | BIGINT | 主键，自增 |
| order_id | BIGINT | 订单ID（外键） |
| book_id | BIGINT | 图书ID（外键） |
| book_title | VARCHAR(100) | 图书名称（冗余） |
| book_cover | VARCHAR(255) | 图书封面（冗余） |
| price | DECIMAL(10,2) | 购买时价格 |
| quantity | INT | 购买数量 |
| total_price | DECIMAL(10,2) | 总价 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

### 收货地址表（Shipping）

| 字段名 | 类型 | 描述 |
| --- | --- | --- |
| id | BIGINT | 主键，自增 |
| user_id | BIGINT | 用户ID（外键） |
| receiver_name | VARCHAR(50) | 收货人姓名 |
| receiver_phone | VARCHAR(20) | 收货人电话 |
| receiver_province | VARCHAR(50) | 省份 |
| receiver_city | VARCHAR(50) | 城市 |
| receiver_district | VARCHAR(50) | 区/县 |
| receiver_address | VARCHAR(255) | 详细地址 |
| is_default | TINYINT | 是否默认（0-否，1-是） |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |

## API接口设计

### 用户相关接口

- `POST /api/user/register` - 用户注册
- `POST /api/user/login` - 用户登录
- `GET /api/user/info` - 获取用户信息
- `PUT /api/user/info` - 更新用户信息
- `PUT /api/user/password` - 修改密码
- `GET /api/user/address` - 获取收货地址列表
- `POST /api/user/address` - 添加收货地址
- `PUT /api/user/address/{id}` - 更新收货地址
- `DELETE /api/user/address/{id}` - 删除收货地址

### 图书相关接口

- `GET /api/books` - 获取图书列表（支持分页、排序、筛选）
- `GET /api/books/{id}` - 获取图书详情
- `GET /api/categories` - 获取分类列表
- `GET /api/books/search` - 搜索图书

### 购物车相关接口

- `GET /api/cart` - 获取购物车列表
- `POST /api/cart` - 添加商品到购物车
- `PUT /api/cart/{id}` - 更新购物车商品数量
- `DELETE /api/cart/{id}` - 删除购物车商品
- `DELETE /api/cart` - 清空购物车

### 订单相关接口

- `POST /api/orders` - 创建订单
- `GET /api/orders` - 获取订单列表
- `GET /api/orders/{id}` - 获取订单详情
- `PUT /api/orders/{id}/cancel` - 取消订单
- `PUT /api/orders/{id}/pay` - 支付订单
- `GET /api/orders/{id}/status` - 查询订单状态

### 管理员相关接口

- `POST /api/admin/books` - 添加图书
- `PUT /api/admin/books/{id}` - 更新图书信息
- `PUT /api/admin/books/{id}/status` - 上架/下架图书
- `GET /api/admin/orders` - 获取所有订单
- `PUT /api/admin/orders/{id}/ship` - 发货
- `GET /api/admin/users` - 获取用户列表
- `PUT /api/admin/users/{id}/status` - 禁用/启用用户

## 安全设计

1. **身份认证**：采用JWT（JSON Web Token）进行身份验证
2. **密码安全**：使用BCrypt算法加密存储用户密码
3. **HTTPS协议**：使用HTTPS协议保障传输安全
4. **CSRF防护**：实现跨站请求伪造防护机制
5. **XSS防护**：输入验证和输出编码，防止跨站脚本攻击
6. **SQL注入防护**：使用参数化查询和ORM框架
7. **权限控制**：基于角色的访问控制（RBAC）

## 部署架构

```
+------------------+    +------------------+    +------------------+
|                  |    |                  |    |                  |
|  Nginx           |    |  应用服务器集群    |    |  数据库服务器     |
|  (负载均衡)       |    |  (Docker容器)    |    |                  |
|                  |    |                  |    |                  |
+------------------+    +------------------+    +------------------+
         |                       |                       |
         |                       |                       |
         v                       v                       v
+------------------+    +------------------+    +------------------+
|                  |    |                  |    |                  |
|  静态资源服务器   |    |  Redis集群       |    |  Elasticsearch   |
|  (CDN)           |    |  (缓存)          |    |  (搜索引擎)      |
|                  |    |                  |    |                  |
+------------------+    +------------------+    +------------------+
```

## 性能优化

1. **前端优化**
   - 资源压缩与合并
   - 懒加载与按需加载
   - 浏览器缓存策略
   - CDN加速静态资源

2. **后端优化**
   - 数据库索引优化
   - 查询语句优化
   - Redis缓存热点数据
   - 分页查询与限流

3. **搜索优化**
   - Elasticsearch实现高效搜索
   - 搜索结果缓存
   - 搜索建议与自动补全

## 扩展性设计

1. **微服务架构**：系统设计为可向微服务架构演进
2. **模块化设计**：功能模块化，便于扩展和维护
3. **API版本控制**：支持API版本控制，便于迭代升级
4. **配置中心**：使用配置中心管理系统配置，支持动态调整

## 开发与部署

### 开发环境

- JDK 11+
- Node.js 14+
- MySQL 8.0+
- Redis 6.0+
- Maven 3.6+
- npm 6.0+

### 部署步骤

1. **前端部署**
   ```bash
   # 安装依赖
   npm install
   
   # 构建生产环境代码
   npm run build
   
   # 将构建产物部署到Nginx或其他Web服务器
   ```

2. **后端部署**
   ```bash
   # 打包
   mvn clean package
   
   # 运行
   java -jar booksalehub-0.0.1-SNAPSHOT.jar
   ```

3. **Docker部署**（推荐）
   ```bash
   # 构建镜像
   docker-compose build
   
   # 启动服务
   docker-compose up -d
   ```

## 项目结构

### 前端项目结构

```
booksalehub-frontend/
├── public/                 # 静态资源
├── src/
│   ├── api/                # API请求
│   ├── assets/             # 资源文件
│   ├── components/         # 公共组件
│   ├── hooks/              # 自定义Hooks
│   ├── layouts/            # 布局组件
│   ├── pages/              # 页面组件
│   ├── redux/              # Redux状态管理
│   ├── routes/             # 路由配置
│   ├── utils/              # 工具函数
│   ├── App.js              # 应用入口
│   └── index.js            # 主入口
├── .env                    # 环境变量
├── package.json            # 项目依赖
└── README.md               # 项目说明
```

### 后端项目结构

```
booksalehub-backend/
├── src/
│   ├── main/
│   │   ├── java/com/booksalehub/
│   │   │   ├── config/           # 配置类
│   │   │   ├── controller/       # 控制器
│   │   │   ├── dto/              # 数据传输对象
│   │   │   ├── entity/           # 实体类
│   │   │   ├── exception/        # 异常处理
│   │   │   ├── mapper/           # MyBatis映射器
│   │   │   ├── service/          # 服务层
│   │   │   │   ├── impl/         # 服务实现
│   │   │   ├── util/             # 工具类
│   │   │   └── BookSaleHubApplication.java  # 应用入口
│   │   └── resources/
│   │       ├── mapper/           # MyBatis XML映射文件
│   │       ├── application.yml   # 应用配置
│   │       └── application-dev.yml  # 开发环境配置
│   └── test/                     # 测试代码
├── pom.xml                       # Maven配置
└── README.md                     # 项目说明
```

## 贡献指南

1. Fork本仓库
2. 创建特性分支 (`git checkout -b feature/amazing-feature`)
3. 提交更改 (`git commit -m 'Add some amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 创建Pull Request

## 许可证

本项目采用MIT许可证 - 详情见[LICENSE](LICENSE)文件

## 联系方式

- 项目维护者：[Your Name](mailto:your.email@example.com)
- 项目仓库：[GitHub Repository](https://github.com/yourusername/booksalehub)

---

© 2023 BookSaleHub. All Rights Reserved.