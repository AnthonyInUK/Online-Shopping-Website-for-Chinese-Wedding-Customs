# 电商项目启动指南

## 环境要求 ✓
- Java 11+ ✓ (已安装: Java 11)
- Node.js 12+ ✓ (已安装: Node 18.20.8)
- Maven 3.6+ ✓ (已安装: Maven 3.9.9)
- MySQL 8.0+ ✓ (已安装: MySQL 9.2.0)

## 第一步：数据库设置

### 方法 1: 使用MySQL命令行（推荐）

```bash
# 1. 登录MySQL (需要root密码)
mysql -u root -p

# 2. 在MySQL命令行中执行以下命令：
CREATE USER IF NOT EXISTS 'ecommerceapp'@'localhost' IDENTIFIED BY 'ecommerceapp';
GRANT ALL PRIVILEGES ON *.* TO 'ecommerceapp'@'localhost';
ALTER USER 'ecommerceapp'@'localhost' IDENTIFIED WITH mysql_native_password BY 'ecommerceapp';
FLUSH PRIVILEGES;
EXIT;

# 3. 导入数据库脚本
cd /Users/anthony/Downloads/fullstack-angular-and-springboot-master/source-code/ecommerce-project-release-3.0/19-payment-processing-with-stripe-email-support

mysql -u ecommerceapp -pecommerceapp < 01-starter-files/db-scripts/02-create-products.sql
mysql -u ecommerceapp -pecommerceapp < 01-starter-files/db-scripts/03-countries-and-states.sql
mysql -u ecommerceapp -pecommerceapp < 01-starter-files/db-scripts/04-create-order-tables.sql
```

### 方法 2: 使用SQL文件

```bash
mysql -u root -p < complete-database-setup.sql
```

## 第二步：配置后端

### 配置文件说明
- 数据库配置：已配置
- Stripe API密钥：已包含测试密钥
- Okta认证：需要配置（可选）
- HTTPS：需要生成证书（或暂时禁用）

## 第三步：启动后端

```bash
cd 02-backend/spring-boot-ecommerce
mvn clean install
mvn spring-boot:run
```

后端将运行在：https://localhost:8443

## 第四步：启动前端

```bash
cd 03-frontend/angular-ecommerce
npm install
npm start
```

前端将运行在：https://localhost:4200

## 常见问题

### 1. 数据库连接失败
- 检查MySQL服务是否运行：`mysql --version`
- 验证用户和密码：`mysql -u ecommerceapp -pecommerceapp`

### 2. SSL证书问题
- 后端和前端都配置了HTTPS
- 开发环境可以忽略证书警告

### 3. Okta认证问题
- 如果没有Okta账户，部分功能（登录、订单历史）将无法使用
- 可以注册免费的Okta开发者账号：https://developer.okta.com/

## 下一步操作

当前状态：
- ✓ 环境检查完成
- ⏳ 等待数据库设置
- ⏳ 等待启动服务

