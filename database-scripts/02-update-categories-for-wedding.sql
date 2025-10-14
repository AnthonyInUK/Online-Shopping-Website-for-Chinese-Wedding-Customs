-- ================================
-- 更新产品分类为婚庆用品分类
-- ================================

USE `full-stack-ecommerce`;

-- 先清空产品数据（因为外键约束）
DELETE FROM order_item;
DELETE FROM product;

-- 清空现有分类
DELETE FROM product_category;

-- 插入婚庆用品分类
INSERT INTO product_category (category_name) VALUES
('新郎礼服'),
('新娘婚纱'),
('婚戒首饰'),
('婚鞋'),
('婚礼装饰'),
('喜糖喜饼'),
('红包礼金'),
('婚庆用品');

SELECT '✓ 婚庆用品分类更新成功' AS Status;

