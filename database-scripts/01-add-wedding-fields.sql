-- ================================
-- 添加婚庆用品相关字段
-- ================================

USE `full-stack-ecommerce`;

-- 检查字段是否存在，不存在才添加
SET @dbname = DATABASE();
SET @tablename = 'product';
SET @columnname1 = 'target_user';
SET @columnname2 = 'is_required';

SET @preparedStatement = (SELECT IF(
  (
    SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
    WHERE
      (table_name = @tablename)
      AND (table_schema = @dbname)
      AND (column_name = @columnname1)
  ) > 0,
  'SELECT 1',
  CONCAT('ALTER TABLE ', @tablename, ' ADD COLUMN ', @columnname1, ' VARCHAR(20) DEFAULT \'COMMON\' COMMENT \'目标用户: GROOM(新郎), BRIDE(新娘), COMMON(通用)\';')
));
PREPARE alterIfNotExists FROM @preparedStatement;
EXECUTE alterIfNotExists;
DEALLOCATE PREPARE alterIfNotExists;

SET @preparedStatement = (SELECT IF(
  (
    SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS
    WHERE
      (table_name = @tablename)
      AND (table_schema = @dbname)
      AND (column_name = @columnname2)
  ) > 0,
  'SELECT 1',
  CONCAT('ALTER TABLE ', @tablename, ' ADD COLUMN ', @columnname2, ' BOOLEAN DEFAULT false COMMENT \'是否必买商品\';')
));
PREPARE alterIfNotExists FROM @preparedStatement;
EXECUTE alterIfNotExists;
DEALLOCATE PREPARE alterIfNotExists;

-- 创建索引提高查询效率（如果不存在）
CREATE INDEX IF NOT EXISTS idx_target_user ON product(target_user);
CREATE INDEX IF NOT EXISTS idx_is_required ON product(is_required);

SELECT '✓ 婚庆用品字段添加成功' AS Status;

