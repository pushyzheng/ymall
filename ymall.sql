/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : ymall

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 27/07/2019 11:21:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `street_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_default` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `tb_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (12, 1, 'Pushy', '18182818288218', '北京东城区', 0);
INSERT INTO `tb_address` VALUES (13, 2, '11', '11', '11', 1);

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item`  (
  `id` bigint(20) NOT NULL COMMENT '商品id，同时也是商品编号',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品卖点',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品价格',
  `num` int(11) NULL DEFAULT NULL COMMENT '库存数量',
  `limit_num` int(11) NULL DEFAULT NULL COMMENT '售卖数量限制',
  `image` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `cid` bigint(11) NULL DEFAULT NULL COMMENT '所属分类',
  `status` int(1) NULL DEFAULT 1 COMMENT '商品状态 1正常 0下架',
  `version` int(11) NULL DEFAULT 0 COMMENT '乐观锁，版本号',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `cid`(`cid`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `updated`(`updated`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES (220001, 'OnePlus 7 Pro', 'OnePlus 在全球 13 个国家 38 个城市举办了 OnePlus 7 系列 Pop-up 活动，喜爱 OnePlus 的“加油”又一次排起了长队', 4499.00, -2, 100, 'https://image01.oneplus.cn/ebp/201905/13/512/ec60817128756f357b1662293d50ceed_840_840.png,https://image01.oneplus.cn/ebp/201905/13/365/18dd0754b7caa7ba4d8cbaad7ed14350_840_840.png,https://image01.oneplus.cn/ebp/201905/13/138/17fa833960927e99ed61efd06c4d008b_840_840.png,https://image01.oneplus.cn/ebp/201905/13/297/e3b9a4edbe47170171dace25d5c47e44_840_840.png', 560, 1, 62, NULL, '2019-07-24 15:28:03');
INSERT INTO `tb_item` VALUES (220002, 'OnePlus 7 硅胶保护壳 红色', '面面俱到的保护，一如既往的轻薄。', 129.00, 999, 100, 'https://image01.oneplus.cn/ebp/201905/02/1593/92d9347e1e1138a63339af2116626882_384_384.png', 560, 1, 0, '2019-07-19 16:18:47', '2019-07-19 21:54:42');
INSERT INTO `tb_item` VALUES (655552, '小米AI音箱', '小米AI音箱 / 能播音乐 / 讲故事 / 听相声 / 查路况 / 设闹钟 / 还能控制智能设备', 229.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1522666970.27937468.jpg', 560, 1, 0, '2019-07-19 15:41:01', '2019-07-19 21:54:41');
INSERT INTO `tb_item` VALUES (660093, '小米CC 9e', '3200万+4800万 前后双旗舰相机', 1299.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1562074170.08396741.jpg', 560, 1, 0, '2019-07-19 15:41:03', '2019-07-19 21:54:41');
INSERT INTO `tb_item` VALUES (660094, '小米9 战斗天使\r\n好看又能打', '骁龙855，索尼4800万超广角微距三摄', 2799.00, 999, 2, 'https://i1.mifile.cn/a1/pms_1550642182.7527088!220x220.jpg', 560, 1, 0, '2019-07-19 15:41:15', '2019-07-19 21:54:40');
INSERT INTO `tb_item` VALUES (660095, '小米mix 3', 'DxOMark拍照108分 / 磁动力滑盖全面屏 / 四曲面陶瓷机身 / 骁龙845旗舰处理器 / 包装盒内附赠10W无线充电器\r\n\r\n', 2999.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1540429657.8272281.jpg,https://i8.mifile.cn/a1/pms_1540429657.72395987.jpg,https://i8.mifile.cn/a1/pms_1540429657.72678126.jpg,https://i8.mifile.cn/a1/pms_1540429657.72451540.jpg,https://i8.mifile.cn/a1/pms_1540429657.8272281.jpg', 560, 1, 4, '2019-07-19 15:55:32', '2019-07-24 14:50:16');
INSERT INTO `tb_item` VALUES (660096, '小米手环4', '全新 AMOLED 大屏彩显，支持77种个性主题。拥有20天的超长续航，能用支付宝抬手支付。50米防水，支持游泳模式。还能看微信、看来电、测心率、测睡眠。带上它，你的改变从今天开始。', 229.00, 998, 10, 'https://i8.mifile.cn/a1/pms_1560238127.29931894.png,https://i8.mifile.cn/a1/pms_1560238127.27742928.png,https://i8.mifile.cn/a1/pms_1560238127.29976628.png,https://i8.mifile.cn/a1/pms_1560238127.27717435.png', 560, 1, 5, '2019-07-19 16:02:16', '2019-07-24 14:49:53');
INSERT INTO `tb_item` VALUES (660097, '小米游戏本15.6\"', '第八代酷睿处理器 ／ 72%NTSC高色域窄边框全高清屏 ／ 256G PCIe高速固态 ／ 双烤不限频不降频\r\n\r\n', 7799.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1557312623.98719913.jpg,https://i8.mifile.cn/a1/pms_1557312622.10117640.jpg,https://i8.mifile.cn/a1/pms_1557312619.91098890.jpg', 560, 1, 0, '2019-07-19 16:25:29', '2019-07-19 21:54:38');
INSERT INTO `tb_item` VALUES (660098, 'Redmi K20', '索尼4800万超广角三摄 / 骁龙730处理器 / 前置2000万升降式相机 / 6.39\"AMOLED全面屏 / 4000mAh超长续航 / 第七代屏下指纹解锁 / 多功能NFC\r\n\r\n', 2099.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1558854907.65258291.jpg,https://i8.mifile.cn/a1/pms_1558854907.59854953.jpg,https://i8.mifile.cn/a1/pms_1558854907.5283799.jpg,https://i8.mifile.cn/a1/pms_1558854907.55114811.jpg', 560, 1, 2, NULL, '2019-07-24 14:50:12');
INSERT INTO `tb_item` VALUES (660099, '小米AI音箱', '小米AI音箱 / 能播音乐 / 讲故事 / 听相声 / 查路况 / 设闹钟 / 还能控制智能设备', 229.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1522666970.27937468.jpg', 560, 1, 0, NULL, '2019-07-19 21:54:32');
INSERT INTO `tb_item` VALUES (660100, '小米9 ARE U OK保护壳', '彰显独特个性 / 轻薄无负担 / 优选PC材料 / 贴合机身 / 潮流五色\r\n\r\n适用于 小米9', 39.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1557028966.95654878.jpg', 560, 1, 0, NULL, '2019-07-19 21:54:32');
INSERT INTO `tb_item` VALUES (660101, '飞天猪米兔', '猪年吉祥 ／ 萌态设计 ／ 亲肤材质 ／ 精致细节', 49.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1546508755.29828342.jpg,https://i8.mifile.cn/a1/pms_1546508755.31573268.jpg,https://i8.mifile.cn/a1/pms_1546508755.41043184.jpg', 560, 1, 0, NULL, '2019-07-19 21:54:32');
INSERT INTO `tb_item` VALUES (660102, '贝医生巴氏牙刷 四色装', '赠精美旅行盒 / 德日进口刷毛 / 专利科学布局 / 全身食品级材质', 39.90, 999, 100, 'https://i8.mifile.cn/a1/pms_1502258923.87257653.jpg', 560, 1, 0, '2019-07-19 16:53:54', '2019-07-19 21:54:31');
INSERT INTO `tb_item` VALUES (660103, '小米小背包', '4 级防泼水 / 10L容量 / 轻盈背负 / YKK 拉链', 29.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1539682895.62231103.png,https://i1.mifile.cn/a1/pms_1539682803.29879353!482x482.png,https://i8.mifile.cn/a1/pms_1539682900.94934206.png', NULL, 1, 0, NULL, '2019-07-19 21:54:31');
INSERT INTO `tb_item` VALUES (660104, '小米米家照片打印机', '细腻还原真实色彩 / 自动覆膜长久保存 / 多尺寸证件照打印 / 多种无线快连 / 可远程打印', 498.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1545303051.38414485.jpg,https://i8.mifile.cn/a1/pms_1545120248.57621875.jpg,https://i8.mifile.cn/a1/pms_1545120248.45618295.jpg', 560, 1, 0, '2019-07-19 16:58:51', '2019-07-19 21:54:30');
INSERT INTO `tb_item` VALUES (660105, '小米小爱触屏音箱', '智能家居，看得见，触得到 / 视频也可定制专属闹钟 / 连接门铃和摄像头，监控更方便 / 爱奇艺海量影视 / QQ音乐曲库', 249.00, 999, 100, 'https://i8.mifile.cn/a1/pms_1550719656.70567332.jpg', 560, 1, 0, NULL, '2019-07-19 21:54:25');
INSERT INTO `tb_item` VALUES (700001, 'Reno 6G+256G 珊瑚橙', '【直降200 | 到手2799 | 限时12期免息 | 赠屏碎保】全景屏，4800万超清像素、支持NFC、VOOC 3.0。Reno Z热卖中 >>>\r\n', 2799.00, 999, 100, 'https://dsfs.oppo.com/archives/201905/201905291105315cedf773cdf73.png,https://dsfs.oppo.com/archives/201905/201905291105585cedf78eca11f.png', NULL, 1, 0, NULL, '2019-07-19 21:54:25');
INSERT INTO `tb_item` VALUES (859669, 'Smartisan 帆布鞋', '一双踏实、舒适的帆布鞋\r\n', 199.00, 999, 100, 'https://resource.smartisan.com/resource/2f9a0f5f3dedf0ed813622003f1b287b.jpg', NULL, 1, 0, NULL, '2019-07-19 21:54:24');
INSERT INTO `tb_item` VALUES (859876, 'RedmiBook14 高性能独显轻薄本', '搭载全新第八代英特尔® 酷睿™ i7处理器、 GeForce® MX250 独立显卡', 3999.00, 999, 1000, 'https://i8.mifile.cn/a1/pms_1559024536.53513407.jpg,https://i8.mifile.cn/a1/pms_1559024536.74789819.jpg', 560, 1, 0, '2019-07-19 15:41:17', '2019-07-19 21:54:23');

-- ----------------------------
-- Table structure for tb_item_desc
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_desc`;
CREATE TABLE `tb_item_desc`  (
  `item_id` bigint(20) NOT NULL COMMENT '商品ID',
  `item_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述',
  `created` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品描述表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_item_desc
-- ----------------------------
INSERT INTO `tb_item_desc` VALUES (220001, '<img src=\"https://image01.oneplus.cn/shop/201907/11/280/8db89788ece54a55571358172f000094.jpg\" alt=\"\" style=\"width:1220px\" /><img src=\"https://image01.oneplus.cn/shop/201907/11/1914/8c4a6bff9fbc985881bba9e2121c4252.jpg\" alt=\"\" style=\"width:1220px\" /><img src=\"https://image01.oneplus.cn/shop/201907/11/410/672aea4bf0c2db82e52652fba03de5b2.jpg\" alt=\"\" style=\"width:1220px\" /><img src=\"https://image01.oneplus.cn/shop/201907/15/288/5710b5364f0aa91b622cc793fe9a5335.jpg\" alt=\"\" style=\"width:1220px\" /><img src=\"https://image01.oneplus.cn/shop/201907/11/931/c6f30c4f204fdbae8a3314d117e73d54.jpg\" alt=\"\" style=\"width:1220px\" />', '2019-07-19 16:16:23', '2019-07-19 16:16:23');
INSERT INTO `tb_item_desc` VALUES (220002, NULL, NULL, NULL);
INSERT INTO `tb_item_desc` VALUES (655552, NULL, '2019-07-19 14:13:36', '2019-07-19 14:13:39');
INSERT INTO `tb_item_desc` VALUES (660093, NULL, '2019-07-19 15:47:32', '2019-07-19 15:47:32');
INSERT INTO `tb_item_desc` VALUES (660094, '<img src=\"https://i.loli.net/2019/07/19/5d312f0d3041157981.png\" style=\"width:1220px\" alt=\"\" />', '2019-07-19 21:45:49', '2019-07-19 21:45:49');
INSERT INTO `tb_item_desc` VALUES (660095, NULL, '2019-07-19 15:57:50', '2019-07-19 15:57:50');
INSERT INTO `tb_item_desc` VALUES (660096, NULL, NULL, NULL);
INSERT INTO `tb_item_desc` VALUES (660097, '<img src=\"https://i.loli.net/2019/07/19/5d317ffc6290867435.png\" style=\"width:1220px\" alt=\"\" />\r\n<img src=\"https://i.loli.net/2019/07/19/5d3180645774c19037.png\" style=\"width:1220px\" alt=\"\" />\r\n<img src=\"https://i.loli.net/2019/07/19/5d31807c272d755261.png\" style=\"width:1220px\" alt=\"\" />', '2019-07-19 16:34:10', '2019-07-19 16:34:10');
INSERT INTO `tb_item_desc` VALUES (660098, '<img src=\"https://i.loli.net/2019/07/19/5d3181d23331b23793.png\" style=\"width:1220px\" alt=\"\" />', '2019-07-19 16:42:15', '2019-07-19 16:42:15');
INSERT INTO `tb_item_desc` VALUES (660099, NULL, NULL, NULL);
INSERT INTO `tb_item_desc` VALUES (660101, NULL, NULL, NULL);
INSERT INTO `tb_item_desc` VALUES (660102, NULL, NULL, NULL);
INSERT INTO `tb_item_desc` VALUES (660103, NULL, NULL, NULL);
INSERT INTO `tb_item_desc` VALUES (660104, '<img src=\"https://c1.mifile.cn/f/i/16/chain/photoprinter/photoprinter-3.jpg\" style=\"width:1220px\" alt=\"\" />', '2019-07-19 17:01:53', '2019-07-19 17:01:53');
INSERT INTO `tb_item_desc` VALUES (660105, '<img src=\"https://i1.mifile.cn/f/i/16/chain/aispeaker-touch/aispeaker-touch-06.jpg\" style=\"width:1220px\" alt=\"\" /><img src=\"https://i1.mifile.cn/f/i/16/chain/aispeaker-touch/aispeaker-touch-08.jpg\" style=\"width:1220px\" alt=\"\" />', '2019-07-19 17:03:45', '2019-07-19 17:03:45');
INSERT INTO `tb_item_desc` VALUES (700001, NULL, NULL, NULL);
INSERT INTO `tb_item_desc` VALUES (859669, NULL, NULL, NULL);
INSERT INTO `tb_item_desc` VALUES (859876, NULL, '2019-07-19 15:47:34', '2019-07-19 15:47:35');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '订单id',
  `payment` decimal(10, 2) NULL DEFAULT NULL COMMENT '实付金额',
  `payment_type` int(1) NULL DEFAULT NULL COMMENT '支付类型 1在线支付 2货到付款',
  `post_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '邮费',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态 0未付款 1已付款 2未发货 3已发货 4交易成功 5交易关闭 6交易失败',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '订单更新时间',
  `payment_time` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime(0) NULL DEFAULT NULL COMMENT '交易关闭时间',
  `shipping_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '物流单号',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `buyer_message` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '买家留言',
  `buyer_nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '买家昵称',
  `buyer_comment` tinyint(1) NULL DEFAULT NULL COMMENT '买家是否已经评价',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `create_time`(`create_time`) USING BTREE,
  INDEX `buyer_nick`(`buyer_nick`) USING BTREE,
  INDEX `status`(`status`) USING BTREE,
  INDEX `payment_type`(`payment_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('156395328351640', NULL, NULL, NULL, 5, '2019-07-24 07:28:04', '2019-07-24 07:28:04', NULL, NULL, NULL, '2019-07-27 01:34:29', NULL, NULL, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '商品id',
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `num` int(10) NULL DEFAULT NULL COMMENT '商品购买数量',
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品标题',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `total_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总金额',
  `pic_path` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '商品图片地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `item_id`(`item_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  CONSTRAINT `tb_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`order_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------
INSERT INTO `tb_order_item` VALUES ('156395328352571', '220001', '156395328351640', 1, 'OnePlus 7 Pro', 1.00, 1.00, 'https://image01.oneplus.cn/ebp/201905/13/512/ec60817128756f357b1662293d50ceed_840_840.png');

-- ----------------------------
-- Table structure for tb_order_shipping
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_shipping`;
CREATE TABLE `tb_order_shipping`  (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `receiver_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人全名',
  `receiver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `receiver_mobile` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '移动电话',
  `receiver_state` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  `receiver_zip` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码,如：310001',
  `created` datetime(0) NULL DEFAULT NULL,
  `updated` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_shipping
-- ----------------------------
INSERT INTO `tb_order_shipping` VALUES ('156395118391434', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:53:04', '2019-07-24 06:53:04');
INSERT INTO `tb_order_shipping` VALUES ('156395135685414', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:55:57', '2019-07-24 06:55:57');
INSERT INTO `tb_order_shipping` VALUES ('156395148055460', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:58:01', '2019-07-24 06:58:01');
INSERT INTO `tb_order_shipping` VALUES ('156395159603215', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:59:56', '2019-07-24 06:59:56');
INSERT INTO `tb_order_shipping` VALUES ('156395159613332', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:59:56', '2019-07-24 06:59:56');
INSERT INTO `tb_order_shipping` VALUES ('156395159616532', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:59:56', '2019-07-24 06:59:56');
INSERT INTO `tb_order_shipping` VALUES ('156395159619230', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:59:56', '2019-07-24 06:59:56');
INSERT INTO `tb_order_shipping` VALUES ('156395159621988', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:59:56', '2019-07-24 06:59:56');
INSERT INTO `tb_order_shipping` VALUES ('156395159624553', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:59:56', '2019-07-24 06:59:56');
INSERT INTO `tb_order_shipping` VALUES ('156395159627673', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 06:59:56', '2019-07-24 06:59:56');
INSERT INTO `tb_order_shipping` VALUES ('156395163053888', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163053892', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163053953', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163055886', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163057526', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163059288', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163060429', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163062914', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163063929', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163068250', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163068285', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163071212', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163071230', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163072620', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163072695', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163076014', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163076561', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163078335', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163080072', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163080277', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163081755', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163081927', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163082037', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163083088', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163084485', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163086060', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163086112', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163089439', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163089496', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163089690', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163090236', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163091600', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163091649', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163093510', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163093669', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163093937', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163094050', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163096521', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163096651', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163097414', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163097462', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163097622', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163097883', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163099586', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163099749', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163100266', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163104316', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163105138', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163105294', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163105320', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163105431', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163105461', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163105540', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163106401', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163107753', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163108704', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163109305', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163110843', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163111842', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163112356', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163113641', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163113802', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163115466', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163115685', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163116318', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395163116876', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:00:31', '2019-07-24 07:00:31');
INSERT INTO `tb_order_shipping` VALUES ('156395174263406', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174264589', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174264696', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174264789', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174264882', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174265278', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174265390', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174265478', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174265543', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174265943', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174266698', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174266857', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174266896', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174267420', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174267432', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174267475', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174268009', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174268476', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174269786', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174270039', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174270369', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174270625', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174270928', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174271652', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174273032', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174273176', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174273251', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174273827', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174275550', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174276234', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174276247', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174276543', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174276631', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174277276', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174278518', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174279362', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174279830', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174279870', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174280518', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174280584', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174282277', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174282398', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174282738', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174283456', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174284401', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174284560', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174284791', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174284825', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174286982', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174287089', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174288322', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174289221', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174289606', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174291157', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174291840', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174291868', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174292362', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174292368', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174293002', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174296032', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174296237', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174296764', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174298728', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174299464', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174299742', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174300091', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174300176', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395174300290', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:02:23', '2019-07-24 07:02:23');
INSERT INTO `tb_order_shipping` VALUES ('156395190169896', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190170303', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190170878', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190171806', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190172752', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190173774', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190174754', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190174762', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190174801', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190174891', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190175753', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190176404', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190177862', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190177934', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190179081', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190179900', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190179933', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190180684', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190180843', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190180907', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190180952', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190181135', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190181698', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190181705', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190183018', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190184500', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190184601', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190184844', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190185487', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190186977', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190187305', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190187658', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190187722', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190188911', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190189029', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190189102', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190190187', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190194723', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190195001', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190195169', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190195608', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190198657', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190198987', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190202077', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190202292', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190202867', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190203565', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190205092', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190206762', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190208580', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190210243', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190211532', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190211606', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190211772', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190212045', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190212263', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190213072', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190213746', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190215635', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190215754', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190220073', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190220740', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190221027', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190222206', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190223278', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190223463', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190223875', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190225359', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190225528', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190226775', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190226901', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190226950', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190227950', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190228358', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190228401', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190229152', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190229256', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190230504', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190230547', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190230553', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190232829', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190233490', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190234142', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190234610', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190234653', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190234677', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190234783', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395190234851', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:05:02', '2019-07-24 07:05:02');
INSERT INTO `tb_order_shipping` VALUES ('156395204263401', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204266946', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204269147', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204269280', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204270161', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204271238', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204272861', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204273529', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204275769', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395204277636', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:07:23', '2019-07-24 07:07:23');
INSERT INTO `tb_order_shipping` VALUES ('156395316918142', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:26:09', '2019-07-24 07:26:09');
INSERT INTO `tb_order_shipping` VALUES ('156395328351640', 'Pushy', '18182818288218', NULL, NULL, NULL, NULL, '北京东城区', NULL, '2019-07-24 07:28:04', '2019-07-24 07:28:04');

-- ----------------------------
-- Table structure for tb_panel
-- ----------------------------
DROP TABLE IF EXISTS `tb_panel`;
CREATE TABLE `tb_panel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '板块名称',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型 0轮播图 1板块种类一 2板块种类二 3板块种类三 ',
  `sort_order` int(4) NULL DEFAULT NULL COMMENT '排列序号',
  `position` int(1) NULL DEFAULT NULL COMMENT '所属位置 0首页 1商品推荐 2我要捐赠',
  `limit_num` int(4) NULL DEFAULT NULL COMMENT '板块限制商品数量',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`status`) USING BTREE,
  INDEX `sort_order`(`sort_order`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '内容分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_panel
-- ----------------------------
INSERT INTO `tb_panel` VALUES (1, '热门商品', 2, 2, 0, 3, 1, '', '2018-04-18 23:49:13', '2018-04-15 19:05:16');
INSERT INTO `tb_panel` VALUES (2, '官方精选', 3, 3, 0, 8, 1, '', NULL, '2018-04-19 11:20:59');
INSERT INTO `tb_panel` VALUES (3, '品牌精选', 3, 5, 0, 7, 1, '', '2018-04-18 23:49:19', '2018-04-17 18:54:15');
INSERT INTO `tb_panel` VALUES (4, '我要捐赠', 2, 3, 2, 2, 1, '', '2017-09-23 15:20:31', '2017-11-06 13:17:04');
INSERT INTO `tb_panel` VALUES (6, '为您推荐', 2, 6, 1, 2, 1, '', '2017-11-06 13:17:41', '2017-11-06 13:17:41');
INSERT INTO `tb_panel` VALUES (7, '轮播图', 0, 0, 0, 5, 1, '', '2018-04-15 12:33:07', '2018-04-15 12:33:07');
INSERT INTO `tb_panel` VALUES (8, '活动版块', 1, 1, 0, 4, 1, '', '2018-04-15 19:05:00', '2018-04-15 19:05:00');
INSERT INTO `tb_panel` VALUES (9, '活动版块2', 1, 7, 0, 4, 1, '', NULL, '2018-04-19 11:57:47');
INSERT INTO `tb_panel` VALUES (10, '品牌周边', 3, 4, 0, 7, 1, NULL, '2018-04-18 23:50:32', '2018-04-18 23:50:35');

-- ----------------------------
-- Table structure for tb_panel_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_panel_content`;
CREATE TABLE `tb_panel_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `panel_id` int(11) NOT NULL COMMENT '所属板块id',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型 0关联商品 1其他链接',
  `product_id` bigint(20) NULL DEFAULT NULL COMMENT '关联商品id',
  `sort_order` int(4) NULL DEFAULT NULL,
  `full_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其他链接',
  `pic_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pic_url2` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '3d轮播图备用',
  `pic_url3` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '3d轮播图备用',
  `created` datetime(0) NULL DEFAULT NULL,
  `updated` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`panel_id`) USING BTREE,
  INDEX `updated`(`updated`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `tb_panel_content_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tb_item` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_panel_content
-- ----------------------------
INSERT INTO `tb_panel_content` VALUES (11, 1, 0, 660095, 1, 'https://resource.smartisan.com/resource/b899d9b82c4bc2710492a26af021d484.jpg', 'https://resource.smartisan.com/resource/b899d9b82c4bc2710492a26af021d484.jpg', 'https://i8.mifile.cn/a1/pms_1540429657.8272281.jpg', 'https://i8.mifile.cn/a1/pms_1540429657.8272281.jpg', '2019-07-19 16:00:57', '2019-07-19 16:00:59');
INSERT INTO `tb_panel_content` VALUES (12, 1, 0, 660096, 2, NULL, 'https://i8.mifile.cn/a1/pms_1560238127.29931894.png', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (21, 2, 2, 220001, 1, NULL, 'https://image01.oneplus.cn/shop/201907/11/280/8db89788ece54a55571358172f000094.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (22, 2, 0, 220002, 2, NULL, 'https://image01.oneplus.cn/ebp/201905/02/1593/92d9347e1e1138a63339af2116626882_384_384.png', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (23, 2, 0, 660097, 3, NULL, 'https://i8.mifile.cn/a1/pms_1557312623.98719913.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (24, 2, 0, 700001, 4, NULL, 'https://dsfs.oppo.com/archives/201905/201905291105315cedf773cdf73.png', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (25, 2, 0, 660098, 5, NULL, 'https://i8.mifile.cn/a1/pms_1558854907.65258291.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (26, 2, 0, 660099, 6, NULL, 'https://i8.mifile.cn/a1/pms_1522666970.27937468.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (27, 2, 0, 660100, 7, NULL, 'https://i8.mifile.cn/a1/pms_1557028966.95654878.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (31, 3, 2, 660104, 1, NULL, 'https://i1.mifile.cn/a4/xmad_15603259571867_NGpLV.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (32, 3, 0, 660103, 2, NULL, 'https://i8.mifile.cn/a1/pms_1539682895.62231103.png', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (33, 3, 0, 660105, 3, NULL, 'https://i8.mifile.cn/a1/pms_1550719656.70567332.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (55, 7, 0, 859876, 3, NULL, 'https://i1.mifile.cn/a4/xmad_15613002137481_tCEzx.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (56, 7, 0, 660094, 2, NULL, 'https://i.loli.net/2019/07/19/5d31305c0f1b252923.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (71, 7, 0, 660093, 1, NULL, 'https://i1.mifile.cn/a4/xmad_15632717871994_bYqIF.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (101, 10, 2, 859669, 1, NULL, 'https://resource.smartisan.com/resource/z/zhoubianshangpin1220858web.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (102, 10, 0, 660101, 2, NULL, 'https://i8.mifile.cn/a1/pms_1546508755.29828342.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `tb_panel_content` VALUES (103, 10, 0, 660102, 3, NULL, 'https://i8.mifile.cn/a1/pms_1502258923.87257653.jpg', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码 md5加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT 0,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT 0,
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `created` datetime(0) NOT NULL,
  `updated` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'Pushy', '123', '18866256556', '1437876073@qq.com', '', '北京东城区', 0, NULL, 0, 'https://pushy.site/images/avatar.gif', '2019-07-19 11:45:44', '2019-07-19 11:45:46');
INSERT INTO `tb_user` VALUES (2, 'tom', '123', '188662566', NULL, '', NULL, 0, NULL, 0, NULL, '2019-07-20 11:26:10', '2019-07-20 11:26:13');

SET FOREIGN_KEY_CHECKS = 1;
