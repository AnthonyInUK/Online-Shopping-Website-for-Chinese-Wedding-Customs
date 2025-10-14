-- ================================
-- 基于实际图片的婚庆用品数据（英文名称）
-- ================================

USE `full-stack-ecommerce`;

-- 清空现有产品数据
DELETE FROM order_item;
DELETE FROM product;

-- 重置自增ID
ALTER TABLE product AUTO_INCREMENT = 1;

-- 获取分类ID
SET @groom_suit = (SELECT id FROM product_category WHERE category_name = '新郎礼服');
SET @bride_dress = (SELECT id FROM product_category WHERE category_name = '新娘婚纱');
SET @ring_jewelry = (SELECT id FROM product_category WHERE category_name = '婚戒首饰');
SET @wedding_shoes = (SELECT id FROM product_category WHERE category_name = '婚鞋');
SET @decoration = (SELECT id FROM product_category WHERE category_name = '婚礼装饰');
SET @candy_cake = (SELECT id FROM product_category WHERE category_name = '喜糖喜饼');
SET @red_envelope = (SELECT id FROM product_category WHERE category_name = '红包礼金');
SET @wedding_supplies = (SELECT id FROM product_category WHERE category_name = '婚庆用品');

-- 插入基于实际图片的婚庆产品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES

-- 新娘相关产品 (BRIDE)
('BRIDE-001', 'Bridal Wedding Dress', 'Beautiful white wedding dress for brides, elegant and romantic design', 'assets/images/wedding/Brides.jpg', 1, 50, 8888.00, @bride_dress, 'BRIDE', true, NOW()),
('BRIDE-002', 'Bridal Wedding Shoes', 'Comfortable and elegant wedding shoes for bride', 'assets/images/wedding/Shoes.jpg', 1, 100, 688.00, @wedding_shoes, 'BRIDE', true, NOW()),
('BRIDE-003', 'Chinese Wedding Umbrella', 'Traditional red umbrella for Chinese wedding ceremony', 'assets/images/wedding/Chinese-wedding-Umbrella.jpg', 1, 80, 388.00, @wedding_supplies, 'BRIDE', true, NOW()),
('BRIDE-004', 'Bridal Tea Cup Set', 'Elegant tea ceremony cups for bride', 'assets/images/wedding/Tea-cup.jpg', 1, 100, 288.00, @wedding_supplies, 'BRIDE', true, NOW()),
('BRIDE-005', 'Wedding Decoration Set 5', 'Romantic wedding decoration package for bride side', 'assets/images/wedding/wedding5.jpeg', 1, 80, 1488.00, @decoration, 'BRIDE', false, NOW()),
('BRIDE-006', 'Wedding Decoration Set 6', 'Elegant wedding decoration package for bride side', 'assets/images/wedding/wedding6.jpeg', 1, 60, 1888.00, @decoration, 'BRIDE', false, NOW()),

-- 新郎相关产品 (GROOM)
('GROOM-001', 'Groom Wedding Suit', 'Premium wedding suit and attire set for groom', 'assets/images/wedding/Clothes.jpg', 1, 60, 3888.00, @groom_suit, 'GROOM', true, NOW()),
('GROOM-002', 'Groom Wedding Package 1', 'Essential wedding items for groom', 'assets/images/wedding/NewPeople1.jpg', 1, 100, 1888.00, @wedding_supplies, 'GROOM', true, NOW()),
('GROOM-003', 'Groom Chinese Style Set', 'Traditional Chinese wedding attire for groom', 'assets/images/wedding/ChineseStyle.jpg', 1, 50, 1888.00, @groom_suit, 'GROOM', false, NOW()),
('GROOM-004', 'Wedding Paper Cups Set', 'Special paper cups for groom side guests (100pcs)', 'assets/images/wedding/PaperCup.jpg', 1, 500, 88.00, @wedding_supplies, 'GROOM', true, NOW()),
('GROOM-005', 'Wedding Decoration Set 1', 'Basic wedding decoration for groom side', 'assets/images/wedding/wedding1.jpeg', 1, 100, 688.00, @decoration, 'GROOM', false, NOW()),
('GROOM-006', 'Wedding Decoration Set 2', 'Premium wedding decoration for groom side', 'assets/images/wedding/wedding2.jpeg', 1, 100, 888.00, @decoration, 'GROOM', false, NOW()),

-- 通用产品 (COMMON) - 双方都需要
('COMMON-001', 'Wedding Ceremony Set', 'Complete wedding ceremony decoration and supplies', 'assets/images/wedding/Ceremony.jpg', 1, 40, 2888.00, @wedding_supplies, 'COMMON', true, NOW()),
('COMMON-002', 'Chinese Wedding Atmosphere Set 1', 'Premium Chinese wedding atmosphere decoration', 'assets/images/wedding/ChineseWeddingAtmosphere1.jpg', 1, 30, 3888.00, @decoration, 'COMMON', true, NOW()),
('COMMON-003', 'Chinese Wedding Atmosphere Set 2', 'Elegant Chinese wedding atmosphere decoration', 'assets/images/wedding/ChineseWeddingAtmosphere2.jpg', 1, 30, 2888.00, @decoration, 'COMMON', false, NOW()),
('COMMON-004', 'Couple Wedding Package', 'Premium wedding package for couples', 'assets/images/wedding/NewPeople2.jpg', 1, 100, 2388.00, @wedding_supplies, 'COMMON', true, NOW()),
('COMMON-005', 'Wedding Seating Decoration', 'Beautiful decoration for couple seating area', 'assets/images/wedding/NewPeopleSitting.jpg', 1, 50, 888.00, @decoration, 'COMMON', false, NOW()),
('COMMON-006', 'Wedding Paper Cups Premium', 'Premium paper cups for wedding party (100pcs)', 'assets/images/wedding/PaperCup2.jpg', 1, 500, 128.00, @wedding_supplies, 'COMMON', true, NOW()),
('COMMON-007', 'Wedding Gifts Package', 'Assorted wedding gifts for guests', 'assets/images/wedding/WeddingGifts.jpg', 1, 200, 588.00, @candy_cake, 'COMMON', true, NOW()),
('COMMON-008', 'Wedding Shop Complete Package', 'Complete wedding supplies from our shop', 'assets/images/wedding/Shop.jpg', 1, 50, 4888.00, @wedding_supplies, 'COMMON', false, NOW()),
('COMMON-009', 'Wedding Decoration Set 3', 'Elegant wedding decoration package', 'assets/images/wedding/wedding3.jpeg', 1, 100, 1088.00, @decoration, 'COMMON', false, NOW()),
('COMMON-010', 'Wedding Decoration Set 4', 'Premium wedding decoration package', 'assets/images/wedding/wedding4.jpeg', 1, 80, 1288.00, @decoration, 'COMMON', false, NOW()),
('COMMON-011', 'Wedding Decoration Set 7', 'Ultimate wedding decoration package', 'assets/images/wedding/wedding7.jpeg', 1, 60, 2088.00, @decoration, 'COMMON', false, NOW()),
('COMMON-012', 'Wedding Animation Decor', 'Interactive animated wedding decoration', 'assets/images/wedding/wedding8.gif', 1, 40, 2888.00, @decoration, 'COMMON', false, NOW());

SELECT '✓ Wedding products created successfully based on actual images' AS Status;
SELECT COUNT(*) AS 'Total Products' FROM product;
SELECT target_user, COUNT(*) AS count FROM product GROUP BY target_user;

