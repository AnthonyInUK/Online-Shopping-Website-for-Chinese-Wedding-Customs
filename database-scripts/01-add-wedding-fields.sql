-- ================================
-- 添加婚庆用品相关字段
-- ================================

USE `full-stack-ecommerce`;

-- 给产品表添加新字段
ALTER TABLE product 
ADD COLUMN target_user VARCHAR(20) DEFAULT 'COMMON' COMMENT '目标用户: GROOM(新郎), BRIDE(新娘), COMMON(通用)',
ADD COLUMN is_required BOOLEAN DEFAULT false COMMENT '是否必买商品';

-- 创建索引提高查询效率
CREATE INDEX idx_target_user ON product(target_user);
CREATE INDEX idx_is_required ON product(is_required);

SELECT '✓ 婚庆用品字段添加成功' AS Status;

