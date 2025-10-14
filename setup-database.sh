#!/bin/bash

# 数据库初始化脚本
# 使用方法: ./setup-database.sh

echo "================================"
echo "电商项目数据库初始化"
echo "================================"
echo ""

DB_SCRIPTS_DIR="01-starter-files/db-scripts"

echo "请输入MySQL root密码以初始化数据库..."
echo ""

# 执行所有SQL脚本
mysql -u root -p << EOF
-- 创建用户
CREATE USER IF NOT EXISTS 'ecommerceapp'@'localhost' IDENTIFIED BY 'ecommerceapp';
GRANT ALL PRIVILEGES ON *.* TO 'ecommerceapp'@'localhost';
ALTER USER 'ecommerceapp'@'localhost' IDENTIFIED WITH mysql_native_password BY 'ecommerceapp';
FLUSH PRIVILEGES;

-- 提示
SELECT '✓ 用户创建成功' AS status;
EOF

if [ $? -eq 0 ]; then
    echo ""
    echo "✓ 步骤 1/4: 用户创建成功"
    echo ""
    
    # 使用新用户执行后续脚本
    mysql -u ecommerceapp -pecommerceapp < "$DB_SCRIPTS_DIR/02-create-products.sql"
    echo "✓ 步骤 2/4: 产品表和数据导入成功"
    
    mysql -u ecommerceapp -pecommerceapp < "$DB_SCRIPTS_DIR/03-countries-and-states.sql"
    echo "✓ 步骤 3/4: 国家和州数据导入成功"
    
    mysql -u ecommerceapp -pecommerceapp < "$DB_SCRIPTS_DIR/04-create-order-tables.sql"
    echo "✓ 步骤 4/4: 订单表创建成功"
    
    echo ""
    echo "================================"
    echo "✓ 数据库初始化完成！"
    echo "================================"
    echo ""
    echo "数据库信息："
    echo "  数据库名: full-stack-ecommerce"
    echo "  用户名: ecommerceapp"
    echo "  密码: ecommerceapp"
    echo "  主机: localhost:3306"
else
    echo ""
    echo "✗ 数据库初始化失败，请检查MySQL root密码是否正确"
    exit 1
fi

