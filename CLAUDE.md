# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

Cosmos-Origin 是一个技术底座单体版项目，采用 Spring Boot 3.5.10 + PostgreSQL + JWT + MyBatis Flex 技术栈构建。项目主要功能包括用户登录、仪表盘、图库管理、站内搜索、知识库等模块。

## 项目架构

这是一个多模块 Maven 项目，采用分层架构设计：

### 核心模块结构

```
origin-springboot (父工程)
├── origin-framework (基础框架层)
│   ├── origin-common (通用工具组件)
│   └── origin-biz-operationlog-spring-boot-starter (接口日志组件)
├── origin-jwt (JWT 认证模块)
├── origin-auth (认证服务模块)
├── origin-admin (管理后台业务模块)
└── origin-web (Web 入口模块，启动类)
```

### 模块职责

- **origin-framework**: 平台基础设施层，封装通用功能和工具类，供业务模块使用
  - **origin-common**: 提供全局异常处理、统一返回结果封装、API 日志切面、自定义校验器等通用功能
  - **origin-biz-operationlog-spring-boot-starter**: 业务操作日志记录组件

- **origin-jwt**: JWT 认证模块，包含 JWT 过滤器、Token 生成/验证、登录成功/失败处理器、限流过滤器等

- **origin-auth**: 用户认证服务模块

- **origin-admin**: 管理后台业务模块，包含用户管理、角色管理等业务逻辑

- **origin-web**: 应用入口模块，包含启动类 `OriginWebApplication`、安全配置、Knife4j 配置等

### 关键架构模式

1. **统一响应结构**: 所有 API 通过 `Response` 类统一封装返回结果，分页查询使用 `PageResponse`

2. **全局异常处理**: 通过 `GlobalExceptionHandler` 统一处理业务异常 `BizException` 和系统异常

3. **API 日志记录**: 使用 `@ApiOperationLog` 注解 + AOP 切面记录 API 请求入参、出参和耗时，每个请求分配唯一 traceId

4. **安全认证**: 基于 Spring Security + JWT 实现认证授权
   - 登录流程通过 `JwtAuthenticationFilter` 处理
   - Token 验证通过 `TokenAuthenticationFilter` 处理
   - 支持登录失败限流、登录尝试次数记录

5. **数据访问层**: 使用 MyBatis Flex 作为 ORM 框架，DO (Data Object) 对象使用 Lombok 简化代码

6. **VO 模式**: 请求使用 ReqVO，响应使用 RspVO，严格区分业务层和展示层的数据结构

## 常用命令

### 构建和运行

```bash
# 编译整个项目
mvn clean install

# 编译并跳过测试
mvn clean install -DskipTests

# 运行应用（在 origin-web 目录下）
cd origin-web
mvn spring-boot:run

# 或者直接运行打包后的 jar
java -jar origin-web/target/origin-web-0.0.1-SNAPSHOT.jar

# 指定环境运行（dev/prod）
java -jar origin-web/target/origin-web-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

### 测试

```bash
# 运行所有测试
mvn test

# 运行特定模块的测试
mvn test -pl origin-admin

# 运行特定测试类
mvn test -Dtest=OriginWebApplicationTests
```

### 其他常用命令

```bash
# 查看依赖树
mvn dependency:tree

# 清理编译产物
mvn clean

# 只编译不打包
mvn compile
```

## 开发规范

### 代码组织

- **DO (Data Object)**: 数据库实体对象，位于 `domain.dos` 包下，继承 `BaseEntity`
- **Mapper**: MyBatis 接口，位于 `domain.mapper` 包下
- **Service**: 业务逻辑层，接口和实现分离
- **Controller**: 控制器层，负责接收请求和返回响应
- **VO**: 视图对象
  - ReqVO: 请求参数对象，位于 `model.vo.*` 包下，需要进行参数校验
  - RspVO: 响应结果对象，位于 `model.vo.*` 包下

### 命名规范

- Service 方法命名: `find*`, `add*`, `update*`, `delete*`
- VO 命名: `{操作}{实体}{Req/Rsp}VO`，例如 `AddUserReqVO`, `FindUserPageListRspVO`
- Controller 接口路径: 使用 RESTful 风格，例如 `/admin/user/*`

### 重要配置

- **应用入口**: `origin-web/src/main/java/com/cosmos/origin/web/OriginWebApplication.java`
- **组件扫描**: 必须扫描 `com.cosmos.origin.*` 包才能识别所有模块的组件
- **配置文件**:
  - `origin-web/src/main/resources/application.yml` (主配置)
  - `application-dev.yml` (开发环境)
  - `application-prod.yml` (生产环境)
- **API 文档**: 启动后访问 `http://localhost:8081/doc.html`
- **JWT 配置**: 在 `application.yml` 中配置 JWT 密钥、过期时间等

### 数据库

- 使用 PostgreSQL 42.7.8
- 连接池使用 HikariCP
- ORM 框架使用 MyBatis Flex 1.11.5
- 数据库连接配置在 `application-dev.yml` 中

### 安全相关

- 密码使用 `PasswordEncoder` 加密存储
- JWT Token 放在请求头 `Authorization: Bearer {token}`
- 登录接口默认为 `/login`，可通过 `JwtAuthenticationSecurityConfig` 自定义
- 支持登录失败限流和尝试次数记录

### API 开发流程

1. 在对应模块的 `model.vo` 包下创建 ReqVO 和 RspVO
2. 在 Service 接口中定义业务方法
3. 在 Service 实现类中实现业务逻辑
4. 在 Controller 中添加接口，使用 `@ApiOperationLog` 记录日志
5. 使用 Knife4j 注解完善 API 文档

### 日志记录

- 在 Controller 方法上添加 `@ApiOperationLog(description = "功能描述")` 自动记录 API 请求日志
- 日志会自动记录：请求入参、出参、耗时、请求类、请求方法
- 每个请求分配唯一 traceId 用于链路追踪
