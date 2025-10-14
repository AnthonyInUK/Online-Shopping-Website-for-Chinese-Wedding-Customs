-- ================================
-- 婚庆用品示例数据（闽南特色）
-- ================================

USE `full-stack-ecommerce`;

-- 清空现有产品数据
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

-- 插入新郎礼服商品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES
('GROOM-001', '经典三件套西装', '意大利进口面料，修身剪裁，含西装外套、马甲、西裤', 'assets/images/wedding/groom-suit-01.png', 1, 100, 2888.00, @groom_suit, 'GROOM', true, NOW()),
('GROOM-002', '高级定制礼服', '纯手工定制，量身打造，含衬衫领带', 'assets/images/wedding/groom-suit-02.png', 1, 50, 5888.00, @groom_suit, 'GROOM', false, NOW()),
('GROOM-003', '中式唐装', '传统闽南刺绣，喜庆红色，龙凤图案', 'assets/images/wedding/groom-suit-03.png', 1, 80, 1888.00, @groom_suit, 'GROOM', false, NOW()),
('GROOM-004', '白色燕尾服', '经典欧式燕尾服，适合西式婚礼', 'assets/images/wedding/groom-suit-04.png', 1, 60, 3288.00, @groom_suit, 'GROOM', false, NOW());

-- 插入新娘婚纱商品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES
('BRIDE-001', '公主蓬蓬婚纱', '法式蕾丝，手工钉珠，拖尾设计', 'assets/images/wedding/bride-dress-01.png', 1, 100, 8888.00, @bride_dress, 'BRIDE', true, NOW()),
('BRIDE-002', '鱼尾修身婚纱', '显身材曲线，性感优雅，珍珠装饰', 'assets/images/wedding/bride-dress-02.png', 1, 80, 6888.00, @bride_dress, 'BRIDE', false, NOW()),
('BRIDE-003', '中式凤冠霞帔', '传统闽南婚服，手工刺绣，含凤冠', 'assets/images/wedding/bride-dress-03.png', 1, 50, 4888.00, @bride_dress, 'BRIDE', false, NOW()),
('BRIDE-004', '轻婚纱（敬酒服）', '短款设计，方便行动，适合敬酒环节', 'assets/images/wedding/bride-dress-04.png', 1, 100, 2888.00, @bride_dress, 'BRIDE', true, NOW());

-- 插入婚戒首饰商品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES
('RING-001', '18K金对戒', '经典素圈对戒，可刻字，一对价格', 'assets/images/wedding/ring-01.png', 1, 200, 6888.00, @ring_jewelry, 'COMMON', true, NOW()),
('RING-002', '钻石婚戒', '50分钻石，铂金戒托，GIA证书', 'assets/images/wedding/ring-02.png', 1, 100, 15888.00, @ring_jewelry, 'BRIDE', true, NOW()),
('RING-003', '黄金龙凤镯', '传统闽南婚嫁黄金手镯一对', 'assets/images/wedding/bracelet-01.png', 1, 150, 8888.00, @ring_jewelry, 'BRIDE', false, NOW()),
('RING-004', '珍珠项链套装', '天然淡水珍珠，含项链耳环', 'assets/images/wedding/necklace-01.png', 1, 100, 2888.00, @ring_jewelry, 'BRIDE', false, NOW());

-- 插入婚鞋商品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES
('SHOES-001', '新娘红色绣花鞋', '传统闽南绣花鞋，龙凤图案，舒适防滑', 'assets/images/wedding/shoes-bride-01.png', 1, 200, 288.00, @wedding_shoes, 'BRIDE', true, NOW()),
('SHOES-002', '水晶高跟婚鞋', '银色水钻高跟鞋，搭配婚纱', 'assets/images/wedding/shoes-bride-02.png', 1, 150, 588.00, @wedding_shoes, 'BRIDE', false, NOW()),
('SHOES-003', '新郎皮鞋', '英伦风牛津鞋，搭配西装', 'assets/images/wedding/shoes-groom-01.png', 1, 150, 688.00, @wedding_shoes, 'GROOM', true, NOW()),
('SHOES-004', '中式布鞋', '传统千层底布鞋，搭配唐装', 'assets/images/wedding/shoes-groom-02.png', 1, 100, 388.00, @wedding_shoes, 'GROOM', false, NOW());

