-- ================================
-- 完整数据库初始化脚本
-- 使用方法: mysql -u root -p < complete-database-setup.sql
-- ================================

-- 创建用户
CREATE USER IF NOT EXISTS 'ecommerceapp'@'localhost' IDENTIFIED BY 'ecommerceapp';
GRANT ALL PRIVILEGES ON *.* TO 'ecommerceapp'@'localhost';
ALTER USER 'ecommerceapp'@'localhost' IDENTIFIED WITH mysql_native_password BY 'ecommerceapp';
FLUSH PRIVILEGES;

SELECT '✓ 用户 ecommerceapp 创建成功' AS Status;

