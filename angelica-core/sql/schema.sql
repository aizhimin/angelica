
-- 管理员表
DROP TABLE IF EXISTS `qingdanxia`.`admin_user`;
CREATE  TABLE IF NOT EXISTS `qingdanxia`.`admin_user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `login_name` CHAR(11) NOT NULL COMMENT '登录名(限手机号)' ,
  `password` VARCHAR(64) NULL ,
  `status` TINYINT NOT NULL COMMENT '状态：0-禁用，1-正常' ,
  `gmt_create` DATETIME NOT NULL COMMENT '创建时间' ,
  `gmt_modified` DATETIME NULL COMMENT '最后更新时间' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `uk_login_name` (`login_name`(9) ASC) )
ENGINE = InnoDB
COMMENT = '管理员表';

-- 管理员角色关系表
DROP TABLE IF EXISTS `qingdanxia`.`admin_user_role`;
CREATE  TABLE IF NOT EXISTS `qingdanxia`.`admin_user_role` (
  `user_id` BIGINT UNSIGNED NOT NULL ,
  `role_id` BIGINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`user_id`, `role_id`) ,
  INDEX `idx_user_role` (`user_id` ASC, `role_id` ASC) )
ENGINE = InnoDB
COMMENT = '管理员角色关系表';

-- 角色表
DROP TABLE IF EXISTS `qingdanxia`.`admin_role`;
CREATE  TABLE IF NOT EXISTS `qingdanxia`.`admin_role` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(15) NOT NULL COMMENT '角色名称' ,
  `role_desc` VARCHAR(45) NULL COMMENT '角色描述' ,
  `status` TINYINT NOT NULL COMMENT '状态：0-禁用，1-正常' ,
  `gmt_create` DATETIME NOT NULL ,
  `gmt_modified` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `uk_role_name` (`role_name`(4) ASC) )
ENGINE = InnoDB
COMMENT = '角色表';


-- 角色资源关系表
DROP TABLE IF EXISTS `qingdanxia`.`admin_role_resource`;
CREATE  TABLE IF NOT EXISTS `qingdanxia`.`admin_role_resource` (
  `role_id` BIGINT UNSIGNED NOT NULL ,
  `resource_id` BIGINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`role_id`, `resource_id`) ,
  INDEX `idx_role_resource` (`role_id` ASC, `resource_id` ASC) )
ENGINE = InnoDB
COMMENT = '角色资源关系表';

-- 资源表
DROP TABLE IF EXISTS `qingdanxia`.`admin_resource`;
CREATE  TABLE IF NOT EXISTS `qingdanxia`.`admin_resource` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `resource_name` VARCHAR(20) NOT NULL COMMENT '资源名称' ,
  `resource_type` TINYINT NOT NULL COMMENT '资源类型：0-模块,1-菜单,2-按钮' ,
  `parent_id` VARCHAR(24) NULL COMMENT '父级ID,一级菜单为null' ,
  `permission` VARCHAR(100) NULL COMMENT '权限字符串：(资源编码:操作编码)，如：user:list,user:create' ,
  `url` VARCHAR(100) NULL COMMENT '菜单URL' ,
  `seq` SMALLINT NULL COMMENT '同一级别下的展示顺序' ,
  `gmt_create` DATETIME NOT NULL ,
  `gmt_modified` DATETIME NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_parent_id` (`parent_id` ASC) ,
  INDEX `idx_resource_type` (`resource_type` ASC) )
ENGINE = InnoDB
COMMENT = '资源表';

-- 用户操作日志表
DROP TABLE IF EXISTS `qingdanxia`.`operation_log`;
CREATE  TABLE IF NOT EXISTS `qingdanxia`.`operation_log` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `app_name` VARCHAR(20) NOT NULL COMMENT '应用名称' ,
  `user_id` BIGINT NOT NULL COMMENT '登录名' ,
  `log_type` TINYINT NOT NULL COMMENT '日志类型：' ,
  `log_name` VARCHAR(100) NOT NULL COMMENT '日志名称' ,
  `classname` VARCHAR(200) NULL ,
  `method` VARCHAR(100) NULL COMMENT '请求方法' ,
  `params` VARCHAR(500) NULL COMMENT '请求参数' ,
  `succeed` VARCHAR(45) NULL COMMENT '是否成功' ,
  `ip` VARCHAR(45) NULL COMMENT 'IP地址' ,
  `msg` TEXT NULL COMMENT '备注信息' ,
  `gmt_create` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_user_id` (`user_id` ASC) ,
  INDEX `idx_log_type` (`log_type` ASC) ,
  INDEX `idx_app_name` (`app_name`(10) ASC) )
ENGINE = InnoDB
COMMENT = '用户操作日志表';