-- 插入婚礼装饰商品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES
('DECO-001', '婚房布置套装', '含拉花、气球、喜字贴、床上用品', 'assets/images/wedding/decoration-01.png', 1, 300, 588.00, @decoration, 'COMMON', true, NOW()),
('DECO-002', '婚礼路引花', '仿真玫瑰路引，10个装', 'assets/images/wedding/decoration-02.png', 1, 200, 388.00, @decoration, 'COMMON', false, NOW()),
('DECO-003', '喜字贴纸大礼包', '各种尺寸喜字贴，50张装', 'assets/images/wedding/decoration-03.png', 1, 500, 88.00, @decoration, 'COMMON', true, NOW()),
('DECO-004', '婚礼签到台布置', '含签到本、签到笔、装饰花', 'assets/images/wedding/decoration-04.png', 1, 150, 288.00, @decoration, 'COMMON', false, NOW());

-- 插入喜糖喜饼商品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES
('CANDY-001', '闽南传统喜饼礼盒', '传统糕饼，6种口味，10盒装', 'assets/images/wedding/candy-01.png', 1, 500, 288.00, @candy_cake, 'COMMON', true, NOW()),
('CANDY-002', '喜糖礼盒装', '进口糖果，精美包装，20盒装', 'assets/images/wedding/candy-02.png', 1, 400, 388.00, @candy_cake, 'COMMON', true, NOW()),
('CANDY-003', '麻糬礼盒', '台湾风味麻糬，多种口味', 'assets/images/wedding/candy-03.png', 1, 300, 188.00, @candy_cake, 'COMMON', false, NOW()),
('CANDY-004', '喜糖袋子', '红色喜糖袋，100个装', 'assets/images/wedding/candy-04.png', 1, 600, 88.00, @candy_cake, 'COMMON', false, NOW());

-- 插入红包礼金商品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES
('ENV-001', '婚礼红包袋', '高档烫金红包，50个装', 'assets/images/wedding/envelope-01.png', 1, 800, 68.00, @red_envelope, 'COMMON', true, NOW()),
('ENV-002', '闽南特色红包', '传统龙凤图案，100个装', 'assets/images/wedding/envelope-02.png', 1, 600, 88.00, @red_envelope, 'COMMON', false, NOW()),
('ENV-003', '礼金记账本', '精美记账本，记录宾客礼金', 'assets/images/wedding/guestbook-01.png', 1, 400, 38.00, @red_envelope, 'COMMON', true, NOW());

-- 插入其他婚庆用品
INSERT INTO product (sku, name, description, image_url, active, units_in_stock, unit_price, category_id, target_user, is_required, date_created) VALUES
('SUPPLY-001', '手捧花', '玫瑰仿真手捧花，新娘用', 'assets/images/wedding/bouquet-01.png', 1, 200, 288.00, @wedding_supplies, 'BRIDE', true, NOW()),
('SUPPLY-002', '胸花套装', '新郎新娘胸花，伴郎伴娘胸花', 'assets/images/wedding/corsage-01.png', 1, 300, 188.00, @wedding_supplies, 'COMMON', false, NOW()),
('SUPPLY-003', '喜烟', '高档香烟，10条装', 'assets/images/wedding/cigarette-01.png', 1, 500, 1888.00, @wedding_supplies, 'GROOM', false, NOW()),
('SUPPLY-004', '喜酒', '品牌白酒，10瓶装', 'assets/images/wedding/wine-01.png', 1, 400, 888.00, @wedding_supplies, 'COMMON', false, NOW()),
('SUPPLY-005', '婚车装饰套装', '含车头花、拉花、气球', 'assets/images/wedding/car-decoration-01.png', 1, 250, 388.00, @wedding_supplies, 'COMMON', true, NOW());

SELECT '✓ 婚庆用品数据插入成功' AS Status;
SELECT COUNT(*) AS '总商品数' FROM product;

