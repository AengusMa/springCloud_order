# 文档

## 配置
### 数据库：  
- 创建docker容器命令：
    - docker pull mysql:5.6

    - docker run -p 3306:3306 --name order_mysql -v $PWD/docker/conf:/etc/mysql/conf.d -v $PWD/docker/logs:/logs -v $PWD/docker/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
    - docker run -p 3306:3306 --name order_mysql   -e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
- 创建数据库命令：
    - CREATE DATABASE `springcloud_order` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
- 数据库编码问题：
    - 显示数据库编码：
show variables like "%character%";
    - 修改编码：  
set character_set_client = utf8;
set character_set_server = utf8;
set character_set_connection = utf8;
set character_set_database = utf8;
set character_set_results = utf8;
set collation_connection = utf8_general_ci;
set collation_database = utf8_general_ci;
set collation_server = utf8_general_ci;
    - 修改数据库编码：  
alter database app_relation character set utf8;   
如果还有乱码：在建表的时候添加：default charset utf8 collate utf8_unicode_ci;

## 部署运行：
- 首先打包：
mvn clean package
- 然后在项目根目录下运行docker命令：  
docker run -p 8080:8080 -t order/product .
docker run -p 8761:8761 -t order/eureka .

## 问题
使用Feign的时候需要在启动类上添加@EnableFeignClients(basePackages = "com.mwl.product.client")，如果是跨项目，则basePackages是必须的否则会报错“not Autowired”